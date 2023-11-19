//Clase Padre
public class IRevistaLibro {

    private int id;
    private String nombre;
    private String tipo;
    private int cantidad;
    private int precio;
    private int cantidadVendida;

    public IRevistaLibro(int id, String nombre, String tipo, int cantidad, int precio, int cantidadVendida) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.cantidadVendida = cantidadVendida;
    }

    @Override
    public String toString() {
        return "IRevistaLibro [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", cantidad=" + cantidad
                + ", precio=" + precio + ", cantidadVendida=" + cantidadVendida + "]";
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}