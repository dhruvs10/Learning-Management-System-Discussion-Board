/**
 * Project 5
 * <p>
 * The class is the VotersList class for the discussion program between
 * teachers and students along with creating accounts. Every time a student votes for a post, a new VotersList
 * object is created.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */

public class VotersList {
    private String courseName;
    private String topicTitle;
    private String reply;
    private String username;

    public VotersList(String courseName, String topicTitle, String reply, String username) {
        this.courseName = courseName;
        this.topicTitle = topicTitle;
        this.reply = reply;
        this.username = username;
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

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String toString() {
        return String.format("%s`%s`%s`%s", courseName, topicTitle, reply, username);
    }
}
