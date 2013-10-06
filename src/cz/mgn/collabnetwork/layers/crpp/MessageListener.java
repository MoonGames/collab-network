/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.mgn.collabnetwork.layers.crpp;

import cz.mgn.collabnetwork.layers.crpp.data.Message;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public interface MessageListener {
    
    public void messageReceived(Message message);
}
