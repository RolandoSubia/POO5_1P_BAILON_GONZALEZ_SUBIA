import java.util.List;
import java.util.Scanner;

public class Editor extends Usuario {
    private String nombreJournal;

    // Constructor para inicializar un objeto Editor
    public Editor(String usuario, String contrasenia, String nombre, String apellido, String email, String nombreJournal) {
        super(usuario, contrasenia, nombre, apellido, email);
        this.nombreJournal = nombreJournal;
    }

    public String getNombreJournal() {
        return nombreJournal;
    }

    public void setNombreJournal(String nombreJournal) {
        this.nombreJournal = nombreJournal;
    }

    @Override
    public void generarCorreo() {
        System.out.println("Correo generado para el Editor: " + getEmail());
    }

    @Override
    public void decidirArticulo(Scanner scanner, List<Revision> revisiones) {
        // El editor no utiliza este método
    }

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
