/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.mgn.collabnetwork.layers.crpp.data;

import cz.mgn.collabnetwork.utils.BinaryUtil;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class MessageUitls {
    
    public static Message.Block createBlock(String name, byte[] value) {
        return new Message.Block(name, value);
    }
    
    public static Message.Block createBlock(String name, int value) {
        return new Message.Block(name, BinaryUtil.intToByteArray(value));
    }
    
    public static Message.Block createBlock(String name, String value) {
        return new Message.Block(name, BinaryUtil.utf8StringToByteArray(value));
    }
    
    public static int getBlockValue(Message.Block block) {
        return BinaryUtil.byteArrayToInt(block.getBlockData());
    }
}
