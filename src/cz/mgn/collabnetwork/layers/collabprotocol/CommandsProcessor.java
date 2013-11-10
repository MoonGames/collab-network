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

import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;
import cz.mgn.collabnetwork.layers.crpp.MessageListener;
import cz.mgn.collabnetwork.layers.crpp.data.Message;
import cz.mgn.collabnetwork.layers.crpp.data.MessageUitls;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class CommandsProcessor implements MessageListener {

    protected CommandsListener commandsListener;

    public void setCommandsListener(CommandsListener commandsListener) {
        this.commandsListener = commandsListener;
    }

    @Override
    public void messageReceived(Message message) {
        switch (message.getMessageCommand()) {
            case ProtocolConstants.COMMAND_PAINT:
                processPaint(message);
                break;
        }
    }

    protected void processPaint(Message message) {
        if (commandsListener != null) {
            int updateType = MessageUitls.getBlockValue(message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_TYPE));
            int updateID = MessageUitls.getBlockValue(message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_ID));
            int layerID = MessageUitls.getBlockValue(message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_LAYER_ID));
            int canvasID = MessageUitls.getBlockValue(message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_CANVAS_ID));
            int xCoordinate = MessageUitls.getBlockValue(message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_X_COORDINATE));
            int yCoordinate = MessageUitls.getBlockValue(message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_Y_COORDINATE));
            byte[] imageData = message.getBlock(ProtocolConstants.COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA).getBlockData();

            PaintUpdate paintUpdate = new PaintUpdate(updateType, updateID, layerID,
                    canvasID, xCoordinate, yCoordinate, imageData);

            commandsListener.paint(paintUpdate);
        }
    }
}
