package co.kahra.practice.practiceapplication.playtester.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.IOException;

import co.kahra.playtester.communication.PlaytesterMessage;

// Activity must implement the OnServerResponseHandler interface.
// Activity must maintain an active PlaytesterServerInterface.
// Activity must have the following permissions:
// TODO: More documentation.

public class PlaytesterServerInterface {
    private PlaytesterConnection playtesterConnection = null;
    public Handler mainThreadObjectHandler;
    OnServerResponseListener onServerResponseListener;

    public PlaytesterServerInterface (final OnServerResponseListener onServerResponseListener) {
        System.out.println("PlaytesterServerInterface: Loading.");
        this.onServerResponseListener = onServerResponseListener;

        mainThreadObjectHandler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                // Handle the message.
                ServerResponseReader.readServerResponse(msg, onServerResponseListener, playtesterConnection);
            }
        };

        playtesterConnection = new PlaytesterConnection(mainThreadObjectHandler, onServerResponseListener);
        new Thread(playtesterConnection).start();
    }

    public void handshake () {
        PlaytesterMessage message = new PlaytesterMessage(PlaytesterMessage.HANDSHAKE);
        message.setUserID(-1);
        message.setVerificationCode("asdf");
        playtesterConnection.send(message);
    }

    public void login (String username, String password) {
        PlaytesterMessage message = new PlaytesterMessage(PlaytesterMessage.LOGIN);
        message.setUsername(username);
        message.setPassword(password);
        playtesterConnection.send(message);
    }

    public void register (String username, String password, String email) {
        PlaytesterMessage message = new PlaytesterMessage(PlaytesterMessage.REGISTER);
        message.setUsername(username);
        message.setPassword(password);
        message.setEmail(email);
        playtesterConnection.send(message);
    }

    public void requestMatchmakingList () {
        PlaytesterMessage request = new PlaytesterMessage(PlaytesterMessage.MATCHMAKING_REFRESH);
        playtesterConnection.send(request);
    }

    public void createGame (String description, int seats) {
        PlaytesterMessage request = new PlaytesterMessage(PlaytesterMessage.MATCHMAKING_CREATE);
        request.setDescription(description);
        request.setSeats(seats);
        playtesterConnection.send(request);
    }

    public void reset () {
        playtesterConnection.shutdown();
        playtesterConnection = new PlaytesterConnection(mainThreadObjectHandler, onServerResponseListener);
        new Thread(playtesterConnection).start();
    }

    public void shutdown () {
        // TODO: Implement graceful shutdown.
        playtesterConnection.shutdown();
    }
}
