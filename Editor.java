import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Grupo3
 * 
 * 
 */
public class Editor extends Usuario {
    //Atributos especificos de la clase Editor
    private String nombreJournal;

    /**Constructor para inicializar un objeto Usuario.
    *@param usuario El usuario del editor.
    *@param contrasenia La contraseña del editor.
    *@param nombre El nombre del editor.
    *@param apellido El apellido del editor.
    *@param email El email del editor.
    *@param nombreJournal Nombre del Journal para el que trabaja el editor.
    **/
    public Editor(String usuario, String contrasenia, String nombre, String apellido, String email, String nombreJournal) {
        super(usuario, contrasenia, nombre, apellido, email);
        this.nombreJournal = nombreJournal;
    }

    /**Sirve para recibir el nombre del Journal.
    *@return String Devuelve el nombre del Journal. 
    **/
    public String getNombreJournal() {
        return nombreJournal;
    }

    /**Establece el nombre del Journal.
    *@param nombreJournal Nombre del Journal para el que trabaja el editor.
    **/
    public void setNombreJournal(String nombreJournal) {
        this.nombreJournal = nombreJournal;
    }

    /**Genera un correo para el editor.
    **/
    @Override
    public void generarCorreo() {
        System.out.println("Correo generado para el Editor: " + getEmail());
    }

    /**Este método no es utilizado por la clase Editor.
    *@param scanner Scanner que recibe la decision del articulo.
    *@param revisiones Lista que contiene las revisiones.
    **/
    @Override
    public void decidirArticulo(Scanner scanner, List<Revision> revisiones) {
    }

    /**Gestiona las revisiones de los artículos, recopilando los comentarios de los revisores y tomando una decisión final sobre la publicación del artículo.
    *@param sc Scanner que recibe la gestion del articulo.
    *@param revisiones Lista que contiene las revisiones.
    *@param articulos Lista que contiene los articulos.
    *@param autores Lista que contiene los autores.
    **/
    public void gestionarRevisionEditor(Scanner sc, List<Revision> revisiones, List<Articulo> articulos, List<Autor> autores) {
       System.out.print("Ingrese el codigo del articulo: ");
        String codigoArticulo = sc.nextLine();
        System.out.println(" ");
        
        Articulo articulo = null;
        for (Articulo art : articulos) {
            if (art.getCodigo().equals(codigoArticulo)) {
                articulo = art;
                break;
            }
        }

        if (articulo == null) {
            System.out.println("Articulo no encontrado.");
            System.out.println(" ");
            return;
        }

        StringBuilder comentariosFinales = new StringBuilder();
        boolean decisionFinal = true;

        System.out.println("Comentarios y decisiones de los revisores para el articulo ");
        for (Revision rev : revisiones) {
            if (rev.getCodigoArticulo().equals(codigoArticulo)) {
                System.out.println("Comentarios del revisor " + rev.getRevisorId() + ": " + rev.getComentarios());
                System.out.println("Decision del revisor: " + (rev.isDecision() ? "Si" : "No"));
                System.out.println(" ");
                comentariosFinales.append("Comentarios del revisor ").append(rev.getRevisorId()).append(": ").append(rev.getComentarios()).append("\n");
                System.out.println(" ");
                if (!rev.isDecision()) {
                    decisionFinal = false;
                }
            }
        }

        System.out.println("Decision final si se publica el articulo (si/no): ");
        String decisionFinalStr = sc.nextLine();
        decisionFinal = decisionFinalStr.equalsIgnoreCase("si");

        enviarCorreoAlAutor(autores, articulo, decisionFinal, comentariosFinales.toString());
    }


    /**Envía un correo al autor del artículo con el resultado de la revisión y los comentarios de los revisores.
    *@param autores Lista que contiene los autores.
    *@param articulo Metodo que invoca al articulo
    *@param decision Booleano que contiene la decision
    *@param comentarios Los comentarios dados por el revisor.
    **/
    private void enviarCorreoAlAutor(List<Autor> autores, Articulo articulo, boolean decision, String comentarios) {
        Autor autor = null;
        for (Autor aut : autores) {
            if (aut.getId().equals(articulo.getAutorId())) {
                autor = aut;
                break;
            }
        }

        if (autor != null) {
            String resultado = decision ? "aceptado" : "rechazado";
            System.out.println("Enviando correo a " + autor.getEmail() + " sobre el articulo " + articulo.getTitulo() + " con resultado: " + resultado);
            System.out.println(" ");
            System.out.println("Comentarios de los revisores ");
            System.out.println(comentarios);
            // Aquí puedes implementar la lógica para enviar correos electrónicos reales
        }
    }
}
