package com.wlines.game;

public class User implements Comparable<User> {


    public String nick;

    public int points;


    public User(String nick, Integer points) {
        this.nick = nick;
        this.points = points;
    }

    @Override
    public int compareTo(User u) {
        return (int) (u.points - this.points);
    }
}
