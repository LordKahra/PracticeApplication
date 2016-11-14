package co.kahra.practice.practiceapplication.playtester.api;

import java.util.ArrayList;

import co.kahra.playtester.communication.LobbyState;

public interface OnServerResponseListener {
    // AUTHENTICATION
    public void onServerHandshakeRequest();
    public void onServerHandshakeSuccessResponse();
    public void onServerHandshakeFailureResponse();
    public void onServerLoginSuccessResponse();

    // MATCHMAKING
    public void onServerMatchmakingListResponse(ArrayList<LobbyState> lobbyStates);
    public void onServerMatchmakingJoinSuccessResponse();

    // IO
    public void onServerConnectionFailure();
}