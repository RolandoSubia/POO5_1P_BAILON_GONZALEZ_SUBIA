import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Revisor extends Usuario {
    private String especialidad;
    private int numArticulosRevisados;
    private boolean tieneArticuloPendiente = false;
    private Articulo articuloAsignado;

    // Constructor para inicializar un objeto Revisor
    public Revisor(String usuario, String contrasenia, String nombre, String apellido, String email, String especialidad) {
        super(usuario, contrasenia, nombre, apellido, email);
        this.especialidad = especialidad;
        this.numArticulosRevisados = 0; 
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getNumArticulosRevisados() {
        return numArticulosRevisados;
    }

    public void incrementarArticulosRevisados() {
        this.numArticulosRevisados++;
    }

    @Override
    public void generarCorreo() {
        System.out.println("Correo generado para el Revisor: " + getEmail());
    }

    //Metodo si decide aceptar o no el articulo
    @Override
    public void decidirArticulo(Scanner sc, List<Revision> revisiones) {
        if (tieneArticuloPendiente) {
            System.out.println("Se le ha asignado el siguiente articulo");
            System.out.println("Titulo: " + articuloAsignado.getTitulo());
            System.out.println("Resumen: " + articuloAsignado.getResumen());
            System.out.println("Contenido: " + articuloAsignado.getContenido());
            System.out.println("Palabras clave: " + articuloAsignado.getPalabrasClave());

            System.out.print("Acepta revisar el articulo? (si/no): ");
            String decisionStr = sc.nextLine();
            boolean decision = decisionStr.equalsIgnoreCase("si");
            if (decision) {
                System.out.println("El articulo ha sido aceptado. Procediendo con el siguiente paso.");
                System.out.println(" ");
                gestionarRevisionRevisor(sc, revisiones);
            } else {
                System.out.println("El articulo fue rechazado para la revision.");
                System.out.println(" ");
                tieneArticuloPendiente = false;
                articuloAsignado = null;
            }
        } else {
            System.out.println("No tiene articulos asignados.");
        }
    }

    // Método específico del revisor para gestionar la revisión
    public void gestionarRevisionRevisor(Scanner sc, List<Revision> revisiones) {
        System.out.println("Articulos asignados para revision");
        for (Revision rev : revisiones) {
            if (rev.getRevisorId().equals(this.getUsuario()) && rev.getCodigoArticulo().equals(articuloAsignado.getCodigo())) {
                System.out.println("Codigo del articulo: " + rev.getCodigoArticulo());
                System.out.println("Ingrese comentarios: ");
                String comentarios = sc.nextLine();
                rev.setComentarios(comentarios);
                System.out.println(" ");
                
                System.out.println("Decide enviar el articulo al editor.");
                System.out.print(" (si/no): ");
                String decisionStr = sc.nextLine();
                System.out.println(" ");
                boolean decision = decisionStr.equalsIgnoreCase("si");
                System.out.println(" ");
                if (decision) {
                    rev.setDecision(true); // Artículo aceptado
                    guardarRevision(rev);
                    enviarArticuloAlEditor(rev);
                    tieneArticuloPendiente = false;
                    articuloAsignado = null;
                } else {
                    rev.setDecision(false); 
                    System.out.println("Articulo no paso la revision.");
                    System.out.println(" ");
                }
                return; // dale del bucle después de una revisión
            }
        }
    }

    private void guardarRevision(Revision revis) {
        try {
            String data3 = String.join(",", revis.getCodigoArticulo(), revis.getRevisorId(), revis.getComentarios(), String.valueOf(revis.isDecision()));
            File file = new File("revisiones.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("revisiones.txt"), (data3 + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al guardar la revision: " + e.getMessage());
        }
    }

    private void enviarArticuloAlEditor(Revision revision) {
        // Implementar la lógica para enviar el artículo al editor
        System.out.println("El articulo ha sido enviado al editor para su revision final.");
    }
    
    public void asignarArticulo(Articulo articulo) {
        this.articuloAsignado = articulo;
        this.tieneArticuloPendiente = true;
    }
}


