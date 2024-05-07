import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Project 5
 * <p>
 * The class is the Comments class for the discussion program between
 * teachers and students along with creating accounts. Every comment added by a student or a teacher
 * is an object of this class.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */

public class Comments {
    private Replies post;
    private String username;
    private String timestamp;
    private String comment;

    public Comments(Replies post, String username, String comment) {
        this.post = post;
        this.username = username;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
        this.comment = comment;
    }

    public Comments(Replies post, String username, String timestamp, String comment) {
        this.post = post;
        this.username = username;
        this.timestamp = timestamp;
        this.comment = comment;
    }

    public Replies getPost() {
        return post;
    }

    public void setPost(Replies reply) {
        this.post = reply;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String toString() {
        return String.format("%s`%s`%s`%s`%s`%s`%s`%s`%s`%s`%s", this.post.getDt().getCourseName(), 
                this.post.getDt().getTopicTitle(),
                this.post.getDt().getTopicDescription(), this.post.getDt().getTimestamp(), this.post.getUsername(),
                this.post.getTimestamp(), this.post.getReply(), String.valueOf(this.post.getVotes()), 
                this.username, this.timestamp, this.comment);
    }
}
