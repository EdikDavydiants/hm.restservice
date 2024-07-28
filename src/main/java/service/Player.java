package service;

import java.io.PrintWriter;


public class Player {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int elo;


    public Player(int id, String firstName, String lastName, int elo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.elo = elo;
    }


    public String printInHTMLForm() {
        return id + "   " + firstName + " " + lastName + "  " + elo + "<br>";
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", elo=" + elo +
                '}';
    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getElo() {
        return elo;
    }


}


