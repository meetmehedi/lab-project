import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class User {
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public abstract void menu();
}


class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu() {
        System.out.println("Admin Menu");
    }

    public void addUser(String type, String username, String password, List<User> userList) {
        if (type.equalsIgnoreCase("teacher")) {
            userList.add(new Teacher(username, password));
        } else if (type.equalsIgnoreCase("student")) {
            userList.add(new Student(username, password));
        }
    }
}


class Student extends User {
    private final List<Integer> scores = new ArrayList<>(); // Store scores for quizzes

    public Student(String username, String password) {
        super(username, password);
    }
    public double getAverageScore() {
        if (scores.isEmpty()) return 0.0;
        return scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
    // Get all quiz scores
    public List<Integer> getScores() {
        return Collections.unmodifiableList(scores); // Return an unmodifiable view to ensure encapsulation
    }

    // Add a new quiz score
    public void addScore(int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Score cannot be negative.");
        }
        scores.add(score);
    }



    // Display the student-specific menu
    @Override
    public void menu() {
        /**System.out.println("\n--- Student Menu ---");
        System.out.println("1. View Quiz Topics");
        System.out.println("2. Take a Quiz");
        System.out.println("3. View Scores");
        System.out.println("4. Logout");
        System.out.println("---------------------");**/
    }

    // To display leaderboard details
    @Override
    public String toString() {
        return getUsername() + " - Average Score: " + String.format("%.2f", getAverageScore());
    }
}

class Teacher extends User {
    private final List<QuizTopic> quizTopics = new ArrayList<>();

    public Teacher(String username, String password) {
        super(username, password);
    }

    public List<QuizTopic> getQuizTopics() {
        return quizTopics;
    }

    public void addQuizTopic(String topicName) {
        quizTopics.add(new QuizTopic(topicName));
    }

    public int getTotalQuizzes() {
        return quizTopics.size();
    }

    @Override
    public void menu() {
        System.out.println("Teacher Menu");
    }
}
