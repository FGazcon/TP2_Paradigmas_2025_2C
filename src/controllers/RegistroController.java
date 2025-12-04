package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import model.Banco.Banco;
import model.Jugador.Jugador;

import java.util.ArrayList;
import java.util.List;

import model.Catan.Catan;

public class RegistroController
{

    private Catan modelo;

    public void setModelo(model.Catan.Catan modelo) {
        this.modelo = modelo;
    }

    @FXML private TextField txtJugador1;
    @FXML private TextField txtJugador2;
    @FXML private TextField txtJugador3;
    @FXML private TextField txtJugador4;

    @FXML
    public void comenzarPartida(ActionEvent event)
    {
        List<Jugador> jugadores = new ArrayList<>();
        String nombre1 = txtJugador1.getText().trim();
        String nombre2 = txtJugador2.getText().trim();
        String nombre3 = txtJugador3.getText().trim();
        String nombre4 = txtJugador4.getText().trim();

        Banco banco = new Banco();


        if(!nombre1.isEmpty()){
            Jugador jugador1 = new Jugador(nombre1, banco);
            jugadores.add(jugador1);
        }
        if(!nombre2.isEmpty()){
            Jugador jugador2 = new Jugador(nombre2, banco);
            jugadores.add(jugador2);
        }

        if(!nombre3.isEmpty()){
            Jugador jugador3 = new Jugador(nombre3, banco);
            jugadores.add(jugador3);
        }

        if(!nombre4.isEmpty()){
            Jugador jugador4 = new Jugador(nombre4, banco);
            jugadores.add(jugador4);
        }


        //este es el condicional de cantidad de jugadores
        if (jugadores.size() < 3)
        {
            System.out.println("Faltan nombres! (de 3 a 4 jugadores)");
            return;
        }

        //System.out.println("Jugadores registrados: " + nombres);

        //modelo.prepararJugadoresConNombres(nombres);

        Catan catan = new Catan(jugadores, banco);

        cambiarEscena(event, "/fxml/inicial.fxml", catan);

        // Para mostrar pantalla de ganador comentar linea 40 y descomentar la 44 (POR AHORA)

        // cambiarEscena(event, "/ganador.fxml");
    }

    @FXML
    public void salir(ActionEvent event)
    {
        System.exit(0);
    }


    private void cambiarEscena(ActionEvent event, String fxml, Catan catan)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Scene nueva = new Scene(loader.load());

            InicialController controller = loader.getController();
            controller.init(catan);

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