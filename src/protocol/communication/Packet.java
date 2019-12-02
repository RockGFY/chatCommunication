package protocol.communication;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class Packet
        extends AbstractPacket
        implements IPacket, Serializable {

    @JSONField(name = PacketField.TYPE)
    private PacketType type;

    @JSONField(name = PacketField.ERROR, ordinal = 1)
    private String errorMessage;

    @JSONField(name = PacketField.ATTACHMENT, ordinal = 2)
    private String attachment;

    public Packet() {
    }

    Packet(PacketType type, String errorMessage, String attachment) {
        this.type = type;
        this.errorMessage = errorMessage;
        this.attachment = attachment;
    }

    public PacketType getType() {
        return type;
    }

    public void setType(PacketType type) {
        this.type = type;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
