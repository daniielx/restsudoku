package com.example.restsudoku;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Gamer {
    private String gamerName;

    private String game;


    @Id
    private int gamerId;

    public String getGamerName() {
        return gamerName;
    }


    public void setGamerName(String gamerName) {
        this.gamerName = gamerName;
    }

    public int getGamerId() {
        return gamerId;
    }

    public void setGamerId(int gamerId) {
        this.gamerId = gamerId;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public Gamer() {
    }

    public Gamer(String gamerName, int gamerId, String game) {
        setGamerName(gamerName);
        setGamerId(gamerId);
        setGame(game);
    }
}
