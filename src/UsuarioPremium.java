import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioPremium implements ITipoUsuario {

    private static final int MAX_LIBROS = 5;
    private static final int MAX_DIAS = 50;
    private int librosPrestados;

    Scanner scan = new Scanner(System.in);

    @Override
    public void prestarLibro(String nombreLibro, int dias) {
        /*
         * if (librosPrestados < MAX_LIBROS && dias <= MAX_DIAS) {
         * // Implementa la lógica para prestar un libro para UsuarioPremium aquí
         * System.out.println("UsuarioPremium - Libro prestado: " + nombreLibro +
         * ", días: " + dias);
         * librosPrestados++;
         * } else {
         * System.out.
         * println("No se puede prestar el libro. Límite de libros o días excedido.");
         * }
         */
    }

    @Override
    public void prestarRevista(Revista revista) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarRevista'");
    }

}
