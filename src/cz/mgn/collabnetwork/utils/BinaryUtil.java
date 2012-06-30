/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabnetwork.utils;

/**
 *
 * @author indy
 */
public class BinaryUtil {

    /**
     * converts positive or zero integer to bytes array
     *
     * @param integer integer to conversion
     * @return array of bytes after conversion
     *
     * @see "collab protocol documentation"
     */
    public static byte[] convertIntToUsignedBytes(int integer) {
        if (integer < 0) {
            throw new IllegalArgumentException("Integer must be positive or zero!");
        }
        return new byte[]{(byte) ((0xff000000 & integer) >>> 24),
                    (byte) ((0x00ff0000 & integer) >>> 16),
                    (byte) ((0x0000ff00 & integer) >> 8),
                    (byte) (0x000000ff & integer)};
    }
}
