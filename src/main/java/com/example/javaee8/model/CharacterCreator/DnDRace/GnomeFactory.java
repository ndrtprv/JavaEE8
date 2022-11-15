package com.example.javaee8.model.CharacterCreator.DnDRace;

public class GnomeFactory implements RaceAbstractFactory {
    @Override
    public CharacterRace create() {
        return new Gnome();
    }
}