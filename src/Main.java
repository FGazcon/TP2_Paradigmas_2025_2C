import Catan.Catan;
import Dados.Dados;
import Jugador.Jugador;
import Tablero.Tablero;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    IO.println(String.format("Hello and welcome!"));

    for (int i = 1; i <= 5; i++) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        IO.println("i = " + i);
    }

    Dados dados = new Dados();

    dados.tirarDados();
    dados.tirarDados();
    dados.tirarDados();

    Catan catan = new Catan();
    //catan.prepararJugadores();
    Jugador jugador = new Jugador("Matias");
    Jugador jugador2 = new Jugador("Matias2");
    //catan.primeraEtapa();
    catan.segundaEleccion(jugador, 45);
    catan.segundaEleccion(jugador2, 47);
    jugador.imprimirRecursos();
    jugador2.imprimirRecursos();
}
