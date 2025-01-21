import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginUI {
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("QuickWitt - Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);
        
        Admin admin = new Admin("admin", "admin123");
        Teacher teacher = new Teacher("teacher", "teacher123");
        users.add(admin);
        users.add(teacher);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(150, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(150, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 150, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                User currentUser = users.stream()
                        .filter(user -> user.username.equals(username) && user.password.equals(password))
                        .findFirst().orElse(null);

                if (currentUser != null) {
                    JOptionPane.showMessageDialog(panel, "Login Successful! Welcome " + currentUser.username);
                    openDashboard(currentUser);
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid username or password.");
                }
            }
        });
    }

    private static void openDashboard(User currentUser) {
        JFrame dashboardFrame = new JFrame(currentUser.username + " Dashboard");

        if (currentUser instanceof Admin) {
            dashboardFrame.setContentPane(new AdminDashboard(currentUser));
        } else if (currentUser instanceof Teacher) {
            dashboardFrame.setContentPane(new TeacherDashboard(currentUser));
        } else {
            dashboardFrame.setContentPane(new StudentDashboard(currentUser));
        }

        dashboardFrame.setSize(400, 300);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setVisible(true);
    }
}
