package co.kahra.practice.practiceapplication.playtester.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import co.kahra.playtester.communication.PlaytesterMessage;

import static java.lang.Thread.currentThread;

public class PlaytesterConnection implements Runnable {
    static final String HOST = "71.122.172.171";
    static final int PORT = 42529;
    Socket socket = null;
    BufferedReader in;
    ObjectInputStream objIn;
    PrintWriter out;
    ObjectOutputStream objOut;

    boolean on;

    private Handler mainThreadObjectHandler;
    private InputHandler inputHandler;

    private OnServerResponseListener onServerResponseListener;

    public PlaytesterConnection (Handler mainThreadObjectHandler, OnServerResponseListener onServerResponseListener) {
        this.mainThreadObjectHandler = mainThreadObjectHandler;
        this.onServerResponseListener = onServerResponseListener;
    }

    public void run() {
        print("Thread started.");
        on = true;
        if (socket == null) {
            try {
                print("Creating sockets.");
                socket = new Socket(HOST, PORT);
                print("Socket created.");
                objOut = new ObjectOutputStream(socket.getOutputStream());
                print("ObjectOutputStream created.");
                out = new PrintWriter(socket.getOutputStream(), true);
                print("PrintWriter created.");
                objIn = new ObjectInputStream(socket.getInputStream());
                print("ObjectInputStream created.");
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                print("BufferedReader created.");

                print("Creating new input handler thread.");
                inputHandler = new InputHandler(socket, in, objIn, out, objOut, mainThreadObjectHandler, onServerResponseListener);
                new Thread(inputHandler).start();
                print("Input handler thread started.");

                Looper.prepare();

                Looper.loop();
            } catch (IOException e) {
                // TODO: Close the app or allow the user to retry!
            }
        } // shutdown
    }

    private void loop() {
        while (on) {
            print("ConnectionHandler loop beginning.");
            try {
                print("Waiting for object.");
                Object object = objIn.readObject();
                print("Object read. Passing along.");
                sendToObjectHandler(object);
            } catch (IOException e) {
                //printAction("IOException during the while.");
            } catch (ClassNotFoundException e) {
                //printAction("ClassNotFoundException during the while.");
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // Do nothing.
            }
        }
    }

    /*public void send(String input) {
        out.println(input);
    }*/

    // TODO: Everyone that uses send MUST toast on failure.
    // TODO: Make send throw.
    public void send (PlaytesterMessage request) {
        System.out.println("PlaytesterConnection.send(PlaytesterMessage): Sending message: " + request.getCode());
        try {
            objOut.writeObject(request);
            System.out.println("ConnectionHandler.send(PlaytesterMessage): Send success.");
        } catch (IOException e) {
            System.out.println("ConnectionHandler.send(PlaytesterMessage): Send failed with IOException!");
            // TODO: Toast here.
            onServerResponseListener.onServerConnectionFailure();
        }
    }

    private void sendToObjectHandler (Object object) {
        Message msg = Message.obtain();
        msg.obj = object;
        mainThreadObjectHandler.sendMessage(msg);
    }

    @Deprecated
    private void printAction (PlaytesterMessage message) {
        Message msg = Message.obtain();
        msg.obj = message;
        mainThreadObjectHandler.sendMessage(msg);
    }

    public void print (String string) {
        System.out.println("PlaytesterConnection[Thread " + currentThread().getId() + "]: " + string);
    }

    /*
    public void setup () {
        on = true;
        try {
            print("Creating sockets.");
            socket = new Socket(HOST, PORT);
            print("Socket created.");
            objOut = new ObjectOutputStream(socket.getOutputStream());
            print("ObjectOutputStream created.");
            out = new PrintWriter(socket.getOutputStream(), true);
            print("PrintWriter created.");
            objIn = new ObjectInputStream(socket.getInputStream());
            print("ObjectInputStream created.");
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            print("Creating new input handler thread.");
            inputHandler = new InputHandler(socket, in, objIn, out, objOut, mainThreadObjectHandler, onServerResponseListener);
            new Thread(inputHandler).start();
            print("Input handler thread started.");

            Looper.prepare();

            Looper.loop();

        } catch (IOException e) {
            // TODO: This is pretty bad. Failure on setup after a few tries to connect should stop trying?
            print("CONNECTION FAILURE ON SETUP");
        }

        // TODO: Looper?
    }*/

    public void shutdown () {
        try {
            in.close();
        } catch (Exception e) {
            // Ignore it and keep going.
        }

        try {
            objIn.close();
        } catch (Exception e) {
            // Ignore it and keep going.
        }

        try {
            out.close();
        } catch (Exception e) {
            // Ignore it and keep going.
        }

        try {
            objOut.close();
        } catch (Exception e) {
            // Ignore it and keep going.
        }

        try {
            socket.close();
        } catch (Exception e) {
            // Ignore it and keep going.
        }

        try {
            inputHandler.shutdown();
        } catch (Exception e) {
            // Ignore it and keep going.
        }

        on = false;

        // TODO: Looper?
    }

    /*
    @Deprecated
    public void reset () {
        shutdown();
        setup();
    }*/
}
