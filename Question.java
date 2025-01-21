import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public boolean isCorrect(String answer) {
        return correctAnswer != null && correctAnswer.equalsIgnoreCase(answer.trim());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(questionText + "\n");
        char optionLabel = 'A';

        for (String option : options) {
            sb.append(optionLabel++).append(") ").append(option).append("\n");
        }

        return sb.toString();
    }
}


 class QuizTopic {
    private String topicName;
    private List<Question> questions;

    public QuizTopic(String topicName) {
        this.topicName = topicName;
        this.questions = new ArrayList<>();
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public String toString() {
        return "Quiz Topic: " + topicName + ", Total Questions: " + questions.size();
    }
}
