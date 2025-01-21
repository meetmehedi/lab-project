import java.util.List;
abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public abstract void menu();
    public void addTeacher(String username, String password, List<User> users) {
    }
}
