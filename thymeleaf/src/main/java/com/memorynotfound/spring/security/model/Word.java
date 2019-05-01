package com.memorynotfound.spring.security.model;

import javax.persistence.*;

@Entity
public class Word {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column(name = "key_word")
    private String keyWord;

    @Column(name = "mean")
    private String mean;

    @Column(name = "type")
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", key='" + keyWord + '\'' +
                ", mean='" + mean + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
