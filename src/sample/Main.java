package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("MIPS ASSEMBLER");
        primaryStage.setScene(new Scene(root));

        //Initialize Register Table
        Controller controller = loader.getController();
        controller.setInitialRegisters();
        controller.setInitialAddress();

        primaryStage.show();
        System.out.println("Start Program");

    }

    public static void main(String[] args) {
        launch(args);
    }
}

