package com.mpp.librarysys.javafx.util;

import com.mpp.librarysys.javafx.MainStageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public final class NavigationManager {


    public static void closeStage(Node currNode) {
        Node node = (Node) currNode;
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static Parent showFxmlView(ApplicationContext applicationContext, Stage stage, FxmlView fxmlView) {

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(MainStageManager.class.getResource(fxmlView.getValue()));
        loader.setControllerFactory(aClass -> applicationContext.getBean(aClass));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene scene = new Scene(rootNode, 600, 600);

        stage.setScene(scene);
        stage.show();
        return rootNode;
    }

}