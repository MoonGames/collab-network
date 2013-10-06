package cz.mgn.collabnetwork.layers.collabprotocol.data;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class ChatMessage {
    
    protected String message;
    
    public ChatMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
