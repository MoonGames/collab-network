/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabnetwork.layers.networkprotocol.data;

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
    protected int messageCommand;
    /**
     * blocks of this message
     */
    protected ArrayList<Block> blocks;

    public Message(int messageCommand, ArrayList<Block> blocks) {
        if (messageCommand < 0 || messageCommand >= 256) {
            throw new IllegalArgumentException("Message command must be integer in interval <0, 65535>");
        }
        this.messageCommand = messageCommand;
        this.blocks = blocks;
    }

    /**
     * message command its number between 0 and 65535 (inclusive)
     *
     * @return message command as integer
     */
    public int getMessageCommand() {
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

        protected int blockTypeID = 0;
        protected byte[] blockData = null;

        /**
         * initize block
         *
         * @param blockTypeID block type identification, number between 0 and
         * 255 (inclusive)
         * @param blockData array of bytes representing data
         */
        public Block(int blockTypeID, byte[] blockData) {
            if (blockTypeID < 0 || blockTypeID >= 256) {
                throw new IllegalArgumentException("Block type ID must be in interfal <0, 255>");
            }
            this.blockTypeID = blockTypeID;
            this.blockData = blockData;
        }

        /**
         * block type
         *
         * @return id as integer between 0 and 255 (inclusive)
         */
        public int getBlockType() {
            return blockTypeID;
        }

        /**
         * length of block, including only length of data (not block type and
         * block length bytes) example: if you need all block length you need
         * add to this number 5 (one is block type byte and four is block length
         * bytes)
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
