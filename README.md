**Account Menu**


The menu allows the user to operate the discussion forum. The user will first be asked to login or sign up to the system. If the user chooses to login, they can type in their username and password to login. If the user chooses to sign up, they are asked if the user is a student or a teacher which cannot be changed after selection. Then the user is to provide the username and password with their real name that cannot be changed after entering. After the user has signed up or logged in, they have the option to edit or delete their account.

**Discussion Forum Menu**

The user can add a course to start the discussion forum if the user is a teacher. Then the user must enter the title and the description for the forum. Then the user has the option to add, delete, or edit the discussion forum. The teacher can only grade a particular post of a student only once. After the teacher has graded a particular post of a student, even other teachers can’t grade that post. The teacher can check the highest votes of each forum if the course that has been entered by the teacher.

If the user is a student, they have the option to go into the course or exit. Then they can join the discussion forum to write the post, comment on the post, upvote, or check the grades. The student can only vote once per login and they will only be able to vote and check the grades for the posts of the particular discussion forum they are in. If there are no posts in the discussion forum they are in, they won’t be asked to vote in the first place.

<h2>LMSClient</h2>


This class includes a main method and the menu that mainly asks the user for an action. There are two parts of the menu which are the login and sign up section and the discussion forum section. The detailed descriptions are written below.

**Account Menu**


The menu allows the user to operate the discussion forum. The client will first ask the user to login or sign up to the system. If the user chooses to sign up, the client will ask for the user’s role. If the user chooses to login, then the client asks the user to provide their username and password. After the user has signed up or logged in, they are asked for the option to edit or delete their account.

**Discussion Forum Menu**

The user is asked to add a course to start the discussion forum if the user is a teacher. Then the user is asked to enter the title and the description for the forum. Then the user is asked for the options to add, delete, edit, comment, vote, or grade on the discussion forum. If the user is a student, they are asked for the option to enter the course or exit. If they enter the course, then the user is asked to write the post, comment on the post, upvote, or check the grades on the discussion forum.


<h2>LMSServer</h2>


This class includes a main method and the menu that mainly manipulates the user’s action. There are two parts of the menu which are the login and sign up section and the discussion forum section. The detailed descriptions are written below.

**Account Menu**


The menu allows the user to operate the discussion forum. The server will take in the user's login or sign up information. If the user chooses to sign up, the server will assign the role for the user as either student or a teacher according to their answer. Then the server will save the username and password from the user provided through the client. If the user chooses to login, then the server will save the username and the password. After the user has signed up or logged in, the server will edit or delete the user’s account according the what they have chosen from the client.

**Discussion Forum Menu**

The client sets up the course given from the user through the client to start the discussion forum if the user is a teacher. Then the title and the description for the forum is set from the client input. When the user chooses options to add, delete, edit, commnet, vote, or grade on the discussion forum, the server performs the action. If the user is a student, the server operates the option to enter the course or exit according to what the student has chosen. If the student enters the course, then the server performs to write the post, comment on the post, upvote, or check the grades on the discussion forum from what the student has chosen.



<h2>DiscussionTopic</h2>


This class constructs a discussion topic object and contains all methods necessary to manipulate and retrieve information regarding the topic.

**Fields**


<table>
  <tr>
   <td>Field Name
   </td>
   <td>Field Type
   </td>
   <td>Access Modifier
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>courseName
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the name of the course to which the discussion topic is tagged to.
   </td>
  </tr>
  <tr>
   <td>topicTitle
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the title of the discussion topic.
   </td>
  </tr>
  <tr>
   <td>topicDescription
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the description associated with the discussion topic.
   </td>
  </tr>
  <tr>
   <td>timestamp
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the timestamp for when the discussion topic was created.
   </td>
  </tr>
</table>


**Constructor**


<table>
  <tr>
   <td>Access Modifier
   </td>
   <td>Parameter List
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>String courseName, 
<p>
String topicTitle, 
<p>
String topicDescription
   </td>
   <td>Constructs a newly allocated <strong>DiscussionTopic</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>String courseName, 
