package co.kahra.playtester.communication;

import java.io.Serializable;
import java.util.ArrayList;

public class PlaytesterMessage implements Serializable {

    // AUTHENTICATION
    public static final int REQUEST_HANDSHAKE = 1000;
    public static final int LOGIN = 1001;
    public static final int REGISTER = 1002;
    public static final int HANDSHAKE = 1003;
    public static final int LOGOUT = 1091;
    public static final int HEARTBEAT = 1099;

    // USER AUTHENTICATION
    public static final int USER_HANDSHAKE = HANDSHAKE;
    public static final int USER_LOGIN = LOGIN; // TODO: ADJUST
    public static final int USER_REGISTER = REGISTER; // TODO: ADJUST
    public static final int USER_LOGOUT = LOGOUT; // TODO: ADJUST

    // SERVER AUTHENTICATION
    public static final int SERVER_HANDSHAKE_REQUEST = REQUEST_HANDSHAKE;

    // SERVER AUTHENTICATION RESPONSE - HANDSHAKE
    public static final int SERVER_HANDSHAKE_SUCCESS = 20100;
    public static final int SERVER_HANDSHAKE_FAILURE = 20200;

    // AUTHENTICATION RESPONSE - LOGIN
    public static final int SERVER_LOGIN_SUCCESS = 1201;
    public static final int SERVER_LOGIN_FAILURE_INVALID_CREDENTIALS = 1202;
    public static final int SERVER_LOGIN_FAILURE_INVALID_INPUT = 1203;

    // AUTHENTICATION RESPONSE - REGISTER
    public static final int SERVER_REGISTRATION_SUCCESS = 1301;
    public static final int SERVER_REGISTRATION_FAILURE_INVALID_INPUT = 1302;
    public static final int SERVER_REGISTRATION_FAILURE_DUPLICATE_NAME = 1303;

    // APP ACTION
    public static final int APP_LAUNCH_REGISTRATION_FRAGMENT = 30001;
    public static final int APP_LAUNCH_MATCHMAKING_LIST_FRAGMENT = 30002;
    public static final int APP_LAUNCH_MATCHMAKING_CREATE_FRAGMENT = 30003;
    public static final int APP_LAUNCH_GAME_PENDING_FRAGMENT = 30004;

    // AUTHENTICATION RESPONSE - LOGOUT
    public static final int SERVER_LOGOUT_SUCCESS = 1401;

    // MATCHMAKING
    public static final int MATCHMAKING_REFRESH = 2001;
    public static final int MATCHMAKING_JOIN = 2002;
    public static final int MATCHMAKING_CREATE = 2003;

    // MATCHMAKING RESPONSE
    public static final int MATCHMAKING_RESPONSE_LIST = 2101;

    // MATCHMAKING RESPONSE - JOIN
    public static final int MATCHMAKING_JOIN_SUCCESS = 2201;
    public static final int MATCHMAKING_JOIN_FAILURE_LOBBY_CLOSED = 2202;

    // GAMEPLAY

    // GAMEPLAY ACTION
    public static final int GAMEPLAY_SHUFFLE = 8001;
    public static final int GAMEPLAY_DRAW = 8002;
    public static final int GAMEPLAY_MULLIGAN = 8003;

    // ERROR CODES
    public static final int SERVER_MYSQL_CONNECTION_FAILURE = 9001;
    public static final int SERVER_DISCONNECTED_IDLE_USER = 9002;

    final int code;
    int userID = -1;
    String username = "";
    String password = "";
    String email = "";
    String verificationCode = "";

    int hostID;

    int seats;
    String description;

    ArrayList<LobbyState> lobbyStates;

    String errorMessage = "";

    public PlaytesterMessage (int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public ArrayList<LobbyState> getLobbyStates() {
        return lobbyStates;
    }

    public int getHostID() {
        return hostID;
    }

    public int getSeats() {
        return seats;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setLobbyStates(ArrayList<LobbyState> lobbyStates) {
        this.lobbyStates = lobbyStates;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}