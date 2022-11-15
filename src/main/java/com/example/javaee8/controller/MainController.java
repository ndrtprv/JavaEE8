package com.example.javaee8.controller;

import com.example.javaee8.model.CharacterCreator.Character;
import com.example.javaee8.model.CharacterCreator.DnDClass.ClassFactory;
import com.example.javaee8.model.CharacterCreator.DnDRace.GnomeFactory;
import com.example.javaee8.model.CharacterCreator.DnDRace.Half_OrcFactory;
import com.example.javaee8.model.DataVisitor.DataElement;
import com.example.javaee8.model.DataVisitor.ElementVisitor;
import com.example.javaee8.model.memen.CareTaker;
import com.example.javaee8.model.memen.Originator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class MainController {

    @FXML
    private TextField dndClassField, dndRaceField, healthField, nameField;

    @FXML
    private TextField strengthField, dexterityField, constitutionField;

    @FXML
    private TextField intellectField, wisdomField, charismaField;

    @FXML
    private ListView<String> charactersList;

    @FXML
    private ListView<String> savedStatsList;

    private Character character;
    private HashMap<String, Character> characters;
    private HashMap<String, Integer> savedStatsAmount;
    private final CareTaker careTaker = new CareTaker();
    private final Originator originator = new Originator();
    private String selected;
    private String selectedStats;
    private ArrayList<Character> charactersForJSON;

    @FXML
    protected void onCreateCharacterButtonClick() {
        if (nameField.getText().isBlank()) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Empty name field!");
            alert.getDialogPane().setContentText("Please enter the name!");
            alert.showAndWait();
        }
        if (!dndClassField.getText().equals("Bard")
                && !dndClassField.getText().equals("Fighter")) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Empty class field!");
            alert.getDialogPane().setContentText("Please choose the class!");
            alert.showAndWait();
        }
        if (!dndRaceField.getText().equals("Gnome")
                && !dndRaceField.getText().equals("Half-Orc")) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Empty race field!");
            alert.getDialogPane().setContentText("Please choose the race!");
            alert.showAndWait();
        }

        switch (dndRaceField.getText()) {
            case "Gnome" -> character = new Character(nameField.getText(),
                    new ClassFactory().getClass(dndClassField.getText()),
                    new GnomeFactory().create()
            );
            case "Half-Orc" -> character = new Character(nameField.getText(),
                    new ClassFactory().getClass(dndClassField.getText()),
                    new Half_OrcFactory().create()
            );
        }

        if (character != null) {
            character.addRaceBonuses();
            character.addBonus();

            strengthField.setText(character.getAttributes().getStats().get("Strength").toString());
            dexterityField.setText(character.getAttributes().getStats().get("Dexterity").toString());
            constitutionField.setText(character.getAttributes().getStats().get("Constitution").toString());
            intellectField.setText(character.getAttributes().getStats().get("Intellect").toString());
            wisdomField.setText(character.getAttributes().getStats().get("Wisdom").toString());
            charismaField.setText(character.getAttributes().getStats().get("Charisma").toString());
            healthField.setText(character.getAttributes().getStats().get("Health").toString());
        }
    }

    @FXML
    protected void onChangeStatsButtonClick() {
        if (character != null) {
            character.setAttributes();

            character.addRaceBonuses();
            character.addBonus();

            strengthField.setText(character.getAttributes().getStats().get("Strength").toString());
            dexterityField.setText(character.getAttributes().getStats().get("Dexterity").toString());
            constitutionField.setText(character.getAttributes().getStats().get("Constitution").toString());
            intellectField.setText(character.getAttributes().getStats().get("Intellect").toString());
            wisdomField.setText(character.getAttributes().getStats().get("Wisdom").toString());
            charismaField.setText(character.getAttributes().getStats().get("Charisma").toString());
            healthField.setText(character.getAttributes().getStats().get("Health").toString());
        }
    }

    @FXML
    protected void onSaveCharacterButtonClick() {
        if (characters == null) {
            characters = new HashMap<>();
        }
        if (savedStatsAmount == null) {
            savedStatsAmount = new HashMap<>();
        }
        if (charactersList == null) {
            charactersList = new ListView<>();
        }
        if (savedStatsList == null) {
            savedStatsList = new ListView<>();
        }

        int num = 1;

        String characterName = character.getName() + character.getDndClass().getName() + character.getRace().getName();
        characters.put(characterName, character);
        if (!charactersList.getItems().contains(characterName)) {
            charactersList.getItems().add(characterName);
            savedStatsAmount.put(characterName, num);
        }

        String stateName = characterName + savedStatsAmount.get(characterName);
        savedStatsAmount.put(characterName, savedStatsAmount.get(characterName) + 1);
        originator.setState(character.getAttributes());
        originator.setStateName(stateName);
        careTaker.add(originator.saveStateToMemento());
        savedStatsList.getItems().add(stateName);
    }

    @FXML
    protected void onChooseCharacterButtonClick() {
        chooseCharacter();
        if (selected != null) {
            character = characters.get(selected);

            nameField.setText(character.getName());
            dndClassField.setText(character.getDndClass().getName());
            dndRaceField.setText(character.getRace().getName());

            strengthField.setText(character.getAttributes().getStats().get("Strength").toString());
            dexterityField.setText(character.getAttributes().getStats().get("Dexterity").toString());
            constitutionField.setText(character.getAttributes().getStats().get("Constitution").toString());
            intellectField.setText(character.getAttributes().getStats().get("Intellect").toString());
            wisdomField.setText(character.getAttributes().getStats().get("Wisdom").toString());
            charismaField.setText(character.getAttributes().getStats().get("Charisma").toString());
            healthField.setText(character.getAttributes().getStats().get("Health").toString());
        }
    }

    protected void chooseCharacter() {
        charactersList.getSelectionModel().selectedItemProperty().addListener((changed, oldValue, newValue) ->
            selected = newValue);
    }

    @FXML
    protected void onChooseSavedStatsButtonClick() {
        chooseSavedStats();
        if (selectedStats != null && selected != null) {
            if (selectedStats.contains(selected)) {
                character.setAttributes(careTaker.getByName(selectedStats).getState().getStats());

                strengthField.setText(character.getAttributes().getStats().get("Strength").toString());
                dexterityField.setText(character.getAttributes().getStats().get("Dexterity").toString());
                constitutionField.setText(character.getAttributes().getStats().get("Constitution").toString());
                intellectField.setText(character.getAttributes().getStats().get("Intellect").toString());
                wisdomField.setText(character.getAttributes().getStats().get("Wisdom").toString());
                charismaField.setText(character.getAttributes().getStats().get("Charisma").toString());
                healthField.setText(character.getAttributes().getStats().get("Health").toString());
            } else {
                Alert.AlertType alertType = Alert.AlertType.ERROR;
                Alert alert = new Alert(alertType,"");
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.getDialogPane().setHeaderText("Mismatch!");
                alert.getDialogPane().setContentText("Please choose the name of saved stats" +
                        " which matches with the name of saved character!");
                alert.showAndWait();
            }
        }
    }

    protected void chooseSavedStats() {
        savedStatsList.getSelectionModel().selectedItemProperty().addListener((changed, oldValue, newValue) ->
                selectedStats = newValue);
    }

    @FXML
    protected void onChooseCharacterForJSONButtonClick() {
        if (charactersForJSON == null) {
            charactersForJSON = new ArrayList<>();
        }

        if (character == null) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("No character!");
            alert.getDialogPane().setContentText("Please choose/create the character!");
            alert.showAndWait();
        } else if (charactersForJSON.contains(character)) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Character is present!");
            alert.getDialogPane().setContentText("Please choose/create another character/stats!");
            alert.showAndWait();
        } else {
            charactersForJSON.add(character);

            Alert.AlertType alertType1 = Alert.AlertType.INFORMATION;
            Alert alert1 = new Alert(alertType1,"");
            alert1.initModality(Modality.APPLICATION_MODAL);
            alert1.getDialogPane().setHeaderText("Choice done!");
            alert1.getDialogPane().setContentText("You've chosen the character!");
            alert1.showAndWait();
        }
    }

    @FXML
    protected void onSaveCharacterToJSONFileButtonClick() {
        try {
            ElementVisitor elementVisitor = new ElementVisitor();

            FileWriter fileWriter = new FileWriter("heroes.json");
            fileWriter.write("[");
            for (int i = 0; i < charactersForJSON.size(); i++) {
                fileWriter.write(formObject(charactersForJSON.get(i),elementVisitor).toJSONString());
                if (i != charactersForJSON.size() - 1) {
                    fileWriter.write(",");
                }
            }
            fileWriter.write("]");

            fileWriter.close();
            Alert.AlertType alertType = Alert.AlertType.INFORMATION;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("Character(s) has(ve) been written to file 'heroes.json'");
            alert.showAndWait();

        } catch (IOException e) {
            Alert.AlertType alertType = Alert.AlertType.ERROR;
            Alert alert = new Alert(alertType,"");
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.getDialogPane().setHeaderText("ERROR!");
            alert.getDialogPane().setContentText("You should create character"+
                    " before generation of character attributes!");
            alert.showAndWait();

            throw new RuntimeException("Impossible to create file 'heroes.json'");
        }
    }

    protected JSONObject formObject(Character character, ElementVisitor elementVisitor) {
        List<DataElement> dataElements = new LinkedList<>();

        dataElements.add(character);
        dataElements.add(character.getDndClass());
        dataElements.add(character.getRace());
        dataElements.add(character.getAttributes());

        JSONObject jsonObject = new JSONObject();

        for (DataElement element: dataElements) {
            jsonObject.putAll(element.accept(elementVisitor));
        }

        return jsonObject;
    }

    @FXML
    protected void onBardButtonClick() {
        dndClassField.setText("Bard");
    }

    @FXML
    protected void onFighterButtonClick() {
        dndClassField.setText("Fighter");
    }

    @FXML
    protected void onGnomeButtonClick() {
        dndRaceField.setText("Gnome");
    }

    @FXML
    protected void onHalfOrcButtonClick() {
        dndRaceField.setText("Half-Orc");
    }
}