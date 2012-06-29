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
