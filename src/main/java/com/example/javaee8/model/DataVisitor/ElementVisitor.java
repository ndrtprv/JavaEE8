package com.example.javaee8.model.DataVisitor;

import com.example.javaee8.model.CharacterCreator.DnDClass.CharacterClass;
import com.example.javaee8.model.CharacterCreator.DnDRace.CharacterRace;
import com.example.javaee8.model.CharacterCreator.Stats;
import com.example.javaee8.model.CharacterCreator.Character;

import java.util.TreeMap;

public class ElementVisitor implements DataElementsVisitor{
    @Override
    public TreeMap<Object, Object> visit(Character character) {
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put("Name", character.getName());
        return map;
    }

    @Override
    public TreeMap<Object, Object> visit(Stats stats) {
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put("Attributes", stats.getStats());
        return map;
    }

    @Override
    public TreeMap<Object, Object> visit(CharacterClass characterClass) {
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put("Class", characterClass.getName());
        return map;
    }

    @Override
    public TreeMap<Object, Object> visit(CharacterRace characterRace) {
        TreeMap<Object, Object> map = new TreeMap<>();
        map.put("Race", characterRace.getName());
        return map;
    }
}