package com.mpp.librarysys.javafx.helper;

import com.mpp.librarysys.javafx.MainStageInitializer;
import com.mpp.librarysys.javafx.constants.FxmlEnums;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

public final class NavigationManager {

    public static FXMLLoader getFxmlLoader(ApplicationContext context, Stage currStage, Enum<?> fxml) {
        FXMLLoader loader = new FXMLLoader();

        String resourcePath = "";
        if (fxml instanceof FxmlEnums.FxmlView) {
            FxmlEnums.FxmlView fxmlView = (FxmlEnums.FxmlView) fxml;
            resourcePath = fxmlView.getValue();
        } else if (fxml instanceof FxmlEnums.FxmlContainer) {
            FxmlEnums.FxmlContainer fxmlContainer = (FxmlEnums.FxmlContainer) fxml;
            resourcePath = fxmlContainer.getValue();
        }
        loader.setLocation(MainStageInitializer.class.getResource(resourcePath));
        loader.setControllerFactory(aClass -> context.getBean(aClass));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AppAbstractFxController controller = loader.getController();
        controller.setStage(currStage);
        return loader;
    }

    public static <T extends AppAbstractFxController> T switchScene(ApplicationContext applicationContext, Stage currStage, FxmlEnums.FxmlView fxmlView) {

        FXMLLoader loader = NavigationManager.getFxmlLoader(applicationContext, currStage, fxmlView);

        Parent rootNode = loader.getRoot();
        Scene scene = new Scene(rootNode, 420, 480);
        currStage.setScene(scene);
        currStage.setResizable(true);
        currStage.show();
        return (T) loader.getController();
    }

    private static void closeFxmlStage(Node currNode) {
        Node node = (Node) currNode;
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

}