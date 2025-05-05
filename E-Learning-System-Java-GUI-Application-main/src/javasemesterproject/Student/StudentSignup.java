package javasemesterproject.Student;



import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javasemesterproject.DBConnection;

public class StudentSignup extends JFrame implements ActionListener, FocusListener {
    JPanel contentPane;
    JTextField firstname, lastname, email, username;
    JPasswordField passwordField;
    JButton registerButton, uploadPicBtn;
    JRadioButton maleRB, femaleRB;
    ButtonGroup radioBtns;
    JLabel fnameValidation, LnameValidation, emailValidation, userNameValidation, profilePicLbl;
    FileInputStream fis = null;
    File f = null;

    public StudentSignup() {
        super("Student SignUp");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1014, 515);
        setLocation(230, 110);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        profilePicLbl = new JLabel();
        profilePicLbl.setIcon(new ImageIcon(ClassLoader.getSystemResource("javasemesterproject/icons/uploadPicIcon.png")));
        profilePicLbl.setBounds(456, 18, 96, 96);
        contentPane.add(profilePicLbl);

        uploadPicBtn = new JButton("Upload");
        uploadPicBtn.setBounds(470, 120, 75, 23);
        uploadPicBtn.addActionListener(this);
        contentPane.add(uploadPicBtn);

