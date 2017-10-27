package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene=new Scene(root,800,400);
        scene.getStylesheets().add(getClass().getResource("baithuchanhso2.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ken Hoàng");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
