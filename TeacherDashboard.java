import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherDashboard extends JPanel {
    private List<QuizTopic> quizTopics;

    public TeacherDashboard() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);

        JPanel buttonPanel = createButtonPanel();
        add(buttonPanel, BorderLayout.CENTER);

        quizTopics = new ArrayList<>();
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton addTopicButton = new JButton("Add New Topic");
        JButton addQuestionButton = new JButton("Add Question to Topic");
        JButton viewQuestionsButton = new JButton("View Questions");
        JButton logoutButton = new JButton("Logout");
        addTopicButton.addActionListener(e -> addNewTopic());
        addQuestionButton.addActionListener(e -> addQuestionToTopic());
        viewQuestionsButton.addActionListener(e -> viewQuestions());
        logoutButton.addActionListener(e -> logout());
        buttonPanel.add(addTopicButton);
        buttonPanel.add(addQuestionButton);
        buttonPanel.add(viewQuestionsButton);
        buttonPanel.add(logoutButton);

        return buttonPanel;
    }

    private void addNewTopic() {
        String topicName = JOptionPane.showInputDialog("Enter the new topic name:");
        if (topicName == null || topicName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Topic name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            quizTopics.add(new QuizTopic(topicName));
            JOptionPane.showMessageDialog(this, "Topic '" + topicName + "' added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void addQuestionToTopic() {
        if (quizTopics.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No topics available. Please add a topic first.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] topicNames = quizTopics.stream().map(QuizTopic::getTopicName).toArray(String[]::new);
        String selectedTopic = (String) JOptionPane.showInputDialog(
                this,
                "Select a topic to add the question to:",
                "Select Topic",
                JOptionPane.PLAIN_MESSAGE,
                null,
                topicNames,
                topicNames[0]
        );

        if (selectedTopic != null) {
            QuizTopic topic = quizTopics.stream()
                    .filter(q -> q.getTopicName().equals(selectedTopic))
                    .findFirst()
                    .orElse(null);

            if (topic != null) {
                String questionText = JOptionPane.showInputDialog("Enter the question text:");
                String optionsText = JOptionPane.showInputDialog("Enter options (comma-separated):");
                String correctAnswer = JOptionPane.showInputDialog("Enter the correct answer (e.g., A, B, C):");

                if (questionText == null || optionsText == null || correctAnswer == null ||
                        questionText.trim().isEmpty() || optionsText.trim().isEmpty() || correctAnswer.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<String> options = Arrays.asList(optionsText.split(","));
                    Question question = new Question(questionText, options, correctAnswer);
                    topic.addQuestion(question);
                    JOptionPane.showMessageDialog(this, "Question added successfully to '" + selectedTopic + "'.", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }

    private void viewQuestions() {
        if (quizTopics.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No topics available.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] topicNames = quizTopics.stream().map(QuizTopic::getTopicName).toArray(String[]::new);
        String selectedTopic = (String) JOptionPane.showInputDialog(
                this,
                "Select a topic to view questions:",
                "Select Topic",
                JOptionPane.PLAIN_MESSAGE,
                null,
                topicNames,
                topicNames[0]
        );

        if (selectedTopic != null) {
            QuizTopic topic = quizTopics.stream()
                    .filter(q -> q.getTopicName().equals(selectedTopic))
                    .findFirst()
                    .orElse(null);

            if (topic != null) {
                StringBuilder questionsDisplay = new StringBuilder("Questions for " + selectedTopic + ":\n");

                for (Question question : topic.getQuestions()) {
                    questionsDisplay.append(question.toString()).append("\n");
                }

                JOptionPane.showMessageDialog(this, questionsDisplay.toString(), "Questions", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teacher Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new TeacherDashboard());
        frame.setVisible(true);
    }
}
