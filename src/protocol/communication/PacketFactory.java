package protocol.communication;

import protocol.entity.Message;
import protocol.entity.UserIP;
import protocol.entity.User;
import protocol.entity.UserRelation;
import util.DataParser;

import java.util.List;

public class PacketFactory {

    /**
     * Create a packet for requesting login to the server.
     * This is a to-server specific packet.
     * @param user The user info that needs to be authenticated.
     * @return A packet.
     */
    public static Packet createLoginRequest(User user) {
        return new Packet(
                PacketType.LOGIN,
                ErrorMessage.EMPTY,
                user.toString()
        );
    }

    public static Packet createLoginFromPeerRequest(UserIP userIP) {
        return new Packet(
                PacketType.LOGIN_FROM_PEER,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }

    /**
     * Create a return login packet from the server to client.
     * This is a from-server specific packet.
     * @param errorMessage The error message.
     * @param userNameList A list of active user names if login succeeded.
     * @return A packet.
     */
    public static Packet createLoginResponse(String errorMessage, List<UserIP> userNameList) {
        return new Packet(
                PacketType.LOGIN,
                errorMessage,
                DataParser.parseObjectToStr(userNameList)
        );
    }

    /**
     * Create a packet that notify other parties on new login.
     * This is a from-server specific packet.
     * @param userIP The newly login user.
     * @return A packet.
     */
    public static Packet createNewUserNotification(UserIP userIP) {
        return new Packet(
                PacketType.NEW_USER_NOTIFICATION,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }

    /**
     * Create a packet for requesting registration on server.
     * This is a to-server specific packet.
     * @param user The user info that needs to be registered.
     * @return A packet.
     */
    public static Packet createRegisterRequest(User user) {
        return new Packet(
                PacketType.REGISTER,
                ErrorMessage.EMPTY,
                user.toString()
        );
    }

    /**
     * Create a return register packet from the server to client.
     * This is a from-server specific packet.
     * @param errorMessage The error message.
     * @return A packet.
     */
    public static Packet createRegisterResponse(String errorMessage) {
        return new Packet(
                PacketType.REGISTER,
                errorMessage,
                null
        );
    }

    /**
     * Create a packet for requesting disconnection from server.
     * @param userIP The user to be disconnected.
     * @return A packet.
     */
    public static Packet createDisconnectRequest(UserIP userIP) {
        return new Packet(
                PacketType.DISCONNECT,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }

    /**
     * Create a return disconnecting packet to client.
     * @param errorMessage The error message.
     * @return A packet.
     */
    public static Packet createDisconnectResponse(String errorMessage) {
        return new Packet(
                PacketType.DISCONNECT,
                errorMessage,
                null
        );
    }

    /**
     * Create a packet for notifying other parties about the disconnection.
     * @param userIP The disconnecting user.
     * @return A packet.
     */
    public static Packet createDisconnectNotification(UserIP userIP) {
        return new Packet(
                PacketType.DISCONNECT_NOTIFICATION,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }

    public static Packet createDisconnectFromPeerRequest(UserIP userIP) {
        return new Packet(
                PacketType.DISCONNECT_FROM_PEER,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }

    /**
     * Create a packet for personal message.
     * @param message The message to be sent.
     * @return A packet.
     */
    public static Packet createMessage(Message message) {
        return new Packet(
                PacketType.MESSAGE,
                ErrorMessage.EMPTY,
                message.toString()
        );
    }

    /**
     * Create a packet that contains a list of relation to client.
     * This is a server specific packet.
     * @param errorMessage The error message.
     * @param relationList The relation list.
     * @return A packet.
     */
    public static Packet createRelationListResponse(String errorMessage, List<UserIP> relationList) {
        return new Packet(
                PacketType.GET_RELATIONS,
                errorMessage,
                DataParser.parseObjectToStr(relationList)
        );
    }

    /**
     * Create a packet requesting a new relation on server
     * This is a to-server specific packet.
     * @param relation The desired relation
     * @return A packet.
     */
    public static Packet createNewRelationRequest(UserRelation relation) {
        return new Packet(
                PacketType.NEW_RELATION,
                ErrorMessage.EMPTY,
                relation.toString()
        );
    }
    /**
     * Create a packet requesting a delete relation on server
     * This is a to-server specific packet.
     * @param relation The desired relation
     * @return A packet.
     */
    public static Packet createDeleteRelationRequest(UserRelation relation) {
        return new Packet(
                PacketType.DELETE_RELATION,
                ErrorMessage.EMPTY,
                relation.toString()
        );
    }

    /**
     * Create a packet that sends pending relation requests.
     * This is a from-server specific packet.
     * @param relationList The list of pending relation requests.
     * @return A packet.
     */
    public static Packet createPendingRelationRequestsResponse(List<UserIP> relationList) {
        return new Packet(
                PacketType.PENDING_RELATION_REQUESTS,
                ErrorMessage.EMPTY,
                relationList.toString()
        );
    }

    /**
     * Create a packet that notifies about a new relation.
     * @param userIP The related user.
     * @return A packet.
     */
    public static Packet createNewRelationNotification(UserIP userIP) {
        return new Packet(
                PacketType.NEW_RELATION_NOTIFICATION,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }
    /**
     * Create a packet that notifies about a delete relation.
     * @param userIP The related user.
     * @return A packet.
     */
    public static Packet createDeleteRelationNotification(UserIP userIP) {
        return new Packet(
                PacketType.DELETE_RELATION_NOTIFICATION,
                ErrorMessage.EMPTY,
                userIP.toString()
        );
    }

    /**
     * Create a packet for requesting latest user ip.
     * @param userName The unique user name.
     * @return A packet.
     */
    public static Packet createGetUserIP(String userName) {
        return new Packet(
                PacketType.GET_USER_IP,
                ErrorMessage.EMPTY,
                userName
        );
    }

    /**
     * Create a packet to the client about user's newest socket info.
     * @param userIP The user's socket info.
     * @param errorMessage The error message.
     * @return A packet.
     */
    public static Packet createGetUserIPResponse(UserIP userIP, String errorMessage) {
        return new Packet(
                PacketType.GET_USER_IP,
                errorMessage,
                userIP.toString()
        );
    }
    
}
