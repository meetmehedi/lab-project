import java.util.Scanner;

class Student extends User {
    private int score = 0;

    public Student(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu() {
        System.out.println("Student Menu:\n1. Take Quiz\n2. Logout");
    }

    public void takeQuiz() {
        Scanner scanner = new Scanner(System.in);
        score = 0;

        for (Question question : Teacher.questions) {
            System.out.println(question);
            System.out.print("Your Answer: ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase(question.getCorrectAnswer())) {
                score++;
            }
        }
        System.out.println("Quiz Completed. Your Score: " + score);
    }

    public int getScore() {
        return score;
    }
}