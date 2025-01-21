import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The TeacherDashboard class represents a GUI-based dashboard for teachers to manage quiz topics, 
 * add new questions, view existing questions, and perform other administrative actions.
 * This panel provides a user-friendly interface for interacting with quiz content.
 *
 * The dashboard includes:
 * - A header displaying the title "Teacher Dashboard".
 * - Buttons for adding new topics, adding questions to topics, viewing questions, and logging out.
 *
 * Actions supported:
 * - Adding topics: Allows the teacher to input and save new quiz topics.
 * - Adding questions: Enables the teacher to associate questions with existing topics.
 * - Viewing questions: Displays all questions for a selected topic.
 * - Logout: Exits the application.
 *
 * The TeacherDashboard also maintains a list of created quiz topics internally for management purposes.
 */
public class TeacherDashboard extends JPanel {
    private List<QuizTopic> quizTopics; // Store all topics

    public TeacherDashboard() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JButton addTopicButton = new JButton("Add New Topic");
        JButton addQuestionButton = new JButton("Add Question to Topic");
        JButton viewQuestionsButton = new JButton("View Questions");
        JButton logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTopicButton);
        buttonPanel.add(addQuestionButton);
        buttonPanel.add(viewQuestionsButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Initialize the quiz topic storage
        quizTopics = new ArrayList<>();

        // Action: Add new topic
        addTopicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String topicName = JOptionPane.showInputDialog("Enter the new topic name:");
                if (topicName == null || topicName.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Topic name cannot be empty.");
                } else {
                    quizTopics.add(new QuizTopic(topicName));
                    JOptionPane.showMessageDialog(null, "Topic '" + topicName + "' added successfully.");
                }
            }
        });

        // Action: Add a question to a particular topic
        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quizTopics.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No topics available. Please add a topic first.");
                    return;
                }

                String[] topicNames = quizTopics.stream().map(QuizTopic::getTopicName).toArray(String[]::new);
                String selectedTopic = (String) JOptionPane.showInputDialog(
                        null,
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
                            JOptionPane.showMessageDialog(null, "All fields are required!");
                        } else {
                            List<String> options = Arrays.asList(optionsText.split(","));
                            Question question = new Question(questionText, options, correctAnswer);
                            topic.addQuestion(question);
                            JOptionPane.showMessageDialog(null, "Question added successfully to '" + selectedTopic + "'.");
                        }
                    }
                }
            }
        });

        // Action: View questions for a particular topic
        viewQuestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quizTopics.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No topics available.");
                    return;
                }

                String[] topicNames = quizTopics.stream().map(QuizTopic::getTopicName).toArray(String[]::new);
                String selectedTopic = (String) JOptionPane.showInputDialog(
                        null,
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

                        JOptionPane.showMessageDialog(null, questionsDisplay.toString());
                    }
                }
            }
        });

        // Logout Button Action
        logoutButton.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Teacher Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new TeacherDashboard());
        frame.setVisible(true);
    }
}

class Teacher extends User {
    static List<Question> questions = new ArrayList<>();
    static List<Student> leaderboard = new ArrayList<>();

    public Teacher(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu() {
        System.out.println("Teacher Menu:\n1. Add Student\n2. Add Questions\n3. View Leaderboard\n4. Logout");
    }

    public void addStudent(String username, String password, List<User> users) {
        Student student = new Student(username, password);
        users.add(student);
        System.out.println("Student added successfully by Teacher.");
    }

    public void addQuestion(String questionText, List<String> options, String correctAnswer) {
        questions.add(new Question(questionText, options, correctAnswer));
        System.out.println("Question added successfully.");
    }

    public void viewLeaderboard() {
        System.out.println("Leaderboard:");
        leaderboard.sort((s1, s2) -> s2.getScore() - s1.getScore());
        for (Student student : leaderboard) {
            System.out.println(student.username + " - " + student.getScore() + " points");
        }
    }
}
