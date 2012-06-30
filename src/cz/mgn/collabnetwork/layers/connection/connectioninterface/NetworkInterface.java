/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabnetwork.layers.connection.connectioninterface;

/**
 *
 * @author indy
 *
 * interface for network control (connection, server...)
 */
public interface NetworkInterface {

    /**
     * sets connection port avalible only if its not connected now
     *
     * @param port new port
     */
    public void setPort(int port);

    /**
     * current connection port
     */
    public int getPort();

    /**
     * try to connect to server its possible only if its not connected now and
     * server isnt started
     *
     * @param address remote address of server
     * @throws when some server is already conneced or server running on this
     * side
     */
    public void connectToServer(String address);

    /**
     * if its connected to server, disconnect
     *
     * @return true if server was connected
     */
    public boolean disconnectFromServer();

    /**
     * returns if its connected to server
     */
    public boolean isConnectedToServer();

    /**
     * returns connected server address or null if its not connected
     *
     * @return server address
     */
    public String getConnectedServerAddress();

    /**
     * returns if server running on this side
     */
    public boolean isServerStarted();

    /**
     * if server running, stops it
     *
     * @return true if server was running
     */
    public boolean stopServer();

    /**
     * starts server
     *
     * @throws when server is already running or client is connected to other
     * server
     */
    public void startServer();
}
