package co.kahra.practice.practiceapplication.playtester.api;

import android.os.Message;

import co.kahra.playtester.communication.PlaytesterMessage;

import static java.lang.Thread.currentThread;

public class ServerResponseReader {
    public static void readServerResponse(Message msg, OnServerResponseListener onServerResponseListenerHandler, PlaytesterConnection playtesterConnection) {
        // Get the message object.
        Object object = msg.obj;
        print("Object's class is " + object.getClass().getName());

        // Is the message the correct format?
        try {
            PlaytesterMessage ptm = (PlaytesterMessage) msg.obj;
            print("PlaytesterMessage loaded. CODE: " + ptm.getCode());

            // Handle the message.
            readServerResponse(ptm, onServerResponseListenerHandler, playtesterConnection);

        } catch (ClassCastException e) {
            // Problem!
            print("Not a PlaytesterMessage.");
        }
    }

    private static void readServerResponse(PlaytesterMessage message, OnServerResponseListener onServerResponseListenerHandler, PlaytesterConnection playtesterConnection) {
        switch(message.getCode()) {
            case PlaytesterMessage.SERVER_HANDSHAKE_REQUEST:
                onServerResponseListenerHandler.onServerHandshakeRequest();
                break;
            case PlaytesterMessage.SERVER_HANDSHAKE_SUCCESS:
                onServerResponseListenerHandler.onServerHandshakeSuccessResponse();
                break;
            case PlaytesterMessage.SERVER_HANDSHAKE_FAILURE:
                onServerResponseListenerHandler.onServerHandshakeFailureResponse();
                break;
            case PlaytesterMessage.SERVER_LOGIN_SUCCESS:
                onServerResponseListenerHandler.onServerLoginSuccessResponse();
                break;
            case PlaytesterMessage.MATCHMAKING_RESPONSE_LIST:
                onServerResponseListenerHandler.onServerMatchmakingListResponse(message.getLobbyStates());
                break;
            case PlaytesterMessage.MATCHMAKING_JOIN_SUCCESS:
                onServerResponseListenerHandler.onServerMatchmakingJoinSuccessResponse();
                break;
            case PlaytesterMessage.HEARTBEAT:
                PlaytesterMessage request = new PlaytesterMessage(PlaytesterMessage.HEARTBEAT);
                playtesterConnection.send(request);
                break;
            default:
                print("UNDEFINED CODE RECEIVED: " + message.getCode());
                break;
        }
    }

    private static void print (String string) {
        System.out.println("ServerResponseHelper[Thread " + currentThread().getId() + "]: " + string);
    }
}
