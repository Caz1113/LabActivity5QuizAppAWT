import java.awt.*;
import java.awt.event.*;

public class LabActivity5QuizAppAWT extends Frame implements ActionListener {
    Label questionLabel;
    Label messageLabel;
    Checkbox[] choices = new Checkbox[4];
    CheckboxGroup choiceGroup;
    Button nextButton;
    Panel choicePanel;

    String[] questions = {
        "1. What does GUI stands for?",
        "2. What does AWT stands for?",
        "3. What does Lightweight do?"
    };

    String[][] options = {
        {"Graphic Users Interface", "Graphical User Interface", "Graphic User Interface", "Graphics Users Interfaces"},
        {"Abstract Window Toolkit", "Abstract Windows Tool", "Abstruct Windows Tools", "Abstract Window Tools"},
        {"Generates Event", "Doesn't rely on native GUI components", "Boxing", "Can Change how it looks"}
    };

    int[] correctAnswers = {1, 0, 1};
    int currentQuestion = 0;
    int score = 0;

    public LabActivity5QuizAppAWT() {
        setTitle("Quiz App");
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        questionLabel = new Label("", Label.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(questionLabel, BorderLayout.NORTH);

        choicePanel = new Panel(new GridLayout(2, 2, 15, 10));
        choiceGroup = new CheckboxGroup();
        for (int i = 0; i < 4; i++) {
            choices[i] = new Checkbox("", choiceGroup, false);
            choices[i].setFont(new Font("Dialog", Font.PLAIN, 14));
            choices[i].setForeground(Color.BLUE);
            choicePanel.add(choices[i]);
        }
        add(choicePanel, BorderLayout.CENTER);

        Panel bottomPanel = new Panel(new BorderLayout());

        messageLabel = new Label(" ", Label.CENTER);
        messageLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
        messageLabel.setForeground(Color.RED);
        bottomPanel.add(messageLabel, BorderLayout.NORTH);

        nextButton = new Button("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(Color.LIGHT_GRAY);
        bottomPanel.add(nextButton, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(this);
        loadQuestion();
        setVisible(true);
    }

    public void loadQuestion() {
        messageLabel.setText(" ");
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion]);
            for (int i = 0; i < 4; i++) {
                choices[i].setLabel(options[currentQuestion][i]);
                choices[i].setState(false);
            }
        } else {
            showResult();
        }
    }

        public void showResult() {
        questionLabel.setText("You got " + score + " out of " + questions.length + " correct!");
        nextButton.setEnabled(false);
        for (Checkbox cb : choices) {
            cb.setEnabled(false);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Checkbox selected = choiceGroup.getSelectedCheckbox();
        if (selected != null) {
            String selectedText = selected.getLabel();
            String correctText = options[currentQuestion][correctAnswers[currentQuestion]];
            if (selectedText.equals(correctText)) {
                score++;
            }
            currentQuestion++;
            loadQuestion();
        } else {
            messageLabel.setText("Please select an answer.");
        }
    }

    public static void main(String[] args) {
        new LabActivity5QuizAppAWT();
    }
}
