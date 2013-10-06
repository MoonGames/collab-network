/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.mgn.collabnetwork.layers.collabprotocol;

import cz.mgn.collabnetwork.layers.collabprotocol.data.PaintUpdate;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public interface CommandsListener {
    
    public void paint(PaintUpdate paintUpdate);
}
