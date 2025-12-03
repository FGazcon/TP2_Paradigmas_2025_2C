package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        model.Banco.Banco banco = new model.Banco.Banco();
        model.Catan.Catan catan = new model.Catan.Catan(banco);

        new view.MenuObserver(catan);
        new view.RegistroObserver(catan);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(loader.load());

        controllers.MenuController controller = loader.getController();
        controller.setModelo(catan);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("CATAN - Paradigmas de Programación - Grupo 10");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}