import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
                ((Admin) admin).addTeacher(username, password, Main.USER_LIST);
            }
        });

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show dialog for adding student
                String username = JOptionPane.showInputDialog("Enter Student Username:");
                String password = JOptionPane.showInputDialog("Enter Student Password:");
                ((Admin) admin).addStudent(username, password, Main.USER_LIST);
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


 class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu() {
        System.out.println("Admin Menu:\n1. Add Teacher\n2. Add Student\n3. Logout");
    }

    public void addTeacher(String username, String password, java.util.List<User> users) {
        Teacher teacher = new Teacher(username, password);
        users.add(teacher);
        System.out.println("Teacher added successfully.");
    }

    public static class Teacher extends User {
        public Teacher(String username, String password) {
            super(username, password);
        }

        @Override
        public void menu() {
            System.out.println("Teacher Menu:\n1. View Classes\n2. Logout");
        }
    }

    public void addStudent(String username, String password, List<User> users) {
        Student student = new Student(username, password);
        users.add(student);
        System.out.println("Student added successfully.");
    }
}