<p>
String topicTitle, 
<p>
String topicDescription, String timestamp
   </td>
   <td>Constructs a newly allocated <strong>DiscussionTopic</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
</table>


**Methods**


<table>
  <tr>
   <td>Method name
   </td>
   <td>Return Type
   </td>
   <td>Access Modifier
   </td>
   <td>Parameters
   </td>
  </tr>
  <tr>
   <td>getCourseName
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setCourseName
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String courseName
   </td>
  </tr>
  <tr>
   <td>getTopicTitle
   </td>
   <td>String
   </td>
   <td>public 
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTopicTitle
   </td>
   <td>void 
   </td>
   <td>public 
   </td>
   <td>String topicTitle
   </td>
  </tr>
  <tr>
   <td>getTopicDescription
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTopicDescription
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String topicDescription
   </td>
  </tr>
  <tr>
   <td>getTimestamp
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTimestamp
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String timestamp
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
</table>


**Method Description**


<table>
  <tr>
   <td>Method
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>getCourseName
   </td>
   <td>Returns the course name.
   </td>
  </tr>
  <tr>
   <td>setCourseName
   </td>
   <td>Sets the course name.
   </td>
  </tr>
  <tr>
   <td>getTopicTitle
   </td>
   <td>Returns the topic title.
   </td>
  </tr>
  <tr>
   <td>setTopicTitle
   </td>
   <td>Sets the topic title.
   </td>
  </tr>
  <tr>
   <td>getTopicDescription
   </td>
   <td>Returns the topic description.
   </td>
  </tr>
  <tr>
   <td>setTopicDescription
   </td>
   <td>Sets the topic description.
   </td>
  </tr>
  <tr>
   <td>getTimestamp
   </td>
   <td>Returns the timestamp.
   </td>
  </tr>
  <tr>
   <td>setTimestamp
   </td>
   <td>Sets the timestamp.
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>Returns a String representation of this <strong>DiscussionTopic</strong>
<p>
For example, given the following fields:
<ul>

<li>Course Name = “CS180”

<li>Topic Title = “topicTitle”

<li>Topic Description = “topicDescription”

<li>Discussion Topic Timestamp = “10:10:10.10”

<p>
The result of calling toString() would be:
<ul>

