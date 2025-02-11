Quiz Application (Quick witt)

Overview:
The Quiz Application is a Java-based project designed to facilitate online assessments. It provides role-based authentication for Admins, Teachers, and Students, allowing them to manage quizzes, attempt assessments, and track performance efficiently. The application follows a modular architecture for scalability and maintainability.

Features:
- User Authentication: Secure login system with role-based access control.
- Admin Dashboard: Manage users, generate reports, and oversee quizzes.
- Teacher Dashboard: Create quizzes, add questions, and evaluate submissions.
- Student Dashboard: Attempt quizzes, view scores, and track progress.
- Quiz Management: Create, edit, and delete quizzes with multiple-choice questions.
- Performance Tracking: Store quiz attempts and calculate scores for students.

Modules:
1. LoginPanel:
- Handles user authentication with username and password.
- Provides an interactive GUI for login with input validation.

2. User Management:
- Defines user roles: `Admin`, `Teacher`, and `Student`.
- Implements role-specific actions such as quiz creation, evaluation, and attempt tracking.

3. Quiz Management:
- `QuizTopic.java`: Defines quiz categories and topics.
- `Question.java`: Manages question details and options.
- `QuizAttempt.java`: Stores and evaluates quiz attempts.

4. Dashboards:
- `AdminDashboard.java`: Provides administrative functionalities.
- `TeacherDashboard.java`: Allows teachers to create and manage quizzes.
- `StudentDashboard.java`: Enables students to attempt quizzes and view scores.

Installation & Setup:
1. Clone the repository:
   ```bash
   git clone https://github.com/meetmehedi/lab-project.git
   ```
2. Open the project in an IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans).
3. Ensure Java is installed (`JDK 8+` recommended).
4. Run the `Main.java` file to start the application.

Usage:
- Admin: Manages users and quizzes.
- Teacher: Creates quizzes and evaluates student submissions.
- Student: Attempts quizzes and views performance reports.

Technologies Used:
- Programming Language: Java (Swing for GUI)
- Frameworks & Libraries: Java Swing, AWT
- IDE: IntelliJ IDEA / Eclipse

Future Enhancements:
- Implement database storage for persistent user authentication.
- A discussion chatbot for students.
- A helping chatbot for teachers.
- Introduce analytics dashboards with visual reports.
- Enhance UI using JavaFX
- On-camera tracking system.
- Tracking windows minimization or switching windows..

Contributors:
- Md. Mehedi Hasan – Developer & Designer
- Abdul Al Mahin - Developer
- Marjanah Afroje - Developer & Designer
- Jannatul fatema - Developer
- Swadhin Chakraborty - Developer
- Nusaiba Jahan Taisara - Developer
