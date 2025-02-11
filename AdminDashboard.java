import javax.swing.*;
import java.awt.*;

class AdminDashboard extends JPanel {
    public AdminDashboard(Admin admin) {
        setLayout(null); // Use null layout for precise positioning
        setBackground(new Color(0, 105, 148));

        // Title Label
        JLabel label = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBounds(190, 10, 200, 30); // Centered at the top
        add(label);

        // Buttons
        JButton addTeacherButton = new JButton("Add Teacher");
        addTeacherButton.setBounds(210, 60, 150, 30);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(210, 100, 150, 30);

        JButton backButton = createBackButton();
        backButton.setBounds(10, 10, 80, 30); // Position the Back button at the top-left

        // Add components to the panel
        add(addTeacherButton);
        add(addStudentButton);
        add(backButton);

        // Action Listeners
        addTeacherButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Enter Teacher Username:");
            String password = JOptionPane.showInputDialog("Enter Teacher Password:");
            admin.addUser("teacher", username, password, Main.USER_LIST);
            JOptionPane.showMessageDialog(null, "Teacher added successfully!");
        });

        addStudentButton.addActionListener(e -> {
            String username = JOptionPane.showInputDialog("Enter Student Username:");
            String password = JOptionPane.showInputDialog("Enter Student Password:");
            admin.addUser("student", username, password, Main.USER_LIST);
            JOptionPane.showMessageDialog(null, "Student added successfully!");
        });
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(0, 105, 148));
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> Main.showLoginWindow());
        return backButton;
    }
}