<li>CS180`topicTitle`topicDescription`10:10:10.10

<p>
Follow the example formatting exactly!
</li>
</ul>
</li>
</ul>
   </td>
  </tr>
</table>




<h2>Replies</h2>


This class constructs a reply object and contains all methods necessary to manipulate and retrieve information regarding the reply. This class can reply back to the associated discussion topic.

**Fields**


<table>
  <tr>
   <td>Field name
   </td>
   <td>Field Type
   </td>
   <td>Access Modifier
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>dt
   </td>
   <td>DiscussionTopic
   </td>
   <td>private
   </td>
   <td>Stores the discussion topic which the reply is connected to.
   </td>
  </tr>
  <tr>
   <td>username
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores username of creator reply
   </td>
  </tr>
  <tr>
   <td>timestamp
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the timestamp of when the reply
   </td>
  </tr>
  <tr>
   <td>reply
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the reply generated by the user
   </td>
  </tr>
  <tr>
   <td>votes
   </td>
   <td>int
   </td>
   <td>private
   </td>
   <td>Stores the number of votes the reply has received.
   </td>
  </tr>
</table>


**Constructor**


<table>
  <tr>
   <td>Access Modifier
   </td>
   <td>Parameter List
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>DiscussionTopic dt, 
<p>
String username, 
<p>
String reply
   </td>
   <td>Constructs a newly allocated <strong>Replies </strong>object and instantiates the fields to the specified parameters.
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>DiscussionTopic dt, 
<p>
String username, 
<p>
String reply,
<p>
int votes
   </td>
   <td>Constructs a newly allocated <strong>Replies </strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
</table>


**Methods**


<table>
  <tr>
   <td>Method name
   </td>
   <td>Return Type
   </td>
   <td>Access Modifier
   </td>
   <td>Parameters
   </td>
  </tr>
  <tr>
   <td>getDt
   </td>
   <td>DiscussionTopic
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setDt
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>DiscussionTopic dt
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String username
   </td>
  </tr>
  <tr>
   <td>getTimestamp
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTimestamp
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String timestamp
   </td>
  </tr>
  <tr>
   <td>getReply
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setReply
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String reply
   </td>
  </tr>
  <tr>
   <td>getVotes
   </td>
   <td>int
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setVotes
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>int votes
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
</table>


**Method Description**


<table>
  <tr>
   <td>Method
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>getDt
   </td>
   <td>Returns the dt.
   </td>
  </tr>
  <tr>
   <td>setDt
   </td>
   <td>Sets the dt.
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>Returns the username.
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>Sets the username.
   </td>
  </tr>
  <tr>
   <td>getTimestamp
   </td>
   <td>Returns the timestamp.
   </td>
  </tr>
  <tr>
   <td>setTimestamp
   </td>
   <td>Sets the timestamp.
   </td>
  </tr>
  <tr>
   <td>getReply
   </td>
   <td>Returns the reply.
   </td>
  </tr>
  <tr>
   <td>setReply
   </td>
   <td>Sets the reply.
   </td>
  </tr>
  <tr>
   <td>getVotes
   </td>
   <td>Returns the votes.
   </td>
  </tr>
  <tr>
   <td>setVotes
   </td>
   <td>Sets the votes.
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>Returns a String representation of this <strong>Replies</strong>
<p>
For example, given the following fields:
<ul>

<li>Course Name = “courseName”

<li>Topic Title = “topicTitle”

<li>Topic Description = “topicDescription”

<li>Discussion Topic Timestamp = “10:10:10.10”

<li>Post Username = “usernameOfPost”

<li>Post Timestamp = “11:11:11.11”

<li>Post Reply = “postReply”

<li>Votes = “1”

<p>
The result of calling toString() would be:
<ul>

<li>courseName`topicTitle`topicDescription`10:10:10.10`usernameOfPost`11:11:11.11`postReply`1

<p>
Follow the example formatting exactly!
</li>
</ul>
</li>
</ul>
   </td>
  </tr>
</table>




<h2>Comments</h2>


This class constructs a comment object and contains all methods necessary to manipulate and retrieve information regarding the comment. This class can comment back on the replies from the same discussion topic.

**Fields**


<table>
  <tr>
   <td>Field name
   </td>
   <td>Field Type
   </td>
   <td>Access Modifier
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>post
   </td>
   <td>Replies
   </td>
   <td>private
   </td>
   <td>Stores the post which the comment is associated with.
   </td>
  </tr>
  <tr>
   <td>username
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the username of the commentor.
   </td>
  </tr>
  <tr>
   <td>comment
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the information of the comment.
   </td>
  </tr>
  <tr>
   <td>timestamp
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the timestamp which the comment is associated with.
   </td>
  </tr>
</table>


**Constructor**


<table>
  <tr>
   <td>Access Modifier
   </td>
   <td>Parameter List
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>Replies post, 
<p>
String username, 
<p>
String comment
   </td>
   <td>Constructs a newly allocated <strong>Comments</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>Replies post, 
<p>
String username, 
<p>
String comment,
<p>
String timestamp
   </td>
   <td>Constructs a newly allocated <strong>Comments</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
</table>


**Methods**


<table>
  <tr>
   <td>Method name
   </td>
   <td>Return Type
   </td>
   <td>Access Modifier
   </td>
   <td>Parameters
   </td>
  </tr>
  <tr>
   <td>getPost
   </td>
   <td>Replies
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setPost
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>Replies reply
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>String
   </td>
   <td>public 
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>void 
   </td>
   <td>public 
   </td>
   <td>String username
   </td>
  </tr>
  <tr>
   <td>getTimestamp
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTimestamp
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String timestamp
   </td>
  </tr>
  <tr>
   <td>getComment
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setComment
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String comment
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
</table>


