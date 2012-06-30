/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabnetwork.layers.networkprotocol;

import cz.mgn.collabnetwork.layers.networkprotocol.data.Message;

/**
 *
 * @author indy
 * 
 * class representing network protocol layer
 */
public abstract class NetworkProtocol {
    
    /**
     * sends message
     * 
     * @param message message data
     */
    public abstract void sendMessage(Message message);
}
