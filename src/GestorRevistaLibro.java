import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestorRevistaLibro {
    public List<IRevistaLibro> leerRevistaLibro(String nombreArchivo) {
        List<IRevistaLibro> inventario = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    int id = Integer.parseInt(partes[0].trim());
                    String tipo = (partes[1].trim());
                    String nombre = (partes[2].trim());
                    int cantidad = Integer.parseInt(partes[3].trim());
                    int precio = Integer.parseInt(partes[4].trim());
                    int cantidadVendida = Integer.parseInt(partes[5].trim());
                    IRevistaLibro RevLib = new IRevistaLibro(id, tipo, nombre, cantidad, precio, cantidadVendida);
                    inventario.add(RevLib);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return inventario;
    }

    public void imprimirInventario(List<IRevistaLibro> inventario) {
        System.out.println("Inventario actual:");
        for (IRevistaLibro RevLib : inventario) {
            System.out.println(RevLib);
        }
    }

    public void guardarCSV(List<IRevistaLibro> inventario, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (IRevistaLibro producto : inventario) {
                String linea = producto.getId() + "," + producto.getNombre() + "," + producto.getPrecio() + ","
                        + producto.getCantidad() + "," + producto.getCantidadVendida();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
