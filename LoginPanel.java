import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private final JTextField userTextField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JCheckBox showPasswordCheckBox;

    public LoginPanel() {
        setLayout(new GridBagLayout());
        setBackground(new Color(0, 105, 148));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username Label and Field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setDisplayedMnemonic('U'); // Alt+U for accessibility
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        userTextField = new JTextField(20);
        userTextField.setToolTipText("Enter your username");
        userLabel.setLabelFor(userTextField);
        gbc.gridx = 1;
        add(userTextField, gbc);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setDisplayedMnemonic('P'); // Alt+P for accessibility
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setToolTipText("Enter your password");
        passwordLabel.setLabelFor(passwordField);
        gbc.gridx = 1;
        add(passwordField, gbc);

        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setOpaque(false);
        showPasswordCheckBox.setForeground(Color.WHITE);
        showPasswordCheckBox.addActionListener(e -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0); // Show password
            } else {
                passwordField.setEchoChar('*'); // Mask password
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(showPasswordCheckBox, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(0, 105, 148));
        loginButton.setFocusPainted(false);
        loginButton.setToolTipText("Click to log in");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(loginButton, gbc);

        // Add Action Listener for Login Button
        loginButton.addActionListener(new LoginActionListener());
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

    // Inner class for login button action
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = getUsername();
            String password = getPassword();

            // Input validation
            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Username cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                userTextField.requestFocus();
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                passwordField.requestFocus();
                return;
            }

            // Dummy authentication logic (replace with actual logic)
            if ("admin".equals(username) && "password".equals(password)) {
                JOptionPane.showMessageDialog(LoginPanel.this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Proceed to the next screen
            } else {
                JOptionPane.showMessageDialog(LoginPanel.this, "Invalid credentials. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}