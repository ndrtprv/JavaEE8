package com.example.javaee8.model.DataVisitor;

import com.example.javaee8.model.CharacterCreator.DnDClass.CharacterClass;
import com.example.javaee8.model.CharacterCreator.DnDRace.CharacterRace;
import com.example.javaee8.model.CharacterCreator.Character;
import com.example.javaee8.model.CharacterCreator.Stats;

import java.util.TreeMap;

public interface DataElementsVisitor {
    TreeMap<Object, Object> visit(Character character);
    TreeMap<Object, Object> visit(Stats stats);
    TreeMap<Object, Object> visit(CharacterClass characterClass);
    TreeMap<Object, Object> visit(CharacterRace characterRace);
}