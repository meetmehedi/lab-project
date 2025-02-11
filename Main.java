import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final List<User> USER_LIST = new ArrayList<>();
    public static User currentUser;

    public static void main(String[] args) {
        // Add default users
        USER_LIST.add(new Admin("admin", "admin123"));
        // USER_LIST.add(new Teacher("teacher", "teacher123"));
        // USER_LIST.add(new Student("student", "student123"));

        // Show login window
        SwingUtilities.invokeLater(Main::showLoginWindow);
    }

    public static void showLoginWindow() {
        JFrame frame = new JFrame("QuickWitt - Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(new Color(0, 105, 148));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        userLabel.setForeground(Color.WHITE);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 20, 165, 25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        passwordLabel.setForeground(Color.WHITE);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 50, 165, 25);
        loginPanel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(100, 90, 100, 25);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(0, 105, 148));
        loginPanel.add(loginButton);

        frame.add(loginPanel);
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            currentUser = validateUserCredentials(username, password);

            if (currentUser != null) {
                JOptionPane.showMessageDialog(frame, "Login Successful! Welcome " + currentUser.getUsername());
                frame.dispose();
                openDashboard();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password.");
            }
        });
    }

    private static User validateUserCredentials(String username, String password) {
        return USER_LIST.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    private static void openDashboard() {
        JFrame dashboardFrame = new JFrame(currentUser.getUsername() + " Dashboard");
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(600, 400);

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());

        // Add real-time date and time label
        JLabel dateTimeLabel = new JLabel("", JLabel.CENTER);
        dateTimeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dateTimeLabel.setForeground(new Color(0, 105, 148));
        dashboardPanel.add(dateTimeLabel, BorderLayout.NORTH);

        // Update the date and time label every second
        Timer timer = new Timer(1000, e -> {
            String currentDateTime = getCurrentDateTime();
            dateTimeLabel.setText(currentDateTime);
        });
        timer.start();

        // Add specific content based on user role
        if (currentUser instanceof Admin) {
            dashboardPanel.add(new AdminDashboard((Admin) currentUser));
        } else if (currentUser instanceof Teacher) {
            dashboardPanel.add(new TeacherDashboard((Teacher) currentUser));
        } else if (currentUser instanceof Student) {
            dashboardPanel.add(new StudentDashboard((Student) currentUser));
        }

        dashboardFrame.add(dashboardPanel);
        dashboardFrame.setVisible(true);
    }

    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Date & Time: " + now.format(formatter);
    }
}
