/*
 * Collab desktop - Software for shared drawing via internet in real-time
 * Copyright (C) 2012 Martin Indra <aktive@seznam.cz>
 *
 * This file is part of Collab desktop.
 *
 * Collab desktop is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Collab desktop is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Collab desktop.  If not, see <http://www.gnu.org/licenses/>.
 */
package cz.mgn.collabnetwork.layers.collabprotocol;

import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import cz.mgn.collabnetwork.layers.crpp.MessageListener;
import cz.mgn.collabnetwork.layers.crpp.CRPP;
import cz.mgn.collabnetwork.layers.crpp.data.Message;
import cz.mgn.collabnetwork.utils.BinaryUtil;
import java.util.ArrayList;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 *
 * collab protocol layer
 */
public class CollabProtocol implements MessageListener {

    public static final String COMMAND_PAINT = "PANT";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_TYPE = "UDTY";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_ID = "UDID";
    public static final String COMMAND_PAINT_BLOCK_LAYER_ID = "LYID";
    public static final String COMMAND_PAINT_BLOCK_CANVAS_ID = "CNID";
    public static final String COMMAND_PAINT_BLOCK_X_COORDINATE = "XCOR";
    public static final String COMMAND_PAINT_BLOCK_Y_COORDINATE = "YCOR";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA = "UIMG";
    /**
     * lower layer
     */
    protected CRPP networkProtocol;
    protected CommandsListener commandsListener;

    /**
     * initize collab protocol layer
     *
     * @param networkProtocol network protocol layer
     */
    public CollabProtocol(CRPP networkProtocol) {
        this.networkProtocol = networkProtocol;
        init();
    }

    private void init() {
        this.networkProtocol.setMessageListener(this);
    }

    public void setCommandsListener(CommandsListener commandsListener) {
        this.commandsListener = commandsListener;
    }

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
        networkProtocol.sendMessage(message);
    }

    @Override
    public void messageReceived(Message messate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
