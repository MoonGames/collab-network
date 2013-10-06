package cz.mgn.collabnetwork;

import cz.mgn.collabnetwork.layers.collabprotocol.CollabProtocol;
import cz.mgn.collabnetwork.layers.connection.crppbinary.CRPPBinary;
import cz.mgn.collabnetwork.layers.crpp.CRPP;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class NetworkFactory {
    
    public CollabProtocol createCRPPBinaryConnection(InputStream inputStream, OutputStream outputStream) {
        CRPPBinary connection = new CRPPBinary(inputStream, outputStream);
        CRPP crpp = new CRPP(connection);
        
        return new CollabProtocol(crpp);
    }
}
