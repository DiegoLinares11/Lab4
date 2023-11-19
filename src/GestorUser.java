import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorUser {
    public List<User> leerCSV(String nombreArchivo) {
        List<User> inventario = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String usuario = (partes[0].trim());
                    String contraseña = (partes[1].trim());
                    Boolean esPremium = Boolean.parseBoolean(partes[2].trim());
                    User user = new User(usuario, contraseña, esPremium);
                    inventario.add(user);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return inventario;
    }

    public List<Venta> leerVentas(String rutaArchivo) throws IOException {
        List<Venta> ventas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separamos la línea por comas
                String[] datos = linea.split(",");

                // Creamos una nueva venta
                Venta venta = new Venta(datos[0], Integer.parseInt(datos[1]), Double.parseDouble(datos[2]),
                        LocalDate.parse(datos[3]));

                // Agregamos la venta al List
                ventas.add(venta);
            }
        }

        return ventas;
    }

    public void imprimirInventario(List<User> inventario) {
        System.out.println("Inventario actual:");
        for (User user : inventario) {
            System.out.println(user);
        }
    }

    public void imprimirVentas(List<Venta> ventas) {
        System.out.println("Ventas realizadas:");
        for (Venta venta : ventas) {
            System.out.println(venta);
        }
    }

    public void agregarUser(List<User> inventario, User nuevoUser) {
        inventario.add(nuevoUser);
    }

    public void guardarCSV(List<User> inventario, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (User user : inventario) {
                String linea = user.getUsuario() + "," + user.getContraseña() + "," + user.isEsPremium();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarCSV2(List<Venta> ventas, String nombreArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Venta venta : ventas) {
                String linea = venta.getNombreProducto() + "," + venta.getCantidadVendida() + "," + venta.getTotal()
                        + "," + venta.getFecha();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
