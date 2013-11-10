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
package cz.mgn.collabnetwork.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class Utils {

    /**
     * Creates SHA-256 has of a string. The hash is counted from UTF-8 binary
     * representation of the source string.
     *
     * @param origin string to be hashed
     * @return bytes of hashed string.
     */
    public static byte[] makeSHA256Hash(String origin) {
        byte[] result = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            result = md.digest(origin.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    /**
     * Creates SHA-256 hash of the string and returns it as hexadecimal string
     * representation. The hash is counted from UTF-8 binary representation of
     * the source string.
     *
     * @param origin string to be hashed
     * @return hash of the string
     */
    public static String makeSHA256HashString(String origin) {
        byte[] mdbytes = makeSHA256Hash(origin);

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
}
