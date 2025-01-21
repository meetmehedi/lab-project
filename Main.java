import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final List<User> USER_LIST = new ArrayList<>();
    private static final String LOGIN_SUCCESS_MESSAGE = "Login Successful! Welcome ";
    private static final String LOGIN_FAILURE_MESSAGE = "Invalid username or password.";

    public static void main(String[] args) {
        JFrame frame = new JFrame("QuickWitt - Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        frame.add(loginPanel);
        configureLoginPanel(loginPanel);

        // Initialize testing data
        USER_LIST.add(new Admin("admin", "admin123"));
        USER_LIST.add(new Admin.Teacher("teacher", "teacher123"));

        frame.setVisible(true);
    }

    private static void configureLoginPanel(JPanel loginPanel) {
        loginPanel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 20, 165, 25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 50, 165, 25);
        loginPanel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 150, 25);
        loginPanel.add(loginButton);

        loginButton.addActionListener(e -> handleLogin(userText.getText(), new String(passwordText.getPassword()), loginPanel));
    }

    private static void handleLogin(String username, String password, JPanel loginPanel) {
        User validatedUser = validateUserCredentials(username, password);

        if (validatedUser != null) {
            JOptionPane.showMessageDialog(loginPanel, LOGIN_SUCCESS_MESSAGE + validatedUser.username);
            openDashboard(validatedUser);
        } else {
            JOptionPane.showMessageDialog(loginPanel, LOGIN_FAILURE_MESSAGE);
        }
    }

    private static User validateUserCredentials(String username, String password) {
        return USER_LIST.stream()
                .filter(user -> user.username.equals(username) && user.password.equals(password))
                .findFirst()
                .orElse(null);
    }

    private static void openDashboard(User currentUser) {
        JFrame dashboardFrame = new JFrame(currentUser.username + " Dashboard");

        if (currentUser instanceof Admin) {
            dashboardFrame.setContentPane(new AdminDashboard(currentUser));
        } else if (currentUser instanceof Admin.Teacher) {
            dashboardFrame.setContentPane(new TeacherDashboard());
        } else {
            dashboardFrame.setContentPane(new StudentDashboard());
        }

        dashboardFrame.setSize(400, 300);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setVisible(true);
    }
}
