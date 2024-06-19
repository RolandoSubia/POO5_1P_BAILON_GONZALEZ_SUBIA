public class Editor {
    private String journal;
    private String user;
    private String contrasenia;
    private static char rol;

    public Editor(String journal, String user, String contrasenia) {
        this.journal = journal;
        this.user = user;
        this.contrasenia = contrasenia;
        rol = 'E';
    }

    public void ingresarDatosEditor(String nombre, String nuevoJournal, String nuevaContrasenia) {
        this.user = nombre;
        this.journal = nuevoJournal;
        this.contrasenia = nuevaContrasenia;
    }

    public boolean publicarArticulo() {
        return true;
    }

    public void enviarDecision() {
  
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public static char getRol() {
        return rol;
    }

    public static void setRol(char rol) {
        Editor.rol = rol;
    }
}

