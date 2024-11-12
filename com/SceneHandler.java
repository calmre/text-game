package com;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JTextArea;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SceneHandler{
	
	JsonNode gameData;
	private GameScreen gamescreen;
	
    public SceneHandler(GameScreen gamescreen){
    	this.gamescreen = gamescreen;
    	loadGameData();
        displayScene("scene1");

    }

	
	void loadGameData() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            gameData = objectMapper.readTree(new File("C:\\Users\\Christine Bellosillo\\eclipse-workspace\\TheGmae\\src\\story.json")); // Loads the JSON file
        } catch (IOException e) {
        	System.err.println("di ito matrack");
        
        }
    }
	public void displayScene(String sceneId) {
	    JTextArea mainTextArea = gamescreen.getMainTextArea();
	    JButton[] buttons = gamescreen.getButtons();

	    // Fetch the image path for the current scene from the JSON
	    String imagePath = gameData.path("scenes").path(sceneId).path("bg").asText();
	    
	    // Call the GameScreen method to update the image
	    gamescreen.setImage(imagePath);  // Update the image according to the scene

	    JsonNode scene = gameData.get("scenes").get(sceneId);
	    if (scene == null) {
	        mainTextArea.setText("Scene not found: " + sceneId);
	        return;
	    }
	    String sceneText = scene.get("text").asText();
	    JsonNode choices = scene.get("choices");

	    // Set the main speech (scene description)
	    mainTextArea.setText(sceneText); 

	    // Iterate over each choice and update the buttons
	    for (int i = 0; i < buttons.length; i++) {
	        if (i < choices.size()) {
	            String choiceText = choices.get(i).get("text").asText();
	            String nextScene = choices.get(i).get("nextScene").asText();

	            buttons[i].setText(choiceText);
	            buttons[i].setEnabled(true);

	            // Clear any existing action listeners
	            for (ActionListener al : buttons[i].getActionListeners()) {
	                buttons[i].removeActionListener(al);
	            }

	            // Add new action listener for the button
	            buttons[i].addActionListener(event -> displayScene(nextScene));
	        } else {
	            buttons[i].setText("");
	            buttons[i].setEnabled(false);
	        }
	    }
	}


}
