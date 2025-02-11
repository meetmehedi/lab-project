import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
class StudentDashboard extends JPanel {
    public StudentDashboard(Student student) {
        setLayout(new BorderLayout());
        setBackground(new Color(0, 105, 148));
        // Header Label
        JLabel label = new JLabel("Student Dashboard", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        add(label, BorderLayout.NORTH);
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10)); // 4 rows, 1 column with spacing
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        JButton takeQuizButton = new JButton("Take Quiz");
        JButton viewScoresButton = new JButton("View Scores");
        JButton leaderboardButton = new JButton("Leaderboard");
        JButton helpButton = new JButton("Help");
        buttonPanel.add(takeQuizButton);
        buttonPanel.add(viewScoresButton);
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(helpButton);
        // Back Button
        JButton backButton = createBackButton();
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setOpaque(false);
        backPanel.add(backButton);
        // Add Panels to Dashboard
        add(buttonPanel, BorderLayout.CENTER);
        add(backPanel, BorderLayout.SOUTH);
        // Take Quiz Button Action
        takeQuizButton.addActionListener(e -> {
            List<QuizTopic> allTopics = Main.USER_LIST.stream()
                    .filter(user -> user instanceof Teacher)
                    .flatMap(user -> ((Teacher) user).getQuizTopics().stream())
                    .toList();
            if (allTopics.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No quiz topics available!");
                return;
            }
            String[] topics = allTopics.stream().map(QuizTopic::getTopicName).toArray(String[]::new);
            String selectedTopic = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a topic:",
                    "Take Quiz",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    topics,
                    topics[0]
            );
            if (selectedTopic == null) return;
            QuizTopic topic = allTopics.stream()
                    .filter(t -> t.getTopicName().equals(selectedTopic))
                    .findFirst()
                    .orElse(null);
            if (topic != null) {
                startQuizWithTimer(student, topic);
            }
        });
        // View Scores Button Action
        viewScoresButton.addActionListener(e -> {
            if (student.getScores().isEmpty()) {
                JOptionPane.showMessageDialog(null, "No scores available yet.");
                return;
            }
            StringBuilder scores = new StringBuilder("Your Scores:\n");
            for (int score : student.getScores()) {
                scores.append(score).append("\n");
            }
            scores.append("\nAverage Score: ").append(student.getAverageScore());
            JOptionPane.showMessageDialog(null, scores.toString());
        });
        // Leaderboard Button Action
        leaderboardButton.addActionListener(e -> {
            List<Student> students = Main.USER_LIST.stream()
                    .filter(user -> user instanceof Student)
                    .map(user -> (Student) user)
                    .sorted(Comparator.comparingDouble(Student::getAverageScore).reversed())
                    .toList();
            StringBuilder leaderboard = new StringBuilder("Leaderboard:\n");
            if (students.isEmpty()) {
                leaderboard.append("No students have completed any quizzes yet.");
            } else {
                int rank = 1;
                for (Student s : students) {
                    leaderboard.append(rank).append(". ").append(s.getUsername())
                            .append(" - Average Score: ").append(String.format("%.2f", s.getAverageScore()))
                            .append("\n");
                    rank++;
                }
            }
            JOptionPane.showMessageDialog(null, leaderboard.toString());
        });
        // Help Button Action
        helpButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    null,
                    "Welcome to the Student Dashboard!\n\n" +
                            "Features:\n" +
                            "- Take quizzes on various topics.\n" +
                            "- View your scores and average performance.\n" +
                            "- Check the leaderboard for top performers.\n\n" +
                            "For further assistance, contact your teacher.",
                    "Help",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
    private void startQuizWithTimer(Student student, QuizTopic topic) {
        int score = 0;
        int questionNumber = 1;
        int timeLimit = 60; // 60 seconds for the quiz
        // Create the timer label
        JLabel timerLabel = new JLabel("Time Left: " + timeLimit + "s", SwingConstants.CENTER);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(Color.RED);
        // Timer setup
        Timer timer = new Timer(1000, null);
        final int[] remainingTime = {timeLimit};
        timer.addActionListener(e -> {
            remainingTime[0]--;
            timerLabel.setText("Time Left: " + remainingTime[0] + "s");
            if (remainingTime[0] <= 0) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Time's up! Submitting your answers...");
            }
        });
        timer.start();
        // Quiz logic
        for (Question question : topic.getQuestions()) {
            if (!timer.isRunning()) break;
            JPanel quizPanel = new JPanel(new BorderLayout(10, 10));
            quizPanel.add(timerLabel, BorderLayout.NORTH); // Add timer to the top
            JLabel questionLabel = new JLabel("<html>" + question.getQuestionText() + "</html>");
            questionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            quizPanel.add(questionLabel, BorderLayout.CENTER);
            String[] options = question.getOptions();
            JComboBox<String> answerBox = new JComboBox<>(options);
            quizPanel.add(answerBox, BorderLayout.SOUTH);
            int result = JOptionPane.showConfirmDialog(
                    null,
                    quizPanel,
                    "Question " + questionNumber + "/" + topic.getQuestions().size(),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );
            if (result != JOptionPane.OK_OPTION) {
                timer.stop();
                JOptionPane.showMessageDialog(null, "Quiz cancelled.");
                return;
            }
            String selectedAnswer = (String) answerBox.getSelectedItem();
            if (question.getCorrectAnswer().equals(selectedAnswer)) {
                score++;
            }
            questionNumber++;
        }
        timer.stop();
        JOptionPane.showMessageDialog(null, "You scored " + score + "!");
        student.addScore(score);
    }
    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(0, 105, 148));
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> Main.showLoginWindow());
        return backButton;
    }
}
