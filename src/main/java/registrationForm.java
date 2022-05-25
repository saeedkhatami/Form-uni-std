import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.*;

public class registrationForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonExit;
    private JLabel firstnameLabel;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JLabel lastnameLabel;
    private JTextField yearField;
    private JLabel dobLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JTextField monthField;
    private JTextField dayField;
    private JTextField idNumberField;
    private JLabel idNumberLabel;
    private JTextField fieldField;
    private JLabel fieldLabel;
    private JTextField emailField;
    private JLabel emailLabel;

    public registrationForm() {
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onExit();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if(firstnameField.getText().isEmpty()
                || lastnameField.getText().isEmpty()
                || emailField.getText().isEmpty()
                || fieldField.getText().isEmpty()
                || dayField.getText().isEmpty()
                || monthField.getText().isEmpty()
                || yearField.getText().isEmpty()
                || idNumberField.getText().isEmpty()
        ){
            JOptionPane.showMessageDialog(null, "لطفا مشخصات خود را کامل وارد کنید.");
        }
        if(!firstnameField.getText().isEmpty()
                && !lastnameField.getText().isEmpty()
                && !emailField.getText().isEmpty()
                && !fieldField.getText().isEmpty()
                && !dayField.getText().isEmpty()
                && !monthField.getText().isEmpty()
                && !yearField.getText().isEmpty()
                && !idNumberField.getText().isEmpty()
        ){
            StudentModel stdmdl = new StudentModel(firstnameField.getText(),
                    lastnameField.getText(),
                    emailField.getText(),
                    fieldField.getText(),
                    Integer.parseInt(yearField.getText()),
                    Integer.parseInt(monthField.getText()),
                    Integer.parseInt(dayField.getText()),
                    Long.parseLong(idNumberField.getText()));

            // Creating a Mongo client
            MongoClient mongoClient = MongoClients.create("mongodb://docker:mongopw@localhost:49153");
            MongoDatabase database = mongoClient.getDatabase("formDB");

            // Get the collection
            MongoCollection<Document> collection = database.getCollection("Students");

            Document document = new Document("First name", stdmdl.getFirstName())
                    .append("Last name", stdmdl.getLastName())
                    .append("email", stdmdl.getEmail())
                    .append("Field of study", stdmdl.getField())
                    .append("Year of birth", stdmdl.getYearOfBirth())
                    .append("Month of birth", stdmdl.getMonthOfBirth())
                    .append("day of birth", stdmdl.getDayOfBirth())
                    .append("idNumber", stdmdl.getIdNumber())
                    .append("password", null);

            //Inserting document into the collection
            collection.insertOne(document);
            idPass idPass = new idPass(stdmdl.getIdNumber());
            dispose();
            idPass.pack();
            idPass.setVisible(true);

        }
    }

    private void onExit() {
        // add your code here if necessary
        dispose();
    }
}
