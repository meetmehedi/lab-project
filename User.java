import java.util.List;
import java.util.ArrayList;

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

    public List<Integer> getScores() {
        return scores;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public double getAverageScore() {
        return scores.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }

    @Override
    public void menu() {
        System.out.println("Student Menu");
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
