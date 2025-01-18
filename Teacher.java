import java.util.ArrayList;
import java.util.List;

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