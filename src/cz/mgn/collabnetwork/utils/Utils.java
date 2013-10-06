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

    public static String makeSHA256Hash(String origin) {
        String result = origin;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] mdbytes = md.digest(origin.getBytes("UTF-8"));

            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            result = sb.toString();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return origin;
    }
}
