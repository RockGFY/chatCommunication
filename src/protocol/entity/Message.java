package protocol.entity;

import java.io.Serializable;

/**
 * Represents a message object
 */
public class Message
        extends AbstractEntity
        implements IEntity, Serializable {

    private String senderId;
    private String recipientId;
    private String content;

    static Message createMessage(
            String sender,
            String recipient,
            String content) {
        return new Message(sender, recipient, content);
    }

    private Message() {}

    private Message(String senderId, String recipientId, String content) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
