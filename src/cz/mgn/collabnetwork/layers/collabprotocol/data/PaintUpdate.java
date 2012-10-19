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
package cz.mgn.collabnetwork.layers.collabprotocol.data;

/**
 *
 * @author indy
 * 
 * paint update data
 */
public class PaintUpdate {

    /**
     * this update type paint update on (over) layer
     */
    public static final int UPDATE_TYPE_ADD = 0;
    /**
     * this update type multiply each pixel alpha value on layer by value of
     * alpha in pixel in update image
     *
     * Ad1 = Ad * Au
     *
     * Ad1 - alpha of pixel in layer after update Ad - alpha of pixel in layer
     * before update Au - alpha of pixel in update image
     */
    public static final int UPDATE_TYPE_REMOVE = 1;
    /**
     * id of this paint update
     */
    protected int updateID;
    /**
     * id of layer which this update updating
     */
    protected int layerID;
    /**
     * id of updating canvas
     */
    protected int canvasID;
    /**
     * x coordinate of update
     *
     * A--B | | C--D
     *
     * corner A
     */
    protected int xCoordinate;
    /**
     * y coordinate of update
     *
     * A--B | | C--D
     *
     * corner A
     */
    protected int yCoordinate;
    /**
     * type of update (look on PaintUpdate.UPDATE_TYPE_ADD, ...)
     */
    protected int updateType;
    /**
     * data of PNG type ARGB image
     */
    protected byte[] imageData;
    
    /**
     * initize update data object
     * 
     * @param updateType type of this update (see PaintUpdate.UPDATE_TYPE_ADD)
     * @param updateID this update identification
     * @param layerID ID of layer which is updating
     * @param canvasID ID of canvas which is updating
     * @param xCoordinate X coordinate of update (left-up corner)
     * @param yCoordinate Y coordinate of update (left-up corner)
     * @param imageData data of PNG type ARGB image, this image contains update 
     * 
     * @see <a href="http://www.w3.org/Graphics/PNG/">PNG specification</a>
     */
    public PaintUpdate(int updateType, int updateID, int layerID, int canvasID, int xCoordinate, int yCoordinate, byte[] imageData) {
        this.updateType = updateType;
        this.updateID = updateID;
        this.layerID = layerID;
        this.canvasID = canvasID;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.imageData = imageData;
    }
    
    /**
     * type of this update
     * 
     * @see "PaintUpdate.UPDATE_TYPE_ADD, etc..."
     */
    public int getUpdateType() {
        return updateType;
    }
    
    /**
     * returns identification of this update
     * 
     * @return id of this update
     */
    public int getUpdateID() {
        return updateID;
    }
    
    /**
     * returns ID of layer which is updating
     * 
     * @return updating layer ID
     */
    public int getLayerID() {
        return layerID;
    }
    
    /**
     * returns ID of canvas which is updating
     * 
     * @return ID of updating canvas
     */
    public int getCanvasID() {
        return canvasID;
    }
    
    /**
     * returns X coordinate of this update (up-left corner)
     * 
     * @return X coordinate of this update
     */
    public int getXCoordinate() {
        return xCoordinate;
    }
    
    /**
     * returns Y coordinate of this update (up-left corner)
     * 
     * @return Y coordinate of this update
     */
    public int getYCoordinate() {
        return yCoordinate;
    }
    
    /**
     * Returns data of this update. Its bytes of PNG type ARGB image.
     * 
     * @return update image data
     * 
     * @see <a href="http://www.w3.org/Graphics/PNG/">PNG specification</a>
     */
    public byte[] getImageData() {
        return imageData;
    }
}
