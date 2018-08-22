package com.strings;

public class Artist {
    private String name;
    private int id;
    private boolean solo;

    public Artist(String name, int id, boolean solo) {
        this.name = name;
        this.id = id;
        this.solo = solo;
    }

    public boolean isSolo() {
        return solo;
    }

    public void setSolo(boolean solo) {
        this.solo = solo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artist(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist(String name) {
        this.name = name;
    }
}