**Method Description**


<table>
  <tr>
   <td>Method
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>getPost
   </td>
   <td>Returns the post.
   </td>
  </tr>
  <tr>
   <td>setPost
   </td>
   <td>Sets the post.
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>Returns the username.
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>Sets the username.
   </td>
  </tr>
  <tr>
   <td>getTimestamp
   </td>
   <td>Returns the timestamp.
   </td>
  </tr>
  <tr>
   <td>setTimestamp
   </td>
   <td>Sets the timestamp.
   </td>
  </tr>
  <tr>
   <td>getComment
   </td>
   <td>Returns the comment.
   </td>
  </tr>
  <tr>
   <td>setComment
   </td>
   <td>Sets the comment.
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>Returns a String representation of this <strong>Comments</strong>
<p>
For example, given the following fields:
<ul>

<li>Course Name = “courseName”

<li>Topic Title = “topicTitle”

<li>Topic Description = “topicDescription”

<li>Discussion Topic Timestamp = “10:10:10.10”

<li>Post Username = “usernameOfPost”

<li>Post Timestamp = “11:11:11.11”

<li>Post Reply = “postReply”

<li>Votes = “numberPostVotes”

<li>Comment username = “usernameOfComment”

<li>Comment Timestamp = “12:12:12.12”

<li>Comment description = “commentContents”

<p>
The result of calling toString() would be:
<ul>

<li>courseName`topicTitle`topicDescription`10:10:10.10`usernameOfPost`11:11:11.11`postReply`numberPostVotes`usernameOfComment`12:12:12.12`commentContents

<p>
Follow the example formatting exactly!
</li>
</ul>
</li>
</ul>
   </td>
  </tr>
</table>




<h2>User</h2>


This class constructs a user object and contains all methods necessary to manipulate and retrieve information regarding the user.

**Fields**


<table>
  <tr>
   <td>Field name
   </td>
   <td>Field Type
   </td>
   <td>Access Modifier
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>name
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the name of the user
   </td>
  </tr>
  <tr>
   <td>role
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the role of the user
   </td>
  </tr>
  <tr>
   <td>username
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the username picked by the user.
   </td>
  </tr>
  <tr>
   <td>password
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the custom password from the user.
   </td>
  </tr>
  <tr>
   <td>grade
   </td>
   <td>double
   </td>
   <td>private
   </td>
   <td>Stores the grade assigned to the user if they are a student.
   </td>
  </tr>
</table>


**Constructor**


<table>
  <tr>
   <td>Access Modifier
   </td>
   <td>Parameter List
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>String name, 
<p>
String role, 
<p>
String username, 
<p>
String password, 
<p>
String grade
   </td>
   <td>Constructs a newly allocated <strong>User</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
</table>


**Methods**


<table>
  <tr>
   <td>Method name
   </td>
   <td>Return Type
   </td>
   <td>Access Modifier
   </td>
   <td>Parameters
   </td>
  </tr>
  <tr>
   <td>getName
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setName
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String name
   </td>
  </tr>
  <tr>
   <td>getRole
   </td>
   <td>String
   </td>
   <td>public 
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setRole
   </td>
   <td>void 
   </td>
   <td>public 
   </td>
   <td>String role
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String username
   </td>
  </tr>
  <tr>
   <td>getPassword
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setPassword
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String password
   </td>
  </tr>
  <tr>
   <td>getGrade
   </td>
   <td>double
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setGrade
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>double grade
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
</table>


**Method Description**


