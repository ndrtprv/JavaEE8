package com.example.javaee8.model.CharacterCreator.DnDRace;

public class Half_OrcFactory implements RaceAbstractFactory {
    @Override
    public CharacterRace create() {
        return new Half_Orc();
    }
}