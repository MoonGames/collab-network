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
package cz.mgn.collabnetwork.layers.crpp.data;

import java.util.ArrayList;

/**
 *
 * @author indy
 *
 * collab message
 */
public class Message {

    /**
     * command of this message, its number between 0 and 65535 (inclusive)
     */
    protected String messageCommand;
    /**
     * blocks of this message
     */
    protected ArrayList<Block> blocks;

    public Message(String messageCommand) {
        testNameAndThrow(messageCommand);
        this.messageCommand = messageCommand;
        this.blocks = new ArrayList<Block>();
    }

    public Message(String messageCommand, ArrayList<Block> blocks) {
        testNameAndThrow(messageCommand);
        this.messageCommand = messageCommand;
        this.blocks = blocks;
    }

    protected void testNameAndThrow(String name) {
        if (name.length() != 4) {
            throw new IllegalArgumentException("Message commant has to have exactly four characters.");
        }
    }

    /**
     * message command its number between 0 and 65535 (inclusive)
     *
     * @return message command as String
     */
    public String getMessageCommand() {
        return messageCommand;
    }

    /**
     * blocks of this message, its not matter on order
     *
     * @return blocks as array list
     */
    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    /**
     * block of collab message
     */
    public static class Block {

        protected String name;
        protected byte[] blockData = null;

        /**
         * initize block
         *
         * @param name block name represented by four ASCII characters
         * @param blockData array of bytes representing data
         */
        public Block(String name, byte[] blockData) {
            if (name.length() != 4) {
                throw new IllegalArgumentException("Message commant has to have exactly four characters.");
            }
            this.name = name;
            this.blockData = blockData;
        }

        public String getBlockName() {
            return name;
        }

        /**
         * length of block, including only length of data (not block name and
         * block length bytes) example: if you need whole block length you need
         * add to this number 8 (four is block name byte and four is block
         * length bytes)
         *
         * @return integer with block data length
         */
        public int blockLength() {
            return blockData.length;
        }

        /**
         * block data, its not including bytes with block type and block length
         *
         * @return block data represented as byte array
         */
        public byte[] getBlockData() {
            return blockData;
        }
    }
}
