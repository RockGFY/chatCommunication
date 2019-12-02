package protocol.entity;

public enum UserRelationStatus {
    PENDING(0),
    ACCEPTED(1),
    REJECTED(2),
    DELETED(3);

    private int value;

    UserRelationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserRelationStatus fromValue(int value) {
        switch (value) {
            case 0:
                return PENDING;
            case 1:
                return ACCEPTED;
            case 2:
                return REJECTED;
            case 3:
                return DELETED;
        }
        return null;
    }
}
