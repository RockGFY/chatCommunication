package protocol.entity;

public class UserRelationFactory {

    public static UserRelation createUserRelation(String requester, String recipient, int value) {
        return UserRelation.createUserRelation(
                requester,
                recipient,
                UserRelationStatus.fromValue(value));
    }

    public static UserRelation createPendingUserRelation(String requester, String recipient) {
        return UserRelation.createUserRelation(
                requester,
                recipient,
                UserRelationStatus.PENDING);
    }

    public static UserRelation createAcceptedUserRelation(String requester, String recipient) {
        return UserRelation.createUserRelation(
                requester,
                recipient,
                UserRelationStatus.ACCEPTED);
    }

    public static UserRelation createDeclinedUserRelation(String requester, String recipient) {
        return UserRelation.createUserRelation(
                requester,
                recipient,
                UserRelationStatus.REJECTED);
    }

    public static UserRelation createDeletedUserRelation(String requester, String recipient) {
        return UserRelation.createUserRelation(
                requester,
                recipient,
                UserRelationStatus.DELETED);
    }
}
