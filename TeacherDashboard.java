import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherDashboard extends JPanel {
    public TeacherDashboard(User teacher) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Teacher Dashboard", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JButton addStudentButton = new JButton("Add Student");
        JButton addQuestionButton = new JButton("Add Question");
        JButton viewLeaderboardButton = new JButton("View Leaderboard");
        JButton logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addStudentButton);
        buttonPanel.add(addQuestionButton);
        buttonPanel.add(viewLeaderboardButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show dialog for adding student
                String username = JOptionPane.showInputDialog("Enter Student Username:");
                String password = JOptionPane.showInputDialog("Enter Student Password:");
                ((Teacher) teacher).addStudent(username, password, LoginUI.users);
            }
        });

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show dialog for adding question
                String questionText = JOptionPane.showInputDialog("Enter Question Text:");
                String options = JOptionPane.showInputDialog("Enter Options (comma separated):");
                String correctAnswer = JOptionPane.showInputDialog("Enter Correct Answer:");
                ((Teacher) teacher).addQuestion(questionText, List.of(options.split(",")), correctAnswer);
            }
        });

        viewLeaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Teacher) teacher).viewLeaderboard();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Logout and close the app
            }
        });
    }
}