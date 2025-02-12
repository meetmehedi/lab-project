import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class QuizTopic {
    private final String topicName;
    private final List<Question> questions = new ArrayList<>();
    private final Map<Student, List<Integer>> studentScores = new HashMap<>(); // Tracks multiple scores per student

    public QuizTopic(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public Map<Student, List<Integer>> getStudentScores() {
        return studentScores;
    }

    public void addQuestion(String questionText, String[] options, String correctAnswer) {
        questions.add(new Question(questionText, options, correctAnswer));
    }

    public void recordAttempt(Student student, int score) {
        // If the student has previous scores, add the new score to the list
        studentScores.computeIfAbsent(student, k -> new ArrayList<>()).add(score);
    }

    public String getStudentReport() {
        StringBuilder report = new StringBuilder("Student Attempts for Topic: " + topicName + "\n");
        for (Map.Entry<Student, List<Integer>> entry : studentScores.entrySet()) {
            Student student = entry.getKey();
            List<Integer> scores = entry.getValue();
            report.append("Student: ").append(student.getUsername())
                    .append(", Scores: ").append(scores).append("\n");
        }
        return report.toString();
    }
}
