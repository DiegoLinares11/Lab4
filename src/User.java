public class User {
    private String usuario;
    private String contraseña;
    private boolean esPremium = false;

    public User(String usuario, String contraseña, boolean esPremium) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.esPremium = esPremium;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isEsPremium() {
        return esPremium;
    }

    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }

}