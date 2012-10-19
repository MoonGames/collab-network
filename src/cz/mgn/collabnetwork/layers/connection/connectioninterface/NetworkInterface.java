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
