package com.sparta.board3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
