package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu implements KeyListener {
	
    JLabel start, achievements, help, settings;
    JLabel[] menuOptions;
    int selectedOption = 0; 
    Timer flashTimer;
    boolean isYellow = true; 
	
	JPanel MainMenu;
	JPanel startButtonPanel;
	JButton startButton;
	GameLauncher gamelauncher;
	
	public MainMenu(GameLauncher launcher){
		this.gamelauncher = launcher;
		
		
		MainMenu = new JPanel();
		MainMenu.setBounds(0,0,1280,720);
		MainMenu.setBackground(Color.BLUE);
		MainMenu.setLayout(new BorderLayout());
		
        JPanel title = new JPanel();
        JPanel options = new JPanel();
        JPanel copyright = new JPanel();
        
        title.setBackground(Color.black);
        options.setBackground(Color.black);
        copyright.setBackground(Color.black);
        

        title.setPreferredSize(new Dimension(100, 200));
        options.setPreferredSize(new Dimension(100, 100));
        copyright.setPreferredSize(new Dimension(100, 100));


        title.setLayout(new GridBagLayout());
        options.setLayout(new GridBagLayout());
        copyright.setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 10, 0, 10);


        JLabel Simpaul = new JLabel("A O O P");
        Simpaul.setFont(new Font("RetroPix Regular", Font.PLAIN, 100));
        Simpaul.setForeground(new Color(0xFFDE59));
        gbc.gridx = 0;
        gbc.gridy = 0;
        title.add(Simpaul, gbc);


        JLabel TextRPG = new JLabel("TEXT ROLE PLAYING GAME");
        TextRPG.setFont(new Font("RetroPix Regular", Font.PLAIN, 25));
        TextRPG.setForeground(new Color(0xFFDE59));
        gbc.gridx = 0;
        gbc.gridy = 1;
        title.add(TextRPG, gbc);


        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;


        // Menu options as JLabels
        start = new JLabel("START");
        start.setFont(new Font("RetroPix Regular", Font.PLAIN, 23));
        start.setForeground(Color.yellow); // Start is selected initially
        gbc.gridx = 0;
        gbc.gridy = 0;
        options.add(start, gbc);


        achievements = new JLabel("ACHIEVEMENTS");
        achievements.setFont(new Font("RetroPix Regular", Font.PLAIN, 23));
        achievements.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 2;
        options.add(achievements, gbc);


        help = new JLabel("HELP");
        help.setFont(new Font("RetroPix Regular", Font.PLAIN, 23));
        help.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 3;
        options.add(help, gbc);


        settings = new JLabel("SETTINGS");
        settings.setFont(new Font("RetroPix Regular", Font.PLAIN, 23));
        settings.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 4;
        options.add(settings, gbc);


        // Store all options in an array for easy access
        menuOptions = new JLabel[]{start, achievements, help, settings};


        JLabel Credits = new JLabel("Copyright 2024 Paul 'n Friends");
        Credits.setFont(new Font("VT323", Font.PLAIN, 20));
        Credits.setForeground(Color.white);
        gbc.gridx = 0;
        gbc.gridy = 0;
        copyright.add(Credits, gbc);


        MainMenu.add(title, BorderLayout.NORTH);
        MainMenu.add(options, BorderLayout.CENTER);
        MainMenu.add(copyright, BorderLayout.SOUTH);
        MainMenu.setVisible(true);
        MainMenu.addKeyListener(this);
        MainMenu.setFocusable(true);
        MainMenu.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                MainMenu.requestFocusInWindow();
            }
        });

        
        
        flashTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSelectionColor();
            }
        });
        flashTimer.start(); 
	}
	
	private void toggleSelectionColor() {
        JLabel selectedLabel = menuOptions[selectedOption];
        if (isYellow) {
            selectedLabel.setForeground(Color.white); // Switch to white
        } else {
            selectedLabel.setForeground(Color.yellow); // Switch to yellow
        }
        isYellow = !isYellow; // Toggle the flag
    }
	
	
	private void updateSelection() {
        for (int i = 0; i < menuOptions.length; i++) {
            if (i != selectedOption) {
                menuOptions[i].setForeground(Color.white); // Reset to white
            }
        }
        isYellow = true; // Reset the flashing flag for the new selection
        toggleSelectionColor();
    }

	
	
	
	public JPanel getMainMenuPanel() {
		return MainMenu;
	}



	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();


        // Use W or UP arrow key to move up the menu
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            if (selectedOption > 0) {
                selectedOption--;
            } else {
                selectedOption = menuOptions.length - 1; // Wrap around to the last option
            }
        }


        // Use S or DOWN arrow key to move down the menu
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            if (selectedOption < menuOptions.length - 1) {
                selectedOption++;
            } else {
                selectedOption = 0; // Wrap around to the first option
            }
        }


        // Trigger action on ENTER key press
        if (keyCode == KeyEvent.VK_ENTER) {
            executeAction(selectedOption);
        }


        // Update the selection highlight
        updateSelection();
    }
	
	private void executeAction(int selectedOption) {
        switch (selectedOption) {
            case 0: gamelauncher.showGameScreen();
                break;
            case 1:
                System.out.println("Achievements!");
                // Achievements logic here
                break;
            case 2:
                System.out.println("Help!");
                // Help logic here
                break;
            case 3:
                System.out.println("Settings!");
                // Settings logic here
                break;
            default:
                break;
        }
    }



	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
