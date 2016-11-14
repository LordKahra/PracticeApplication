package co.kahra.practice.practiceapplication.playtester.api;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import co.kahra.playtester.communication.PlaytesterMessage;

import static java.lang.Thread.*;

public class InputHandler implements Runnable {
    Socket socket;
    BufferedReader in;
    ObjectInputStream objIn;
    ObjectOutputStream objOut;
    boolean on;
    long heartbeat;
    private Handler mainThreadObjectHandler;
    private OnServerResponseListener onServerResponseListener;
    ArrayList<Object> queue;

    public InputHandler(Socket socket, BufferedReader in, ObjectInputStream objIn, PrintWriter out, ObjectOutputStream objOut, Handler mainThreadObjectHandler, OnServerResponseListener onServerResponseListener) {
        this.socket = socket;
        this.in = in;
        this.objIn = objIn;
        this.objOut = objOut;
        on = true;
        heartbeat = Calendar.getInstance().getTimeInMillis();
        this.mainThreadObjectHandler = mainThreadObjectHandler;
        this.onServerResponseListener = onServerResponseListener;
        this.queue = new ArrayList<Object>();
    }

    @Override
    public void run() {
        print("Started.");
        while (on) {
            try {
                // Read the object sent from the server.
                print("Attempting to read object.");
                Object object = objIn.readObject();
                print("Object read: " + object.getClass().getName());

                // Pass it to the looper.
                sendActionToHandler(object);

                /*
                // Make sure it's a supported class.
                try {
                    PlaytesterMessage message = (PlaytesterMessage) object;
                    // Class is supported. Send along to handler.
                    print("PlaytesterMessage found.");
                    sendActionToHandler(message);
                } catch (ClassCastException e) {
                    print("Sent message was NOT a PTM");
                }*/
            } catch (EOFException e) {
                System.out.println("EOFException occurred. Server has disconnected. Shutting down.");
                // tODO: PROPER SHUTDOWN
                on = false;
            } catch (InvalidClassException e) {
                System.out.println("InvalidClassException occurred. Check printStackTrace.");
                e.printStackTrace();
            } catch (IOException e) {
                // To be expected amirite
                print("IOException occurred!");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                // uh oh class not found ho
            }

            if (Calendar.getInstance().getTimeInMillis() - 60000 > heartbeat) {
                sendHeartbeat(objOut);
            }

            try {
                sleep(2000);
                // TODO: Set back to 20 after testing.
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        print("Stopped.");
    }

    public void shutdown() {
        on = false;
    }

    // DEBUG PURPOSES?
    private void print(String string) {
        System.out.println("InputHandler.run()[Thread " + currentThread().getId() + "]: " + string);
    }

    private void sendActionToHandler (Object object) {
        print("Sending object to mainThreadObjectHandler.");
        Message msg = Message.obtain();
        msg.obj = object;
        mainThreadObjectHandler.sendMessage(msg);
        //ServerResponseReader.readServerResponse(msg, onServerResponseListener);
    }

    @Deprecated
    private void sendActionToHandler (PlaytesterMessage message) {
        print("PlaytesterMessage Code: " + message.getCode());
        Message msg = Message.obtain();
        msg.obj = message;
        mainThreadObjectHandler.sendMessage(msg);
    }

    // TODO: Throws IOException.
    private void sendHeartbeat(ObjectOutputStream objOut) {
        //print(new PTMessage());
        //out.println(CodeManager.CODE_HEARTBEAT);
        heartbeat = Calendar.getInstance().getTimeInMillis();
        // TODO: Implement!
    }
}
