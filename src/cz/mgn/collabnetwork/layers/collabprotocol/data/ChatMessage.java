package cz.mgn.collabnetwork.layers.collabprotocol.data;

/**
 *
 * @author Martin Indra <aktive at seznam.cz>
 */
public class ChatMessage {

    protected String message;
    protected String author;

    public ChatMessage(String message) {
        this.message = message;
    }

    public ChatMessage(String message, String author) {
        this.message = message;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}
