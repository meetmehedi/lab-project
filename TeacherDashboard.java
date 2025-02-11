import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;.
class TeacherDashboard extends JPanel {
    public TeacherDashboard(Teacher teacher) {
        setLayout(new BorderLayout()); 
        setBackground(new Color(0, 105, 148));
        setupHeader(); 
        setupButtons(teacher); // Sets up action buttons.
    }
    // Creates the header label for the dashboard.
    private void setupHeader() {
        JLabel label = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);
    }
    // Adds action buttons for teacher functionalities.
    private void setupButtons(Teacher teacher) {
        JPanel buttonPanel = createButtonPanel();
        buttonPanel.add(createButton("Add Topic", e -> handleAddTopic(teacher)));
        buttonPanel.add(createButton("Add Question", e -> handleAddQuestion(teacher)));
        buttonPanel.add(createButton("View Topics", e -> handleViewTopics(teacher)));
        buttonPanel.add(createButton("View Quiz Attempts", e -> handleViewQuizAttempts(teacher)));
        buttonPanel.add(createButton("Teacher Implements", e -> handleTeacherImplement()));
        add(buttonPanel, BorderLayout.CENTER);
        add(createBackPanel(), BorderLayout.SOUTH);
    }
    // Creates a panel for holding buttons with grid layout.
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new GridLayout(5, 1, 10, 10)); // 5 buttons in one column.
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        return panel;
    }
    // Creates a styled JButton with an action listener.
    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.setForeground(new Color(0, 105, 148));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.addActionListener(action);
        return button;
    }
    // Creates a back button panel to navigate back to the login screen.
    private JPanel createBackPanel() {
        JPanel backPanel = new JPanel();
        backPanel.setOpaque(false);
        backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        backPanel.add(createButton("Back", e -> Main.showLoginWindow()));
        return backPanel;
    }
    // Handles adding a new quiz topic.
    private void handleAddTopic(Teacher teacher) {
        String topicName = JOptionPane.showInputDialog("Enter Topic Name:");
        if (topicName != null && !topicName.trim().isEmpty()) {
            teacher.addQuizTopic(topicName.trim());
            JOptionPane.showMessageDialog(null, "Topic added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Topic name cannot be empty.");
        }
    }
    // Handles adding a new question to a selected topic.
    private void handleAddQuestion(Teacher teacher) {
        QuizTopic topic = selectTopic(teacher);
        if (topic == null) return;
        String questionText = JOptionPane.showInputDialog("Enter the question:");
        if (questionText == null || questionText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Question cannot be empty.");
            return;
        }
        String[] options = new String[4];
        for (int i = 0; i < 4; i++) {
            options[i] = JOptionPane.showInputDialog("Enter option " + (i + 1) + ":");
            if (options[i] == null || options[i].trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Option cannot be empty.");
                return;
            }
        }
        String correctAnswer = (String) JOptionPane.showInputDialog(
                null, "Select the correct answer:", "Correct Answer",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]
        );
        if (correctAnswer != null) {
            topic.addQuestion(questionText.trim(), options, correctAnswer.trim());
            JOptionPane.showMessageDialog(null, "Question added successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "You must select a correct answer.");
        }
    }
    // Displays a list of quiz topics.
    private void handleViewTopics(Teacher teacher) {
        if (teacher.getQuizTopics().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No topics available.");
            return;
        }
        StringBuilder topicsList = new StringBuilder("Quiz Topics:\n");
        teacher.getQuizTopics().forEach(topic -> topicsList.append("- ").append(topic.getTopicName()).append("\n"));

        JOptionPane.showMessageDialog(null, topicsList.toString());
    }
    // Displays quiz attempts made by students.
    private void handleViewQuizAttempts(Teacher teacher) {
        if (teacher.getQuizTopics().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No topics available to view attempts.");
            return;
        }
        StringBuilder result = new StringBuilder("Quiz Attempts:\n");
        for (QuizTopic topic : teacher.getQuizTopics()) {
            result.append("\nTopic: ").append(topic.getTopicName()).append("\n");
            // Retrieve and sort students by highest score.
            List<Student> students = Main.USER_LIST.stream()
                    .filter(user -> user instanceof Student)
                    .map(user -> (Student) user)
                    .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed())
                    .toList();
            if (students.isEmpty()) {
                result.append("  No attempts yet.\n");
                continue;
            }
            for (Student student : students) {
                List<Integer> scores = student.getScores();
                if (scores != null && !scores.isEmpty()) {
                    result.append("  ").append(student.getUsername())
                            .append(" - Scores: ").append(scores).append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }
    // Displays the ranking of teachers based on total quizzes created.
    private void handleTeacherImplement() {
        List<Teacher> teachers = Main.USER_LIST.stream()
                .filter(user -> user instanceof Teacher)
                .map(user -> (Teacher) user)
                .sorted(Comparator.comparingInt(Teacher::getTotalQuizzes).reversed())
                .toList();
        StringBuilder teacherImplement = new StringBuilder("Teacher Implements:\n");
        for (Teacher t : teachers) {
            teacherImplement.append(t.getUsername()).append(": ").append(t.getTotalQuizzes()).append(" quizzes\n");
        }
        JOptionPane.showMessageDialog(null, teacherImplement.toString());
    }
    // Prompts the teacher to select an existing quiz topic.
    private QuizTopic selectTopic(Teacher teacher) {
        if (teacher.getQuizTopics().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No topics available. Add a topic first.");
            return null;
        }
        String[] topics = teacher.getQuizTopics().stream()
                .map(QuizTopic::getTopicName)
                .toArray(String[]::new);
        String selectedTopic = (String) JOptionPane.showInputDialog(
                null, "Select a topic:", "Topic Selection",
                JOptionPane.QUESTION_MESSAGE, null, topics, topics[0]
        );
        return teacher.getQuizTopics().stream()
                .filter(topic -> topic.getTopicName().equals(selectedTopic))
                .findFirst()
                .orElse(null);
    }
}
