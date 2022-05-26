import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mongodb.Mongo;
import com.mongodb.MongoCredential;
import com.mongodb.client.*;
import org.bson.Document;

public class mainWindow extends JDialog {
    private JPanel contentPane;
    private JLabel welcomeLabel;
    private JButton registationButton;
    private JButton Loginbutton;

    public mainWindow() {
        setAlwaysOnTop(true);
        setResizable(false);
        setContentPane(contentPane);

        registationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegister();
            }
        });
        Loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLogin();
            }
        });
    }

    private void onRegister() {
        dispose();
        registrationForm register = new registrationForm();
        register.pack();
        register.setVisible(true);
    }

    private void onLogin() {
        dispose();
        loginForm login = new loginForm();
        login.pack();
        login.setVisible(true);
    }

    public static void main(String[] args) {


        mainWindow window = new mainWindow();
        window.pack();
        window.setVisible(true);

    }
}
