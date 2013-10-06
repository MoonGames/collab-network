package cz.mgn.collabnetwork.layers.connection;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public interface Connection {
    
    public void setDataListener(DataListener dataListener);
    
    public void sendData(byte[] data);
}
