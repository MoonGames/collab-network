package cz.mgn.collabnetwork.layers.collabprotocol.data;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class RoomData {

    protected String name;
    protected String password;
    protected int width;
    protected int height;

    public RoomData(String name, String password, int width, int height) {
        this.name = name;
        this.password = password;
        this.width = width;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
