import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDashboard extends JPanel {
    public StudentDashboard(User student) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Student Dashboard", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JButton takeQuizButton = new JButton("Take Quiz");
        JButton logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(takeQuizButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        takeQuizButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement quiz functionality
                JOptionPane.showMessageDialog(null, "Quiz is not implemented yet.");
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