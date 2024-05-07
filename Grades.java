/**
 * Project 5
 * <p>
 * The class is the Grades class for the discussion program between
 * teachers and students along with creating accounts. Every time a teacher grades a student's post, 
 * a new grade object is created.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */
public class Grades {
    
    private String courseName;
    private String topicTitle;
    private String reply;
    private String username;
    private double grade;

    public Grades(String courseName, String topicTitle, String reply, String username, double grade) {
        this.courseName = courseName;
        this.topicTitle = topicTitle;
        this.reply = reply;
        this.username = username;
        this.grade = grade;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String toString() {
        return String.format("%s`%s`%s`%s`%.1f", courseName, topicTitle, reply, username, grade);
    }
}
