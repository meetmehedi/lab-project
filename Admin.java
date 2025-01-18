import java.util.List;

class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void menu() {
        System.out.println("Admin Menu:\n1. Add Teacher\n2. Add Student\n3. Logout");
    }

    public void addTeacher(String username, String password, List<User> users) {
        Teacher teacher = new Teacher(username, password);
        users.add(teacher);
        System.out.println("Teacher added successfully.");
    }

    public void addStudent(String username, String password, List<User> users) {
        Student student = new Student(username, password);
        users.add(student);
        System.out.println("Student added successfully.");
    }
}