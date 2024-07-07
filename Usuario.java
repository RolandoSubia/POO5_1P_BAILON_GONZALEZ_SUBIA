import java.util.List;
import java.util.Scanner;

public abstract class Usuario {
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private String email;

    // Constructor para inicializar un objeto Usuario
    public Usuario(String usuario, String contrasenia, String nombre, String apellido, String email) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    
    // Métodos getter y setter para acceder y modificar los atributos de la clase
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract void generarCorreo();

    public abstract void decidirArticulo(Scanner sc, List<Revision> revisiones);
    
    // Método para gestionar el inicio de sesión de los usuarios
    public static void gestionarIniciarSesion(Scanner sc, List<Usuario> usuarios, List<Revision> revisiones, List<Articulo> articulos, List<Autor> autores) {
        System.out.println("Ingrese credenciales");
        System.out.print("Usuario: ");
        String user = sc.nextLine();
        System.out.print("Contrasenia: ");
        String contra = sc.nextLine();
        System.out.println(" ");
        // Verificar las credenciales del usuario
        for (Usuario usu : usuarios) {
            if (usu.getUsuario().equals(user) && usu.getContrasenia().equals(contra)) {
                if (usu instanceof Revisor) {
                    Revisor revisor = (Revisor) usu;
                    revisor.decidirArticulo(sc, revisiones);
                } else if (usu instanceof Editor) {
                    ((Editor) usu).gestionarRevisionEditor(sc, revisiones, articulos, autores);
                }
                return;
            }
        }
        System.out.println("Credenciales incorrectas.");
        System.out.println(" ");
    }
}
