package cz.mgn.collabnetwork.layers.connection;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public interface DataListener {
    
    public void dataReceived(byte[] bytes);
}
