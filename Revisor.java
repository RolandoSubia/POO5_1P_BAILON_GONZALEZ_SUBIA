
/**
 * 
 * @author Grupo3
 * 
 * 
 */

public class Revisor extends Usuario {
    //Atributos especificos para la clase Revisor
    private String especialidad;
    private int numArticulosRevisados;
    private boolean tieneArticuloPendiente = false;
    private Articulo articuloAsignado;

    /**
     * Constructor para inicializar un objeto Revisor.
     *@param usuario Nombre de Usuario
     *@param contrasenia Contraseña de Usuario
     *@param nombre Nombre del revisor
     *@param apellido Apellido del revisor
     *@param email Email del revisor
     *@param especialidad Especialidad del revisor
     **/
    public Revisor(String usuario, String contrasenia, String nombre, String apellido, String email, String especialidad) {
        super(usuario, contrasenia, nombre, apellido, email);
        this.especialidad = especialidad;
        this.numArticulosRevisados = 0; 
    }

    /**
    *Sirve para devolver la especialidad.
    *@return Devuelve la especialidad, un string. 
    **/
    public String getEspecialidad() {
        return especialidad;
    }

    /**
    *Establece la especialidad.
    *@param especialidad. Especialidad del revisor
    **/
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
    *Sirve para devolver el numero de articulos revisados por el revisor. 
    *@return Devuelve el numero de articulos revisados por el revisor. 
    **/
    public int getNumArticulosRevisados() {
        return numArticulosRevisados;
    }

    /**
    * Incrementa el numero de articulos revisados.
    **/
    public void incrementarArticulosRevisados() {
        this.numArticulosRevisados++;
    }

    /**
    *Genera un correo para el revisor. 
    **/
    @Override
    public void generarCorreo() {
        System.out.println("Correo generado para el Revisor: " + getEmail());
    }

    /**
    *Metodo que decide si recibir el articulo o no.
    *@param sc  Scanner para recibir la orden
    *@param revisiones. Lista que contienen las revisiones
    **/
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

    /**
    *Metodo específico del revisor para gestionar la revisión.
    *@param sc Scanner para recibir la orden
    *@param revisiones. Lista que contiene las revisiones
    **/ 
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

    /**
    *Metodo específico para guardar los detalles de revision.
    *@param revision. Metodo que se invoca para guardar la revision.
    *@return Nada.
    **/ 
    private void guardarRevision(Revision revis) {
        try {
            String data3 = String.join(",", revis.getCodigoArticulo(), revis.getRevisorId(), revis.getComentarios(), String.valueOf(revis.isDecision()));
            File file = new File("revisiones.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("revisiones.txt"), (data3 + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al guardar la revision");
        }
    }

    /**
    *Metodo específico para enviar el articulo al editor.
    *@param revision. Metodo que se invoca para enviar la revision
    *@return Nada.
    **/ 
    private void enviarArticuloAlEditor(Revision revision) {
        // Implementar la lógica para enviar el artículo al editor
        System.out.println("El articulo ha sido enviado al editor para su revision final.");
    }
    
    /**
    *Metodo para asignar un articulo al revisor.
    *@param articulo. Metodo que se invoca para asignar el articulo.
    **/ 
    public void asignarArticulo(Articulo articulo) {
        this.articuloAsignado = articulo;
        this.tieneArticuloPendiente = true;
    }
}



