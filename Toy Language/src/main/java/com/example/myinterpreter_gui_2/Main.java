package com.example.myinterpreter_gui_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader programChooserLoader = new FXMLLoader();
        programChooserLoader.setLocation(Main.class.getResource("ProgramChooserController.fxml"));

        Parent programChooserRoot = programChooserLoader.load();
        Scene programChooserScene = new Scene(programChooserRoot, 500, 560);
        programChooserScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("button_style1.css")).toExternalForm());

        ProgramChooserController programChooserController = programChooserLoader.getController();

        primaryStage.setTitle("Program List");
        primaryStage.setScene(programChooserScene);

        FXMLLoader programExecutorLoader = new FXMLLoader();
        programExecutorLoader.setLocation(Main.class.getResource("ProgramExecutorController.fxml"));

        Parent programExecutorRoot = programExecutorLoader.load();
        Scene programExecutorScene = new Scene(programExecutorRoot, 917, 650);
        programExecutorScene.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("button_style2.css")).toExternalForm());

        ProgramExecutorController programExecutorController = programExecutorLoader.getController();
        programChooserController.setProgramExecutorController(programExecutorController);

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Interpreter");
        secondaryStage.setScene(programExecutorScene);

        secondaryStage.show();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}