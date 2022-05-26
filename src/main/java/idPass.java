import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class idPass extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel idLabel;
    private JLabel passLabel;
    private JLabel noticeLabel;
    private JTextPane idPane;
    private JTextPane passPane;
    private JButton loginBut;

    public idPass(Long idnumber) {
        setAlwaysOnTop(true);
        setResizable(false);
        setTitle("اطلاعات ورود");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        StudentModel std = new StudentModel();

        String pass = std.setPass();

        MongoClient mongoClient = MongoClients.create("mongodb://docker:mongopw@localhost:49153");
        MongoDatabase database = mongoClient.getDatabase("formDB");

        // Get the collection
        MongoCollection<Document> collection = database.getCollection("Students");

        // Find all documents
        collection.updateOne(Filters.eq("idNumber", idnumber),
                Updates.set("password", pass));
        System.out.println("***Updated Document***");

        // Select a particular document
        FindIterable<Document> documents = collection.find(Filters.eq("idNumber", idnumber));

        idPane.setText(Long.toString(idnumber));
        passPane.setText(pass);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onLogin(){
        loginForm login = new loginForm();
        login.pack();
        login.setVisible(true);
        dispose();
    }
}
