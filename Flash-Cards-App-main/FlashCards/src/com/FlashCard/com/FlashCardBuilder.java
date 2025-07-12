package com.FlashCard.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FlashCardBuilder {

    private JTextArea question;
    private JTextArea answer;

    private ArrayList<FlashCard> cardList;
    private JFrame frame;

    public FlashCardBuilder() {
        frame = new JFrame("Flash Card");
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

        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);
        Font greatFont = new Font("Helvetica Neu", Font.BOLD, 21);
        JLabel qLabel = new JLabel("Question");
        question = new JTextArea(6, 20);

        // wrap the line to be in case words are too long to fit
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(greatFont);

        // JScrollPane for question area
        JScrollPane qJScrollPane = new JScrollPane(question);
        qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // answer area
        JLabel aLabel = new JLabel("Answer");
        answer = new JTextArea(6, 20);

        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(greatFont);

        // JScrollPane for answer area
        JScrollPane aJScrollPane = new JScrollPane(answer);
        aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton("Next Card");

        cardList = new ArrayList<FlashCard>();

        //MenuBar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveItem = new JMenuItem("Save");

        //add event listeners

        newMenuItem.addActionListener(new newMenuItemListener());
        saveItem.addActionListener(new saveMenuItemListener());

        frame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        fileMenu.add(saveItem);
        fileMenu.add(newMenuItem);


        // Add components to Main panel
        mainPanel.add(qLabel);
        mainPanel.add(qJScrollPane);
        mainPanel.add(aLabel);
        mainPanel.add(aJScrollPane);
        mainPanel.add(nextButton);

        nextButton.addActionListener(new NextCardListener());



        // Add to frame
        frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
        frame.setVisible(true);
        frame.setSize(500, 600);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardBuilder();
            }
        });
    }
    class NextCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Next Button is cicked!");
            FlashCard card = new FlashCard(question.getText(),answer.getText());
            cardList.add(card);
            System.out.println(card.getQuestion()+" " + card.getAnswer());
            clearCard();
        }
    }

    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }

    class newMenuItemListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Menu bar :)");
            clearCard();

        }
    }
    class saveMenuItemListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Save it :)");
            FlashCard card = new  FlashCard(question.getText(), answer.getText());
            cardList.add(card);
            //Create  a file dialog with file chooser
            JFileChooser fileSave = new JFileChooser();
            fileSave.showOpenDialog(frame);
            saveFile(fileSave.getSelectedFile());

        }
    }

    private void saveFile(File selectedFile) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

            Iterator<FlashCard> cardIterator = cardList.iterator();

            while(cardIterator.hasNext()){
                // ensure that everything coming inside iterator is as expected
                FlashCard card = (FlashCard) cardIterator.next();
                writer.write(card.getQuestion()+"/");
                writer.write(card.getAnswer() + "\n");

            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
