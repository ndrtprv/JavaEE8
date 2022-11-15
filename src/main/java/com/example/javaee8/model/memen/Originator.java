package com.example.javaee8.model.memen;

import com.example.javaee8.model.CharacterCreator.Stats;

public class Originator {
    private String stateName;
    private Stats state;

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setState(Stats state) {
        this.state = state;
    }

    public Stats getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(stateName, state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}