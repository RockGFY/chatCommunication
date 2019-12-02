package protocol.entity;

import java.io.Serializable;

/**
 * A user relation object
 */
public class UserRelation
        extends AbstractEntity
        implements IEntity, Serializable {

    private String requester;
    private String recipient;
    private UserRelationStatus userRelationStatus;

    public static UserRelation createUserRelation(
            String requester,
            String recipient,
            UserRelationStatus userRelationStatus) {
        return new UserRelation(requester, recipient, userRelationStatus);
    }

    private UserRelation() {
    }

    private UserRelation(
            String requester,
            String recipient,
            UserRelationStatus userRelationStatus) {
        this.requester = requester;
        this.recipient = recipient;
        this.userRelationStatus = userRelationStatus;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public UserRelationStatus getUserRelationStatus() {
        return userRelationStatus;
    }

    public void setUserRelationStatus(UserRelationStatus userRelationStatus) {
        this.userRelationStatus = userRelationStatus;
    }
}
