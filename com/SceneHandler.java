package com;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SceneHandler{
    JTextArea mainTextArea; 
    JButton[] buttons;
    JsonNode scene;
    String sceneId;
    JsonNode choices;
    Timer timer;
    String gameWeapon;
    

	JsonNode gameData;
	private GameScreen gamescreen;
	
    public SceneHandler(GameScreen gamescreen){
    	this.gamescreen = gamescreen;
    	loadGameData();
        displayScene("scene1");
    }

	
    void loadGameData() {
        try (InputStream inputStream = getClass().getResourceAsStream("/story.json")) {
            if (inputStream == null) {
                System.err.println("File not found: story.json");
                return;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            gameData = objectMapper.readTree(inputStream);
        } catch (IOException e) {
            System.err.println("Error loading game data: " + e.getMessage());
        }
    }
    
    
	public void displayScene(String sceneId) {
		if (gameWeapon != null ){
			setWeaponIcon();
		}
	    mainTextArea = gamescreen.getMainTextArea();
	    buttons = gamescreen.getButtons();

	    // Fetch the image path for the current scene from the JSON
	    String imagePath = gameData.path("scenes").path(sceneId).path("bg").asText();
	    
	    // Call the GameScreen method to update the image
	    gamescreen.setImage(imagePath);  // Update the image according to the scene
	    
	    

	    scene = gameData.get("scenes").get(sceneId);
	    if (scene == null) {
	        mainTextArea.setText("Scene not found: " + sceneId);
	        return;
	    }
	    
	    
	    String sceneText = scene.get("text").asText();

	    // Set the main speech (scene description)
	    
	    displayTextWithTypingEffect(sceneText);
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(false);
			buttons[i].setText(null);
		}
		
	}
	
	public void updateButtons() {
		choices = scene.get("choices");
		for (int i = 0; i < buttons.length; i++) {
	        if (i < choices.size()) {
	            String choiceText = choices.get(i).get("text").asText();
	    	  //  String weaponPath = choices.get(i).get("path");
	            
	            String nextScene = choices.get(i).get("nextScene").asText();
	            buttons[i].setText(choiceText);
	            buttons[i].setEnabled(true);
	            
	            String weapon = choices.get(i).has("weapon") ? choices.get(i).get("weapon").asText() : null;

	            
	                       
	 

	            // Clear any existing action listeners
	            for (ActionListener al : buttons[i].getActionListeners()) {
	                buttons[i].removeActionListener(al);
	            }

	            // Add new action listener for the button
	            buttons[i].addActionListener(event -> {
	                if (weapon != null) {
	                    gameWeapon = weapon;  // Assign weapon only if it's non-null
	                    
	                }
	                displayScene(nextScene);  // Continue to the next scene regardless of weapon existence
	            });
	        } else {
	            buttons[i].setText("...");
	            buttons[i].setEnabled(false);
	        }
	   }
	}


	
	public void displayTextWithTypingEffect(String fullText) {
		
        // Clear the existing text
        JPanel mainTextPanel = gamescreen.getMainTextPanel();
        JPanel GameScreen = gamescreen.getGameScreenPanel();

        mainTextArea.setText(null);

        timer = new Timer();
        TimerTask task = new TimerTask() {
            int index = 0;

            @Override
            public void run() {
                // Add the next character
                if (index < fullText.length()) {
                    mainTextArea.append(String.valueOf(fullText.charAt(index)));
                    index++;
                } else {
                    // Stop the timer once the full text is displayed
                    timer.cancel();
                	updateButtons();

                    
                    
                }
            }
        };
        timer.schedule(task, 0, 25);
        GameScreen.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		System.out.println("gago");
        		if(timer != null) {
        			timer.cancel();
        		}
        		displayText(fullText);
            	updateButtons();

        
        	}
        });
    }
	
	public void displayText(String fullText) {
		if(timer !=null) {
			timer.cancel();
		}
		mainTextArea.setText(fullText);
	}
	
	public void setWeaponIcon() {
		JPanel weaponPanel = gamescreen.getWeaponPanel();
		
		String weaponPath = "";
		if(gameWeapon.equals("sword")) {
			weaponPath = "/resources/ui/sword.png";
		}
		else if(gameWeapon.equals("slingshot")) {
			weaponPath = "/resources/ui/slingshot.png";
		}
		ImageIcon weaponImage = new ImageIcon(getClass().getResource(weaponPath));
		Image scaledImage = weaponImage.getImage().getScaledInstance(weaponPanel.getWidth(), weaponPanel.getHeight(), Image.SCALE_SMOOTH);
		weaponPanel.removeAll();
        JLabel image1Label = new JLabel(new ImageIcon(scaledImage));
        weaponPanel.add(image1Label);
        weaponPanel.revalidate();
        weaponPanel.repaint();
	}


}

