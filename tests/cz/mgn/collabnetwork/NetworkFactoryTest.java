/*
 * Copyright (C) 2013 Martin Indra <aktive at seznam.cz>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.mgn.collabnetwork;

import cz.mgn.collabnetwork.layers.collabprotocol.CollabProtocol;
import cz.mgn.collabnetwork.layers.collabprotocol.CommandsSender;
import cz.mgn.collabnetwork.layers.collabprotocol.ProtocolConstants;
import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import cz.mgn.collabnetwork.utils.BinaryUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class NetworkFactoryTest {

    public NetworkFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("NetworkFactory:");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createCRPPBinaryConnection method, of class NetworkFactory.
     */
    @Test
    public void testCreateCRPPBinaryConnection() {
        System.out.println("createCRPPBinaryConnection");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new byte[0]);

        CollabProtocol collabProtocol = NetworkFactory.createCRPPBinaryConnection(inputStream, outputStream);

        testOutgoingCommands(outputStream, collabProtocol.getCommandsSender());

        //TODO: set of input commands

    }

    /**
     * Processes test for out commands.
     *
     * @param outputStream the output stream of CollabProtocol instance.
     * @param sender CollabProtocol commands sender
     */
    protected void testOutgoingCommands(ByteArrayOutputStream outputStream, CommandsSender sender) {
        testPaintOut(outputStream, sender);
        outputStream.reset();
        //TODO: all other commands
    }

    protected void testPaintOut(ByteArrayOutputStream outputStream, CommandsSender sender) {
        System.out.println("testing paint command");

        byte[] fakeImageData = new byte[]{0, 1, 2, 3, 5};
        int updateType = 0;
        int updateID = 5;
        int layerID = 6;
        int canvasID = 7;
        int xCoordinate = 320;
        int yCoordinate = 480;

        PaintUpdate update = new PaintUpdate(PaintUpdate.UPDATE_TYPE_ADD, updateID, layerID, canvasID, xCoordinate, yCoordinate, fakeImageData);
        sender.sendPaint(update);

        byte[] receivedData = outputStream.toByteArray();
        /*
         Expected result structure (parameters can be received in diffrend order):
         {4 ID} {4 data length} {4 PANT} {12 UDTY} {12 UDID} {12 LYID} {12 CNID} {12 XCOR} {12 YCOR} {x UIMG}
         */

        // test for command
        findAndTestBlock("paint command", receivedData, new byte[]{80, 65, 78, 84}, new byte[0]);

        // tests for blocks
        findAndTestBlock("image data", ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA, receivedData, fakeImageData);
        findAndTestBlock("X coordinate", ProtocolConstants.COMMAND_PAINT_BLOCK_X_COORDINATE, receivedData, BinaryUtil.intToByteArray(xCoordinate));
        findAndTestBlock("Y coordinate", ProtocolConstants.COMMAND_PAINT_BLOCK_Y_COORDINATE, receivedData, BinaryUtil.intToByteArray(yCoordinate));
        findAndTestBlock("update type", ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_TYPE, receivedData, BinaryUtil.intToByteArray(updateType));
        findAndTestBlock("update ID", ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_ID, receivedData, BinaryUtil.intToByteArray(updateID));
        findAndTestBlock("layer ID", ProtocolConstants.COMMAND_PAINT_BLOCK_LAYER_ID, receivedData, BinaryUtil.intToByteArray(layerID));
        findAndTestBlock("canvas ID", ProtocolConstants.COMMAND_PAINT_BLOCK_CANVAS_ID, receivedData, BinaryUtil.intToByteArray(canvasID));
    }

    /**
     * Tests data if it contains particular block with specified value.
     *
     * @param blockName human readable name of the block. It's used for logging.
     * @param prefix block name prefix (used in the protocol).
     * @param data data which to search in.
     * @param value expected value of the block.
     */
    protected void findAndTestBlock(String blockName, String prefix, byte[] data, byte[] value) {
        byte[] expectedData = new byte[value.length + 4];
        byte[] prefixBinary = BinaryUtil.asciiStringToByteArray(prefix);

        System.arraycopy(BinaryUtil.intToByteArray(value.length), 0, expectedData, 0, 4);
        System.arraycopy(value, 0, expectedData, 4, value.length);

        findAndTestBlock(blockName, data, prefixBinary, expectedData);
    }

    /**
     * Tests data if it contains particular block with specified data.
     *
     * @param blockName human readable name of the block. It's used for logging.
     * @param data source data. This data will be tested for expected content.
     * @param prefix prefix of the block. Data of block continues after prefix.
     * @param expected expected block value.
     */
    protected void findAndTestBlock(String blockName, byte[] data, byte[] prefix, byte[] expected) {
        int index = subarrayIndex(data, prefix);

        assert index != -1 : "Data doesn't contaion block \"" + blockName + "\"!";

        int from = index + prefix.length;
        int to = from + expected.length;

        for (int i = from; i < to; i++) {
            assert (i < data.length && data[i] == expected[i - from]) : "Data of block \"" + blockName + "\" doesn't fit!";
        }
    }

    /**
     * Finds index of array inside of another array.
     *
     * @param source array which to search in
     * @param target array whit to search for
     * @return returns index in source array if target array has been founded or
     * -1 if not
     */
    protected int subarrayIndex(byte[] source, byte[] target) {
        int index = -1;

        for (int i = 0; i < (source.length - target.length + 1) && index == -1; i++) {
            boolean fit = true;

            for (int j = i; j < (i + target.length); j++) {
                if (source[j] != target[j - i]) {
                    fit = false;
                }
            }

            if (fit) {
                index = i;
            }
        }

        return index;
    }
}
