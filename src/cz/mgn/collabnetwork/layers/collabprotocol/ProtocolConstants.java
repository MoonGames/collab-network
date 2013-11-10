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
package cz.mgn.collabnetwork.layers.collabprotocol;

/**
 * List of all constants used in CRPP such as commands names.
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class ProtocolConstants {

    //
    public static final String COMMAND_REMOVE_LAYER = "RLAY";
    public static final String COMMAND_CREATE_ROOM_BLOCK_WIDTH = "WIDT";
    //
    public static final String COMMAND_MAKE_HTTP_IMAGE = "HTIM";
    public static final String COMMAND_SET_LAYER_LOCATION_BLOCK_NEW_LOCATION = "NLOC";
    // get connection info
    public static final String COMMAND_GET_CONNECTION_INFO = "GCIN";
    public static final String COMMAND_ADD_LAYER_BLOCK_NAME = "NAME";
    public static final String COMMAND_CREATE_ROOM_BLOCK_PASSWORD = "PSWD";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_IMAGE_DATA = "UIMG";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_ID = "UDID";
    public static final String COMMAND_CREATE_ROOM_BLOCK_HEIGHT = "HEIG";
    //
    public static final String COMMAND_O_CHAT_MESSAGE = "OCHA";
    public static final String COMMAND_O_CHAT_MESSAGE_BLOCK_TEXT = "TEXT";
    //
    public static final String COMMAND_GET_ROOMS_LIST = "GRLI";
    public static final String COMMAND_SET_LAYER_NAME_BLOCK_LAYER_NAME = "LANA";
    public static final String COMMAND_O_JOIN_TO_ROOM_BLOCK_PASSWORD = "PSWD";
    //
    //
    public static final String COMMAND_O_JOIN_TO_ROOM = "OJRO";
    public static final String COMMAND_PAINT_BLOCK_Y_COORDINATE = "YCOR";
    public static final String COMMAND_PAINT_BLOCK_UPDATE_TYPE = "UDTY";
    public static final String COMMAND_SET_LAYER_NAME_BLOCK_LAYER_ID = "LAID";
    // paint
    public static final String COMMAND_PAINT = "PANT";
    public static final String COMMAND_PAINT_BLOCK_LAYER_ID = "LYID";
    public static final String COMMAND_AUTENTICATE_CLIENT_BLOCK_PASSWORD = "PSWD";
    public static final String COMMAND_CREATE_ROOM_BLOCK_NAME = "NAME";
    public static final String COMMAND_PAINT_BLOCK_X_COORDINATE = "XCOR";
    public static final String COMMAND_PAINT_BLOCK_CANVAS_ID = "CNID";
    // autenticate client
    public static final String COMMAND_AUTENTICATE_CLIENT = "AUTN";
    //
    public static final String COMMAND_SET_LAYER_NAME = "SLAN";
    public static final String COMMAND_O_JOIN_TO_ROOM_BLOCK_ROOM_ID = "ROID";
    // TODO
    public static final String COMMAND_REMOVE_CANVAS = "RCAN";
    //
    public static final String COMMAND_ADD_LAYER = "ALAY";
    public static final String COMMAND_SET_LAYER_LOCATION_BLOCK_LAYER_ID = "LAID";
    // TODO
    public static final String COMMAND_ADD_CANVAS = "ACAN";
    //
    public static final String COMMAND_SET_LAYER_LOCATION = "SLAL";
    public static final String COMMAND_ADD_LAYER_BLOCK_LOCATION = "LOCA";
    //
    public static final String COMMAND_DISCONNECT_FROM_ROOM = "ODRO";
    //
    public static final String COMMAND_CREATE_ROOM = "CROM";
    public static final String COMMAND_REMOVE_LAYER_BLOCK_ID = "LAID";
    //
    public static final String COMMAND_SET_NICK = "SNIC";
    public static final String COMMAND_SET_NICK_BLOCK_NICK = "NICK";
    // TODO
    public static final String COMMAND_SERVER_INFO = "SINF";
    // TODO
    public static final String COMMAND_CONNECTION_INFO = "CINF";
    // TODO
    public static final String COMMAND_CLIENT_CONNECTION_SUCCESS = "SSUC";
    // TODO
    public static final String COMMAND_CLIENT_CONNECTION_PROBLEM = "SERR";
    // TODO
    public static final String COMMAND_ROOMS_LIST = "RLIS";
    // TODO
    public static final String COMMAND_I_JOIN_ROOM = "IJRO";
    // TODO
    public static final String COMMAND_I_DISCONNECT_FROM_ROOM = "IDRO";
    // TODO
    public static final String COMMAND_USERS_LIST = "ULIS";
    // TODO
    public static final String COMMAND_LAYERS_ORDER = "LORD";
    // TODO
    public static final String COMMAND_SET_RESOLUTION = "SRES";
    //
    public static final String COMMAND_I_CHAT_MESSAGE = "ICHA";
    public static final String COMMAND_I_CHAT_MESSAGE_BLOCK_TEXT = "TEXT";
    public static final String COMMAND_I_CHAT_MESSAGE_BLOCK_AUTHOR = "AUTR";
}
