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
package cz.mgn.collabnetwork.layers.crpp;

import cz.mgn.collabnetwork.layers.connection.Connection;
import cz.mgn.collabnetwork.layers.connection.DataListener;
import cz.mgn.collabnetwork.layers.crpp.data.Message;
import cz.mgn.collabnetwork.utils.BinaryUtil;
import java.util.ArrayList;

/**
 *
 * @author indy
 *
 * class representing network protocol layer
 */
public class CRPP implements DataListener {

    protected Connection connection;
    protected MessageListener messageListener;

    public CRPP(Connection connection) {
        this.connection = connection;
        init();
    }

    private void init() {
        this.connection.setDataListener(this);
    }

    public void setMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    /**
     * sends message
     *
     * @param message message data
     */
    public void sendMessage(Message message) {
        ArrayList<Message.Block> blocks = message.getBlocks();
        ArrayList<byte[]> blocksData = new ArrayList<byte[]>();
        int blocksDataLength = 0;
        byte[] data;

        for (Message.Block block : blocks) {
            data = blockToBinary(block);
            blocksDataLength += data.length;
            blocksData.add(data);
        }

        data = new byte[blocksDataLength + 4];
        System.arraycopy(
                BinaryUtil.asciiStringToByteArray(message.getMessageCommand()),
                0, data, 0, 4);

        int dstPos = 4;
        for (byte[] block : blocksData) {
            System.arraycopy(block, 0, data, dstPos, block.length);
            dstPos += block.length;
        }

        connection.sendData(data);
    }
    
    protected byte[] blockToBinary(Message.Block block) {
        byte[] data;
        byte[] bytes = new byte[block.blockLength() + 8];

        data = BinaryUtil.asciiStringToByteArray(block.getBlockName());
        System.arraycopy(data, 0, bytes, 0, 4);
        data = BinaryUtil.intToByteArray(block.blockLength());
        System.arraycopy(data, 0, bytes, 4, 4);
        data = block.getBlockData();
        System.arraycopy(data, 0, bytes, 8, data.length);

        return bytes;
    }

    @Override
    public void dataReceived(byte[] data) {
        byte[] commandBytes = new byte[4];
        System.arraycopy(data, 0, commandBytes, 0, 4);
        String command = BinaryUtil.byteArrayToAsciiString(commandBytes);

        int offset = 4;
        ArrayList<Message.Block> blocks = new ArrayList<Message.Block>();
        while (offset < data.length) {
            Object[] res = readNextMessage(data, offset);
            offset += (int) res[0];
            blocks.add((Message.Block) res[1]);
        }

        messageListener.messageReceived(new Message(command, blocks));
    }

    /**
     *
     * @param data
     * @param offset
     *
     * @return array with two items. First is new offset and second is loaded
     * block.
     */
    protected Object[] readNextMessage(byte[] data, int offset) {
        byte[] parsing = new byte[4];
        System.arraycopy(data, offset, parsing, 0, 4);
        offset += 4;
        String name = BinaryUtil.byteArrayToAsciiString(parsing);

        parsing = new byte[4];
        System.arraycopy(data, offset, parsing, 0, 4);
        offset += 4;
        int length = BinaryUtil.byteArrayToInt(parsing);
        
        parsing = new byte[length];
        System.arraycopy(data, offset, parsing, 0, length);
        offset += length;
        
        Object[] result = new Object[2];
        result[0] = offset;
        result[1] = new Message.Block(name, parsing);
        return result;
    }
}
