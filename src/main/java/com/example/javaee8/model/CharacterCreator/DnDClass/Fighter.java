package com.example.javaee8.model.CharacterCreator.DnDClass;

public class Fighter extends CharacterClass {
    private final String perk;

    public Fighter() {
        setName("Fighter");
        setHp(10);
        perk = "Extra Attack";
    }

    public String getPerk() {
        return perk;
    }

    @Override
    public void printMagika() {
        System.out.println("Perk: " + perk);
    }
}