import java.awt.*;
import java.awt.event.*;

public class QuizAppAWT extends Frame implements ActionListener {
    Label questionLabel;
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
    
    int[] correctAnswers = {0, 2, 1};
    int currentQuestion = 0;
    int score = 0;

    public QuizAppAWT() {

        setTitle("Quiz App");
        setSize(600, 300);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // Question label
        questionLabel = new Label();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setAlignment(Label.CENTER);
        add(questionLabel, BorderLayout.NORTH);

        choicePanel = new Panel();
        choicePanel.setLayout(new GridLayout(4, 1));
        choiceGroup = new CheckboxGroup();

        for (int i = 0; i < 4; i++) {
            choices[i] = new Checkbox("", choiceGroup, false);
            choices[i].setFont(new Font("Dialog", Font.PLAIN, 14));
            choices[i].setForeground(Color.BLUE);
            choicePanel.add(choices[i]);
        }
        add(choicePanel, BorderLayout.CENTER);

        nextButton = new Button("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 14));
        nextButton.setBackground(Color.LIGHT_GRAY);
        nextButton.addActionListener(this);
        add(nextButton, BorderLayout.SOUTH);

        loadQuestion();

        setVisible(true);
    }

    public void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion]);
            for (int i = 0; i < 4; i++) {
                choices[i].setLabel(options[currentQuestion][i]);
                choices[i].setState(false); // reset selection
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
    
            questionLabel.setText("Please select an answer before proceeding.");
        }
    }

    public static void main(String[] args) {
        new QuizAppAWT();
    }
}
