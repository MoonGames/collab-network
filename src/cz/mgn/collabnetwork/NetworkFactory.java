/*
 * Copyright (C) 2013 Martin Indra <aktive at seznam.cz>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
    
    public static CollabProtocol createCRPPBinaryConnection(InputStream inputStream, OutputStream outputStream) {
        CRPPBinary connection = new CRPPBinary(inputStream, outputStream);
        CRPP crpp = new CRPP(connection);
        
        return new CollabProtocol(crpp);
    }
}
