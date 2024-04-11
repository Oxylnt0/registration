import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

public class controller implements Initializable {

    @FXML
    private VBox vboxathletic;

    @FXML
    private VBox vboxhealth;

    @FXML
    private VBox vboxpersonal;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private BorderPane borderpane;

    @FXML
    private TextField firstname;

    @FXML
    private TextField middlename;

    @FXML
    private TextField lastname;

    @FXML
    private TextField nationality;

    @FXML
    private TextField street;

    @FXML
    private TextField city;

    @FXML
    private TextField province;

    @FXML
    private TextField email;

    @FXML
    private TextField number;

    @FXML
    private TextField religion;

    @FXML
    private TextField height;

    @FXML
    private TextField weight;

    @FXML
    private TextArea sport_experience;

    @FXML
    private TextField age;

    @FXML
    private TextArea medical_condition;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton male;

    @FXML
    private RadioButton no;

    @FXML
    private RadioButton no1;

    @FXML
    private RadioButton yes;

    @FXML
    private RadioButton yes1;

    @FXML
    private Button submit;

    @FXML
    private Button reset;

    @FXML
    private ComboBox<String> sports;

    @FXML
    private Label agelabel;

    @FXML
    private Label citylabel;

    @FXML
    private Label condlabel;

    @FXML
    private Label explabel;

    @FXML
    private Label fnamelabel;

    @FXML
    private Label hlabel;

    @FXML
    private Label lnamelabel;

    @FXML
    private Label mnamelabel;

    @FXML
    private Label nationlabel;

    @FXML
    private Label provlabel;

    @FXML
    private Label religionlabel;

    @FXML
    private Label sexlabel;

    @FXML
    private Label sportlabel;

    @FXML
    private Label streetlabel;

    @FXML
    private Label wlabel;

    @FXML
    private Label numlabel;

    @FXML
    private Label emaillabel;

    @FXML
    private ToggleGroup sportexp;

    @FXML
    private ToggleGroup mftoggle;

