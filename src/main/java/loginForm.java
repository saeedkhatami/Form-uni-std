import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.*;

public class loginForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonEdit;
    private JButton buttonCancel;
    private JTextField idField;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel idLabel;

    public loginForm() {
        setAlwaysOnTop(true);
        setTitle("ورود");
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonEdit);

        buttonEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        if (idField.getText().isEmpty()
                || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "لطفا نام کاربری و رمزعبور خود را کامل وارد کنید.");
        }
        Long id = Long.valueOf(0);
        String password = "";
        if (!idField.getText().isEmpty()
                && passwordField.getPassword().length > 0) {

            // Creating a Mongo client
            MongoClient mongoClient = MongoClients.create("mongodb://docker:mongopw@localhost:49153");
            MongoDatabase database = mongoClient.getDatabase("formDB");

            // Get the collection
            MongoCollection<Document> collection = database.getCollection("Students");

            // Select a particular document
            FindIterable<Document> documents = collection.find(Filters.eq("idNumber", Long.parseLong(idField.getText())));

            for (Document document : documents) {
                Document idPass = new Document(document);

                id = (Long) idPass.get("idNumber");
                password = (String) idPass.get("password");
            }
            Long typedUserID = Long.parseLong(idField.getText());
            String typedUserPassword = String.valueOf(passwordField.getPassword());
            if (typedUserID.equals(id)
                    && typedUserPassword.equals(password)) {
                dispose();
                studentInfo student = new studentInfo(id, password);
                student.pack();
                student.setVisible(true);
            }
            if (!typedUserID.equals(id)
                    || !typedUserPassword.equals(password)) {
                JOptionPane.showMessageDialog(null, "نام کاربری یا رمز ورود درست نیست");
            }

        }
        // add your code here

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
