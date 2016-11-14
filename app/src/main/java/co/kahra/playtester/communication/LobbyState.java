package co.kahra.playtester.communication;

import java.io.Serializable;

public class LobbyState implements Serializable {
    // UNIQUE KEY
    private int hostID;
    private String description;
    private int users;
    private int seats;

    public LobbyState(int hostID, String description, int users, int seats) {
        this.hostID = hostID;
        this.description = description;
        this.users = users;
        this.seats = seats;
    }

    public int getHostID() {
        return hostID;
    }

    public int getSeats() {
        return seats;
    }

    public int getUsers() {
        return users;
    }

    public String getDescription() {
        return description;
    }

    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setUsers(int users) {
        this.users = users;
    }
}