    @FXML
    private ToggleGroup medcond;

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {

        sports.setItems(FXCollections.observableArrayList("Basketball", "Volleyball", "Badminton"));

        sports.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(sports.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

    }

    @FXML
    public void onsubmit(ActionEvent event) {

        try {
            String sex = "";
            String choice = "";
            String choice1 = "";

            if (male.isSelected()) {
                sex = "Male";
            }

            else if (female.isSelected()) {
                sex = "Female";
            }

            if (yes.isSelected()) {
                choice = "Yes";
            }

            else if (no.isSelected()) {
                choice = "No";
            }

            if (yes1.isSelected()) {
                choice1 = "Yes";
            }

            else if (no1.isSelected()) {
                choice1 = "No";
            }
            // SQL
            database database = new database();

            Connection connectdb = database.getConnection();

            PreparedStatement ps1 = connectdb
                    .prepareStatement("INSERT INTO player_id (firstname, middlename, lastname) Values (?, ?, ?)");

            ps1.setString(1, firstname.getText());
            ps1.setString(2, middlename.getText());
            ps1.setString(3, lastname.getText());

            PreparedStatement ps2 = connectdb
                    .prepareStatement(
                            "INSERT INTO player_info (age, sex, Nationality, Religion, height, weight, street_address, city, province, mobile_no, email) Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps2.setString(1, age.getText());
            ps2.setString(2, sex);
            ps2.setString(3, nationality.getText());
            ps2.setString(4, religion.getText());
            ps2.setString(5, height.getText());
            ps2.setString(6, weight.getText());
            ps2.setString(7, street.getText());
            ps2.setString(8, city.getText());
            ps2.setString(9, province.getText());
            ps2.setString(10, number.getText());
            ps2.setString(11, email.getText());

            PreparedStatement ps3 = connectdb
                    .prepareStatement(
                            "INSERT INTO player_sports_info (sport, sport_experience, sport_specifics) Values (?, ?, ?)");

            ps3.setString(1, sports.getValue());
            ps3.setString(2, choice);
            ps3.setString(3, sport_experience.getText());

            PreparedStatement ps4 = connectdb
                    .prepareStatement(
                            "INSERT INTO player_health (medical_condition, condition_specifics) Values (?, ?)");

            ps4.setString(1, medical_condition.getText());
            ps4.setString(2, choice1);

            if (firstname.getText().isEmpty() |
                    middlename.getText().isEmpty() |
                    lastname.getText().isEmpty() |
                    age.getText().isEmpty() |
                    (!male.isSelected() &
                            !female.isSelected())
                    |
                    nationality.getText().isEmpty() |
                    religion.getText().isEmpty() |
                    height.getText().isEmpty() |
                    weight.getText().isEmpty() |
                    street.getText().isEmpty() |
                    city.getText().isEmpty() |
                    province.getText().isEmpty() |
                    number.getText().isEmpty() |
                    email.getText().isEmpty() |
                    (!yes.isSelected() &
                            !no.isSelected())
                    |
                    sports.getValue() == null |
                    (!yes1.isSelected() &
                            !no1.isSelected())) {
                if (firstname.getText().isEmpty()) {
                    fnamelabel.setText("*Required field");
                } else if (!firstname.getText().isEmpty()) {
                    fnamelabel.setText("");
                }
                if (middlename.getText().isEmpty()) {
                    mnamelabel.setText("*Required field");
                } else if (!middlename.getText().isEmpty()) {
                    mnamelabel.setText("");
                }
                if (lastname.getText().isEmpty()) {
                    lnamelabel.setText("*Required field");
                } else if (!lastname.getText().isEmpty()) {
                    lnamelabel.setText("");
                }
                if (age.getText().isEmpty()) {
                    agelabel.setText("*Required field");
                } else if (!age.getText().isEmpty()) {
                    agelabel.setText("");
                }
                if (!male.isSelected() & !female.isSelected()) {
                    sexlabel.setText("*Required field");
                } else if (male.isSelected() | female.isSelected()) {
                    sexlabel.setText("");
                }
                if (nationality.getText().isEmpty()) {
                    nationlabel.setText("*Required field");
                } else if (!nationality.getText().isEmpty()) {
                    nationlabel.setText("");
                }
                if (religion.getText().isEmpty()) {
                    religionlabel.setText("*Required field");
                } else if (!religion.getText().isEmpty()) {
                    religionlabel.setText("");
                }
                if (height.getText().isEmpty()) {
                    hlabel.setText("*Required field");
                } else if (!height.getText().isEmpty()) {
                    hlabel.setText("");
                }
                if (weight.getText().isEmpty()) {
                    wlabel.setText("*Required field");
                } else if (!weight.getText().isEmpty()) {
                    wlabel.setText("");
                }
                if (street.getText().isEmpty()) {
                    streetlabel.setText("*Required field");
                } else if (!street.getText().isEmpty()) {
                    streetlabel.setText("");
                }
                if (city.getText().isEmpty()) {
                    citylabel.setText("*Required field");
                } else if (!city.getText().isEmpty()) {
                    citylabel.setText("");
                }
                if (province.getText().isEmpty()) {
                    provlabel.setText("*Required field");
                } else if (!province.getText().isEmpty()) {
                    provlabel.setText("");
                }
                if (number.getText().isEmpty()) {
                    numlabel.setText("*Required field");
                } else if (!number.getText().isEmpty()) {
                    numlabel.setText("");
                }
                if (email.getText().isEmpty()) {
                    emaillabel.setText("*Required field");
                } else if (!email.getText().isEmpty()) {
                    emaillabel.setText("");
                }
                if (!yes.isSelected() & !no.isSelected()) {
                    explabel.setText("*Required field");
                } else if (yes.isSelected() | no.isSelected()) {
                    explabel.setText("");
                }
                if (sports.getValue() == null) {
                    sportlabel.setText("*Required field");
                } else if (!sports.getValue().isEmpty()) {
                    sportlabel.setText("");
                }
                if (!yes1.isSelected() & !no1.isSelected()) {
                    condlabel.setText("*Required field");
                } else if (yes1.isSelected() | no1.isSelected()) {
                    condlabel.setText("");
                }
            } else if (!firstname.getText().isEmpty() &
                    !middlename.getText().isEmpty() &
                    !lastname.getText().isEmpty() &
                    !age.getText().isEmpty() &
                    (male.isSelected() |
                            female.isSelected())
                    |
                    !nationality.getText().isEmpty() &
                            !religion.getText().isEmpty() &
                            !height.getText().isEmpty() &
                            !weight.getText().isEmpty() &
                            !street.getText().isEmpty() &
                            !city.getText().isEmpty() &
                            !province.getText().isEmpty() &
                            !number.getText().isEmpty() &
                            !email.getText().isEmpty() &
                            (yes.isSelected() |
                                    no.isSelected())
                    |
                    sports.getValue() == null &
                            (yes1.isSelected() |
                                    no1.isSelected())) {

                fnamelabel.setText("");
                mnamelabel.setText("");
                lnamelabel.setText("");
                agelabel.setText("");
                sexlabel.setText("");
                sexlabel.setText("");
                nationlabel.setText("");
                religionlabel.setText("");
                hlabel.setText("");
                wlabel.setText("");
                streetlabel.setText("");
                citylabel.setText("");
                provlabel.setText("");
                numlabel.setText("");
                emaillabel.setText("");
                explabel.setText("");
                explabel.setText("");
                sportlabel.setText("");
                condlabel.setText("");
                condlabel.setText("");

                ps1.executeUpdate();
                ps2.executeUpdate();
                ps3.executeUpdate();
                ps4.executeUpdate();

                JOptionPane.showMessageDialog(null, "You have successfully registered!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onreset(ActionEvent event) {
        firstname.setText("");
        middlename.setText("");
        lastname.setText("");
        age.setText("");
        male.setSelected(false);
        female.setSelected(false);
        nationality.setText("");
        religion.setText("");
        height.setText("");
        weight.setText("");
        street.setText("");
        city.setText("");
        province.setText("");
        number.setText("");
        email.setText("");
        yes.setSelected(false);
        no.setSelected(false);
        sports.setValue(null);
        sport_experience.setText("");
        yes1.setSelected(false);
        no1.setSelected(false);
        medical_condition.setText("");

        fnamelabel.setText("");
        mnamelabel.setText("");
        lnamelabel.setText("");
        agelabel.setText("");
        sexlabel.setText("");
        nationlabel.setText("");
        religionlabel.setText("");
        hlabel.setText("");
        wlabel.setText("");
        streetlabel.setText("");
        citylabel.setText("");
        provlabel.setText("");
        numlabel.setText("");
        emaillabel.setText("");
        explabel.setText("");
        sportlabel.setText("");
        condlabel.setText("");
    }

}
