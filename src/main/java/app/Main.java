package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controllers.MenuController;
import model.Banco.Banco;
import model.Catan.Catan;
import utils.MusicPlayer;
import view.MenuObserver;
import view.RegistroObserver;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Banco banco = new Banco();
        Catan catan = new Catan(banco);

        new MenuObserver(catan);
        new RegistroObserver(catan);

        MusicPlayer.play("/audio/musica_fondo_ok.wav");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"));
        Scene scene = new Scene(loader.load());

        MenuController controller = loader.getController();
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