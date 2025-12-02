package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import model.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

public class RegistroController
{

    @FXML private TextField txtJugador1;
    @FXML private TextField txtJugador2;
    @FXML private TextField txtJugador3;
    @FXML private TextField txtJugador4;

    @FXML
    public void comenzarPartida(ActionEvent event)
    {
        List<String> nombres = new ArrayList<>();
        String nombre1 = txtJugador1.getText().trim();
        String nombre2 = txtJugador2.getText().trim();
        String nombre3 = txtJugador3.getText().trim();
        String nombre4 = txtJugador4.getText().trim();

        if(!nombre1.isEmpty()){
            nombres.add(nombre1);
            Jugador jugador1 = new Jugador(nombre1);
        }
        if(!nombre2.isEmpty()){
            nombres.add(nombre2);
            Jugador jugador2 = new Jugador(nombre2);
        }

        if(!nombre3.isEmpty()){
            nombres.add(nombre3);
            Jugador jugador3 = new Jugador(nombre3);
        }

        if(!nombre4.isEmpty()){
            nombres.add(nombre4);
            Jugador jugador4 = new Jugador(nombre4);
        }

        //este es el condicional de cantidad de jugadores
        if (nombres.size() < 3)
        {
            System.out.println("Faltan nombres! (de 3 a 4 jugadores)");
            return;
        }

        System.out.println("Jugadores registrados: " + nombres);

        cambiarEscena(event, "/fxml/juego.fxml");

        // Para mostrar pantalla de ganador comentar linea 40 y descomentar la 44 (POR AHORA)

        // cambiarEscena(event, "/ganador.fxml");
    }

    @FXML
    public void salir(ActionEvent event)
    {
        System.exit(0);
    }

    private void cambiarEscena(ActionEvent event, String fxml)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene nueva = new Scene(loader.load());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(nueva);
            stage.show();

        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}