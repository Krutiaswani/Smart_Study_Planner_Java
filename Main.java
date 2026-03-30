import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Subject {
    String name;
    int difficulty;
    int deadlineDays;

    public Subject(String name, int difficulty, int deadlineDays) {
        this.name = name;
        this.difficulty = difficulty;
        this.deadlineDays = deadlineDays;
    }
}

public class Main {
    static ArrayList<Subject> subjects = new ArrayList<>();

    public static void main(String[] args) {

        JFrame frame = new JFrame("Smart Study Planner");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextField nameField = new JTextField(10);
        JTextField difficultyField = new JTextField(5);
        JTextField deadlineField = new JTextField(5);

        JButton addButton = new JButton("Add Subject");
        JTextArea outputArea = new JTextArea(15, 40);

        frame.add(new JLabel("Subject:"));
        frame.add(nameField);

        frame.add(new JLabel("Difficulty (1-5):"));
        frame.add(difficultyField);

        frame.add(new JLabel("Deadline (days):"));
        frame.add(deadlineField);

        frame.add(addButton);
        frame.add(new JScrollPane(outputArea));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int difficulty = Integer.parseInt(difficultyField.getText());
                int deadline = Integer.parseInt(deadlineField.getText());

                subjects.add(new Subject(name, difficulty, deadline));

                outputArea.setText("");

                outputArea.append("Study Plan:\n");

                int totalDifficulty = 0;
                for (Subject s : subjects) {
                    totalDifficulty += s.difficulty;
                }

                for (Subject s : subjects) {
                    int hours = (s.difficulty * 6) / totalDifficulty;
                    outputArea.append(s.name + ": " + hours + " hrs/day\n");
                }

                outputArea.append("\nAI Suggestions:\n");

                for (Subject s : subjects) {
                    if (s.difficulty >= 4) {
                        outputArea.append("Focus more on " + s.name + "\n");
                    }
                    if (s.deadlineDays <= 3) {
                        outputArea.append("Urgent: " + s.name + " deadline near!\n");
                    }
                }

                nameField.setText("");
                difficultyField.setText("");
                deadlineField.setText("");
            }
        });

        frame.setVisible(true);
    }
}