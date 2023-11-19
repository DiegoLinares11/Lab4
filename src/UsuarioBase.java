import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsuarioBase implements ITipoUsuario {

    private static final int MAX_LIBROS = 3;
    private static final int MAX_DIAS = 30;
    private int librosPrestados;

    Scanner scan = new Scanner(System.in);

    @Override
    public void prestarLibro(String nombre, int dias) {

        if (librosPrestados < MAX_LIBROS && dias <= MAX_DIAS) {
            // Implementa la lógica para prestar un libro para UsuarioBase aquí
            System.out.println("UsuarioBase - Libro prestado: " + nombre + ", días: " + dias);
            librosPrestados++;
        } else {
            System.out.println("No se puede prestar el libro. Se alcanzó el límite de libros o días de préstamo.");
        }

    }

    @Override
    public void prestarRevista(Revista revista) {/*
                                                  * System.out.println("Ingrese el nombre de la revista a agregar: ");
                                                  * String nombre = scan.nextLine();
                                                  * System.out.println("Ingrese la cantidad disponible de revistas: ");
                                                  * int cantidad = scan.nextInt();
                                                  * scan.nextLine();
                                                  * System.out.println("Ingrese el precio de la revista: ");
                                                  * int precio = scan.nextInt();
                                                  * scan.nextLine();
                                                  * Revista reevista = new Revista("Revista", nombre, cantidad, precio,
                                                  * 0);
                                                  * inventario.add(reevista);
                                                  * System.out.println("Revista agregada: " + revista.getNombre());
                                                  */
    }

}
