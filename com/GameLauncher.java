package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLauncher {
	
	JFrame gameWindow;
	Container con;
	
	public static void main (String [] args) {
		new GameLauncher();
	}
	
	public GameLauncher() {
		
        
		gameWindow = new JFrame();
		gameWindow.setSize(1280,720);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.getContentPane().setBackground(Color.BLACK);
		gameWindow.setLayout(null);
		gameWindow.setVisible(true);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setResizable(false);
		con = gameWindow.getContentPane();
		
		showMainMenu();
		
		
	}
	public void showMainMenu() {
        MainMenu mainMenu = new MainMenu(this);
        con.removeAll(); 
        con.add(mainMenu.getMainMenuPanel()); 
        gameWindow.revalidate();
        gameWindow.repaint();
    }

    public void showGameScreen() {
        GameScreen gameScreen = new GameScreen(this);
        con.removeAll(); 
        con.add(gameScreen.getGameScreenPanel());
        con.add(gameScreen.getPlayerPanel());
        gameWindow.revalidate();
        gameWindow.repaint();
    }
	
	

}
