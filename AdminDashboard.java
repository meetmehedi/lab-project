import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JPanel {
    public AdminDashboard(User admin) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JButton addTeacherButton = new JButton("Add Teacher");
        JButton addStudentButton = new JButton("Add Student");
        JButton logoutButton = new JButton("Logout");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addTeacherButton);
        buttonPanel.add(addStudentButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER);

        addTeacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show dialog for adding teacher
                String username = JOptionPane.showInputDialog("Enter Teacher Username:");
                String password = JOptionPane.showInputDialog("Enter Teacher Password:");
                ((Admin) admin).addTeacher(username, password, LoginUI.users);
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show dialog for adding student
                String username = JOptionPane.showInputDialog("Enter Student Username:");
                String password = JOptionPane.showInputDialog("Enter Student Password:");
                ((Admin) admin).addStudent(username, password, LoginUI.users);
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