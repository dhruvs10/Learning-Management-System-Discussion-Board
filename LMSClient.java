import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Project 5
 * <p>
 * The program is the Client class for the discussion program between
 * teachers and students along with creating accounts.
 *
 * @author Sanya Gangwani, Dhruv Shah, Akash Mullick, Amo Bai, Suhon Choe
 * @version May 02, 2022
 */


public class LMSClient {

    public static void main(String[] args) {
        try {
            JOptionPane.showMessageDialog(null, "Welcome to the Learning Management System!",
                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
            boolean a = false;
            Socket socket = null;

            do {
                String hostName;
                int portNumber = 0;
                int y = 6;
                do {
                    hostName = JOptionPane.showInputDialog(null, "Please enter the Host Name.",
                            "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                    y = 6;

                    if (hostName == null) { //when the user presses the cross button on top
                        y = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?",
                                "Button Game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (y == JOptionPane.YES_OPTION) {
                            displayFarewell();
                            return;
                        }
                        if (y == JOptionPane.CLOSED_OPTION) {
                            return;
                        }

                    } else if (hostName.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No input received.",
                                "Learning Management System",
                                JOptionPane.ERROR_MESSAGE);
                    }

                } while (hostName == null || hostName.isEmpty() || y == JOptionPane.NO_OPTION);
                boolean b = false;
                int z = 8;

                do {
                    String s = JOptionPane.showInputDialog(null, "Please enter the Port Number",
                            "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                    b = false;
                    if (s == null) {
                        z = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to exit?",
                                "Learning Management System",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (z == JOptionPane.YES_OPTION) {
                            displayFarewell();
                            return;
                        } else if (z == JOptionPane.NO_OPTION) {
                            b = true;
                        } else if (z == JOptionPane.CLOSED_OPTION) {
                            return;
                        }
                    } else if (s != null) {

                        try {
                            portNumber = Integer.parseInt(s);
                            if (portNumber < 0) {
                                JOptionPane.showMessageDialog(null,
                                        "Port Number can not be negative!",
                                        "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                b = true;
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Invalid Input!\n" +
                                            "Please enter a number",
                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                            b = true;
                        }

                    }
                } while (b);

                try {
                    socket = new Socket(hostName, portNumber);
                    if (!socket.isConnected()) {
                        JOptionPane.showMessageDialog(null, "Sorry, couldn't establish a " +
                                        "connection with the Server.\nPlease try again",
                                "Learning Management System", JOptionPane.ERROR_MESSAGE);
                        a = true;

                    }
                    if (socket.isConnected()) {
                        JOptionPane.showMessageDialog(null, "Hurray, connection established!",
                                "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                        a = false;
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Sorry, couldn't establish a" +
                                    " connection with the Server.\nPlease try again",
                            "Learning Management System", JOptionPane.ERROR_MESSAGE);
                    a = true;
                }
            } while (a);


            BufferedReader br = null;
            PrintWriter pw = null;
            try {

                pw = new PrintWriter(socket.getOutputStream());
                pw.flush();
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            String result = "";
            String loginSignupInput = "";
            String[] options = {"Log In", "Sign Up"};

            result = (String) JOptionPane.showInputDialog(null,
                    "Would you like to...", "Learning Management System",
                    JOptionPane.PLAIN_MESSAGE, null, options, null);

            if (result == null) {
                loginSignupInput = "3";
            } else if (result.equals("Log In")) {
                loginSignupInput = "1";
            } else if (result.equals("Sign Up")) {
                loginSignupInput = "2";
            }

            if (loginSignupInput.equals("2")) {
                pw.write("signup"); //1st send
                pw.println();
                pw.flush();
            } else if (loginSignupInput.equals("1")) {
                pw.write("login"); //1st send
                pw.println();
                pw.flush();
            } else if (loginSignupInput.equals("3")) {
                pw.write("exit"); //1st send
                pw.println();
                pw.flush();
                displayFarewell();
                return;
            }

            if (loginSignupInput.equals("2")) {
                String name = "";
                boolean f = false;

                do {
                    try {
                        name = JOptionPane.showInputDialog(null,
                                "Please enter your full name:\n(Warning: You will not be able to " +
                                "change your name later)", "Learning Management System - SignUp",
                                JOptionPane.QUESTION_MESSAGE);
                        f = false;

                        if (name == null) {
                            f = false;
                        } else if (name.indexOf(" ") == -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter your firstname and lastname separated by a space.",
                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                            f = true;
                        } else if (name.indexOf(',') != -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Please DO NOT use any commas in your name!",
                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                            f = true;
                        } else if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Name can not be empty", "Learning" +
                                    "Management System", JOptionPane.ERROR_MESSAGE);
                            f = true;
                        }
                    } catch (NullPointerException n) {
                        f = false;
                    }

                } while (f);

                if (name == null) {
                    pw.write("exit");
                    pw.println();
                    pw.flush();
                    displayFarewell();
                    return;
                } else {
                    pw.write(name); //send 1a (name)
                    pw.println();
                    pw.flush();
                }

                boolean c = false;
                int roleInt = 6;
                String roleString = "";
                String[] options2 = {"Teacher", "Student"};

                roleString = (String) JOptionPane.showInputDialog(null, "Please select your " +
                                "role", "Learning Management System - SignUp",
                        JOptionPane.PLAIN_MESSAGE, null, options2, null);
                if (roleString == null) {
                    roleInt = 3;
                } else if (roleString.equals("Student")) {
                    roleInt = 2;
                } else if (roleString.equals("Teacher")) {
                    roleInt = 1;
                }
                String role = "";

                if (roleInt == 1) {
                    role = "t";
                } else if (roleInt == 2) {
                    role = "s";
                } else if (roleInt == 3) {
                    role = "exit";
                }


                pw.write(role);//send 1b (role)
                pw.println();
                pw.flush();
                if (role.equals("exit")) {
                    displayFarewell();
                    return;
                }

                String username;
                boolean d = false;
                boolean unExists = false;
                do {
                    do {
                        try {
                            username = JOptionPane.showInputDialog(null,
                                    "Please enter a username",
                                    "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                            d = false;
                            if (username == null) {
                                username = "exit";
                                d = false;
                            } else if (username.isEmpty()) {
                                JOptionPane.showMessageDialog(null,
                                        "Username can not be blank! Try again.",
                                        "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                                d = true;
                            } else if (username.indexOf(',') != -1) {

                                JOptionPane.showMessageDialog(null,
                                        "Please do not use any commas in your username! Try again.",
                                        "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                                d = true;
                            }
                        } catch (NullPointerException e) {
                            username = "exit";
                            d = false;
                        }
                    } while (d);
                    if (username.equals("exit")) {
                        pw.write("exit"); //1ca send
                        pw.println();
                        pw.flush();
                        displayFarewell();
                        return;
                    } else {
                        pw.write(username); //send 1cb (Username for checking if it's not already taken)
                        pw.println();
                        pw.flush();
                    }
                    String unameExists = br.readLine(); //1ci/1cii read

                    if (unameExists.equals("unexists")) {
                        unExists = true;
                        JOptionPane.showMessageDialog(null,
                                "Oops, this username is already taken! Try again.",
                                "Learning Management System - SignUp", JOptionPane.INFORMATION_MESSAGE);
                    } else if (unameExists.equals("undexist")) {
                        unExists = false;
                    }

                } while (unExists);

                boolean pswdRePrompt = false;
                String password = "";
                do {
                    try {
                        password = JOptionPane.showInputDialog(null,
                                "Please enter a 6 characters password(DO NOT USE ANY COMMAS):",
                                "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                        pswdRePrompt = false;
                        if (password == null) {
                            password = "exit";
                            pswdRePrompt = false;
                        } else if (password.length() != 6 || password.indexOf(',') != -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Password should be 6 characters long and without a comma!",
                                    "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                            pswdRePrompt = true;
                        }
                    } catch (NullPointerException e) {
                        password = "exit";
                        pswdRePrompt = false;
                    }
                } while (pswdRePrompt);

                if (password.equals("exit")) {
                    pw.write("exit"); //send 1da
                    pw.println();
                    pw.flush();
                } else {
                    pw.write(password); // send 1db (password)
                    pw.println();
                    pw.flush();
                }


                if (br.readLine().equals("usercreated")) { //1e read
                    JOptionPane.showMessageDialog(null, "Hurray, account successfully created!",
                            "Learning Management System - SignUp", JOptionPane.INFORMATION_MESSAGE);
                }


                String logInExitAns;
                String[] options3 = {"Log in", "Exit"};
                try {
                    result = (String) JOptionPane.showInputDialog(null,
                            "Would you like to ...", "Learning Management System",
                            JOptionPane.PLAIN_MESSAGE, null, options3, null);
                    if (result.equals("Log in")) {
                        logInExitAns = "1";
                    } else {
                        logInExitAns = "2";
                    }
                } catch (NullPointerException e) {
                    logInExitAns = "2";
                }

                if (logInExitAns.equals("2")) {
                    pw.write("exit"); //1fi write
                    pw.println();
                    pw.flush();
                    displayFarewell();
                    return;
                } else if (logInExitAns.equals("1")) {
                    pw.write("login"); //1fii write
                    pw.println();
                    pw.flush();
                    loginSignupInput = "1";
                }

            }

            if (loginSignupInput.equals("1")) {

                String isUsersEmpty = br.readLine();// login read 1i


                if (isUsersEmpty.equals("empty")) {
                    JOptionPane.showMessageDialog(null, "You will first need to Sign Up",
                            "Learning Management System", JOptionPane.INFORMATION_MESSAGE);


                    String name = "";
                    boolean nameRePrompt = false;
                    do {

                        name = JOptionPane.showInputDialog(null,
                                "Please enter your full name:\n(Warning: You will not be able to " +
                                "change your name later)", "Learning Management System - SignUp",
                                JOptionPane.QUESTION_MESSAGE);
                        nameRePrompt = false;
                        if (name == null) {
                            nameRePrompt = false;
                        } else if (name.indexOf(" ") == -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter your firstname and lastname separated by a space.",
                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                            nameRePrompt = true;
                        } else if (name.indexOf(',') != -1) {
                            JOptionPane.showMessageDialog(null,
                                    "Please DO NOT use any commas in your name!",
                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                            nameRePrompt = true;
                        } else if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(null,
                                    "Name can not be empty", "Learning" +
                                    "Management System", JOptionPane.ERROR_MESSAGE);
                            nameRePrompt = true;
                        }

                    } while (nameRePrompt);
                    if (name == null) {
                        pw.write("exit");
                        pw.println();
                        pw.flush();
                        displayFarewell();
                        return;
                    } else {
                        pw.write(name); //send 1a (name)
                        pw.println();
                        pw.flush();
                    }

                    int roleInt = 6;
                    String roleString = "";
                    String[] options2 = {"Teacher", "Student"};

                    roleString = (String) JOptionPane.showInputDialog(null,
                            "Please select your " +
                                    "role", "Learning Management System - SignUp",
                            JOptionPane.PLAIN_MESSAGE, null, options2, null);
                    if (roleString == null) {
                        roleInt = 3;
                    } else if (roleString.equals("Student")) {
                        roleInt = 2;
                    } else if (roleString.equals("Teacher")) {
                        roleInt = 1;
                    }
                    String role = "";

                    if (roleInt == 1) {
                        role = "t";
                    } else if (roleInt == 2) {
                        role = "s";
                    } else if (roleInt == 3) {
                        role = "exit";
                    }


                    pw.write(role);//send 1b (role)
                    pw.println();
                    pw.flush();
                    if (role.equals("exit")) {
                        displayFarewell();
                        return;
                    }

                    String username;
                    boolean d = false;
                    boolean unExists = false;
                    do {
                        do {
                            try {
                                username = JOptionPane.showInputDialog(null,
                                        "Please enter a username",
                                        "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                                d = false;
                                if (username.isEmpty()) {
                                    JOptionPane.showMessageDialog(null,
                                            "Username can not be blank! Try again.",
                                            "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                                    d = true;
                                } else if (username.indexOf(',') != -1) {

                                    JOptionPane.showMessageDialog(null,
                                            "Please do not use any commas in your username! Try again.",
                                            "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                                    d = true;
                                }
                            } catch (NullPointerException e) {
                                username = "exit";
                                d = false;
                            }
                        } while (d);
                        if (username.equals("exit")) {
                            pw.write("exit"); //1ca send
                            pw.println();
                            pw.flush();
                            displayFarewell();
                            return;
                        } else {
                            pw.write(username); //send 1cb (Username for checking if it's not already taken)
                            pw.println();
                            pw.flush();
                        }
                        String unameExists = br.readLine(); //1ci/1cii read

                        if (unameExists.equals("unexists")) {
                            unExists = true;
                            JOptionPane.showMessageDialog(null,
                                    "Oops, this username is already taken! Try again.",
                                    "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                        } else if (unameExists.equals("undexist")) {
                            unExists = false;
                        }

                    } while (unExists);

                    String password = "";
                    boolean pswdRePrompt = false;
                    do {

                        try {
                            password = JOptionPane.showInputDialog(null,
                                    "Please enter a 6 characters password(DO NOT USE ANY COMMAS):",
                                    "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                            pswdRePrompt = false;
                            if (password.length() != 6 || password.indexOf(',') != -1) {
                                JOptionPane.showMessageDialog(null,
                                        "Password should be 6 characters long and without a comma!",
                                        "Learning Management System - SignUp", JOptionPane.ERROR_MESSAGE);
                                pswdRePrompt = true;
                            }
                        } catch (NullPointerException e) {
                            password = "exit";
                            pswdRePrompt = false;
                        }
                    } while (pswdRePrompt);

                    if (password.equals("exit")) {
                        pw.write("exit"); //send 1da
                        pw.println();
                        pw.flush();
                    } else {
                        pw.write(password); // send 1db (password)
                        pw.println();
                        pw.flush();
                    }


                    if (br.readLine().equals("usercreated")) { //1e read
                        JOptionPane.showMessageDialog(null,
                                "Hurray, account successfully created!\nYou can now LogIn",
                                "Learning Management System - SignUp", JOptionPane.INFORMATION_MESSAGE);
                        isUsersEmpty = "notempty";
                    }
                }


                String username = "";
                String password = "";
                boolean unExists = true;


                if (isUsersEmpty.equals("notempty")) {
                    do {
                        do {
                            try {
                                username = (String) JOptionPane.showInputDialog(null,
                                        "Username: ",
                                        "Learning Management System - LogIn", JOptionPane.QUESTION_MESSAGE);
                                if (username.isEmpty()) {
                                    JOptionPane.showMessageDialog(null,
                                            "Username can not be empty!",
                                            "Learning Management System - LogIn", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NullPointerException e) {
                                username = "exit";
                            }
                        } while (username.isEmpty());

                        pw.write(username); // 1f login username send
                        pw.println();
                        pw.flush();
                        if (username.equals("exit")) {
                            displayFarewell();
                            return;
                        }

                        String unameExists = br.readLine(); //1g read

                        if (unameExists.equals("undexist")) {
                            JOptionPane.showMessageDialog(null,
                                    "Oops! This username doesn't exist.\nPlease try again!",
                                    "Learning Management System - LogIn", JOptionPane.ERROR_MESSAGE);
                            unExists = false;
                        } else if (unameExists.equals("unexists")) {
                            unExists = true;
                        }
                    } while (!unExists);

                    boolean isPswdCorrect = true;
                    do {
                        do {
                            try {

                                password = (String) JOptionPane.showInputDialog(null,
                                        "Password: ",
                                        "Learning Management System - LogIn", JOptionPane.QUESTION_MESSAGE);
                                if (password.length() != 6) {
                                    JOptionPane.showMessageDialog(null,
                                            "Please enter your 6 characters password!",
                                            "Learning Management System - LogIn", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NullPointerException e) {
                                password = "exit";
                            }
                        } while (password.length() != 6 && !password.equals("exit"));
                        pw.write(password); //1h write password
                        pw.println();
                        pw.flush();
                        if (password.equals("exit")) {
                            displayFarewell();
                            return;
                        }
                        String correctPswd = br.readLine();//1hi read (correct or incorrect pswd)

                        if (correctPswd.equals("correctpswd")) {
                            isPswdCorrect = true;
                        } else if (correctPswd.equals("incorrectpswd")) {
                            JOptionPane.showMessageDialog(null,
                                    "Oops! Incorrect password.\nPlease try again!",
                                    "Learning Management System - LogIn", JOptionPane.ERROR_MESSAGE);
                            isPswdCorrect = false;
                        }

                    } while (!isPswdCorrect);

                    String loggedIn = br.readLine(); // 2nd read
                    if (loggedIn.equals("loggedin")) {
                        JOptionPane.showMessageDialog(null,
                                "Hurray, you've successfully logged in!",
                                "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                    }

                    String afterLogIn = "";
                    String[] options3 = {"Edit account", "Delete account", "Go to the main page", "Exit"};
                    try {
                        afterLogIn = (String) JOptionPane.showInputDialog(null,
                                "What would you like to do", "Learning Management System",
                                JOptionPane.PLAIN_MESSAGE, null, options3, null);
                        if (afterLogIn.equals("Edit account")) {
                            afterLogIn = "1";
                            pw.write("editaccount"); //3a write
                            pw.println();
                            pw.flush();
                        } else if (afterLogIn.equals("Delete account")) {
                            afterLogIn = "2";
                            pw.write("deleteaccount");//3b write
                            pw.println();
                            pw.flush();
                        } else if (afterLogIn.equals("Go to the main page")) {
                            afterLogIn = "3";
                            pw.write("gotomainpage"); //3c write
                            pw.println();
                            pw.flush();
                        } else if (afterLogIn.equals("Exit")) {
                            afterLogIn = "4";
                            pw.write("exit"); //3d write
                            pw.println();
                            pw.flush();
                        }
                    } catch (NullPointerException e) {
                        afterLogIn = "4";
                        pw.write("exit"); //3d write
                        pw.println();
                        pw.flush();
                    }

                    if (afterLogIn.equals("4")) {
                        displayFarewell();
                        return;
                    } else if (afterLogIn.equals("2")) {
                        String accDeleted = br.readLine(); //3e read
                        if (accDeleted.equals("accountdeleted")) {
                            JOptionPane.showMessageDialog(null,
                                    "You've successfully deleted your account.",
                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                            displayFarewell();
                            return;
                        }
                    } else if (afterLogIn.equals("1")) {
                        String changeDetailsInput = "";
                        String[] options4 = {"Username", "Password", "Both"};
                        try {
                            changeDetailsInput = (String) JOptionPane.showInputDialog(null,
                                    "What would you like to change?", "Learning Management System",
                                    JOptionPane.PLAIN_MESSAGE, null, options4, null);
                            if (changeDetailsInput.equals("Username")) {
                                changeDetailsInput = "1";
                                pw.write("changeusername"); // 4a send
                                pw.println();
                                pw.flush();
                            } else if (changeDetailsInput.equals("Password")) {
                                changeDetailsInput = "2";
                                pw.write("changepassword"); // 4b send
                                pw.println();
                                pw.flush();
                            } else {
                                changeDetailsInput = "3";
                                pw.write("changeboth"); // 4c send
                                pw.println();
                                pw.flush();
                            }
                        } catch (NullPointerException e) {
                            pw.write("exit"); // 4c send
                            pw.println();
                            pw.flush();
                            displayFarewell();
                            return;
                        }
                        if (changeDetailsInput.equals("1")) {

                            boolean b = false;
                            boolean c = false;
                            String newUsername = "";
                            do {
                                do {
                                    try {
                                        newUsername = (String) JOptionPane.showInputDialog(null,
                                                "Please enter the new username",
                                                "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                                        b = false;
                                        if (newUsername.isEmpty()) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Username can not be empty!",
                                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                            b = true;
                                        }
                                    } catch (NullPointerException e) {
                                        newUsername = "exit";
                                        b = false;
                                    }
                                } while (b);
                                pw.write(newUsername); // 5th send
                                pw.println();
                                pw.flush();
                                if (newUsername.equals("exit")) {
                                    displayFarewell();
                                    return;
                                }

                                String promptAgain = br.readLine(); // 6 (a,b) read
                                if (promptAgain.equals("unexists")) {
                                    JOptionPane.showMessageDialog(null,
                                            "Sorry, this username already exists.\nTry another one.",
                                            "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                    c = true;
                                } else if (promptAgain.equals("undexist")) {
                                    c = false;
                                }

                            } while (c);

                        } else if (changeDetailsInput.equals("2")) {

                            String newPassword = "";
                            boolean pswdReprompt = false;
                            do {
                                try {
                                    newPassword = (String) JOptionPane.showInputDialog(null,
                                            "Please enter a new 6 characters password(DO NOT USE ANY COMMAS):",
                                            "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                                    pswdReprompt = false;
                                    if (newPassword.length() != 6 || newPassword.indexOf(',') != -1) {
                                        JOptionPane.showMessageDialog(null,
                                                "Password should be 6 characters long and without a comma!",
                                                "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                        pswdReprompt = true;
                                    }
                                } catch (NullPointerException e) {
                                    newPassword = "exit";
                                    pswdReprompt = false;
                                }
                            } while (pswdReprompt);
                            pw.write(newPassword); //7th send
                            pw.println();
                            pw.flush();
                            if (newPassword.equals("exit")) {
                                displayFarewell();
                                return;
                            }
                        } else if (changeDetailsInput.equals("3")) {

                            boolean b = false;
                            boolean c = false;
                            String newUsername = "";
                            do {
                                do {
                                    try {
                                        newUsername = (String) JOptionPane.showInputDialog(null,
                                                "Please enter the new username",
                                                "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                                        b = false;
                                        if (newUsername.isEmpty()) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Username can not be empty!",
                                                    "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                            b = true;
                                        }
                                    } catch (NullPointerException e) {
                                        b = false;
                                        newUsername = "exit";
                                    }
                                } while (b);
                                pw.write(newUsername); // 5th send
                                pw.println();
                                pw.flush();
                                if (newUsername.equals("exit")) {
                                    displayFarewell();
                                    return;
                                }

                                String promptAgain = br.readLine(); // 6 (a,b) read
                                if (promptAgain.equals("unexists")) {
                                    JOptionPane.showMessageDialog(null,
                                            "Sorry, this username already exists.\nTry another one.",
                                            "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                    c = true;
                                } else if (promptAgain.equals("undexist")) {
                                    c = false;
                                }

                            } while (c);

                            String newPassword = "";
                            boolean pswdreprompt = false;
                            do {
                                try {
                                    newPassword = (String) JOptionPane.showInputDialog(null,
                                            "Please enter a new 6 characters password(DO NOT USE ANY COMMAS):",
                                            "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                                    pswdreprompt = false;
                                    if (newPassword.length() != 6 || newPassword.indexOf(',') != -1) {
                                        JOptionPane.showMessageDialog(null,
                                                "Password should be 6 characters long and without a comma!",
                                                "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                        pswdreprompt = true;
                                    }
                                } catch (NullPointerException e) {
                                    newPassword = "exit";
                                    pswdreprompt = false;
                                }
                            } while (pswdreprompt);
                            pw.write(newPassword); //7th send
                            pw.println();
                            pw.flush();
                            if (newPassword.equals("exit")) {
                                displayFarewell();
                                return;
                            }

                        }
                        String successfullyEdited = br.readLine(); //8th read
                        if (successfullyEdited.equals("editedaccount")) {
                            JOptionPane.showMessageDialog(null,
                                    "You've successfully edited your account!",
                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                        }

                        String goingBack = "";
                        String[] options5 = {"Go to the Main Page", "Exit"};
                        try {
                            goingBack = (String) JOptionPane.showInputDialog(null,
                                    "Would you like to ...", "Learning Management System",
                                    JOptionPane.PLAIN_MESSAGE, null, options5, null);
                            if (goingBack.equals("Go to the Main Page")) {
                                goingBack = "1";
                                pw.write("gotomp"); //9a send
                                pw.println();
                                pw.flush();
                                afterLogIn = "3";
                            } else if (goingBack.equals("Exit")) {
                                goingBack = "2";
                                pw.write("exit"); //9b send
                                pw.println();
                                pw.flush();
                                displayFarewell();
                                return;
                            }
                        } catch (NullPointerException e) {
                            goingBack = "2";
                            pw.write("exit"); //9b send
                            pw.println();
                            pw.flush();
                            displayFarewell();
                            return;
                        }

                    }

                    if (afterLogIn.equals("3")) {
                        String loopingBackOrExit;
                        do {
                            String roleOfUser = br.readLine(); //10(a,b) read
                            if (roleOfUser.equals("teacher")) {

                                String goToCourseExit = "";
                                String addGoExit = "";
                                String coursesEmptyOrNot = br.readLine(); //11 (a,b) read
                                if (coursesEmptyOrNot.equals("coursesempty")) {

                                    String courseName;
                                    JOptionPane.showMessageDialog(null,
                                            "No courses have been added yet!",
                                            "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                    do {
                                        try {
                                            courseName = (String) JOptionPane.showInputDialog(null,
                                                    "Please enter the name of the course you'd like to add\n",
                                                    "Learning Management System", JOptionPane.QUESTION_MESSAGE);
                                            if (courseName.isEmpty()) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Course Name can not be empty!",
                                                        "Learning Management System", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } catch (NullPointerException e) {
                                            courseName = "exit";
                                        }
                                    } while (courseName.isEmpty());
                                    if (courseName.equals("exit")) {
                                        pw.write("exit"); //12 a send
                                        pw.println();
                                        pw.flush();
                                        displayFarewell();
                                        return;
                                    } else {
                                        pw.write(courseName); //12b send
                                        pw.println();
                                        pw.flush();

                                        String courseAdded = br.readLine();//13 read
                                        if (courseAdded.equals("courseadded")) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Hurray, course added!",
                                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                        }

                                        String[] options6 = {"Go to a course", "Exit"};
                                        try {
                                            goToCourseExit = (String) JOptionPane.showInputDialog(null,
                                                    "Would you like to ...", "Learning Management System",
                                                    JOptionPane.PLAIN_MESSAGE, null, options6,
                                                    null);
                                            if (goToCourseExit == null) {
                                                goToCourseExit = "Exit";
                                            }
                                        } catch (NullPointerException n) {
                                            goToCourseExit = "Exit";
                                        }
                                        if (goToCourseExit.equals("Go to a course")) {
                                            goToCourseExit = "1";
                                            pw.write("gotocourse");// 14a send
                                            pw.println();
                                            pw.flush();
                                        } else if (goToCourseExit.equals("Exit")) {
                                            pw.write("exit");//14b send
                                            pw.println();
                                            pw.flush();
                                            displayFarewell();
                                            return;
                                        }

                                    }
                                } else if (coursesEmptyOrNot.equals("coursesnotempty")) {

                                    String[] options5 = {"Add a course", "Go to a course", "Exit"};
                                    try {
                                        addGoExit = (String) JOptionPane.showInputDialog(null,
                                                "Would you like to ...", "Learning Management System",
                                                JOptionPane.PLAIN_MESSAGE, null, options5, null);
                                        if (addGoExit == null) {
                                            addGoExit = "Exit";
                                        }
                                    } catch (NullPointerException e) {
                                        addGoExit = "Exit";
                                    }
                                    if (addGoExit.equals("Add a course")) {
                                        addGoExit = "1";
                                    } else if (addGoExit.equals("Go to a course")) {
                                        addGoExit = "2";
                                    } else if (addGoExit.equals("Exit")) {
                                        addGoExit = "3";
                                    }
                                    if (addGoExit.equals("1")) {
                                        pw.write("addcourse");//15a send
                                        pw.println();
                                        pw.flush();

                                        String courseName;
                                        do {
                                            try {
                                                courseName = (String) JOptionPane.showInputDialog(null,
                                                        "Please enter the name of the course you'd " +
                                                                "like to add\n", "Learning Management System",
                                                        JOptionPane.QUESTION_MESSAGE);
                                                if (courseName.isEmpty()) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "Course Name can not be empty!",
                                                            "Learning Management System",
                                                            JOptionPane.ERROR_MESSAGE);
                                                }
                                            } catch (NullPointerException e) {
                                                courseName = "exit";
                                            }
                                        } while (courseName.isEmpty());
                                        if (courseName.equals("exit")) {
                                            pw.write("exit"); //12 a send
                                            pw.println();
                                            pw.flush();
                                            displayFarewell();
                                            return;
                                        } else {
                                            pw.write(courseName); //12b send
                                            pw.println();
                                            pw.flush();

                                            String courseAdded = br.readLine();//13 read
                                            if (courseAdded.equals("courseadded")) {
                                                JOptionPane.showMessageDialog(null,
                                                        "Hurray, course added!",
                                                        "Learning Management System",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }

                                            String[] options6 = {"Go to a course", "Exit"};
                                            try {
                                                goToCourseExit = (String) JOptionPane.showInputDialog(
                                                        null, "Would you like to ...",
                                                        "Learning Management System", JOptionPane.PLAIN_MESSAGE,
                                                        null, options6, null);
                                                if (goToCourseExit == null) {
                                                    goToCourseExit = "Exit";
                                                }
                                            } catch (NullPointerException n) {
                                                goToCourseExit = "Exit";
                                            }
                                            if (goToCourseExit.equals("Go to a course")) {
                                                goToCourseExit = "1";
                                                pw.write("gotocourse");// 14a send
                                                pw.println();
                                                pw.flush();
                                            } else if (goToCourseExit.equals("Exit")) {
                                                pw.write("exit");//14b send
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            }

                                        }

                                    } else if (addGoExit.equals("2")) {
                                        pw.write("gotocourse"); //15b send
                                        pw.println();
                                        pw.flush();
                                    } else if (addGoExit.equals("3")) {
                                        pw.write("exit"); //15c send
                                        pw.println();
                                        pw.flush();
                                        displayFarewell();
                                        return;
                                    }
                                }
                                if (goToCourseExit.equals("1") || addGoExit.equals("2")) {

                                    ArrayList<String> courses = new ArrayList<>();
                                    String activeCourse;
                                    String size = br.readLine(); //16 read
                                    int sizeCoursesArray = Integer.parseInt(size);
                                    for (int i = 0; i < sizeCoursesArray; i++) {
                                        String course = br.readLine(); //17 read (loop)
                                        courses.add(course);
                                    }

                                    String[] options7 = new String[courses.size()];
                                    for (int i = 0; i < options7.length; i++) {
                                        options7[i] = String.valueOf(i + 1);
                                    }
                                    String courseMenu;
                                    try {
                                        courseMenu = (String) JOptionPane.showInputDialog(null,
                                                displayCourses(courses) + "\nWhich course would you" +
                                                        " like to go to?\n",
                                                "Learning Management System", JOptionPane.QUESTION_MESSAGE,
                                                null, options7, null);
                                        if (courseMenu == null) {
                                            courseMenu = "0";
                                        }
                                    } catch (NullPointerException n) {
                                        courseMenu = "0";
                                    }
                                    int courseOption = Integer.parseInt(courseMenu);
                                    if (courseOption == 0) {
                                        activeCourse = "exit";
                                    } else {
                                        activeCourse = courses.get(courseOption - 1);
                                    }
                                    pw.write(activeCourse); //18th send (active course)
                                    pw.println();
                                    pw.flush();

                                    if (activeCourse.equals("exit")) {
                                        displayFarewell();
                                        return;
                                    }

                                    String dTopicsEmptyOrNot = br.readLine(); //19 (a,b) read
                                    String addDFAns;
                                    String goToDFexit = "";
                                    String discussionForumMenu = "";
                                    if (dTopicsEmptyOrNot.equals("nodtopics")) {

                                        JOptionPane.showMessageDialog(null,
                                                "No Discussion Forums have been added to this course yet.",
                                                "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                        String[] options8 = {"Add a Discussion Forum", "Exit"};

                                        try {
                                            addDFAns = (String) JOptionPane.showInputDialog(null,
                                                    "Would you like to...", "Learning Management System",
                                                    JOptionPane.QUESTION_MESSAGE, null, options8,
                                                    null);
                                            if (addDFAns == null) {
                                                addDFAns = "Exit";
                                            }
                                        } catch (NullPointerException n) {
                                            addDFAns = "Exit";
                                        }
                                        if (addDFAns.equals("Add a Discussion Forum")) {
                                            addDFAns = "1";
                                        } else if (addDFAns.equals("Exit")) {
                                            addDFAns = "2";
                                        }
                                        if (addDFAns.equals("1")) {
                                            pw.write("adddf"); //20a write
                                            pw.println();
                                            pw.flush();
                                        } else if (addDFAns.equals("2")) {
                                            pw.write("exit"); //20b write
                                            pw.println();
                                            pw.flush();
                                            displayFarewell();
                                            return;
                                        }
                                        if (addDFAns.equals("1")) {

                                            JOptionPane.showMessageDialog(null,
                                                    "Please note- The format of the Discussion forum is" +
                                                            " as follows:\na) Please write a brief Topic Title in" +
                                                            " the single line.\nb) Next, write a short topic" +
                                                            " description in a single line.\n(If you're importing" +
                                                            " a file, The first line should be the Title and the" +
                                                            " second should be the Description.)",
                                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                            String addOptions = "";
                                            String[] options9 = {"In the TextBox", "Import a file for it"};
                                            boolean promptAgain = false;
                                            do {
                                                try {
                                                    addOptions = (String) JOptionPane.showInputDialog(
                                                            null,
                                                            "Would you like to write the Discussion Forum" +
                                                                    " Title and description...",
                                                            "Learning Management System",
                                                            JOptionPane.QUESTION_MESSAGE, null, options9,
                                                            null);
                                                    promptAgain = false;

                                                    if (addOptions == null) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you add" +
                                                                        " a Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.WARNING_MESSAGE);
                                                        promptAgain = true;
                                                    }
                                                } catch (NullPointerException n) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "You can't exit until you add a Discussion Forum",
                                                            "Learning Management System",
                                                            JOptionPane.WARNING_MESSAGE);
                                                    promptAgain = true;
                                                }
                                            } while (promptAgain);
                                            if (addOptions.equals("In the TextBox")) {
                                                addOptions = "1";
                                            } else if (addOptions.equals("Import a file for it")) {
                                                addOptions = "2";
                                            }
                                            String topicTitle = null;
                                            String topicDescription = null;
                                            if (addOptions.equals("1")) {
                                                boolean promptForumAgain = false;
                                                do {
                                                    try {
                                                        topicTitle = (String) JOptionPane.showInputDialog(
                                                                null,
                                                                "Please enter the Forum Title in a single" +
                                                                        " line", "Learning Management System",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        promptForumAgain = false;
                                                        if (topicTitle.isEmpty()) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Forum Title can not be empty",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            promptForumAgain = true;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you add" +
                                                                        " a Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        promptForumAgain = true;
                                                    }
                                                } while (promptForumAgain);

                                                promptForumAgain = false;
                                                do {
                                                    try {
                                                        topicDescription = (String) JOptionPane.showInputDialog(
                                                                null,
                                                                "Please enter a short Forum Description in a" +
                                                                        " single line",
                                                                "Learning Management System",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        promptForumAgain = false;
                                                        if (topicDescription.isEmpty()) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Forum Description can not be empty",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            promptForumAgain = true;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you add" +
                                                                        " a Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        promptForumAgain = true;
                                                    }
                                                } while (promptForumAgain);


                                            } else if (addOptions.equals("2")) {

                                                boolean errorInFileImport = false;
                                                do {
                                                    String f = "";
                                                    try {
                                                        f = (String) JOptionPane.showInputDialog(null,
                                                                "Please enter the file pathname.",
                                                                "Learning Management System",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        errorInFileImport = false;
                                                        if (f == null) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "You can't exit until you " +
                                                                            "add a Discussion Forum",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            errorInFileImport = true;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you " +
                                                                        "add a Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        errorInFileImport = true;
                                                    }
                                                    if (f != null) {
                                                        try {
                                                            File file = new File(f);

                                                            FileReader fr = new FileReader(file);
                                                            BufferedReader brr = new BufferedReader(fr);
                                                            String s = brr.readLine();
                                                            if (s == null || s.isEmpty()) {

                                                                JOptionPane.showMessageDialog(null,
                                                                        "This first line of this file is" +
                                                                                " empty.\nPlease enter the file path" +
                                                                                " of a non-empty file.",
                                                                        "Learning Management System",
                                                                        JOptionPane.ERROR_MESSAGE);

                                                                errorInFileImport = true;
                                                            } else if (!s.isEmpty() && s != null) {
                                                                topicTitle = s;
                                                                errorInFileImport = false;
                                                                s = brr.readLine();
                                                                if (!s.isEmpty() && s != null) {
                                                                    topicDescription = s;
                                                                    errorInFileImport = false;
                                                                }
                                                            }

                                                            brr.close();
                                                            fr.close();
                                                        } catch (FileNotFoundException fnfe) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "No file with this name was found." +
                                                                            "\nPlease enter the correct file path.",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            errorInFileImport = true;
                                                        } catch (IOException ioe) {
                                                            ioe.printStackTrace();

                                                            JOptionPane.showMessageDialog(null,
                                                                    "\nThere was an error reading your " +
                                                                            "file,\nPlease enter the path name of " +
                                                                            "a valid file again",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            errorInFileImport = true;
                                                        }
                                                    }

                                                } while (errorInFileImport);

                                            }
                                            DiscussionTopic newDT = new DiscussionTopic(activeCourse,
                                                    topicTitle, topicDescription);
                                            pw.write(newDT.toString()); //21 send
                                            pw.println();
                                            pw.flush();
                                            if (br.readLine().equals("dfadded")) { //22 read
                                                JOptionPane.showMessageDialog(null,
                                                        "Hurray, discussion forum successfully added!",
                                                        "Learning Management System",
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                dTopicsEmptyOrNot = "yesdtopics";
                                            }

                                        }
                                    }
                                    if (dTopicsEmptyOrNot.equals("yesdtopics")) {
                                        ArrayList<DiscussionTopic> activeCourseDtopics = new ArrayList<>();
                                        int activeCourseDtopicsSize = Integer.parseInt(br.readLine()); //23 read
                                        for (int i = 0; i < activeCourseDtopicsSize; i++) {
                                            String dtopic = br.readLine(); //24 read (loop)
                                            DiscussionTopic dt = readDiscussionTopicString(dtopic);
                                            activeCourseDtopics.add(dt);
                                        }

                                        String[] options10 = {"Add a Discussion Forum", "Edit a Discussion Forum",
                                                "Delete a Discussion Forum", "Go to a Discussion Forum", "Exit"};

                                        try {
                                            discussionForumMenu = (String) JOptionPane.showInputDialog(
                                                    null,
                                                    discussionTopicsDisplay(activeCourseDtopics) +
                                                            "What would you like to do?",
                                                    "Learning Management System",
                                                    JOptionPane.QUESTION_MESSAGE, null, options10,
                                                    null);
                                            if (discussionForumMenu == null) {
                                                discussionForumMenu = "Exit";
                                            }
                                        } catch (NullPointerException n) {
                                            discussionForumMenu = "Exit";
                                        }

                                        if (discussionForumMenu.equals("Add a Discussion Forum")) {
                                            discussionForumMenu = "1";
                                            pw.write("addforum"); //25 a write
                                            pw.println();
                                            pw.flush();
                                        } else if (discussionForumMenu.equals("Edit a Discussion Forum")) {

                                            discussionForumMenu = "2";
                                            pw.write("editforum"); //25 b write
                                            pw.println();
                                            pw.flush();
                                        } else if (discussionForumMenu.equals("Delete a Discussion Forum")) {
                                            discussionForumMenu = "3";
                                            pw.write("deleteforum"); //25c write
                                            pw.println();
                                            pw.flush();
                                        } else if (discussionForumMenu.equals("Go to a Discussion Forum")) {

                                            discussionForumMenu = "4";
                                            pw.write("gotoforum"); //25d write
                                            pw.println();
                                            pw.flush();
                                        } else if (discussionForumMenu.equals("Exit")) {
                                            discussionForumMenu = "5";
                                            pw.write("exit"); //25e write
                                            pw.println();
                                            pw.flush();
                                        }

                                        if (discussionForumMenu.equals("5")) {
                                            displayFarewell();
                                            return;
                                        } else if (discussionForumMenu.equals("1")) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Please note- The format of the Discussion forum is" +
                                                            " as follows:\na) Please write a brief Topic Title" +
                                                            " in the single line.\nb) Next, write a short topic" +
                                                            " description in a single line.\n(If you're importing" +
                                                            " a file, The first line should be the Title and the" +
                                                            " second should be the Description.)",
                                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                            String addOptions = "";
                                            String[] options9 = {"In the TextBox", "Import a file for it"};
                                            boolean promptAgain = false;
                                            do {
                                                try {
                                                    addOptions = (String) JOptionPane.showInputDialog(
                                                            null,
                                                            "Would you like to write the Discussion " +
                                                                    "Forum Title and description...",
                                                            "Learning Management System",
                                                            JOptionPane.QUESTION_MESSAGE, null, options9,
                                                            null);
                                                    promptAgain = false;
                                                    if (addOptions == null) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you " +
                                                                        "add a Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.WARNING_MESSAGE);
                                                        promptAgain = true;
                                                    }

                                                } catch (NullPointerException n) {
                                                    JOptionPane.showMessageDialog(null,
                                                            "You can't exit until you add a Discussion Forum",
                                                            "Learning Management System",
                                                            JOptionPane.WARNING_MESSAGE);
                                                    promptAgain = true;
                                                }
                                            } while (promptAgain);
                                            if (addOptions.equals("In the TextBox")) {
                                                addOptions = "1";
                                            } else if (addOptions.equals("Import a file for it")) {
                                                addOptions = "2";
                                            }
                                            String topicTitle = null;
                                            String topicDescription = null;
                                            if (addOptions.equals("1")) {
                                                boolean promptForumAgain = false;
                                                do {
                                                    try {
                                                        topicTitle = (String) JOptionPane.showInputDialog(
                                                                null,
                                                                "Please enter the Forum Title in a single" +
                                                                        " line", "Learning Management System",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        promptForumAgain = false;
                                                        if (topicTitle.isEmpty()) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Forum Title can not be empty",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            promptForumAgain = true;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you add a " +
                                                                        "Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        promptForumAgain = true;
                                                    }
                                                } while (promptForumAgain);

                                                promptForumAgain = false;
                                                do {
                                                    try {
                                                        topicDescription = (String) JOptionPane.showInputDialog(
                                                                null,
                                                                "Please enter a short Forum Description in a" +
                                                                        " single line",
                                                                "Learning Management System",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        promptForumAgain = false;
                                                        if (topicDescription.isEmpty()) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "Forum Description can not be empty",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            promptForumAgain = true;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you add a " +
                                                                        "Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        promptForumAgain = true;
                                                    }
                                                } while (promptForumAgain);


                                            } else if (addOptions.equals("2")) {

                                                boolean errorInFileImport = false;
                                                do {
                                                    String f = "";
                                                    try {
                                                        f = (String) JOptionPane.showInputDialog(null,
                                                                "Please enter the file pathname.",
                                                                "Learning Management System",
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        errorInFileImport = false;
                                                        if (f == null) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "You can't exit until you add " +
                                                                            "a Discussion Forum",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            errorInFileImport = true;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null,
                                                                "You can't exit until you add " +
                                                                        "a Discussion Forum",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        errorInFileImport = true;
                                                    }
                                                    if (f != null) {
                                                        try {
                                                            File file = new File(f);

                                                            FileReader fr = new FileReader(file);
                                                            BufferedReader brr = new BufferedReader(fr);
                                                            String s = brr.readLine();
                                                            if (s == null || s.isEmpty()) {

                                                                JOptionPane.showMessageDialog(null,
                                                                        "This first line of this file is " +
                                                                                "empty.\nPlease enter the file path " +
                                                                                "of a non-empty file.",
                                                                        "Learning Management System",
                                                                        JOptionPane.ERROR_MESSAGE);

                                                                errorInFileImport = true;
                                                            } else if (!s.isEmpty() && s != null) {
                                                                topicTitle = s;
                                                                errorInFileImport = false;
                                                                s = brr.readLine();
                                                                if (!s.isEmpty() && s != null) {
                                                                    topicDescription = s;
                                                                    errorInFileImport = false;
                                                                }
                                                            }

                                                            brr.close();
                                                            fr.close();
                                                        } catch (FileNotFoundException fnfe) {
                                                            JOptionPane.showMessageDialog(null,
                                                                    "No file with this name was found.\n" +
                                                                            "Please enter the correct file path.",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            errorInFileImport = true;
                                                        } catch (IOException ioe) {
                                                            ioe.printStackTrace();

                                                            JOptionPane.showMessageDialog(null,
                                                                    "\nThere was an error reading your file," +
                                                                            "\nPlease enter the path name of a " +
                                                                            "valid file again",
                                                                    "Learning Management System",
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            errorInFileImport = true;
                                                        }
                                                    }

                                                } while (errorInFileImport);

                                            }
                                            DiscussionTopic newDT = new DiscussionTopic(activeCourse,
                                                    topicTitle, topicDescription);
                                            pw.write(newDT.toString()); //21 send
                                            pw.println();
                                            pw.flush();
                                            if (br.readLine().equals("dfadded")) { //22 read
                                                JOptionPane.showMessageDialog(null, 
                                                        "Hurray, discussion forum successfully added!",
                                                        "Learning Management System", 
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }


                                            String[] options11 = {"Go to a Discussion Forum", "Exit"};
                                            try {
                                                goToDFexit = (String) JOptionPane.showInputDialog(null,
                                                        "Would you like to...", 
                                                        "Learning Management System", 
                                                        JOptionPane.QUESTION_MESSAGE,
                                                        null, options11, null);
                                                if (goToDFexit == null) {
                                                    goToDFexit = "Exit";
                                                }
                                            } catch (NullPointerException n) {
                                                goToDFexit = "Exit";
                                            }

                                            if (goToDFexit.equals("Go to a Discussion Forum")) {
                                                goToDFexit = "1";
                                                pw.write("gotodf"); //26a write
                                                pw.println();
                                                pw.flush();
                                            } else if (goToDFexit.equals("Exit")) {
                                                goToDFexit = "2";
                                                pw.write("exit"); //26b write
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            }

                                        } else if (discussionForumMenu.equals("2")) {
                                            ArrayList<DiscussionTopic> activeCourseDtopicss = new ArrayList<>();
                                            int activeCourseDtopicsSizee = Integer.parseInt(br.readLine()); //23 read
                                            for (int i = 0; i < activeCourseDtopicsSizee; i++) {
                                                String dtopic = br.readLine(); //24 read (loop)
                                                DiscussionTopic dt = readDiscussionTopicString(dtopic);
                                                activeCourseDtopicss.add(dt);
                                            }

                                            String editForumNum;
                                            int w = 0;
                                            String[] options12 = new String[activeCourseDtopicss.size()];
                                            for (int i = 0; i < options12.length; i++) {
                                                options12[i] = String.valueOf(i + 1);
                                            }

                                            try {
                                                editForumNum = (String) JOptionPane.showInputDialog(null,
                                                        "Which Discussion Forum would you like " +
                                                                "to edit?\n\n" + 
                                                                discussionTopicsDisplay(activeCourseDtopicss),
                                                        "Learning Management System", 
                                                        JOptionPane.QUESTION_MESSAGE, null,
                                                        options12, null);
                                                if (editForumNum == null) {
                                                    editForumNum = "exit";
                                                }
                                            } catch (NullPointerException n) {
                                                editForumNum = "exit";
                                            }
                                            if (editForumNum.equals("exit")) {
                                                pw.write("exit");
                                                pw.println();
                                                pw.flush();
                                                pw.write("exit");
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            } else {

                                                w = Integer.parseInt(editForumNum);
                                                pw.write(activeCourseDtopicss.get(w - 1).getTopicTitle()); 
                                                //27 send (original topictitle)
                                                pw.println();
                                                pw.flush();
                                                pw.write(activeCourseDtopicss.get(w - 1).getTopicDescription()); 
                                                //28 send (original topicdescription)
                                                pw.println();
                                                pw.flush();
                                            }

                                            String newTopicTitle = "";
                                            String newTopicDescription = "";
                                            boolean promptForumAgain = false;
                                            do {
                                                try {
                                                    newTopicTitle = (String) JOptionPane.showInputDialog(
                                                            null,
                                                            "Please enter the edited Topic Title", 
                                                            "Learning Management System",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    promptForumAgain = false;

                                                    if (newTopicTitle.isEmpty()) {
                                                        JOptionPane.showMessageDialog(null, 
                                                                "Forum Title can not be empty",
                                                                "Learning Management System", 
                                                                JOptionPane.ERROR_MESSAGE);
                                                        promptForumAgain = true;
                                                    }
                                                } catch (NullPointerException n) {
                                                    JOptionPane.showMessageDialog(null, 
                                                            "You can't exit until you edit" +
                                                                    " this Discussion Forum",
                                                            "Learning Management System", 
                                                            JOptionPane.ERROR_MESSAGE);
                                                    promptForumAgain = true;
                                                }
                                            } while (promptForumAgain);

                                            promptForumAgain = false;
                                            do {
                                                try {
                                                    newTopicDescription = (String) JOptionPane.showInputDialog(
                                                            null,
                                                            "Please enter the edited Topic Description", 
                                                            "Learning Management System",
                                                            JOptionPane.QUESTION_MESSAGE);
                                                    promptForumAgain = false;

                                                    if (newTopicDescription.isEmpty()) {
                                                        JOptionPane.showMessageDialog(null, 
                                                                "Forum Description can not be empty",
                                                                "Learning Management System",
                                                                JOptionPane.ERROR_MESSAGE);
                                                        promptForumAgain = true;
                                                    }
                                                } catch (NullPointerException n) {
                                                    JOptionPane.showMessageDialog(null, 
                                                            "You can't exit until you edit this Discussion Forum",
                                                            "Learning Management System", 
                                                            JOptionPane.ERROR_MESSAGE);
                                                    promptForumAgain = true;
                                                }
                                            } while (promptForumAgain);

                                            pw.write(newTopicTitle); //29th write (edited topic title)
                                            pw.println();
                                            pw.flush();
                                            pw.write(newTopicDescription); //30th write (edited topic desc)
                                            pw.println();
                                            pw.flush();
                                            if (br.readLine().equals("succesfullyedited")) {
                                                JOptionPane.showMessageDialog(null, 
                                                        "Hurray, discussion forum successfully edited!",
                                                        "Learning Management System", 
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }

                                            String[] options11 = {"Go to a Discussion Forum", "Exit"};
                                            try {
                                                goToDFexit = (String) JOptionPane.showInputDialog(null,
                                                        "Would you like to...", 
                                                        "Learning Management System", JOptionPane.QUESTION_MESSAGE,
                                                        null, options11, null);
                                                if (goToDFexit == null) {
                                                    goToDFexit = "Exit";
                                                }
                                            } catch (NullPointerException n) {
                                                goToDFexit = "Exit";
                                            }

                                            if (goToDFexit.equals("Go to a Discussion Forum")) {
                                                goToDFexit = "1";
                                                pw.write("gotodf"); //26a write
                                                pw.println();
                                                pw.flush();
                                            } else if (goToDFexit.equals("Exit")) {
                                                goToDFexit = "2";
                                                pw.write("exit"); //26b write
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            }
                                        } else if (discussionForumMenu.equals("3")) {

                                            ArrayList<DiscussionTopic> activeCourseDtopicss = new ArrayList<>();
                                            int activeCourseDtopicsSizee = Integer.parseInt(br.readLine()); //23 read
                                            for (int i = 0; i < activeCourseDtopicsSizee; i++) {
                                                String dtopic = br.readLine(); //24 read (loop)
                                                DiscussionTopic dt = readDiscussionTopicString(dtopic);
                                                activeCourseDtopicss.add(dt);
                                            }

                                            String deleteForumNum;
                                            int w = 0;

                                            String[] options12 = new String[activeCourseDtopicss.size()];
                                            for (int i = 0; i < activeCourseDtopicss.size(); i++) {
                                                options12[i] = String.valueOf(i + 1);
                                            }

                                            try {
                                                deleteForumNum = (String) JOptionPane.showInputDialog(
                                                        null,
                                                        "Which Discussion Forum would you like to delete?" + 
                                                                discussionTopicsDisplay(activeCourseDtopicss),
                                                        "Learning Management System", 
                                                        JOptionPane.QUESTION_MESSAGE, null,
                                                        options12, null);
                                                if (deleteForumNum == null) {
                                                    deleteForumNum = "exit";
                                                }
                                            } catch (NullPointerException n) {
                                                deleteForumNum = "exit";
                                            }
                                            if (deleteForumNum.equals("exit")) {
                                                pw.write("exit");
                                                pw.println();
                                                pw.flush();
                                                pw.write("exit");
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            } else {
                                                w = Integer.parseInt(deleteForumNum);
                                                pw.write(activeCourseDtopicss.get(w - 1).getTopicTitle()); 
                                                //27 send (original topictitle) repeat
                                                pw.println();
                                                pw.flush();
                                                pw.write(activeCourseDtopicss.get(w - 1).getTopicDescription());
                                                //28 send (original topicdescription) repeat
                                                pw.println();
                                                pw.flush();
                                            }

                                            if (br.readLine().equals("successfullydeleted")) {
                                                JOptionPane.showMessageDialog(null, 
                                                        "Discussion forum successfully deleted!",
                                                        "Learning Management System", 
                                                        JOptionPane.INFORMATION_MESSAGE);
                                            }
                                            String[] options11 = {"Go to a Discussion Forum", "Exit"};
                                            try {
                                                goToDFexit = (String) JOptionPane.showInputDialog(null,
                                                        "Would you like to...", 
                                                        "Learning Management System", JOptionPane.QUESTION_MESSAGE,
                                                        null, options11, null);
                                                if (goToDFexit == null) {
                                                    goToDFexit = "Exit";
                                                }
                                            } catch (NullPointerException n) {
                                                goToDFexit = "Exit";
                                            }

                                            if (goToDFexit.equals("Go to a Discussion Forum")) {
                                                goToDFexit = "1";
                                                pw.write("gotodf"); //26a write
                                                pw.println();
                                                pw.flush();
                                            } else if (goToDFexit.equals("Exit")) {
                                                goToDFexit = "2";
                                                pw.write("exit"); //26b write
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            }
                                        }
                                    }

                                    if (discussionForumMenu.equals("4") || goToDFexit.equals("1")) {
                                        String dteon = br.readLine(); //33(a,b) read

                                        if (dteon.equals("nodf")) {
                                            JOptionPane.showMessageDialog(null, 
                                                    "Sorry there are no discussion forums " +
                                                            "in this course anymore.",
                                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                           
                                        } else if (dteon.equals("yesdf")) {
                                            int activeCoursedtsize = Integer.parseInt(br.readLine()); //34 read
                                            ArrayList<DiscussionTopic> activeCourseDtopics = new ArrayList<>();
                                            for (int i = 0; i < activeCoursedtsize; i++) {
                                                String dtopic = br.readLine(); //24 read (loop)
                                                DiscussionTopic dt = readDiscussionTopicString(dtopic);
                                                activeCourseDtopics.add(dt);
                                            }
                                            String dfNumString = "";
                                            String[] options13 = new String[activeCourseDtopics.size()];
                                            for (int i = 0; i < activeCourseDtopics.size(); i++) {
                                                options13[i] = String.valueOf(i + 1);
                                            }
                                            try {
                                                dfNumString = (String) JOptionPane.showInputDialog(null,
                                                        discussionTopicsDisplay(activeCourseDtopics) +
                                                                "\nWhich Discussion Forum would you like to go to?\n",
                                                        "Learning Management System", 
                                                        JOptionPane.QUESTION_MESSAGE, null, options13, 
                                                        null);

                                                if (dfNumString == null) {
                                                    dfNumString = "exit";
                                                }
                                            } catch (NullPointerException n) {
                                                dfNumString = "exit";
                                            }
                                            String topicTitle;
                                            String topicDescription;
                                            String topicTimestamp;
                                            if (dfNumString.equals("exit")) {
                                                pw.write("exit");
                                                pw.println();
                                                pw.flush();
                                                pw.write("exit");
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            } else {
                                                int dfNum = Integer.parseInt(dfNumString);
                                                topicTitle = activeCourseDtopics.get(dfNum - 1).getTopicTitle();
                                                topicDescription = 
                                                        activeCourseDtopics.get(dfNum - 1).getTopicDescription();
                                                topicTimestamp = activeCourseDtopics.get(dfNum - 1).getTimestamp();
                                                pw.write(topicTitle);//35 send
                                                pw.println();
                                                pw.flush();
                                                pw.write(topicDescription); //36 send
                                                pw.println();
                                                pw.flush();
                                            }

                                            String repliesExistOrNot = br.readLine(); //37 (a,b) read
                                            if (repliesExistOrNot.equals("noreplies")) {
                                                JOptionPane.showMessageDialog(null, 
                                                        "Sorry, there are no replies to this " +
                                                                "Discussion Forum yet!",
                                                        "Learning Management System", 
                                                        JOptionPane.INFORMATION_MESSAGE);
                                                //we want to ask the end question of looping back after this
                                            } else if (repliesExistOrNot.equals("yesreplies")) {
                                                ArrayList<Replies> activeDTReplies = new ArrayList<>();
                                                ArrayList<Comments> activeDTComments = new ArrayList<>();
                                                int activeDTRepliesSize = Integer.parseInt(br.readLine());  //38 read

                                                for (int i = 0; i < activeDTRepliesSize; i++) {
                                                    String replyObjString = br.readLine(); //39 read (loop)
                                                    Replies replyObject = readRepliesString(replyObjString);
                                                    activeDTReplies.add(replyObject);
                                                }
                                                String commentsExistOrNot = br.readLine(); //40 (a,b) read
                                                if (commentsExistOrNot.equals("yescomments")) {
                                                    int commentsALSize = Integer.parseInt(br.readLine()); //41 read
                                                    for (int i = 0; i < commentsALSize; i++) {
                                                        Comments commentObject = readCommentsString(br.readLine()); 
                                                        //42 read (loop)
                                                        activeDTComments.add(commentObject);
                                                    }
                                                }

                                                String[] options14 = 
                                                        {"Comment on a student's post", "Move ahead", "Exit"};
                                                String commentorExit;
                                                try {
                                                    commentorExit = (String) JOptionPane.showInputDialog(
                                                            null, 
                                                            dtDisplay(activeDTReplies, activeDTComments) + 
                                                                    "\nWould you like to...",
                                                            "Learning Management System", 
                                                            JOptionPane.QUESTION_MESSAGE, null, options14, 
                                                            null);
                                                    if (commentorExit == null) {
                                                        commentorExit = "Exit";
                                                    }
                                                } catch (NullPointerException n) {
                                                    commentorExit = "Exit";
                                                }

                                                if (commentorExit.equals("Exit")) {
                                                    pw.write("exit"); //43b write
                                                    pw.println();
                                                    pw.flush();
                                                    displayFarewell();
                                                    return;
                                                } else if (commentorExit.equals("Move ahead")) {
                                                    commentorExit = "2";
                                                    pw.write("moveahead"); //43 c write
                                                    pw.println();
                                                    pw.flush();
                                                } else if (commentorExit.equals("Comment on a student's post")) {
                                                    commentorExit = "1";
                                                    pw.write("teacherwantstocomment"); //43a write
                                                    pw.println();
                                                    pw.flush();
                                                    String teacherUsername = br.readLine(); //44 read

                                                    String[] options15 = new String[activeDTReplies.size()];
                                                    for (int i = 0; i < options15.length; i++) {
                                                        options15[i] = String.valueOf(i + 1);
                                                    }


                                                    String postNumber = "";
                                                    boolean askforcommentAgain = false;
                                                    do {
                                                        try {
                                                            postNumber = (String) JOptionPane.showInputDialog(
                                                                    null, 
                                                                    dtDisplay(activeDTReplies, activeDTComments)
                                                                            + "\nWhich post would you like to " +
                                                                            "comment on?\n",
                                                                    "Learning Management System", 
                                                                    JOptionPane.QUESTION_MESSAGE, null, 
                                                                    options15, null);

                                                            askforcommentAgain = false;
                                                            if (postNumber == null) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "You can't exit until you add a " +
                                                                                "comment",
                                                                        "Learning Management System", 
                                                                        JOptionPane.ERROR_MESSAGE);
                                                                askforcommentAgain = true;
                                                            }
                                                        } catch (NullPointerException n) {
                                                            JOptionPane.showMessageDialog(null, 
                                                                    "You can't exit until you add a comment",
                                                                    "Learning Management System", 
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            askforcommentAgain = true;
                                                        }
                                                    } while (askforcommentAgain);
                                                    int postNum = Integer.parseInt(postNumber);
                                                    String teacherComment = "";
                                                    askforcommentAgain = false;
                                                    do {


                                                        try {
                                                            teacherComment = (String) JOptionPane.showInputDialog(
                                                                    null, "Please enter " +
                                                                            "your comment in a single line.", 
                                                                    "Learning Management System", 
                                                                    JOptionPane.QUESTION_MESSAGE);

                                                            askforcommentAgain = false;
                                                            if (teacherComment == null) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "You can't exit until you add a " +
                                                                                "comment",
                                                                        "Learning Management System", 
                                                                        JOptionPane.ERROR_MESSAGE);
                                                                askforcommentAgain = true;
                                                            } else if (teacherComment.isEmpty()) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "Comment can not be empty.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                askforcommentAgain = true;
                                                            }

                                                        } catch (NullPointerException n) {

                                                            JOptionPane.showMessageDialog(null, 
                                                                    "You can't exit until you add a comment",
                                                                    "Learning Management System", 
                                                                    JOptionPane.ERROR_MESSAGE);
                                                            askforcommentAgain = true;
                                                        }

                                                    } while (askforcommentAgain);
                                                    Replies studentReply = activeDTReplies.get(activeDTReplies.size() -
                                                            postNum);
                                                    Comments teacherCommentObj = new Comments(studentReply,
                                                            teacherUsername, teacherComment);
                                                    pw.write(teacherCommentObj.toString()); // 45 write
                                                    pw.println();
                                                    pw.flush();

                                                    Replies replyObj = readRepliesString(br.readLine()); //46 read
                                                    String postUsername = replyObj.getUsername();
                                                    String postTimestamp = replyObj.getTimestamp();
                                                    String postContent = replyObj.getReply();

                                                    ArrayList<Comments> editedComments = new ArrayList<>();
                                                    int numOfCommentsTeacherComment = Integer.parseInt(br.readLine());
                                                    //47 read
                                                    for (int i = 0; i < numOfCommentsTeacherComment; i++) {
                                                        Comments commentObj = readCommentsString(br.readLine()); 
                                                        //48 read (loop)
                                                        editedComments.add(commentObj);
                                                    }

                                                    JOptionPane.showMessageDialog(null, 
                                                            commentsDisplay(editedComments) + "\n\nYour" +
                                                                    " comment was successfully added!", 
                                                            "Learning Management System",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }

                                            }

                                        }
                                    }
                                    //votes
                                    String askForVoteChecking = br.readLine(); //49 (a,b) read
                                    if (askForVoteChecking.equals("askforvotechecking")) {

                                        int checkVotesAns;
                                        try {
                                            checkVotesAns = JOptionPane.showConfirmDialog(null, 
                                                    "Would you like to view the highest votes for each" +
                                                            " forum?\n",
                                                    "Learning Management System", JOptionPane.YES_NO_OPTION,
                                                    JOptionPane.QUESTION_MESSAGE);
                                            if (checkVotesAns == JOptionPane.CLOSED_OPTION) {
                                                checkVotesAns = 3;
                                            }

                                        } catch (NullPointerException n) {
                                            checkVotesAns = 3;
                                        }

                                        if (checkVotesAns == 3) {
                                            pw.write("exit"); //50a write
                                            pw.println();
                                            pw.flush();
                                            displayFarewell();
                                            return;
                                        } else if (checkVotesAns == JOptionPane.NO_OPTION) {
                                            pw.write("teacherdoesntwanttocheckvotes"); //50b write
                                            pw.println();
                                            pw.flush();
                                        } else if (checkVotesAns == JOptionPane.YES_OPTION) {
                                            pw.write("teacherwantstocheckvotes"); //50c write
                                            pw.println();
                                            pw.flush();

                                            int numOfDf = Integer.parseInt(br.readLine()); //51 read

                                            StringBuilder sb = new StringBuilder();
                                            for (int i = 0; i < numOfDf; i++) {


                                                String topicTitle = br.readLine(); //52 read
                                                String topicTimestamp = br.readLine(); //53 read
                                                String topicDescription = br.readLine(); //54 read

                                                String topicPart = String.format("Discussion Forum Topic:" +
                                                                " %s (%s)\nDescription: %s\n\n",
                                                        topicTitle, topicTimestamp, stringMethod(topicDescription));

                                                sb.append(topicPart);
                                                String repliesEmptyOrNot = br.readLine();//55 (a,b,c) read

                                                if (repliesEmptyOrNot.equals("noreplies")) {
                                                    
                                                    sb.append("This Discussion Forum has no replies.\n\n");
                                                } else if (repliesEmptyOrNot.equals("yesrepliesbutallzero")) {

                                                    sb.append("All the replies to this Discussion Forum " +
                                                            "currently have 0 votes.\n\n");
                                                } else if (repliesEmptyOrNot.equals("yesrepliesbutnotallzero")) {
                                                    ArrayList<Replies> highestVotedRepliesArrayList = new ArrayList<>();
                                                    ArrayList<String> highestVotedRepliesNameArrayList = 
                                                            new ArrayList<>();

                                                    int loopRounds = Integer.parseInt(br.readLine());// 56 read

                                                    for (int m = 0; m < loopRounds; m++) {
                                                        highestVotedRepliesArrayList.add(readRepliesString(br.readLine())); 
                                                        //58 read (loop)
                                                        highestVotedRepliesNameArrayList.add(br.readLine()); 
                                                        //58 read (loop)
                                                    }
                                                    
                                                    sb.append(votesDisplayTeacher(highestVotedRepliesArrayList, 
                                                            highestVotedRepliesNameArrayList));

                                                }

                                            }
                                            JOptionPane.showMessageDialog(null,
                                                    sb.toString(), "Learning Management System", 
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    }

                                }

                                int gradingAns;
                                try {
                                    gradingAns = JOptionPane.showConfirmDialog(null, 
                                            "Would you like to grade a student?\n",
                                            "Learning Management System", JOptionPane.YES_NO_OPTION, 
                                            JOptionPane.QUESTION_MESSAGE);
                                    if (gradingAns == JOptionPane.CLOSED_OPTION) {
                                        gradingAns = 3;
                                    }

                                } catch (NullPointerException n) {
                                    gradingAns = 3;
                                }
                                if (gradingAns == JOptionPane.YES_OPTION) {
                                    pw.write("teacherwantstograde"); //59 a write
                                    pw.println();
                                    pw.flush();

                                    String studentsEmptyOrNot = br.readLine(); //60 (a,b) read
                                    if (studentsEmptyOrNot.equals("nostudents")) {

                                        JOptionPane.showMessageDialog(null, "Sorry, " +
                                                        "no students have signed up yet :(",
                                                "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                    } else if (studentsEmptyOrNot.equals("studentsnotempty")) {

                                        ArrayList<User> students = new ArrayList<>();
                                        int studentSize = Integer.parseInt(br.readLine()); //61 read
                                        for (int i = 0; i < studentSize; i++) {
                                            User student = readUserString(br.readLine()); //62 read (loop)
                                            students.add(student);
                                        }

                                        StringBuilder sb = new StringBuilder();

                                        for (int i = 0; i < students.size(); i++) {
                                            String s = String.format("%d. %s (%s)\n", i + 1, 
                                                    students.get(i).getName(),
                                                    students.get(i).getUsername());
                                            sb.append(s);
                                        }
                                        String[] options16 = new String[students.size()];
                                        for (int i = 0; i < options16.length; i++) {
                                            options16[i] = String.valueOf(i + 1);
                                        }

                                        String studentChoiceString;
                                        try {
                                            studentChoiceString = (String) JOptionPane.showInputDialog(
                                                    null,
                                                    sb.toString() + "\nWhich student would you like to grade?",
                                                    "Learning Management System",
                                                    JOptionPane.QUESTION_MESSAGE, null, options16, 
                                                    null);
                                            if (studentChoiceString == null) {
                                                studentChoiceString = "exit";
                                            }
                                        } catch (NullPointerException n) {
                                            studentChoiceString = "exit";
                                        }

                                        if (studentChoiceString.equals("exit")) {
                                            pw.write("exit");
                                            pw.println();
                                            pw.flush();
                                            displayFarewell();
                                            return;

                                        } else {
                                            int studentChoice = Integer.parseInt(studentChoiceString);
                                            String studentUsername = students.get(studentChoice - 1).getUsername();
                                            pw.write(studentUsername); //63 write
                                            pw.println();
                                            pw.flush();
                                        }

                                        String postsByStudentOrNot = br.readLine(); //64 (a,b) read
                                        if (postsByStudentOrNot.equals("nopostsbythisstudent")) {

                                            JOptionPane.showMessageDialog(null, 
                                                    "There are no posts from this student yet " +
                                                            "or a teacher has already graded them!",
                                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                        } else if (postsByStudentOrNot.equals("yespostsbythisstudent")) {

                                            int selectedStudentRepliesSize = Integer.parseInt(br.readLine()); //65 read
                                            ArrayList<Replies> selectedStudentReplies = new ArrayList<>();
                                            ArrayList<Grades> gradesList = new ArrayList<>();

                                            for (int i = 0; i < selectedStudentRepliesSize; i++) {
                                                Replies selectedstudentReply = readRepliesString(br.readLine()); 
                                                //66 read (loop)
                                                selectedStudentReplies.add(selectedstudentReply);
                                            }

                                            for (int i = 0; i < selectedStudentReplies.size(); i++) {
                                                String s = String.format("What grade will you assign to post %d. ?\n" +
                                                        "(Please assign a point value grade out of 10 to each post " +
                                                        "and write it in decimal format up to one decimal point)\n", 
                                                        i + 1);
                                                double grade = 0.0;
                                                boolean why = false;
                                                do {
                                                    try {
                                                        String t = (String) JOptionPane.showInputDialog(
                                                                null, 
                                                                gradesDisplayTeacher(selectedStudentReplies) +
                                                                s, "Learning Management System", 
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        if (t == null) {
                                                            JOptionPane.showMessageDialog(null, 
                                                                    "You can't exit until you " +
                                                                            "assign grades to this student.",
                                                                    "Learning Management System", 
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                            why = true;
                                                        } else {
                                                            try {
                                                                grade = Double.parseDouble(t);
                                                                why = false;
                                                                if (grade < 0.0 || grade > 10.0) {

                                                                    JOptionPane.showMessageDialog(null, 
                                                                            "Please enter a valid input.",
                                                                            "Learning Management System", 
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    why = true;
                                                                }
                                                            } catch (Exception e) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "Please enter a valid input.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                why = true;
                                                            }
                                                        }
                                                    } catch (NullPointerException n) {
                                                        JOptionPane.showMessageDialog(null, 
                                                                "You can't exit until you assign " +
                                                                        "grades to this student.",
                                                                "Learning Management System", 
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        why = true;
                                                    }
                                                } while (why);

                                                Grades gradesObj = new Grades(selectedStudentReplies.get(i).getDt().
                                                        getCourseName(), 
                                                        selectedStudentReplies.get(i).getDt().getTopicTitle(),
                                                        selectedStudentReplies.get(i).getReply(),
                                                        selectedStudentReplies.get(i).getUsername(), grade);
                                                gradesList.add(gradesObj);

                                            }
                                            pw.write(String.valueOf(gradesList.size())); // 67 send
                                            pw.println();
                                            pw.flush();
                                            for (int i = 0; i < gradesList.size(); i++) {
                                                pw.write(gradesList.get(i).toString()); //68 send (loop)
                                                pw.println();
                                                pw.flush();
                                            }
                                            JOptionPane.showMessageDialog(null, 
                                                    "You've successfully assigned the grades!",
                                                    "Learning Management System", 
                                                    JOptionPane.INFORMATION_MESSAGE);

                                        }

                                    }

                                } else if (gradingAns == JOptionPane.NO_OPTION) {
                                    pw.write("teacherdoesntwanttograde");//59b write
                                    pw.println();
                                    pw.flush();
                                } else if (gradingAns == 3) {
                                    pw.write("exit"); //59c write
                                    pw.println();
                                    pw.flush();
                                    displayFarewell();
                                    return;
                                }

                            } else if (roleOfUser.equals("student")) {// teacher part ends here

                                String coursesOrNot = br.readLine(); //s1 (a,b) read
                                if (coursesOrNot.equals("nocourses")) {

                                    JOptionPane.showMessageDialog(null, "Sorry, " +
                                                    "no courses have been added yet.\nVisit after some time!",
                                            "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                    displayFarewell();
                                    return;
                                } else if (coursesOrNot.equals("yescourses")) {
                                    int coursesSize = Integer.parseInt(br.readLine()); // s2 read
                                    ArrayList<String> courses = new ArrayList<>();
                                    for (int i = 0; i < coursesSize; i++) {
                                        courses.add(br.readLine()); //s3 read (loop)
                                    }

                                    String[] options17 = new String[courses.size()];
                                    for (int i = 0; i < options17.length; i++) {
                                        options17[i] = String.valueOf(i + 1);
                                    }
                                    String courseChoiceString = "";
                                    try {
                                        courseChoiceString = (String) JOptionPane.showInputDialog(
                                                null, displayCourses(courses) + "\nWhich" +
                                                        " course would you like to go to?\n", 
                                                "Learning Management System", JOptionPane.QUESTION_MESSAGE,
                                                null, options17, null);
                                        if (courseChoiceString == null) {
                                            courseChoiceString = "0";
                                        }
                                    } catch (NullPointerException n) {
                                        courseChoiceString = "0";
                                    }

                                    int courseChoice = Integer.parseInt(courseChoiceString);
                                    if (courseChoice == 0) {
                                        pw.write("exit"); //s4a send
                                        pw.println();
                                        pw.flush();
                                        displayFarewell();
                                        return;
                                    } else {
                                        String activeCourse = courses.get(courseChoice - 1);
                                        //System.out.println(activeCourse);
                                        pw.write(activeCourse); //s4b send
                                        pw.println();
                                        pw.flush();

                                        String dTopicsOrNot = br.readLine(); //s5 (a,b) read
                                        if (dTopicsOrNot.equals("nodtopics")) {

                                            JOptionPane.showMessageDialog(null, "No " +
                                                            "Discussion Forums have been added to this course yet!",
                                                    "Learning Management System", JOptionPane.INFORMATION_MESSAGE);
                                        } else if (dTopicsOrNot.equals("yesdtopics")) {
                                            int activeCourseDTopicsSize = Integer.parseInt(br.readLine()); // s6 read
                                            ArrayList<DiscussionTopic> activeCourseDTopics = new ArrayList<>();

                                            for (int i = 0; i < activeCourseDTopicsSize; i++) {
                                                DiscussionTopic dtObj = readDiscussionTopicString(br.readLine()); 
                                                // s7 read(loop)
                                                activeCourseDTopics.add(dtObj);
                                            }

                                            String dfNumString = "";
                                            String[] options13 = new String[activeCourseDTopics.size()];
                                            for (int i = 0; i < activeCourseDTopics.size(); i++) {
                                                options13[i] = String.valueOf(i + 1);
                                            }

                                            try {
                                                dfNumString = (String) JOptionPane.showInputDialog(null,
                                                        discussionTopicsDisplay(activeCourseDTopics) + 
                                                                "\nWhich Discussion Forum would you like to go to?\n",
                                                        "Learning Management System", 
                                                        JOptionPane.QUESTION_MESSAGE, null, options13, 
                                                        null);

                                                if (dfNumString == null) {
                                                    dfNumString = "0";
                                                }
                                            } catch (NullPointerException n) {
                                                dfNumString = "0";
                                            }

                                            int dtChoice;

                                            dtChoice = Integer.parseInt(dfNumString);

                                            if (dtChoice == 0) {
                                                pw.write("exit"); //s8a send
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            } else {
                                                pw.write("dfselected"); //s8b send
                                                pw.println();
                                                pw.flush();

                                                String topicTitle = 
                                                        activeCourseDTopics.get(dtChoice - 1).getTopicTitle();
                                                String topicDescription = 
                                                        activeCourseDTopics.get(dtChoice - 1).getTopicDescription();
                                                String topicTimestamp = 
                                                        activeCourseDTopics.get(dtChoice - 1).getTimestamp();

                                                pw.write(topicTitle); //s9 send
                                                pw.println();
                                                pw.flush();

                                                pw.write(topicDescription); //s10 send
                                                pw.println();
                                                pw.flush();

                                                String repliesExistOrNot = br.readLine(); //37 (a,b) read
                                                if (repliesExistOrNot.equals("noreplies")) {

                                                    JOptionPane.showMessageDialog(null, 
                                                            "Sorry, there are no replies to this " +
                                                                    "Discussion Forum yet!",
                                                            "Learning Management System",
                                                            JOptionPane.INFORMATION_MESSAGE);

                                                } else if (repliesExistOrNot.equals("yesreplies")) {
                                                    ArrayList<Replies> activeDTReplies = new ArrayList<>();
                                                    ArrayList<Comments> activeDTComments = new ArrayList<>();
                                                    int activeDTRepliesSize = Integer.parseInt(br.readLine());//38 read

                                                    for (int i = 0; i < activeDTRepliesSize; i++) {
                                                        String replyObjString = br.readLine(); //39 read (loop)
                                                        Replies replyObject = readRepliesString(replyObjString);
                                                        activeDTReplies.add(replyObject);
                                                    }
                                                    String commentsExistOrNot = br.readLine(); //40 (a,b) read
                                                    if (commentsExistOrNot.equals("yescomments")) {
                                                        int commentsALSize = Integer.parseInt(br.readLine()); //41 read
                                                        for (int i = 0; i < commentsALSize; i++) {
                                                            Comments commentObject = readCommentsString(br.readLine()); 
                                                            //42 read (loop)
                                                            activeDTComments.add(commentObject);
                                                        }
                                                    }

                                                    JOptionPane.showMessageDialog(null, 
                                                            dtDisplay(activeDTReplies, activeDTComments),
                                                            "Learning Management System", 
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }
                                                int addToDF;

                                                try {
                                                    addToDF = JOptionPane.showConfirmDialog(null, 
                                                            "Would you like to add a post " +
                                                                    "to this Discussion Forum?",
                                                            "Learning Management System", 
                                                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                                                    if (addToDF == JOptionPane.CLOSED_OPTION) {
                                                        addToDF = 3;
                                                    }

                                                } catch (NullPointerException n) {
                                                    addToDF = 3;
                                                }

                                                if (addToDF == JOptionPane.YES_OPTION) {
                                                    pw.write("studentwillpost"); //s11a send
                                                    pw.println();
                                                    pw.flush();
                                                    String activeUserUsername = br.readLine(); //s12 read

                                                    JOptionPane.showMessageDialog(null, 
                                                            "Note: Please write the post as a single line!",
                                                            "Learning Management System", 
                                                            JOptionPane.INFORMATION_MESSAGE);

                                                    String[] options18 = {"In the text box", "Import a file for it"};

                                                    String addPostOptions = "";
                                                    boolean askForPostAgain = false;
                                                    do {
                                                        try {
                                                            addPostOptions = (String) JOptionPane.showInputDialog(
                                                                    null, "Would you like to " +
                                                                            "write the post...",
                                                                    "Learning Management System", 
                                                                    JOptionPane.QUESTION_MESSAGE, null, options18,
                                                                    null);

                                                            askForPostAgain = false;
                                                            if (addPostOptions == null) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "You can't exit until " +
                                                                                "you add your post.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                askForPostAgain = true;
                                                            }
                                                        } catch (NullPointerException n) {
                                                            JOptionPane.showMessageDialog(null, 
                                                                    "You can't exit until you add your post.",
                                                                    "Learning Management System", 
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                            askForPostAgain = true;
                                                        }
                                                    } while (askForPostAgain);
                                                    String newPost = null;
                                                    Replies reply = null;
                                                    if (addPostOptions.equals("In the text box")) {

                                                        askForPostAgain = false;
                                                        do {
                                                            try {
                                                                newPost = (String) JOptionPane.showInputDialog(
                                                                        null, "Please enter " +
                                                                                "your post in a single line in " +
                                                                                "the Text box",
                                                                        "Learning Management System", 
                                                                        JOptionPane.QUESTION_MESSAGE);
                                                                askForPostAgain = false;
                                                                if (newPost == null) {
                                                                    JOptionPane.showMessageDialog(null,
                                                                            "You can't exit until you " +
                                                                                    "add your post.",
                                                                            "Learning Management System", 
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    askForPostAgain = true;
                                                                } else if (newPost.isEmpty()) {
                                                                    JOptionPane.showMessageDialog(null, 
                                                                            "Post can not be empty.",
                                                                            "Learning Management System", 
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    askForPostAgain = true;
                                                                }
                                                            } catch (NullPointerException n) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "You can't exit until you" +
                                                                                " add your post.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                askForPostAgain = true;
                                                            }
                                                        } while (askForPostAgain);

                                                        reply = new Replies(activeCourseDTopics.get(dtChoice - 1),
                                                                activeUserUsername, newPost);
                                                    } else if (addPostOptions.equals("Import a file for it")) {

                                                        boolean errorInFileImport = false;
                                                        do {
                                                            String f;
                                                            try {

                                                                f = (String) JOptionPane.showInputDialog(
                                                                        null, "Please " +
                                                                                "enter the file pathname.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.QUESTION_MESSAGE);
                                                                if (f == null) {
                                                                    JOptionPane.showMessageDialog(null, 
                                                                            "You can't exit until you " +
                                                                                    "add your post.",
                                                                            "Learning Management System",
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    errorInFileImport = true;
                                                                } else {
                                                                    try {
                                                                        File file = new File(f);
                                                                        FileReader fr = new FileReader(file);
                                                                        BufferedReader brr = new BufferedReader(fr);
                                                                        String s = brr.readLine();
                                                                        if (s == null || s.isEmpty()) {

                                                                            JOptionPane.showMessageDialog(
                                                                                    null, 
                                                                                    "The first line of this " +
                                                                                            "file is empty.\nPlease " +
                                                                                            "enter the file path of " +
                                                                                            "a non-empty file.",
                                                                                    "Learning Management System", 
                                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                                            errorInFileImport = true;
                                                                        } else if (!s.isEmpty() && s != null) {
                                                                            newPost = s;
                                                                            errorInFileImport = false;
                                                                        }
                                                                        brr.close();
                                                                        fr.close();
                                                                    } catch (FileNotFoundException fnfe) {

                                                                        JOptionPane.showMessageDialog(
                                                                                null, 
                                                                                "No file with this name was " +
                                                                                        "found.\nPlease enter the " +
                                                                                        "correct file path.",
                                                                                "Learning Management System", 
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        errorInFileImport = true;
                                                                    } catch (IOException ioe) {
                                                                        ioe.printStackTrace();

                                                                        JOptionPane.showMessageDialog(
                                                                                null, 
                                                                                "\nThere was an error reading" +
                                                                                        " your file, please enter " +
                                                                                        "the path name of a valid " +
                                                                                        "file again.",
                                                                                "Learning Management System", 
                                                                                JOptionPane.INFORMATION_MESSAGE);
                                                                        errorInFileImport = true;
                                                                    }
                                                                }
                                                            } catch (NullPointerException n) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "You can't exit until you add" +
                                                                                " your post.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                errorInFileImport = true;
                                                            }


                                                        } while (errorInFileImport);
                                                        reply = new Replies(activeCourseDTopics.get(dtChoice - 1),
                                                                activeUserUsername, newPost);
                                                    }

                                                    pw.write(reply.toString());//s13 write
                                                    pw.println();
                                                    pw.flush();

                                                    ArrayList<Replies> activeDTReplies = new ArrayList<>();
                                                    int activeDTRepliesSize = Integer.parseInt(br.readLine());//38 read

                                                    for (int i = 0; i < activeDTRepliesSize; i++) {
                                                        String replyObjString = br.readLine(); //39 read (loop)
                                                        Replies replyObject = readRepliesString(replyObjString);
                                                        activeDTReplies.add(replyObject);
                                                    }

                                                    JOptionPane.showMessageDialog(null, 
                                                            repliesDisplay(activeDTReplies) +
                                                            "\nYour reply was successfully posted!", 
                                                            "Learning Management System", 
                                                            JOptionPane.INFORMATION_MESSAGE);


                                                } else if (addToDF == JOptionPane.NO_OPTION) {
                                                    pw.write("studentwillnotpost"); //s11b send
                                                    pw.println();
                                                    pw.flush();
                                                } else if (addToDF == 3) {
                                                    pw.write("exit"); //s11c send
                                                    pw.println();
                                                    pw.flush();
                                                    displayFarewell();
                                                    return;
                                                }
                                                //comments
                                                String askForCommentsOrNot = br.readLine(); //s15 (a,b) read
                                                if (askForCommentsOrNot.equals("norepliestocommenton")) {

                                                } else if (askForCommentsOrNot.equals("yesrepliestocommenton")) {

                                                    int commentAns;
                                                    try {
                                                        commentAns = JOptionPane.showConfirmDialog(null, 
                                                                "Would you like to comment on a student post?", 
                                                                "Learning Management System",
                                                                JOptionPane.YES_NO_OPTION, 
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        if (commentAns == JOptionPane.CLOSED_OPTION) {
                                                            commentAns = 3;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        commentAns = 3;
                                                    }
                                                    if (commentAns == JOptionPane.YES_OPTION) {
                                                        pw.write("studentwantstocomment"); //s16a send
                                                        pw.println();
                                                        pw.flush();

                                                        String activeUserUsername = br.readLine(); //s20 read
                                                        ArrayList<Replies> activeDTReplies = new ArrayList<>();
                                                        ArrayList<Comments> activeDTComments = new ArrayList<>();
                                                        int activeDTRepliesSize = Integer.parseInt(br.readLine());
                                                        //38 read

                                                        for (int i = 0; i < activeDTRepliesSize; i++) {
                                                            String replyObjString = br.readLine(); //39 read (loop)
                                                            Replies replyObject = readRepliesString(replyObjString);
                                                            activeDTReplies.add(replyObject);
                                                        }
                                                        String commentsExistOrNot = br.readLine(); //40 (a,b) read
                                                        if (commentsExistOrNot.equals("yescomments")) {
                                                            int commentsALSize = Integer.parseInt(br.readLine()); 
                                                            //41 read
                                                            for (int i = 0; i < commentsALSize; i++) {
                                                                Comments commentObject = 
                                                                        readCommentsString(br.readLine()); 
                                                                //42 read (loop)
                                                                activeDTComments.add(commentObject);
                                                            }
                                                        }

                                                        String[] options15 = new String[activeDTReplies.size()];
                                                        for (int i = 0; i < options15.length; i++) {
                                                            options15[i] = String.valueOf(i + 1);
                                                        }
                                                        String postNumber = "";
                                                        boolean askforcommentagain = false;
                                                        do {
                                                            try {
                                                                postNumber = (String) JOptionPane.showInputDialog(
                                                                        null, 
                                                                        dtDisplay(activeDTReplies, 
                                                                                activeDTComments) +
                                                                                "\nWhich post would you " +
                                                                                "like to comment on?", 
                                                                        "Learning Management System", 
                                                                        JOptionPane.QUESTION_MESSAGE,
                                                                        null, options15, null);
                                                                askforcommentagain = false;
                                                                if (postNumber == null) {
                                                                    JOptionPane.showMessageDialog(null, 
                                                                            "You can't exit until" +
                                                                                    " you add your comment.",
                                                                            "Learning Management System", 
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    askforcommentagain = true;
                                                                }
                                                            } catch (NullPointerException n) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "You can't exit until you " +
                                                                                "add your comment.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                askforcommentagain = true;
                                                            }
                                                        } while (askforcommentagain);
                                                        int postNum = Integer.parseInt(postNumber);
                                                        String studentComment = "";
                                                        askforcommentagain = false;
                                                        do {
                                                            try {
                                                                studentComment = (String) JOptionPane.showInputDialog(
                                                                        null, 
                                                                        "Please enter your comment in " +
                                                                                "a single line.",
                                                                        "Learning Management System",
                                                                        JOptionPane.QUESTION_MESSAGE);
                                                                askforcommentagain = false;
                                                                if (studentComment == null) {
                                                                    JOptionPane.showMessageDialog(null, 
                                                                            "You can't exit until you " +
                                                                                    "add your comment.",
                                                                            "Learning Management System",
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    askforcommentagain = true;
                                                                } else if (studentComment.isEmpty()) {
                                                                    System.out.println("Comment can not be empty.");
                                                                    askforcommentagain = true;
                                                                }
                                                            } catch (NullPointerException n) {
                                                                JOptionPane.showMessageDialog(null, 
                                                                        "You can't exit until you" +
                                                                                " add your comment.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                askforcommentagain = true;
                                                            }
                                                        } while (askforcommentagain);

                                                        Replies studentReply 
                                                                = activeDTReplies.get(activeDTReplies.size() - postNum);
                                                        Comments studentCommentObj = new Comments(studentReply,
                                                                activeUserUsername, studentComment);

                                                        pw.write(studentCommentObj.toString());//s21 send
                                                        pw.println();
                                                        pw.flush();

                                                        int commentSize = Integer.parseInt(br.readLine()); //s22 read
                                                        ArrayList<Comments> editedCommentsList = new ArrayList<>();

                                                        for (int i = 0; i < commentSize; i++) {
                                                            String comment = br.readLine(); //s23 read (loop)
                                                            Comments commentObj = readCommentsString(comment);
                                                            editedCommentsList.add(commentObj);
                                                        }

                                                        JOptionPane.showMessageDialog(null, 
                                                                commentsDisplay(editedCommentsList) + "\n\n" +
                                                                        "Your comment was successfully added!", 
                                                                "Learning Management System",
                                                                JOptionPane.INFORMATION_MESSAGE);

                                                    } else if (commentAns == JOptionPane.NO_OPTION) {
                                                        pw.write("studentdoesntwanttocomment"); //s16b send
                                                        pw.println();
                                                        pw.flush();
                                                    } else if (commentAns == 3) {
                                                        pw.write("exit"); //s16c send
                                                        pw.println();
                                                        pw.flush();
                                                        displayFarewell();
                                                        return;
                                                    }
                                                }
                                                //voting
                                                String askForVotingOrNot = br.readLine(); //s40 (a,b,c) read
                                                if (askForVotingOrNot.equals("askforvoting")) {


                                                    int votingAns;
                                                    try {
                                                        votingAns = JOptionPane.showConfirmDialog(null,
                                                                "Would you like to upvote any post?",
                                                                "Learning Management System", 
                                                                JOptionPane.YES_NO_OPTION, 
                                                                JOptionPane.QUESTION_MESSAGE);
                                                        if (votingAns == JOptionPane.CLOSED_OPTION) {
                                                            votingAns = 3;
                                                        }
                                                    } catch (NullPointerException n) {
                                                        votingAns = 3;
                                                    }

                                                    if (votingAns == JOptionPane.YES_OPTION) {
                                                        pw.write("studentwantstovote"); //s41a write
                                                        pw.println();
                                                        pw.flush();

                                                        int numOfReplies = Integer.parseInt(br.readLine()); //s42 read
                                                        ArrayList<Replies> activeDTreplies = new ArrayList<>();

                                                        for (int i = 0; i < numOfReplies; i++) {
                                                            String replyObjString = br.readLine(); //39 read (loop)
                                                            Replies replyObject = readRepliesString(replyObjString);
                                                            activeDTreplies.add(replyObject);
                                                        }

                                                        String[] options15 = new String[activeDTreplies.size()];
                                                        for (int i = 0; i < options15.length; i++) {
                                                            options15[i] = String.valueOf(i + 1);
                                                        }
                                                        String upvotedPostString = "";

                                                        boolean askforvoteagain = false;
                                                        do {
                                                            try {
                                                                upvotedPostString = 
                                                                        (String) JOptionPane.showInputDialog(
                                                                                null, 
                                                                                repliesDisplay(activeDTreplies)
                                                                                        +
                                                                                "Which post would you like to upvote " +
                                                                                        "for?\n(You can only upvote " +
                                                                                        "one post of these)\n", 
                                                                                "Learning Management System", 
                                                                                JOptionPane.QUESTION_MESSAGE,
                                                                        null, options15, null);
                                                                askforvoteagain = false;
                                                                if (upvotedPostString == null) {
                                                                    JOptionPane.showMessageDialog(null, 
                                                                            "You can't exit until " +
                                                                                    "you upvote a post.",
                                                                            "Learning Management System", 
                                                                            JOptionPane.INFORMATION_MESSAGE);
                                                                    askforvoteagain = true;
                                                                }
                                                            } catch (NullPointerException n) {
                                                                JOptionPane.showMessageDialog(null,
                                                                        "You can't exit until you " +
                                                                                "upvote a post.",
                                                                        "Learning Management System", 
                                                                        JOptionPane.INFORMATION_MESSAGE);
                                                                askforvoteagain = true;
                                                            }
                                                        } while (askforvoteagain);

                                                        int upvotedPostNum = Integer.parseInt(upvotedPostString);
                                                        Replies upvotedPost = activeDTreplies.get(
                                                                activeDTreplies.size() - upvotedPostNum);

                                                        pw.write(upvotedPost.toString()); //s43 send
                                                        pw.println();
                                                        pw.flush();
                                                        String successfullyVotedOrNot = br.readLine();//s44 read
                                                        if (successfullyVotedOrNot.equals("successfullyvoted")) {
                                                            JOptionPane.showMessageDialog(null, 
                                                                    "Hurray, you've successfully voted!",
                                                                    "Learning Management System", 
                                                                    JOptionPane.INFORMATION_MESSAGE);
                                                        }

                                                    } else if (votingAns == JOptionPane.NO_OPTION) {
                                                        pw.write("studentdoesntwanttovote"); //s41b write
                                                        pw.println();
                                                        pw.flush();
                                                    } else if (votingAns == 3) {
                                                        pw.write("exit"); //s41c write
                                                        pw.println();
                                                        pw.flush();
                                                        displayFarewell();
                                                        return;
                                                    }

                                                } else if (askForVotingOrNot.equals("dontaskforvoting")) {

                                                }
                                            }
                                            //CHECK GRADES


                                            int checkGrades;
                                            try {
                                                checkGrades = JOptionPane.showConfirmDialog(null, 
                                                        "Would you like to check your grades for this course?",
                                                        "Learning Management System", JOptionPane.YES_NO_OPTION, 
                                                        JOptionPane.QUESTION_MESSAGE);
                                                if (checkGrades == JOptionPane.CLOSED_OPTION) {
                                                    checkGrades = 3;
                                                }
                                            } catch (NullPointerException n) {
                                                checkGrades = 3;
                                            }
                                            if (checkGrades == JOptionPane.YES_OPTION) {
                                                pw.write("studentwantstocheckgrades"); //s30a write
                                                pw.println();
                                                pw.flush();

                                                String gradedOrNot = br.readLine(); //s31 (a,b) read

                                                if (gradedOrNot.equals("nogrades")) {

                                                    JOptionPane.showMessageDialog(null, 
                                                            "Either you have not made any posts in " +
                                                                    "this course or your teacher has not " +
                                                                    "graded your work yet!",
                                                            "Learning Management System", 
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                } else if (gradedOrNot.equals("yesgrades")) {
                                                    int studentGradeSize = Integer.parseInt(br.readLine()); //s32 read
                                                    ArrayList<Grades> studentGrades = new ArrayList<>();

                                                    for (int i = 0; i < studentGradeSize; i++) {
                                                        String grade = br.readLine(); //s33 read (loop)
                                                        Grades studentGrade = readGradesString(grade);
                                                        studentGrades.add(studentGrade);
                                                    }

                                                    JOptionPane.showMessageDialog(null, 
                                                            gradesDisplayStudent(studentGrades), 
                                                            "Learning Management System",
                                                            JOptionPane.INFORMATION_MESSAGE);
                                                }

                                            } else if (checkGrades == JOptionPane.NO_OPTION) {
                                                pw.write("studentdoesntwanttocheckgrades"); //s30b write
                                                pw.println();
                                                pw.flush();
                                            } else if (checkGrades == 3) {
                                                pw.write("exit"); //s30c write
                                                pw.println();
                                                pw.flush();
                                                displayFarewell();
                                                return;
                                            }
                                        }
                                    }
                                }
                            } // student part ends here

                            String[] options6 = {"Go back to the main page", "Exit"};
                            try {
                                loopingBackOrExit = (String) JOptionPane.showInputDialog(null, 
                                        "Would you like to...",
                                        "Learning Management System", JOptionPane.QUESTION_MESSAGE, 
                                        null, options6, null);

                                if (loopingBackOrExit == null) {
                                    loopingBackOrExit = "Exit";
                                }
                            } catch (NullPointerException n) {
                                loopingBackOrExit = "Exit";
                            }

                            if (loopingBackOrExit.equals("Exit")) {
                                pw.write("exit"); //69a send
                                pw.println();
                                pw.flush();
                                displayFarewell();
                                return;
                            } else if (loopingBackOrExit.equals("Go back to the main page")) {
                                pw.write("loopback"); //69b send
                                pw.println();
                                pw.flush();
                            }

                        } while (loopingBackOrExit.equals("Go back to the main page")); 
                        //looping back entre menu from after login = 3
                    }
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error reading/writing to/from" +
                    " the client", "Learning Management System", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    public static void displayFarewell() {

        JOptionPane.showMessageDialog(null, "Thank you for using the " +
                        "Learning Management System!" +
                        "\nBye! Have a nice day!",
                "Learning Management System - SignUp", JOptionPane.PLAIN_MESSAGE);
    }

    //for when you want to display the whole disc forum
    public static String dtDisplay(ArrayList<Replies> replies, ArrayList<Comments> comments) {

        StringBuilder sb = new StringBuilder("");
        String coursePart = "Course: " + replies.get(0).getDt().getCourseName();
        sb.append(coursePart);
        sb.append("\n");
        String topicTitle = replies.get(0).getDt().getTopicTitle();
        String topicDescription = replies.get(0).getDt().getTopicDescription();
        String topicTimestamp = replies.get(0).getDt().getTimestamp();
        String titlePart = String.format("Discussion Forum Topic: %s  (%s)\nDescription: %s",
                topicTitle, topicTimestamp, stringMethod(topicDescription));
        sb.append(titlePart);
        sb.append("\n\n");
        sb.append("Replies and Comments (Newest to Oldest)");
        sb.append("\n\n");

        int k = 1;
        for (int i = replies.size() - 1; i >= 0; i--) {
            String replierUsername = replies.get(i).getUsername();
            String replyTimestamp = replies.get(i).getTimestamp();
            String reply = replies.get(i).getReply();

            ArrayList<Comments> activeReplyComments = new ArrayList<>();
            if (!comments.isEmpty()) {
                for (int j = 0; j < comments.size(); j++) {
                    if (comments.get(j).getPost().getUsername().equals(replierUsername)) {
                        if (comments.get(j).getPost().getReply().equals(reply)) {
                            activeReplyComments.add(comments.get(j));
                        }
                    }
                }
            }

            String replyPart = String.format("%d. %s  (%s)\n%s", k, replierUsername,
                    replyTimestamp, stringMethod(reply));
            k++;
            sb.append(replyPart);
            if (!activeReplyComments.isEmpty()) {
                sb.append("\n\n");
                for (int l = activeReplyComments.size() - 1; l >= 0; l--) {
                    String commenterUsername = activeReplyComments.get(l).getUsername();
                    String commentTimestamp = activeReplyComments.get(l).getTimestamp();
                    String comment = activeReplyComments.get(l).getComment();
                    String commentPart = String.format("    %s  (%s)\n    %s", commenterUsername,
                            commentTimestamp, stringMethod(comment));
                }
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    //for when student wants to see their grades
    public static String gradesDisplayStudent(ArrayList<Grades> studentGrades) {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < studentGrades.size(); i++) {
            String grade = String.format("Topic: %s\nYour Post: %s\n\nGrade: %.1f/10.0\n\n",
                    studentGrades.get(i).getTopicTitle(),
                    stringMethod(studentGrades.get(i).getReply()),
                    studentGrades.get(i).getGrade());

            sb.append(grade);
        }
        return sb.toString();
    }

    //for when teacher wants to grade
    public static String gradesDisplayTeacher(ArrayList<Replies> selectedStudentReplies) {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < selectedStudentReplies.size(); i++) {
            String s1 = String.format("%d. Topic: %s\n", i + 1,
                    selectedStudentReplies.get(i).getDt().getTopicTitle());
            String s2 = String.format("Student Reply: (%s)\n%s",
                    selectedStudentReplies.get(i).getTimestamp(),
                    stringMethod(selectedStudentReplies.get(i).getReply()));
            sb.append(s1);
            sb.append(s2);
            sb.append("\n\n");
        }
        return sb.toString();
    }

    //for when teacher wants to see highest votes
    public static String votesDisplayTeacher(ArrayList<Replies> highestVotedRepliesArrayList, 
                                             ArrayList<String> highestVotedRepliesNameArrayList) {

        StringBuilder sb = new StringBuilder("");
        for (int m = 0; m < highestVotedRepliesArrayList.size(); m++) {
            String highestVotesUsername = highestVotedRepliesArrayList.get(m).getUsername();
            String highestVotesTimestamp = highestVotedRepliesArrayList.get(m).getTimestamp();
            String highestVotes = String.valueOf(highestVotedRepliesArrayList.get(m).
                    getVotes());
            String highestVotesPost = highestVotedRepliesArrayList.get(m).getReply();
            String highestVotesName = highestVotedRepliesNameArrayList.get(m);

            String s1 = String.format("Highest voted post:\nName: %s Votes: %s\n%s %s\n%s\n\n", highestVotesName,
                    highestVotes, highestVotesUsername, highestVotesTimestamp, stringMethod(highestVotesPost));
            sb.append(s1);
        }
        return sb.toString();

    }

    //for when you just want to display the replies
    public static String repliesDisplay(ArrayList<Replies> activeDTReplies) {

        StringBuilder sb = new StringBuilder("");
        String coursePart = "Course: " + activeDTReplies.get(0).getDt().getCourseName();
        sb.append(coursePart);
        sb.append("\n");
        String topicTitle = activeDTReplies.get(0).getDt().getTopicTitle();
        String topicDescription = activeDTReplies.get(0).getDt().getTopicDescription();
        String topicTimestamp = activeDTReplies.get(0).getDt().getTimestamp();
        String titlePart = String.format("Discussion Forum Topic: %s  (%s)\nDescription: %s",
                topicTitle, topicTimestamp, stringMethod(topicDescription));
        sb.append(titlePart);
        sb.append("\n\n");
        sb.append("Replies (Newest to Oldest)");
        sb.append("\n\n");

        int k = 1;
        for (int i = activeDTReplies.size() - 1; i >= 0; i--) {
            String replierUsername = activeDTReplies.get(i).getUsername();
            String replyTimestamp = activeDTReplies.get(i).getTimestamp();
            String reply = activeDTReplies.get(i).getReply();

            String replyPart = String.format("%d. %s  (%s)\n%s", k, replierUsername,
                    replyTimestamp, stringMethod(reply));
            k++;
            sb.append(replyPart);
            sb.append("\n\n");
        }
        return sb.toString();

    }

    //for when you want to display just the reply with its comments
    public static String commentsDisplay(ArrayList<Comments> editedCommentsList) {

        StringBuilder sb = new StringBuilder("");
        String coursePart = "Course: " + editedCommentsList.get(0).getPost().getDt().getCourseName();
        sb.append(coursePart);
        sb.append("\n");
        String topicTitle = editedCommentsList.get(0).getPost().getDt().getTopicTitle();
        String topicDescription = editedCommentsList.get(0).getPost().getDt().getTopicDescription();
        String topicTimestamp = editedCommentsList.get(0).getPost().getDt().getTimestamp();
        String titlePart = String.format("Discussion Forum Topic: %s  (%s)\nDescription: %s",
                topicTitle, topicTimestamp, stringMethod(topicDescription));
        sb.append(titlePart);
        sb.append("\n\n");
        sb.append("Reply with Comments (Newest to Oldest)");
        sb.append("\n\n");

        String replierUsername = editedCommentsList.get(0).getPost().getUsername();
        String replyTimestamp = editedCommentsList.get(0).getPost().getTimestamp();
        String reply = editedCommentsList.get(0).getPost().getReply();

        String replyPart = String.format("%s  (%s)\n%s", replierUsername,
                replyTimestamp, stringMethod(reply));
        sb.append(replyPart);
        sb.append("\n\n");

        for (int l = editedCommentsList.size() - 1; l >= 0; l--) {
            String commenterUsername = editedCommentsList.get(l).getUsername();
            String commentTimestamp = editedCommentsList.get(l).getTimestamp();
            String comment = editedCommentsList.get(l).getComment();
            String commentPart = String.format("    %s  (%s)\n    %s\n\n", commenterUsername,
                    commentTimestamp, stringMethod(comment));
            sb.append(commentPart);
        }
        return sb.toString();
    }

    //when you just want to display the discussion topics
    public static String discussionTopicsDisplay(ArrayList<DiscussionTopic> activeCourseDTopics) {

        StringBuilder sb = new StringBuilder("");
        String coursePart = "Course: " + activeCourseDTopics.get(0).getCourseName();
        sb.append(coursePart);
        sb.append("\n\n");

        for (int i = 0; i < activeCourseDTopics.size(); i++) {
            String topicTitle = activeCourseDTopics.get(i).getTopicTitle();
            String topicDescription = activeCourseDTopics.get(i).getTopicDescription();
            String topicTimestamp = activeCourseDTopics.get(i).getTimestamp();
            String titlePart = String.format("%d. Topic: %s  (%s)\nDescription: %s", i + 1,
                    topicTitle, topicTimestamp, stringMethod(topicDescription));
            sb.append(titlePart);
            sb.append("\n\n");
        }
        return sb.toString();
    }

    //for when you just want to display the courses
    public static String displayCourses(ArrayList<String> courses) {

        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < courses.size(); i++) {
            String course = String.format("%d. %s\n\n", i + 1, courses.get(i)); //(buttons/dropdown)
            sb.append(course);
        }
        return sb.toString();
    }

    public static String stringMethod(String s) {
        StringBuilder sb = new StringBuilder("");
        char[] characters = {' ', ',', '.', '"', '-', ')', '(', ':', '/', '?', '<', '>', '[', ']', 
                '{', ';', '_', '+', '=', '*', '&', '^', '%', '$', '#', '@', '!', '~'};
        if (s.length() > 160) {
            for (int i = 159; i < s.length(); i = i) {
                boolean dashNeeded = true;
                for (int j = 0; j < characters.length; j++) {
                    if (s.charAt(i + 1) == characters[j]) {
                        dashNeeded = false;
                    }
                }

                if (s.charAt(i) == ' ') {
                    dashNeeded = false;
                }
                sb.append(s.substring(0, i + 1));
                if (dashNeeded) {
                    sb.append("-");
                }
                sb.append("\n");
                s = s.substring(i + 1);
            }
            if (s.length() < 160) {
                sb.append(s);
                sb.append("\n");
            }

        } else if (s.length() < 160) {
            return s;
        }
        String result = sb.toString();
        return result;
    }

    //make sure to check the string isn't empty
    public static User readUserString(String s) {
        int commaIndex;
        User user = null;
        if (s != null && !s.isEmpty()) {
            commaIndex = s.indexOf(',');
            String name = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf(',');
            String role = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf(',');
            String username = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf(',');
            String password = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            String grade = s;

            user = new User(name, role, username, password, grade);
        }
        return user;
    }

    public static DiscussionTopic readDiscussionTopicString(String s) {
        int commaIndex;
        DiscussionTopic dtObject = null;
        if (s != null && !s.isEmpty()) {
            commaIndex = s.indexOf('`');
            String courseName = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String topicTitle = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String topicDescription = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            String timestamp = s;

            dtObject = new DiscussionTopic(courseName,
                    topicTitle, topicDescription, timestamp);
        }
        return dtObject;

    }


    public static Replies readRepliesString(String s) {
        int index;
        Replies replyObject = null;
        if (s != null && !s.isEmpty()) {
            index = s.indexOf('`');
            String courseName = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String topicTitle = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String topicDescription = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String dtTimestamp = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String username = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String replyTimestamp = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String reply = s.substring(0, index);
            s = s.substring(index + 1);
            int votes = Integer.parseInt(s);


            DiscussionTopic dtObject = new DiscussionTopic(courseName,
                    topicTitle, topicDescription, dtTimestamp);
            replyObject = new Replies(dtObject, username,
                    replyTimestamp, reply, votes);
        }
        return replyObject;
    }

    public static Comments readCommentsString(String s) {
        int index;
        Comments commentObject = null;
        if (s != null && !s.isEmpty()) {
            index = s.indexOf('`');
            String courseName = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String topicTitle = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String topicDescription = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String dtTimestamp = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String replierUsername = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String replyTimestamp = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String reply = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String vote = s.substring(0, index);
            int replyVotes = Integer.parseInt(vote);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String commenterUsername = s.substring(0, index);
            s = s.substring(index + 1);
            index = s.indexOf('`');
            String commenterTimestamp = s.substring(0, index);
            s = s.substring(index + 1);
            String comment = s;


            DiscussionTopic dtObject = new DiscussionTopic(courseName,
                    topicTitle, topicDescription, dtTimestamp);
            Replies replyObject = new Replies(dtObject, replierUsername,
                    replyTimestamp, reply, replyVotes);
            commentObject = new Comments(replyObject,
                    commenterUsername, commenterTimestamp, comment);
        }
        return commentObject;
    }

    public static VotersList readVotersListString(String s) {
        int commaIndex;
        VotersList vlObject = null;
        if (s != null && !s.isEmpty()) {
            commaIndex = s.indexOf('`');
            String courseName = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String topicTitle = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String reply = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            String username = s;

            vlObject = new VotersList(courseName, topicTitle,
                    reply, username);
        }
        return vlObject;
    }

    public static Grades readGradesString(String s) {
        int commaIndex;
        Grades gradeObject = null;
        if (s != null && !s.isEmpty()) {
            commaIndex = s.indexOf('`');
            String courseName = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String topicTitle = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String reply = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            commaIndex = s.indexOf('`');
            String username = s.substring(0, commaIndex);
            s = s.substring(commaIndex + 1);
            double grade = Double.valueOf(s);

            gradeObject = new Grades(courseName, topicTitle,
                    reply, username, grade);
        }
        return gradeObject;
    }


}



