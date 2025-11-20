package Recurso;

public class RecursoFactory {
    //Recurso recurso;

    public static Recurso crearRecurso(String tipo){
        return switch (tipo.toLowerCase()) {
            case "madera" -> new Madera();
            case "piedra" -> new Piedra();
            case "trigo" -> new Trigo();
            case "ladrillo" -> new Ladrillo();
            case "oveja" -> new Oveja();
            default -> throw new IllegalArgumentException("Tipo de recurso inválido: " + tipo);
        };
    }
}