<table>
  <tr>
   <td>Method
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>getName
   </td>
   <td>Returns the name.
   </td>
  </tr>
  <tr>
   <td>setName
   </td>
   <td>Sets the name.
   </td>
  </tr>
  <tr>
   <td>getRole
   </td>
   <td>Returns the role.
   </td>
  </tr>
  <tr>
   <td>setRole
   </td>
   <td>Sets the role.
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>Returns the username.
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>Sets the username.
   </td>
  </tr>
  <tr>
   <td>getPassword
   </td>
   <td>Returns the password.
   </td>
  </tr>
  <tr>
   <td>setPassword
   </td>
   <td>Sets the password.
   </td>
  </tr>
  <tr>
   <td>getGrade
   </td>
   <td>Returns the grade.
   </td>
  </tr>
  <tr>
   <td>setGrade
   </td>
   <td>Sets the grade.
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>Returns a String representation of this <strong>User</strong>
<p>
For example, given the following fields:
<ul>

<li>name = “name”

<li>role = “role”

<li>username = “username”

<li>password = “password”

<p>
The result of calling toString() would be:
<ul>

<li>name`role`username`password`

<p>
Follow the example formatting exactly!
</li>
</ul>
</li>
</ul>
   </td>
  </tr>
</table>


<h2>VotersList</h2>


This class constructs a voter list object  and contains all methods necessary to manipulate and retrieve information regarding the voter list.

**Fields**


<table>
  <tr>
   <td>Field name
   </td>
   <td>Field Type
   </td>
   <td>Access Modifier
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>courseName
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the course name associated with the voter list.
   </td>
  </tr>
  <tr>
   <td>topicTitle
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the topic associated with the voter list.
   </td>
  </tr>
  <tr>
   <td>reply
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the reply associated with the voter list.
   </td>
  </tr>
  <tr>
   <td>username
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the username of the user who is being manipulated in the voter list.
   </td>
  </tr>
</table>


**Constructor**


<table>
  <tr>
   <td>Access Modifier
   </td>
   <td>Parameter List
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>String courseName, 
<p>
String topicTitle, 
<p>
String reply, 
<p>
String username
   </td>
   <td>Constructs a newly allocated <strong>VotersList</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
</table>


**Methods**


<table>
  <tr>
   <td>Method name
   </td>
   <td>Return Type
   </td>
   <td>Access Modifier
   </td>
   <td>Parameters
   </td>
  </tr>
  <tr>
   <td>getCourseName
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setCourseName
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String courseName
   </td>
  </tr>
  <tr>
   <td>getTopicTitle
   </td>
   <td>String
   </td>
   <td>public 
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTopicTitle
   </td>
   <td>void 
   </td>
   <td>public 
   </td>
   <td>String topicTitle
   </td>
  </tr>
  <tr>
   <td>getReply
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setReply
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String reply
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String username
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
</table>


**Method Description**


<table>
  <tr>
   <td>Method
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>getCourseName
   </td>
   <td>Returns the course name.
   </td>
  </tr>
  <tr>
   <td>setCourseName
   </td>
   <td>Sets the course name.
   </td>
  </tr>
  <tr>
   <td>getTopicTitle
   </td>
   <td>Returns the topic title.
   </td>
  </tr>
  <tr>
   <td>setTopicTitle
   </td>
   <td>Sets the topic title.
   </td>
  </tr>
  <tr>
   <td>getReply
   </td>
   <td>Returns the reply.
   </td>
  </tr>
  <tr>
   <td>setReply
   </td>
   <td>Sets the reply.
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>Returns the username.
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>Sets the username.
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>Returns a String representation of this <strong>VotersList</strong>
<p>
For example, given the following fields:
<ul>

<li>Course Name = “courseName”

<li>Topic Title = “topicTitle”

<li>Reply = “reply”

<li>Username = “username”

<p>
The result of calling toString() would be:
<ul>

