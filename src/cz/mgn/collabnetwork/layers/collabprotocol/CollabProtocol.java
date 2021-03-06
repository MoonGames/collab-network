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
package cz.mgn.collabnetwork.layers.collabprotocol;

import cz.mgn.collabnetwork.layers.crpp.CRPP;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 *
 * Collab protocol layer
 */
public class CollabProtocol  {

    /**
     * lower layer
     */
    protected CRPP networkProtocol;
    protected CommandsSender commandsSender;
    protected CommandsProcessor commandsProcessor;

    /**
     * initialize Collab protocol layer
     *
     * @param networkProtocol network protocol layer
     */
    public CollabProtocol(CRPP networkProtocol) {
        this.networkProtocol = networkProtocol;
        init();
    }

    private void init() {
        this.commandsSender = new CommandsSender(networkProtocol);
        this.commandsProcessor = new CommandsProcessor();
        this.networkProtocol.setMessageListener(this.commandsProcessor);
    }

    public CommandsSender getCommandsSender() {
        return commandsSender;
    }
}
