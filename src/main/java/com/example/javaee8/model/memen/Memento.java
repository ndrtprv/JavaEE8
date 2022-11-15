package com.example.javaee8.model.memen;

import com.example.javaee8.model.CharacterCreator.Stats;

public class Memento {
    private String stateName;
    private Stats state;

    public Memento() {

    }

    public Memento(String stateName, Stats state) {
        this.stateName = stateName;
        this.state = state;
    }

    public String getStateName() {
        return stateName;
    }

    public Stats getState() {
        return state;
    }
}