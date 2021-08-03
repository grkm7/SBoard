package com.info.sboard;

import java.io.Serializable;

public class Boards implements Serializable {
    private int board_id;
    private String board_image;
    private String board_name;
    private String board_color;
    private String board_size;
    private String board_price;

    public Boards() {
    }

    public Boards(int board_id, String board_image, String board_name, String board_color, String board_size, String board_price) {
        this.board_id = board_id;
        this.board_image = board_image;
        this.board_name = board_name;
        this.board_color = board_color;
        this.board_size = board_size;
        this.board_price = board_price;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getBoard_image() {
        return board_image;
    }

    public void setBoard_image(String board_image) {
        this.board_image = board_image;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public String getBoard_color() {
        return board_color;
    }

    public void setBoard_color(String board_color) {
        this.board_color = board_color;
    }

    public String getBoard_size() {
        return board_size;
    }

    public void setBoard_size(String board_size) {
        this.board_size = board_size;
    }

    public String getBoard_price() {
        return board_price;
    }

    public void setBoard_price(String board_price) {
        this.board_price = board_price;
    }
}
