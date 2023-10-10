package com.example.topgames;

public class TopGames {

    String gameName;
    int gameImage;

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameImage() {
        return gameImage;
    }

    public void setGameImage(int gameImage) {
        this.gameImage = gameImage;
    }

    public TopGames(String gameName, int gameImage) {
        this.gameName = gameName;
        this.gameImage = gameImage;
    }
}
