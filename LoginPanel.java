import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private final JTextField userTextField;
    private final JPasswordField passwordField;
    private final JButton loginButton;

    public LoginPanel() {
        setLayout(null);
        setBackground(new Color(0, 105, 148));

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        userLabel.setForeground(Color.WHITE);
        add(userLabel);

        userTextField = new JTextField(20);
        userTextField.setBounds(150, 20, 165, 25);
        add(userTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        passwordLabel.setForeground(Color.WHITE);
        add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 50, 165, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 90, 100, 25);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(0, 105, 148));
        add(loginButton);
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public String getUsername() {
        return userTextField.getText().trim();
    }

    public String getPassword() {
        return new String(passwordField.getPassword()).trim();
    }
}

