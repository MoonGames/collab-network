package cz.mgn.collabnetwork;

import cz.mgn.collabnetwork.layers.collabprotocol.CollabProtocol;
import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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

        // set of ouput commands
        testPaintOut(outputStream, collabProtocol);

        //TODO: set of input commands
    }

    protected void testPaintOut(ByteArrayOutputStream outputStream, CollabProtocol collabProtocol) {
        System.out.println("testing paint command");

        byte[] fakeImageData = new byte[]{0, 1, 2, 3, 5};

        PaintUpdate update = new PaintUpdate(PaintUpdate.UPDATE_TYPE_ADD, 5, 6, 7, 20, 20, fakeImageData);
        collabProtocol.sendPaint(update);

        byte[] receivedData = outputStream.toByteArray();
        /*
         Expected result structure (parameters can be received in diffrend order):
         {4 ID} {4 data length} {4 PANT} {12 UDTY} {12 UDID} {12 LYID} {12 CNID} {12 XCOR} {12 YCOR} {x UIMG}

         These data are expected:
        
         command:
         80:65:78:84 = PANT

         update type:
         85:68:84:89 = UDTY
         0:0:0:4 = 4 (integer)
         0:0:0:0 = 0

         Y coordinate:
         85:68:73:68 = UDID
         0:0:0:4 = 4 (integer)
         0:0:0:5 = 5

         76:89:73:68 = LYID
         0:0:0:4
         0:0:0:6

         67:78:73:68 = CNID
         0:0:0:4
         0:0:0:7

         88:67:79:82 = XCOR
         0:0:0:4
         0:0:0:20

         89:67:79:82 = YCOR
         0:0:0:4
         0:0:0:20

         85:73:77:71 = UIMG
         0:0:0:5
         0:1:2:3:5 = fakeImageData
         */
        
        System.out.print("Received data are: ");
        for (byte b : receivedData) {
            System.out.print(":" + b);
        }
        System.out.println(":");

        findAndTest("paint command", receivedData, new byte[] {80, 65, 78, 84}, new byte[0]);
        findAndTest("x coordinate", receivedData, new byte[] {88, 67, 79, 82}, new byte[] {0, 0, 0, 4, 0, 0, 0, 20});
        findAndTest("image data", receivedData, new byte[] {85, 73, 77, 71}, new byte[] {0, 0, 0, 5, 0, 1, 2, 3, 5});
    }

    protected void findAndTest(String blockName, byte[] data, byte[] prefix, byte[] expected) {        
        int index = subarrayIndex(data, prefix);

        assert index != -1 : "Data doesn't contaion block \"" + blockName + "\"!";

        int from = index + prefix.length;
        int to = from + expected.length;
        for (int i = from; i < to; i++) {
            assert (i < data.length && data[i] == expected[i - from]) : "Data of block \"" + blockName + "\" doesn't fit!";
        }
    }

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
