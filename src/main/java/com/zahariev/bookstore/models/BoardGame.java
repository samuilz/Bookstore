package com.zahariev.bookstore.models;

public class BoardGame {
    private Integer boardGameId;
    private String name;
    private Integer minimumNumberOfPlayers;
    private Integer maximumNumberOfPlayers;

    public BoardGame() {

    }

    public BoardGame(String name, Integer minimumNumberOfPlayers, Integer maximumNumberOfPlayers) {
        this.name = name;
        this.minimumNumberOfPlayers = minimumNumberOfPlayers;
        this.maximumNumberOfPlayers = maximumNumberOfPlayers;
    }

    public Integer getBoardGameId() {
        return boardGameId;
    }

    public void setBoardGameId(Integer boardGameId) {
        this.boardGameId = boardGameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinimumNumberOfPlayers() {
        return minimumNumberOfPlayers;
    }

    public void setMinimumNumberOfPlayers(Integer minimumNumberOfPlayers) {
        this.minimumNumberOfPlayers = minimumNumberOfPlayers;
    }

    public Integer getMaximumNumberOfPlayers() {
        return maximumNumberOfPlayers;
    }

    public void setMaximumNumberOfPlayers(Integer maximumNumberOfPlayers) {
        this.maximumNumberOfPlayers = maximumNumberOfPlayers;
    }
}
