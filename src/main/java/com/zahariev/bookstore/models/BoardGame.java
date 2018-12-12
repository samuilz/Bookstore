package com.zahariev.bookstore.models;

import javax.persistence.*;

@Entity
@Table(name = "board_games")
public class BoardGame extends Product{
    private Integer minimumNumberOfPlayers;

    private Integer maximumNumberOfPlayers;

//    @Column(name = "image")
//    private byte[] image;

    public BoardGame() {

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
