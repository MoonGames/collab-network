/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.mgn.collabnetwork.layers.context;

import cz.mgn.collabnetwork.layers.collabprotocol.CollabProtocol;

/**
 *
 * @author indy
 *
 * context layer
 */
public abstract class Context {

    /**
     * down layer
     */
    protected CollabProtocol collabProtocol;

    /**
     * initize context layer
     *
     * @param collabProtocol collab protocol layer
     */
    public Context(CollabProtocol collabProtocol) {
        this.collabProtocol = collabProtocol;
    }
}
