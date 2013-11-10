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
package cz.mgn.collabnetwork.layers.collabprotocol;

import cz.mgn.collabnetwork.layers.collabprotocol.data.ChatMessage;
import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import cz.mgn.collabnetwork.layers.collabprotocol.data.RoomData;
import cz.mgn.collabnetwork.layers.crpp.CRPP;
import cz.mgn.collabnetwork.layers.crpp.data.Message;
import cz.mgn.collabnetwork.layers.crpp.data.MessageUitls;
import cz.mgn.collabnetwork.utils.Utils;
import java.util.ArrayList;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class CommandsSender {

    protected CRPP networkProtocol;

    public CommandsSender(CRPP networkProtocol) {
        this.networkProtocol = networkProtocol;
    }

    /**
     * send to server paint update
     *
     * @param paintUpdate data of this update
     */
    public void sendPaint(PaintUpdate paintUpdate) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();
        // creating blocks
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_TYPE,
                paintUpdate.getUpdateType()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_ID,
                paintUpdate.getUpdateID()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_LAYER_ID,
                paintUpdate.getLayerID()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_CANVAS_ID,
                paintUpdate.getCanvasID()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_X_COORDINATE,
                paintUpdate.getXCoordinate()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_Y_COORDINATE,
                paintUpdate.getYCoordinate()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA,
                paintUpdate.getImageData()));

        send(ProtocolConstants.COMMAND_PAINT, blocks);
    }

    public void sendGetConnectionInfo() {
        send(ProtocolConstants.COMMAND_GET_CONNECTION_INFO);
    }

    public void sendGetRoomsList() {
        send(ProtocolConstants.COMMAND_GET_ROOMS_LIST);
    }

    public void sendCreateRoom(RoomData room) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();
        // creating blocks
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_CREATE_ROOM_BLOCK_WIDTH,
                room.getWidth()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_CREATE_ROOM_BLOCK_HEIGHT,
                room.getHeight()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_CREATE_ROOM_BLOCK_NAME,
                room.getName()));

        if (room.getPassword() != null) {
            blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_CREATE_ROOM_BLOCK_PASSWORD,
                    Utils.makeSHA256HashString(room.getPassword())));
        }

        send(ProtocolConstants.COMMAND_CREATE_ROOM, blocks);
    }

    public void sendOutgoingJoinToRoom(int roomID, String password) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_O_JOIN_TO_ROOM_BLOCK_ROOM_ID,
                roomID));
        if (password != null) {
            blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_O_JOIN_TO_ROOM_BLOCK_PASSWORD,
                    Utils.makeSHA256HashString(password)));
        }

        send(ProtocolConstants.COMMAND_O_JOIN_TO_ROOM, blocks);
    }

    public void sendDisconnectFromRoom() {
        send(ProtocolConstants.COMMAND_DISCONNECT_FROM_ROOM);
    }

    public void sendAuthenticateClient(String password) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_AUTENTICATE_CLIENT_BLOCK_PASSWORD, password, true));

        send(ProtocolConstants.COMMAND_AUTENTICATE_CLIENT, blocks);
    }

    public void sendAddLayer(String name, int location) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_ADD_LAYER_BLOCK_NAME, name));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_ADD_LAYER_BLOCK_LOCATION, location));

        send(ProtocolConstants.COMMAND_ADD_LAYER, blocks);
    }

    public void sendRemoveLayer(int id) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_REMOVE_LAYER_BLOCK_ID, id));

        send(ProtocolConstants.COMMAND_REMOVE_LAYER, blocks);
    }

    public void sendSetLayerLocation(int id, int newLocation) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_SET_LAYER_LOCATION_BLOCK_LAYER_ID, id));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_SET_LAYER_LOCATION_BLOCK_NEW_LOCATION, newLocation));

        send(ProtocolConstants.COMMAND_SET_LAYER_LOCATION, blocks);
    }

    public void sendMakeHTTPImage() {
        send(ProtocolConstants.COMMAND_MAKE_HTTP_IMAGE);
    }

    public void sendToServerChatMessage(ChatMessage message) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_O_CHAT_MESSAGE_BLOCK_TEXT,
                message.getMessage()));

        send(ProtocolConstants.COMMAND_O_CHAT_MESSAGE, blocks);
    }

    public void sendFromServerChatMessage(ChatMessage message) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_I_CHAT_MESSAGE_BLOCK_TEXT,
                message.getMessage()));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_I_CHAT_MESSAGE_BLOCK_AUTHOR,
                message.getAuthor()));

        send(ProtocolConstants.COMMAND_I_CHAT_MESSAGE, blocks);
    }

    public void sendSetNick(String nick) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_SET_NICK_BLOCK_NICK, nick));

        send(ProtocolConstants.COMMAND_SET_NICK, blocks);
    }

    public void sendSetLayerName(int layerID, String name) {
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();

        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_SET_LAYER_NAME_BLOCK_LAYER_ID, layerID));
        blocks.add(MessageUitls.createBlock(ProtocolConstants.COMMAND_SET_LAYER_NAME_BLOCK_LAYER_NAME, name));

        send(ProtocolConstants.COMMAND_SET_LAYER_NAME, blocks);
    }

    protected void send(String command) {
        networkProtocol.sendMessage(new Message(command));
    }

    protected void send(String command, ArrayList<Message.Block> blocks) {
        networkProtocol.sendMessage(new Message(command, blocks));
    }

}
