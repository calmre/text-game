package com;

import javax.swing.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class GameScreen{
	
    JButton choice1, choice2, choice3, choice4;
    JPanel GameScreen;
    GameLauncher gamelauncher;
    JsonNode gameData;
    JPanel mainTextPanel = new JPanel();
    JPanel choiceButtonPanel = new JPanel();
    JPanel playerPanel = new JPanel();
    JPanel imagePanel = new JPanel();
    JLabel nameLabel, levelLabel, hpLabel, manaLabel;
    JFrame window = new JFrame();
    JButton saveButton, exitButton, inventoryButton;
    JTextArea mainTextArea = new JTextArea();
    int playerHP, playerMana, playerLevel;
    String playerName;
    JPanel optionsPanel = new JPanel();

    Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
    Font choicesFont = new Font("Times New Roman", Font.PLAIN, 20);
    
    
    //image scaling
    ImageIcon bagIcon = new ImageIcon("bag2.png");  
    Image scaledImageBag = bagIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    ImageIcon scaledIconBag = new ImageIcon(scaledImageBag);
    
    ImageIcon saveIcon = new ImageIcon("save2.png"); 
    Image scaledImageSave = saveIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    ImageIcon scaledIconSave = new ImageIcon(scaledImageSave);
    
    ImageIcon exitIcon = new ImageIcon("exit2.png");
    Image scaledImageExit =  exitIcon.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
    ImageIcon scaledIconExit = new ImageIcon(scaledImageExit);

		
	public GameScreen(GameLauncher launcher) {
		this.gamelauncher = launcher;
		
		GameScreen = new JPanel();
		GameScreen.setBounds(0,0,1280,720);
		GameScreen.setBackground(Color.black);
		GameScreen.setLayout(null);
	
	        
        imagePanel.setBounds(400, 100, 425, 250);
        imagePanel.setBackground(Color.blue);
        GameScreen.add(imagePanel);
        
        
        ImageIcon originalImage1 = new ImageIcon("test1.jpg"); 
        Image scaledImage = originalImage1.getImage().getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
        JLabel image1Label = new JLabel(new ImageIcon(scaledImage));
        imagePanel.add(image1Label);
        
        mainTextPanel.setBounds(250, 360, 800, 175);
        mainTextPanel.setBackground(Color.pink);
        GameScreen.add(mainTextPanel);
        
        
		        mainTextArea.setBounds(100, 100, 800, 100);
		        mainTextArea.setBackground(Color.black);
		        mainTextArea.setForeground(Color.white);
		        mainTextArea.setFont(normalFont);
		        mainTextArea.setWrapStyleWord(true);
		        mainTextArea.setLineWrap(true);
		        mainTextPanel.add(mainTextArea);

        choiceButtonPanel.setBounds(300, 550, 600, 75);
        choiceButtonPanel.setBackground(Color.black);
        choiceButtonPanel.setLayout(new GridLayout(1, 3));
        GameScreen.add(choiceButtonPanel);

		        choice1 = new JButton();
		        styleButton(choice1);
		        choiceButtonPanel.add(choice1);
		
		        choice2 = new JButton();
		        styleButton(choice2);
		        choiceButtonPanel.add(choice2);
		
		        choice3 = new JButton();
		        styleButton(choice3);
		        choiceButtonPanel.add(choice3);
		        
		        

        
        playerPanel.setBounds(50, 15, 300, 75);
        playerPanel.setBackground(Color.yellow);
        playerPanel.setLayout(new GridLayout(2, 2)); 
        GameScreen.add(playerPanel);

		        nameLabel = new JLabel(playerName);
		        styleLabel(nameLabel);
		        playerPanel.add(nameLabel);
		
		        hpLabel = new JLabel("HP: " + playerHP);
		        styleLabel(hpLabel, Color.red);
		        playerPanel.add(hpLabel);
		
		        levelLabel = new JLabel("lvl " + playerLevel);
		        styleLabel(levelLabel, Color.green);
		        playerPanel.add(levelLabel);
		
		        manaLabel = new JLabel("Mana: " + playerMana);
		        styleLabel(manaLabel, Color.cyan);
		        playerPanel.add(manaLabel);

        
        optionsPanel.setBounds(820, 15, 350, 75);
        optionsPanel.setBackground(Color.orange);
        optionsPanel.setLayout(new GridLayout(1, 3));
        GameScreen.add(optionsPanel);

        
		        inventoryButton = new JButton("(I)", scaledIconBag);
		        inventoryButton.setVerticalTextPosition(JButton.BOTTOM);
		        inventoryButton.setHorizontalTextPosition(JButton.CENTER);
		        styleButton(inventoryButton);
		        inventoryButton.setBorderPainted(false);
		        optionsPanel.add(inventoryButton);
		
		        saveButton = new JButton("(S)", scaledIconSave);
		        saveButton.setVerticalTextPosition(JButton.BOTTOM);
		        saveButton.setHorizontalTextPosition(JButton.CENTER);
		        styleButton(saveButton);
		        saveButton.setBorderPainted(false);
		        optionsPanel.add(saveButton);
		
		        exitButton = new JButton("(E)xit", scaledIconExit);
		        exitButton.setVerticalTextPosition(JButton.BOTTOM);
		        exitButton.setHorizontalTextPosition(JButton.CENTER);
		        styleButton(exitButton);
		        exitButton.setBorderPainted(false);
		        optionsPanel.add(exitButton);

        playerSetup();
        SceneHandler SH = new SceneHandler(this);
	}
		
        
    public void playerSetup() {
        
        playerHP = 100;
        playerMana = 20;
        playerName = "SEAN_123";
        playerLevel = 1;  //default level
        nameLabel.setText(playerName);
        hpLabel.setText("HP: " + playerHP);
        levelLabel.setText("lvl " + playerLevel);
        manaLabel.setText("Mana: " + playerMana);
    }

    
    private void styleButton(JButton button) {
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFont(choicesFont);
        button.setFocusable(false);
    }

  
    private void styleLabel(JLabel label) {
        label.setFont(choicesFont);
        label.setForeground(Color.white);
    }

    
    private void styleLabel(JLabel label, Color color) {
        label.setFont(choicesFont);
        label.setForeground(color);
    }    
		
	public JTextArea getMainTextArea() {
		return mainTextArea;
	}
	public JButton[] getButtons() {
		JButton[] buttons = {choice1, choice2, choice3};
		return buttons;
	}

	public JPanel getGameScreenPanel() {
		return GameScreen;
	}
	
	
}