        JLabel lblName = new JLabel("First name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblName.setBounds(58, 152, 99, 43);
        contentPane.add(lblName);

        JLabel lblNewLabel = new JLabel("Last name");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(58, 243, 110, 29);
        contentPane.add(lblNewLabel);

        JLabel lblEmailAddress = new JLabel("Email address");
        lblEmailAddress.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblEmailAddress.setBounds(58, 324, 124, 36);
        contentPane.add(lblEmailAddress);

        fnameValidation = new JLabel();
        firstname = new JTextField();
        firstname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        firstname.setBounds(214, 151, 228, 50);
        firstname.addFocusListener(this);
        fnameValidation.setBounds(214, 205, 150, 10);
        contentPane.add(fnameValidation);
        contentPane.add(firstname);

        LnameValidation = new JLabel();
        lastname = new JTextField();
        lastname.setFont(new Font("Tahoma", Font.PLAIN, 32));
        lastname.setBounds(214, 235, 228, 50);
        lastname.addFocusListener(this);
        LnameValidation.setBounds(214, 289, 150, 10);
        contentPane.add(LnameValidation);
        contentPane.add(lastname);

        emailValidation = new JLabel();
        email = new JTextField();
        email.setFont(new Font("Tahoma", Font.PLAIN, 32));
        email.setBounds(214, 320, 228, 50);
        email.addFocusListener(this);
        emailValidation.setBounds(214, 374, 150, 10);
        contentPane.add(emailValidation);
        contentPane.add(email);

        userNameValidation = new JLabel();
        username = new JTextField();
        username.setFont(new Font("Tahoma", Font.PLAIN, 32));
        username.setBounds(707, 151, 228, 50);
        username.addFocusListener(this);
        userNameValidation.setBounds(707, 205, 150, 10);
        contentPane.add(userNameValidation);
        contentPane.add(username);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblUsername.setBounds(542, 159, 99, 29);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPassword.setBounds(542, 245, 99, 24);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(707, 235, 228, 50);
        contentPane.add(passwordField);

        JLabel genderLbl = new JLabel("Gender");
        genderLbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
        genderLbl.setBounds(542, 331, 99, 24);
        contentPane.add(genderLbl);

        maleRB = new JRadioButton("Male");
        femaleRB = new JRadioButton("Female");
        radioBtns = new ButtonGroup();

        maleRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
        maleRB.setBounds(707, 321, 110, 50);
        maleRB.setActionCommand("Male");
        contentPane.add(maleRB);

        femaleRB.setFont(new Font("Tahoma", Font.PLAIN, 25));
        femaleRB.setBounds(825, 321, 120, 50);
        femaleRB.setActionCommand("Female");
        contentPane.add(femaleRB);

        radioBtns.add(maleRB);
        radioBtns.add(femaleRB);

        registerButton = new JButton("Register");
        registerButton.setBounds(410, 400, 228, 60);
        registerButton.addActionListener(this);
        contentPane.add(registerButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == uploadPicBtn) {
            JFileChooser fchoser = new JFileChooser();
            fchoser.showOpenDialog(null);
            f = fchoser.getSelectedFile();
            if (f != null) {
                profilePicLbl.setIcon(resizeImage(f.getAbsolutePath()));
                try {
                    fis = new FileInputStream(f);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        } else {
            String firstName = firstname.getText().trim();
            String lastName = lastname.getText().trim();
            String emailId = email.getText().trim();
            String userName = username.getText().trim();
            String password = String.valueOf(passwordField.getPassword());
            String genderStr = radioBtns.getSelection() != null ? radioBtns.getSelection().getActionCommand() : "";

            if (fnameValidation.getText().isEmpty() &&
                LnameValidation.getText().isEmpty() &&
                emailValidation.getText().isEmpty() &&
                userNameValidation.getText().isEmpty()) {
                if (firstName.isEmpty() || lastName.isEmpty() || emailId.isEmpty() || userName.isEmpty() || password.isEmpty()
                        || genderStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please Fill All Fields!");
                } else {
                    try {
                        DBConnection c1 = new DBConnection();

                        PreparedStatement ps = c1.c.prepareStatement("Insert into Student (fname, lname, Email_ID, username, password, Registration_Date, Gender, picture) "
                                + "values(?,?,?,?,?,?,?,?)");
                        ps.setString(1, firstName);
                        ps.setString(2, lastName);
                        ps.setString(3, emailId);
                        ps.setString(4, userName);
                        ps.setString(5, password);
                        ps.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
                        ps.setString(7, genderStr);
                        ps.setBinaryStream(8, fis != null ? fis : null);
                        int x = ps.executeUpdate();
                        if (x == 0) {
                            JOptionPane.showMessageDialog(null, "This User already exists!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Account successfully created! You can now log in.");
                            setVisible(false);
                            new StudentLogin();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please provide valid inputs!");
            }
        }
    }

    public ImageIcon resizeImage(String imagePath) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(imagePath));
            int width = bufferedImage.getWidth();
            BufferedImage circleBuffer = new BufferedImage(width, width, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = circleBuffer.createGraphics();
            g2.setClip(new Ellipse2D.Float(0, 0, width, width));
            g2.drawImage(bufferedImage, 0, 0, width, width, null);
            ImageIcon icon = new ImageIcon(circleBuffer);
            Image i2 = icon.getImage().getScaledInstance(96, 96, Image.SCALE_DEFAULT);
            return new ImageIcon(i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == firstname) fnameValidation.setText("");
        else if (e.getSource() == lastname) LnameValidation.setText("");
        else if (e.getSource() == email) emailValidation.setText("");
        else if (e.getSource() == username) userNameValidation.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == firstname) {
            String fName = firstname.getText().trim();
            if (fName.isEmpty()) fnameValidation.setText("Enter First Name");
            else if (!fName.matches("[a-zA-Z][a-zA-Z\\s\\-]*")) fnameValidation.setText("Invalid First Name");
            else fnameValidation.setText("");
        } else if (e.getSource() == lastname) {
            String lName = lastname.getText().trim();
            if (lName.isEmpty()) LnameValidation.setText("Enter Last Name");
            else if (!lName.matches("[a-zA-Z][a-zA-Z\\s\\-]*")) LnameValidation.setText("Invalid Last Name");
            else LnameValidation.setText("");
        } else if (e.getSource() == email) {
            String emailTxt = email.getText().trim();
            if (emailTxt.isEmpty()) emailValidation.setText("Enter Email");
            else if (!emailTxt.matches("[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}")) emailValidation.setText("Invalid Email");
            else emailValidation.setText("");
        } else if (e.getSource() == username) {
            String usernameTxt = username.getText().trim();
            if (usernameTxt.isEmpty()) userNameValidation.setText("Enter Username");
            else if (!usernameTxt.matches("[a-zA-Z][a-zA-Z0-9._-]{3,}")) userNameValidation.setText("Invalid Username");
            else userNameValidation.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentSignup();
    }
}
