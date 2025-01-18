import java.util.List;

class Question {
    private String questionText;
    private List<String> options;
    private String correctAnswer;

    public Question(String questionText, List<String> options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
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