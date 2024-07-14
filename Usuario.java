import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Grupo3
 * 
 * 
 */

public abstract class Usuario {
//Atributos especificos de la clase Usuario.
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private String email;

    /**Constructor para inicializar un objeto Usuario.
    *@param usuario El usuario a ingresar.
    *@param contrasenia La contraseña del usuario.
    *@param nombre El nombre del usuario.
    *@param apellido El apellido del usuario.
    *@param email El email del usuario.
    **/
    public Usuario(String usuario, String contrasenia, String nombre, String apellido, String email) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }
    
    /**Sirve para recibir el usuario.
    *@return String Devuelve el usuario. 
    **/
    public String getUsuario() {
        return usuario;
    }

    /**Establece el usuario.
    *@param usuario El usuario a ingresar.
    **/
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**Sirve para recibir la contraseña.
    *@return String Devuelve la contraseña. 
    **/
    public String getContrasenia() {
        return contrasenia;
    }

    /**Establece  la contraseña.
    *@param contrasenia La contraseña del usuario.
    **/
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**Sirve para recibir el nombre.
    *@return String Devuelve el nombre. 
    **/
    public String getNombre() {
        return nombre;
    }

    /**Establece el nombre.
    *@param nombre El nombre del usuario.
    **/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**Sirve para recibir el apellido.
    *@return String Devuelve el apellido. 
    **/
    public String getApellido() {
        return apellido;
    }

    /**Establece el apellido.
    *@param apellido El apellido del usuario.
    **/
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**Sirve para recibir el email.
    *@return String Devuelve el email. 
    **/
    public String getEmail() {
        return email;
    }

    /**Establece el email.
    *@param email El email del usuario.
    **/
    public void setEmail(String email) {
        this.email = email;
    }

   /**Método abstracto que debe ser implementado por las subclases para generar un correo.
    **/
    public abstract void generarCorreo();

    /**Método abstracto que debe ser implementado por las subclases para decidir sobre un artículo.
    *@param sc Scanner que obtiene la decision sobre el articulo.
    *@param revisiones Lista que contiene las revisiones.
    **/
    public abstract void decidirArticulo(Scanner sc, List<Revision> revisiones);
    
    /**Gestiona el inicio de sesión de los usuarios, verificando sus credenciales y redirigiendo a la funcionalidad adecuada según el tipo de usuario.
    *@param sc Scanner que recibe los datos del usuario.
    *@param usuarios Lista que contiene los usuarios.
    *@param revisiones Lista que contiene las revisiones.
    *@param articulos Lista que contiene los articulos.
    *@param autores Lista que contiene a los autores.
    **/
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
