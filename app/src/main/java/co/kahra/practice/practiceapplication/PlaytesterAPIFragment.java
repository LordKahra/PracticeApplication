package co.kahra.practice.practiceapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;

import co.kahra.playtester.communication.LobbyState;
import co.kahra.practice.practiceapplication.playtester.api.OnServerResponseListener;
import co.kahra.practice.practiceapplication.playtester.api.PlaytesterServerInterface;

public class PlaytesterAPIFragment extends PracticeFragment implements OnServerResponseListener {
    PlaytesterServerInterface playtesterServerInterface;

    ToggleButton authenticatedStatusView;
    TextView debugOutputView;

    TextView textMatchStatus;

    Button buttonLogin;
    Button buttonRegister;
    Button buttonRequestMatches;
    Button buttonCreateGame;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_playtester_api, container, false);
        this.rootView = rootView;

        playtesterServerInterface = new PlaytesterServerInterface(this);

        authenticatedStatusView = (ToggleButton) rootView.findViewById(R.id.authenticationStatusButton);
        authenticatedStatusView.setChecked(false);

        debugOutputView = (TextView) rootView.findViewById(R.id.playtesterDebugText);
        debugOutputView.setText("Loaded.");

        textMatchStatus = (TextView) rootView.findViewById(R.id.playtester_matchmaking_status);
        textMatchStatus.setText("NO MATCH");
        textMatchStatus.setTextColor(Color.RED);

        buttonLogin = (Button) rootView.findViewById(R.id.playtester_button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playtesterServerInterface.login("lulz", "lawlzy");
            }
        });

        buttonRequestMatches = (Button) rootView.findViewById(R.id.playtester_button_request_matches);
        buttonRequestMatches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playtesterServerInterface.requestMatchmakingList();
            }
        });

        buttonCreateGame = (Button) rootView.findViewById(R.id.playtester_button_create_game);
        buttonCreateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playtesterServerInterface.createGame("test game", 1);
            }
        });

        return rootView;
    }

    @Override
    public void onServerHandshakeRequest() {
        printDebug("Handshake requested. Sending Handshake.");
        playtesterServerInterface.handshake();
    }

    @Override
    public void onServerHandshakeSuccessResponse() {
        printDebug("Handshake Success");
        authenticatedStatusView.setChecked(true);
    }

    @Override
    public void onServerHandshakeFailureResponse() {
        printDebug("Handshake Failure");
        authenticatedStatusView.setChecked(false);
    }

    @Override
    public void onServerLoginSuccessResponse() {
        printDebug("Login Success");
        authenticatedStatusView.setChecked(true);
    }

    @Override
    public void onServerMatchmakingListResponse(ArrayList<LobbyState> lobbyStates) {
        printDebug("Matches received");
        for (LobbyState lobbyState : lobbyStates) {
            printDebug("LOBBY: " + lobbyState.getUsers() + "/" + lobbyState.getSeats() + " | " + lobbyState.getDescription());
        }
    }

    @Override
    public void onServerMatchmakingJoinSuccessResponse() {
        textMatchStatus.setText("MATCH JOINED");
        textMatchStatus.setTextColor(Color.GREEN);
    }

    @Override
    public void onServerConnectionFailure() {
        printDebug("Server connection failed. Restarting.");
        // TODO: How do we fix this? For now, do a reset.
        // Future: Implement repeating reconnection attempts.
        playtesterServerInterface.reset();
    }

    public void printDebug (String string) {
        System.out.println("PRINTDEBUG: " + string);
        debugOutputView.setText(debugOutputView.getText().toString() + "\n" + string);
    }
}
