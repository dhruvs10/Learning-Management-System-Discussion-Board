import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Project 5
 * <p>
 * The class is the Replies class for the discussion program between
 * teachers and students along with creating accounts. Every post added by a student is an object of this class.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */

public class Replies {
    private DiscussionTopic dt;
    private String username;
    private String timestamp;
    private String reply;
    private int votes;

    public Replies(DiscussionTopic dt, String username, String reply) {
        this.dt = dt;
        this.username = username;
        this.reply = reply;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.votes = 0;
    }

    public Replies(DiscussionTopic dt, String username, String timestamp, String reply, int votes) {
        this.dt = dt;
        this.username = username;
        this.timestamp = timestamp;
        this.reply = reply;
        this.votes = votes;
    }

    public DiscussionTopic getDt() {
        return dt;
    }

    public void setDt(DiscussionTopic dt) {
        this.dt = dt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }


    public String toString() {
        return String.format("%s`%s`%s`%s`%s`%s`%s`%s", this.dt.getCourseName(), this.dt.getTopicTitle(),
                this.dt.getTopicDescription(),
                this.dt.getTimestamp(), this.username, this.timestamp, this.reply, String.valueOf(this.votes));
    }
}
