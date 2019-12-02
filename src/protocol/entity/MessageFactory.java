package protocol.entity;

public class MessageFactory {

    /**
     * Create a personal message
     * @param sender The sender
     * @param recipient The recipient
     * @param content The content
     * @return A personal message.
     */
    public static Message createPersonalMessage(
            String sender,
            String recipient,
            String content) {
        return Message.createMessage(sender, recipient, content);
    }

    /**
     * Create a message to all live clients.
     * The recipient field is null.
     * @param sender The sender
     * @param content The content
     * @return A broadcast message.
     */
    public static Message createBoardCastMessage(
            String sender,
            String content) {
        return Message.createMessage(sender, null, content);
    }

}
