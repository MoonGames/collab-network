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

    /**
     * converts integer to byte array
     *
     * @param value integer for conversion
     * @return byte array containing integer
     */
    public static final byte[] intToByteArray(int value) {
        return new byte[]{
                    (byte) (value >>> 24),
                    (byte) (value >>> 16),
                    (byte) (value >>> 8),
                    (byte) value};
    }

    /**
     * converts byte array to integer
     *
     * @param b array for conversion, contains four bytes
     * @return integer coded in array
     */
    public static final int byteArrayToInt(byte[] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }

    /**
     * converts byte array containing boolean to boolean
     */
    public static final boolean byteArrayToBoolean(byte[] b) {
        return (b == null || b.length == 0) ? false : b[0] != 0x00;
    }

    /**
     * converts boolean to byte array
     *
     * @param value boolean which be converted
     * @return value in bytes
     */
    public static final byte[] booleanToByteArray(boolean value) {
        return new byte[]{(byte) (value ? 0x01 : 0x00)};
    }
}
