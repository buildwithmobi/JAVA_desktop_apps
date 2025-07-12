package com.FlashCard.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class FlashCardPlayer {
    private JTextArea display;
    private JTextArea answer;
    private ArrayList<FlashCard> cardList;
    private Iterator cardIterator;
    private FlashCard currentCard;
    private int currentCardIndex;
    private  JFrame frame;
    private boolean isShowAnswer;

    private JButton showAnswer;
    public FlashCardPlayer(){
        // build UI
        frame = new JFrame("Flash Card Player");
        JPanel mainPanel = new JPanel();
        Font mFont = new Font("Helvetica Neue ", Font.BOLD, 22);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        try {
            // Set Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            // Customize Nimbus properties for dark theme
            UIManager.put("control", new Color(128, 128, 128)); // Control color
            UIManager.put("nimbusBase", new Color(18, 30, 49)); // Base color
            UIManager.put("nimbusFocus", new Color(115, 164, 209)); // Focus color
            UIManager.put("nimbusLightBackground", new Color(18, 30, 49)); // Light background color
            UIManager.put("nimbusSelectionBackground", new Color(115, 164, 209)); // Selection background color
            UIManager.put("text", new Color(230, 230, 230)); // Text color
        } catch (Exception e) {
            e.printStackTrace();
        }

        display = new JTextArea(10,20);
        display.setFont(mFont);

        JScrollPane qScrollPane =  new JScrollPane(display);
        qScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        showAnswer = new JButton("Show Answer");
        mainPanel.add(qScrollPane);
        mainPanel.add(showAnswer);
        showAnswer.addActionListener(new NextCardListener());

        // Add menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
        loadMenuItem.addActionListener(new OpenMenuListener());

        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);

        frame.getContentPane().add(BorderLayout.CENTER,mainPanel);
        frame.setSize(640,500);
        frame.setVisible(true);

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                    new FlashCardPlayer();
            }
        });
    }

    class NextCardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (isShowAnswer) {
                display.setText(currentCard.getAnswer());
                showAnswer.setText("NextCard");
                isShowAnswer = false;
            } else {
                if (cardIterator.hasNext()) {
                    showNextCard();
                } else {
                    display.setText("That was last Card");
                    showAnswer.setEnabled(false);
                }
            }
        }

        private void showNextCard() {
            currentCard = (FlashCard) cardIterator.next();
            display.setText(currentCard.getQuestion());
            showAnswer.setText("Show Answer");
            isShowAnswer = true;
        }
    }

        class OpenMenuListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.showOpenDialog(frame);
                loadFile(fileOpen.getSelectedFile());
            }
        }

        private void loadFile(File selectedFile) {

            cardList = new ArrayList<FlashCard>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    makeCard(line);

                }
            } catch (Exception e) {
                throw new RuntimeException("Error is : " + e);
            }
            //show first card
            cardIterator = cardList.iterator();
            showNext();

        }

        private void showNext() {
            currentCard = (FlashCard) cardIterator.next();
            display.setText(currentCard.getQuestion());
            showAnswer.setText("Show Answer");
            isShowAnswer = true;
        }

        private void makeCard(String lineToParse) {

            StringTokenizer result = new StringTokenizer(lineToParse, "/");
            if (result.hasMoreTokens()) {
                FlashCard card = new FlashCard(result.nextToken(), result.nextToken());
                cardList.add(card);
                System.out.println("Card is Made");
            }

        /*

        String[] result =  lineToParse.split("/"); // [question, answer]
        FlashCard card = new FlashCard(result[0],result[1]);
        cardList.add(card);
        System.out.println("Made a card :)");


         */
        }

    }
