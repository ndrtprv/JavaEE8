package com.example.javaee8.model.CharacterCreator.DnDClass;

public class ClassFactory {
    public CharacterClass getClass(String type) {
        if (type.equals("Fighter")) {
            return new Fighter();
        } else if (type.equals("Bard")) {
            return new Bard();
        } else {
            return null;
        }
    }
}