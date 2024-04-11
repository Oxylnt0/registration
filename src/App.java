import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primary) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("primary.fxml"));
        Scene scene = new Scene(root);
        primary.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Register.css").toExternalForm());
        primary.setTitle("Sports Registration");
        primary.setTitle("San Sebastian Recoletos Sports Registration");
        primary.getIcons().add(new Image("SSCRLogo1.png"));
        primary.setResizable(false);
        primary.setMaximized(true);
        primary.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
