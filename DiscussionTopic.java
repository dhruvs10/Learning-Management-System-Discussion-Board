import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Project 5
 * <p>
 * The class is the DiscussionTopic class for the discussion program between
 * teachers and students along with creating accounts. Every discussion forum if first an 
 * object of this class and stores the Discussion Topic Title, Description and Timestamp.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */


public class DiscussionTopic {
    private String courseName;
    private String topicTitle;
    private String topicDescription;
    private String timestamp;

    public DiscussionTopic(String courseName, String topicTitle, String topicDescription) {
        this.courseName = courseName;
        this.topicTitle = topicTitle;
        this.topicDescription = topicDescription;
        this.timestamp = new Timestamp(System.currentTimeMillis()).toString();
    }

    public DiscussionTopic(String courseName, String topicTitle, String topicDescription, String timestamp) {
        this.courseName = courseName;
        this.topicTitle = topicTitle;
        this.topicDescription = topicDescription;
        this.timestamp = timestamp;
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

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return String.format("%s`%s`%s`%s", courseName, topicTitle, topicDescription, timestamp);
    }
    
}
