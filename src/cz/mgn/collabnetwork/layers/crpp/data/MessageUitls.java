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

import cz.mgn.collabnetwork.utils.BinaryUtil;
import cz.mgn.collabnetwork.utils.Utils;

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

    /**
     * Creates message block from specified data.
     *
     * @param name block name
     * @param value text value
     * @param hash if true value will be send as SHA-256 has of UTF-8 coded
     * string, otherwise as plain UTF-8 coded string
     * @return message block
     */
    public static Message.Block createBlock(String name, String value, boolean hash) {
        Message.Block block = null;
        if (hash) {
            block = new Message.Block(name, Utils.makeSHA256Hash(value));
        } else {
            return createBlock(name, value);
        }
        return block;
    }

    public static int getBlockValue(Message.Block block) {
        return BinaryUtil.byteArrayToInt(block.getBlockData());
    }
}