<li>courseName`topicTitle`reply`username

<p>
Follow the example formatting exactly!
</li>
</ul>
</li>
</ul>
   </td>
  </tr>
</table>




<h2>Grades</h2>


This class constructs a grade object and contains all methods necessary to manipulate and retrieve information regarding the grade.

**Fields**


<table>
  <tr>
   <td>Field name
   </td>
   <td>Field Type
   </td>
   <td>Access Modifier
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>courseName
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the course name of which the grade is being assigned to.
   </td>
  </tr>
  <tr>
   <td>topicTitle
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the topic title of which the grade is being assigned to.
   </td>
  </tr>
  <tr>
   <td>reply
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the reply which the grade is being assigned to.
   </td>
  </tr>
  <tr>
   <td>username
   </td>
   <td>String
   </td>
   <td>private
   </td>
   <td>Stores the username of the user receiving the grade.
   </td>
  </tr>
  <tr>
   <td>grade
   </td>
   <td>double
   </td>
   <td>private
   </td>
   <td>Stores the value of the grade being assigned.
   </td>
  </tr>
</table>


**Constructor**


<table>
  <tr>
   <td>Access Modifier
   </td>
   <td>Parameter List
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>public
   </td>
   <td>String courseName, 
<p>
String topicTitle, 
<p>
String reply, 
<p>
String username, 
<p>
double grade
   </td>
   <td>Constructs a newly allocated <strong>Grades</strong> object and instantiates the fields to the specified parameters.
   </td>
  </tr>
</table>


**Methods**


<table>
  <tr>
   <td>Method name
   </td>
   <td>Return Type
   </td>
   <td>Access Modifier
   </td>
   <td>Parameters
   </td>
  </tr>
  <tr>
   <td>getCourseName
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setCourseName
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String courseName
   </td>
  </tr>
  <tr>
   <td>getTopicTitle
   </td>
   <td>String
   </td>
   <td>public 
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setTopicTitle
   </td>
   <td>void 
   </td>
   <td>public 
   </td>
   <td>String topicTitle
   </td>
  </tr>
  <tr>
   <td>getReply
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setReply
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String reply
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>String username
   </td>
  </tr>
  <tr>
   <td>getGrade
   </td>
   <td>double
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
  <tr>
   <td>setGrade
   </td>
   <td>void
   </td>
   <td>public
   </td>
   <td>double grade
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>String
   </td>
   <td>public
   </td>
   <td>None
   </td>
  </tr>
</table>


**Method Description**


<table>
  <tr>
   <td>Method
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td>getCourseName
   </td>
   <td>Returns the course name.
   </td>
  </tr>
  <tr>
   <td>setCourseName
   </td>
   <td>Sets the course name.
   </td>
  </tr>
  <tr>
   <td>getTopicTitle
   </td>
   <td>Returns the topic title.
   </td>
  </tr>
  <tr>
   <td>setTopicTitle
   </td>
   <td>Sets the topic title.
   </td>
  </tr>
  <tr>
   <td>getReply
   </td>
   <td>Returns the reply.
   </td>
  </tr>
  <tr>
   <td>setReply
   </td>
   <td>Sets the reply.
   </td>
  </tr>
  <tr>
   <td>getUsername
   </td>
   <td>Returns the username.
   </td>
  </tr>
  <tr>
   <td>setUsername
   </td>
   <td>Sets the username.
   </td>
  </tr>
  <tr>
   <td>getGrade
   </td>
   <td>Returns the grade.
   </td>
  </tr>
  <tr>
   <td>setGrade
   </td>
   <td>Sets the grade.
   </td>
  </tr>
  <tr>
   <td>toString
   </td>
   <td>Returns a String representation of this <strong>Grades</strong>
<p>
For example, given the following fields:
<ul>

<li>Course Name = “courseName”

<li>Topic Title = “topicTitle”

<li>Reply = “reply”

<li>Username = “username”

<li>Grade = “5.5”

<p>
The result of calling toString() would be:
<ul>

<li>courseName`topicTitle`reply`username`5.5

<p>
Follow the example formatting exactly!
</li>
</ul>
</li>
</ul>
   </td>
  </tr>
</table>




<h2>GUIs</h2>


This class includes a main method and menu GUI that lets the users post or reply on the discussion forum. There are two parts of the menu which are the login and sign up section and the discussion forum section. The cancel button or the cross on the top of the panel can be clicked to exit, except for a few places in the program where once a particular task has been chosen, the program cannot exit until it finishes. The detailed descriptions are written below.

