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

import cz.mgn.collabnetwork.layers.collabprotocol.data.ChatMessage;
import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import cz.mgn.collabnetwork.layers.collabprotocol.data.RoomData;
import cz.mgn.collabnetwork.layers.crpp.MessageListener;
import cz.mgn.collabnetwork.layers.crpp.CRPP;
import cz.mgn.collabnetwork.layers.crpp.data.Message;
import cz.mgn.collabnetwork.layers.crpp.data.MessageUitls;
import cz.mgn.collabnetwork.utils.Utils;
import java.util.ArrayList;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 *
 * collab protocol layer
 */
public class CollabProtocol implements MessageListener {

    // paint
    public static final String COMMAND_PAINT = "PANT";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_TYPE = "UDTY";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_ID = "UDID";
    public static final String COMMAND_PAINT_BLOCK_LAYER_ID = "LYID";
    public static final String COMMAND_PAINT_BLOCK_CANVAS_ID = "CNID";
    public static final String COMMAND_PAINT_BLOCK_X_COORDINATE = "XCOR";
    public static final String COMMAND_PAINT_BLOCK_Y_COORDINATE = "YCOR";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA = "UIMG";
    // get connection info
    public static final String COMMAND_GET_CONNECTION_INFO = "GCIN";
    // autenticate client TODO
    public static final String COMMAND_AUTENTICATE_CLIENT = "AUTN";
    //
    public static final String COMMAND_GET_ROOMS_LIST = "GRLI";
    //
    public static final String COMMAND_CREATE_ROOM = "CROM";
    public static final String COMMAND_CREATE_ROOM_BLOCK_WIDTH = "WIDT";
    public static final String COMMAND_CREATE_ROOM_BLOCK_HEIGHT = "HEIG";
    public static final String COMMAND_CREATE_ROOM_BLOCK_NAME = "NAME";
    public static final String COMMAND_CREATE_ROOM_BLOCK_PASSWORD = "PSWD";
    //
    //
    public static final String COMMAND_O_JOIN_TO_ROOM = "OJRO";
    public static final String COMMAND_O_JOIN_TO_ROOM_BLOCK_PASSWORD = "PSWD";
    public static final String COMMAND_O_JOIN_TO_ROOM_BLOCK_ROOM_ID = "ROID";
    //
    public static final String COMMAND_DISCONNECT_FROM_ROOM = "ODRO";
    //
    public static final String COMMAND_ADD_LAYER = "ALAY";
    public static final String COMMAND_ADD_LAYER_BLOCK_NAME = "NAME";
    public static final String COMMAND_ADD_LAYER_BLOCK_LOCATION = "LOCA";
    //
    public static final String COMMAND_REMOVE_LAYER = "RLAY";
    public static final String COMMAND_REMOVE_LAYER_BLOCK_ID = "LAID";
    //
    public static final String COMMAND_SET_LAYER_LOCATION = "SLAL";
    public static final String COMMAND_SET_LAYER_LOCATION_BLOCK_LAYER_ID = "LAID";
    public static final String COMMAND_SET_LAYER_LOCATION_BLOCK_NEW_LOCATION = "NLOC";
    // TODO
    public static final String COMMAND_ADD_CANVAS = "ACAN";
    // TODO
    public static final String COMMAND_REMOVE_CANVAS = "RCAN";
    //
    public static final String COMMAND_MAKE_HTTP_IMAGE = "HTIM";
    //
    public static final String COMMAND_O_CHAT_MESSAGE = "OCHA";
    public static final String COMMAND_O_CHAT_MESSAGE_BLOCK_TEXT = "TEXT";

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
    public void sendPaint(PaintUpdate paintUpdate) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();
        // creating blocks
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_UPDATE_TYPE,
                paintUpdate.getUpdateType()));
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_UPDATE_ID,
                paintUpdate.getUpdateID()));
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_LAYER_ID,
                paintUpdate.getLayerID()));
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_CANVAS_ID,
                paintUpdate.getCanvasID()));
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_X_COORDINATE,
                paintUpdate.getXCoordinate()));
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_Y_COORDINATE,
                paintUpdate.getYCoordinate()));
        blocks.add(MessageUitls.createBlock(COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA,
                paintUpdate.getImageData()));

        send(COMMAND_PAINT, blocks);
    }

    public void sendGetConnectionInfo() {
        send(COMMAND_GET_CONNECTION_INFO);
    }

    public void sendGetRoomsList() {
        send(COMMAND_GET_ROOMS_LIST);
    }

    public void sendCreateRoom(RoomData room) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();
        // creating blocks
        blocks.add(MessageUitls.createBlock(COMMAND_CREATE_ROOM_BLOCK_WIDTH,
                room.getWidth()));
        blocks.add(MessageUitls.createBlock(COMMAND_CREATE_ROOM_BLOCK_HEIGHT,
                room.getHeight()));
        blocks.add(MessageUitls.createBlock(COMMAND_CREATE_ROOM_BLOCK_NAME,
                room.getName()));

        if (room.getPassword() != null) {
            blocks.add(MessageUitls.createBlock(COMMAND_CREATE_ROOM_BLOCK_PASSWORD,
                    Utils.makeSHA256Hash(room.getPassword())));
        }

        send(COMMAND_CREATE_ROOM, blocks);
    }

    public void sendOutgoingJoinToRoom(int roomID, String password) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(COMMAND_O_JOIN_TO_ROOM_BLOCK_ROOM_ID,
                roomID));
        if (password != null) {
            blocks.add(MessageUitls.createBlock(COMMAND_O_JOIN_TO_ROOM_BLOCK_PASSWORD,
                    Utils.makeSHA256Hash(password)));
        }

        send(COMMAND_O_JOIN_TO_ROOM, blocks);
    }

    public void sendDisconnectFromRoom() {
        send(COMMAND_DISCONNECT_FROM_ROOM);
    }

    public void sendAddLayer(String name, int location) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(COMMAND_ADD_LAYER_BLOCK_NAME, name));
        blocks.add(MessageUitls.createBlock(COMMAND_ADD_LAYER_BLOCK_LOCATION, location));

        send(COMMAND_ADD_LAYER, blocks);
    }

    public void sendRemoveLayer(int id) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(COMMAND_REMOVE_LAYER_BLOCK_ID, id));

        send(COMMAND_REMOVE_LAYER, blocks);
    }

    public void sendSetLayerLocation(int id, int newLocation) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(COMMAND_SET_LAYER_LOCATION_BLOCK_LAYER_ID, id));
        blocks.add(MessageUitls.createBlock(COMMAND_SET_LAYER_LOCATION_BLOCK_NEW_LOCATION, newLocation));

        send(COMMAND_SET_LAYER_LOCATION, blocks);
    }

    public void sendMakeHTTPImage() {
        send(COMMAND_MAKE_HTTP_IMAGE);
    }

    public void sendChatMessage(ChatMessage message) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(COMMAND_O_CHAT_MESSAGE_BLOCK_TEXT,
                message.getMessage()));

        send(COMMAND_O_CHAT_MESSAGE, blocks);
    }

    @Override
    public void messageReceived(Message message) {
        switch (message.getMessageCommand()) {
            case COMMAND_PAINT:
                processPaint(message);
                break;
        }
    }

    protected void processPaint(Message message) {
        if (commandsListener != null) {
            int updateType = MessageUitls.getBlockValue(message.getBlock(COMMAND_PAINT_BLOCK_UPDATE_TYPE));
            int updateID = MessageUitls.getBlockValue(message.getBlock(COMMAND_PAINT_BLOCK_UPDATE_ID));
            int layerID = MessageUitls.getBlockValue(message.getBlock(COMMAND_PAINT_BLOCK_LAYER_ID));
            int canvasID = MessageUitls.getBlockValue(message.getBlock(COMMAND_PAINT_BLOCK_CANVAS_ID));
            int xCoordinate = MessageUitls.getBlockValue(message.getBlock(COMMAND_PAINT_BLOCK_X_COORDINATE));
            int yCoordinate = MessageUitls.getBlockValue(message.getBlock(COMMAND_PAINT_BLOCK_Y_COORDINATE));
            byte[] imageData = message.getBlock(COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA).getBlockData();

            PaintUpdate paintUpdate = new PaintUpdate(updateType, updateID, layerID,
                    canvasID, xCoordinate, yCoordinate, imageData);

            commandsListener.paint(paintUpdate);
        }
    }

    protected void send(String command) {
        networkProtocol.sendMessage(new Message(command));
    }

    protected void send(String command, ArrayList<Message.Block> blocks) {
        networkProtocol.sendMessage(new Message(command, blocks));
    }

}
