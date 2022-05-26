import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.swing.*;

public class studentInfo extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel firstname;
    private JLabel lastname;
    private JLabel email;
    private JLabel fos;
    private JLabel dob;
    private JLabel idnumber;
    private JLabel fistnameVar;
    private JLabel lastnameVar;
    private JLabel emailVar;
    private JLabel fosVar;
    private JLabel yearVar;
    private JLabel monthVar;
    private JLabel dayVar;
    private JLabel idnumberVar;

    public studentInfo(Long id, String password) {
        setAlwaysOnTop(true);
        setTitle("مشخصات");
        setResizable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        MongoClient mongoClient = MongoClients.create("mongodb://docker:mongopw@localhost:49153");
        MongoDatabase database = mongoClient.getDatabase("formDB");

        // Get the collection
        MongoCollection<Document> collection = database.getCollection("Students");

        // Select a particular document
        FindIterable<Document> documents = collection.find(Filters.eq("idNumber", id));
        String firstname = "";
        String lastname = "";
        String email = "";
        String FOS = "";
        Integer year = 0;
        Integer month = 0;
        Integer day = 0;
        Long idS = 0L;
        for (Document document : documents) {
            Document stdInfo = new Document(document);

            firstname = stdInfo.getString("firstname");
            lastname = stdInfo.getString("lastname");
            email = stdInfo.getString("email");
            FOS = stdInfo.getString("fieldOfStudy");
            year = stdInfo.getInteger("yearOfBirth");
            month = stdInfo.getInteger("monthOfBirth");
            day = stdInfo.getInteger("dayOfBirth");
            idS = stdInfo.getLong("idNumber");

        }
        fistnameVar.setText(firstname);
        lastnameVar.setText(lastname);
        emailVar.setText(email);
        fosVar.setText(FOS);
        yearVar.setText(String.valueOf(year));
        monthVar.setText(String.valueOf(month));
        dayVar.setText(String.valueOf(day));
        idnumberVar.setText(String.valueOf(idS));
    }
}
