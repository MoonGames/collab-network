/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabnetwork.layers.collabprotocol;

import cz.mgn.collabnetwork.layers.collabprotocol.data.Message;
import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import cz.mgn.collabnetwork.utils.BinaryUtil;
import java.util.ArrayList;

/**
 *
 * @author indy
 */
public class CollabProtocol {

    public static final int COMMAND_PAINT = 1;
    public static final int COMMAND_PAINT_BLOCK_UPDATE_TYPE = 1;
    public static final int COMMAND_PAINT_BLOCK_UPDATE_ID = 2;
    public static final int COMMAND_PAINT_BLOCK_LAYER_ID = 3;
    public static final int COMMAND_PAINT_BLOCK_CANVAS_ID = 4;
    public static final int COMMAND_PAINT_BLOCK_X_COORDINATE = 5;
    public static final int COMMAND_PAINT_BLOCK_Y_COORDINATE = 6;
    public static final int COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA = 7;

    /**
     * send to server paint update
     *
     * @param paintUpdate data of this update
     */
    public void paint(PaintUpdate paintUpdate) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();
        // creating blocks
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_UPDATE_TYPE,
                BinaryUtil.convertIntToUsignedBytes(paintUpdate.getUpdateType())));
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_UPDATE_ID,
                BinaryUtil.convertIntToUsignedBytes(paintUpdate.getUpdateID())));
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_LAYER_ID,
                BinaryUtil.convertIntToUsignedBytes(paintUpdate.getLayerID())));
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_CANVAS_ID,
                BinaryUtil.convertIntToUsignedBytes(paintUpdate.getCanvasID())));
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_X_COORDINATE,
                BinaryUtil.convertIntToUsignedBytes(paintUpdate.getXCoordinate())));
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_Y_COORDINATE,
                BinaryUtil.convertIntToUsignedBytes(paintUpdate.getYCoordinate())));
        blocks.add(new Message.Block(COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA,
                paintUpdate.getImageData()));
        // creating message
        Message message = new Message(COMMAND_PAINT, blocks);
        //TODO: send to lower layer
    }
}
