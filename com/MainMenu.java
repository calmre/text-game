package com;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class MainMenu {
    JLabel start, achievements, help, settings;
    JLabel[] menuOptions;
    int selectedOption = 0;
    Timer flashTimer;
    boolean isYellow = true;

    JPanel MainMenu;
    GameLauncher gamelauncher;

    public MainMenu(GameLauncher launcher) {
        this.gamelauncher = launcher;

        MainMenu = new JPanel();
        MainMenu.setBounds(0, 0, 1280, 720);
        MainMenu.setBackground(Color.BLUE);
        MainMenu.setLayout(new BorderLayout());
        MainMenu.setVisible(true);

        // Panels for layout
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

        // Flashing effect
        flashTimer = new Timer(150, e -> toggleSelectionColor());
        flashTimer.start();

        // Add key bindings
        setupKeyBindings();
    }

    private void setupKeyBindings() {
        // InputMap and ActionMap for Key Bindings
        InputMap inputMap = MainMenu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = MainMenu.getActionMap();

        // Move up
        inputMap.put(KeyStroke.getKeyStroke("UP"), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke("W"), "moveUp");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveUp();
            }
        });

        // Move down
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke("S"), "moveDown");
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
            }
        });

        // Select
        inputMap.put(KeyStroke.getKeyStroke("ENTER"), "select");
        actionMap.put("select", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeAction(selectedOption);
            }
        });
    }

    private void moveUp() {
        if (selectedOption > 0) {
            selectedOption--;
        } else {
            selectedOption = menuOptions.length - 1; // Wrap around to the last option
        }
        updateSelection();
    }

    private void moveDown() {
        if (selectedOption < menuOptions.length - 1) {
            selectedOption++;
        } else {
            selectedOption = 0; // Wrap around to the first option
        }
        updateSelection();
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

    private void executeAction(int selectedOption) {
        switch (selectedOption) {
            case 0:
                gamelauncher.showGameScreen();
                break;
            case 1:
                System.out.println("Achievements!");
                break;
            case 2:
                System.out.println("Help!");
                break;
            case 3:
                System.out.println("Settings!");
                break;
            default:
                break;
        }
    }

    public JPanel getMainMenuPanel() {
        return MainMenu;
    }
}
