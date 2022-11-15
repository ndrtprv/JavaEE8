package com.example.javaee8.model.memen;

import java.util.*;

public class CareTaker {
    private List<Memento> mementoList;

    public void add(Memento memento) {
        if (mementoList == null) {
            mementoList = new ArrayList<>();
        }
        mementoList.add(memento);
    }

    public Memento get(int i) {
        return mementoList.get(i);
    }

    public Memento getByName(String name) {
        int i;
        for (i = 0; i < mementoList.size(); i++) {
            if (name.equals(mementoList.get(i).getStateName())) {
                return mementoList.get(i);
            }
        }
        return null;
    }
}