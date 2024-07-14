
package proyecto1final;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

/**
 * 
 * @author Grupo3
 * 
 * 
 */

public class Autor {
    
//Atributos especificos de la clase autor.
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String institucion;
    private String campoInvestigacion;

    /**Constructor para inicializar un objeto Autor.
    *@param id Id del autor.
    *@param nombre Nombre del autor.
    *@param apellido Apellido del autor.
    *@param email Email del autor.
    *@param institucion Institucion en la que trabaja el autor.
    *@param campoInvestigacion Campo de investigacion en la que trabajo el autor.
    **/
    public Autor(String id, String nombre, String apellido, String email, String institucion, String campoInvestigacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.institucion = institucion;
        this.campoInvestigacion = campoInvestigacion;
    }

    /**Sirve para recibir el id del autor.
    *@return String Devuelve el id del autor. 
    **/
    public String getId() {
        return id;
    }

     /**Establece el id del autor.
    *@param id Id del autor.
    **/
    public void setId(String id) {
        this.id = id;
    }

    /**Sirve para recibir el nombre del autor.
    *@return String Devuelve el nombre del autor
    **/
    public String getNombre() {
        return nombre;
    }

    /**Establece el nombre del autor.
    *@param nombre Nombre del autor.
    **/
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**Sirve para recibir el apellido del autor.
    *@return String Devuelve el apellido del autor
    **/
    public String getApellido() {
        return apellido;
    }

    /**Establece el apellido del autor.
    *@param apellido Apellido del autor.
    **/
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**Sirve para recibir el email del autor.
    *@return String Devuelve el email del autor
    **/
    public String getEmail() {
        return email;
    }

    /**Establece el email del autor.
    *@param email Email del autor.
    **/
    public void setEmail(String email) {
        this.email = email;
    }

    /**Sirve para recibir la institucion del autor.
    *@return String Devuelve la institucion del autor.
    **/
    public String getInstitucion() {
        return institucion;
    }

    /**Establece la institucion del autor.
    *@param institucion Institucion en la que trabaja el autor.
    **/
    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    /**Sirve para recibir el campo de investigacion del autor.
    *@return String Devuelve el campo de investigacion del autor.
    **/
    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }

    /**Establece el campo de investigacion del autor.
    *@param campoInvestigacion Campo de investigacion en la que trabajo el autor.
    **/
    public void setCampoInvestigacion(String campoInvestigacion) {
        this.campoInvestigacion = campoInvestigacion;
    }

    /**Metodo para gestionar el sometimiento de articulos
    *@param scanner Scanner que recibe si someter el articulo o no.
    *@param autores Lista que contiene a los autores.
    *@param articulos Lista que contiene los articulos.
    *@param revisiones Lista que contiene las revisiones.
    *@param usuarios Lista que contiene los usuarios.
    **/
    public static void gestionarSometerArticulo(Scanner scanner, List<Autor> autores, List<Articulo> articulos, List<Revision> revisiones, List<Usuario> usuarios) {
        System.out.println("Registrar datos del autor");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Correo electronico: ");
        String email = scanner.nextLine();
        System.out.print("Institucion: ");
        String institucion = scanner.nextLine();
        System.out.print("Campo de investigacion: ");
        String campoInvestigacion = scanner.nextLine();
//        System.out.println(" ");
        String autorId = UUID.randomUUID().toString();
        Autor autor = new Autor(autorId, nombre, apellido, email, institucion, campoInvestigacion);
        autores.add(autor);
        guardarAutor(autor);

        System.out.println("Registrar datos del articulo");
        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();
        System.out.print("Resumen: ");
        String resumen = scanner.nextLine();
        System.out.print("Contenido: ");
        String contenido = scanner.nextLine();
        System.out.print("Palabras claves: ");
        String palabrasClave = scanner.nextLine();
        System.out.println(" ");

        String codigoArticulo = UUID.randomUUID().toString();
        Articulo articulo = new Articulo(codigoArticulo, titulo, resumen, contenido, palabrasClave, autorId);
        System.out.print("Desea enviar el articulo a revision? (si/no): ");
        String decision = scanner.nextLine();

        if (decision.equalsIgnoreCase("si")) {
            autores.add(autor);
            guardarAutor(autor);
            articulos.add(articulo);
            guardarArticulo(articulo);
            System.out.println("Articulo sometido para revision.");
            System.out.println(" ");
            asignarRevisoresYEnviarCorreo(articulo, usuarios, revisiones);
        } else {
            System.out.println("Articulo cancelado exitosamente.");
            System.out.println(" ");
        }
    
    }

    /**Metodo para guardar al autor.
    *@param aut. Metodo que invoca al autor
    **/
    private static void guardarAutor(Autor aut) {
        try {
            String data1 = String.join(",", aut.getId(), aut.getNombre(), aut.getApellido(), aut.getEmail(), aut.getInstitucion(), aut.getCampoInvestigacion());
            File file = new File("autores.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("autores.txt"), (data1 + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al guardar el autor");
        }
    }

    /**Metodo para guardar el articulo.
    *@param arti Metodo que invoca al articulo
    **/
    private static void guardarArticulo(Articulo arti) {
        try {
            String data2 = String.join(",", arti.getCodigo(), arti.getTitulo(), arti.getResumen(), arti.getContenido(), arti.getPalabrasClave(), arti.getAutorId());
            File file = new File("articulos.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("articulos.txt"), (data2 + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al guardar el articulo");
        }
    }

    /**Metodo para guardar la revision.
    *@param revis Metodo que invoca la revision
    **/
    private static void guardarRevision(Revision revis) {
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

    /**Metodo para asignar revisores aleatorios al artículo y envía un correo de notificación.
    *@param articulo Metodo que invoca al articulo.
    *@param usuarios Lista que contiene los usuarios.
    *@param revisiones Lista que contiene las revisiones.
    **/
    private static void asignarRevisoresYEnviarCorreo(Articulo articulo, List<Usuario> usuarios, List<Revision> revisiones) {
        Random rd = new Random();
        List<Revisor> revisoresDisponibles = usuarios.stream().filter(Revisor.class::isInstance).map(Revisor.class::cast).collect(Collectors.toList());

        if (revisoresDisponibles.size() < 2) {
            System.out.println("No hay suficientes revisores disponibles.");
            System.out.println(" ");
            return;
        }

        // Seleccionar dos revisores distintos aleatorios
        Set<String> revisoresAsignados = new HashSet<>();
        while (revisoresAsignados.size() < 2) {
            Revisor revisor = revisoresDisponibles.get(rd.nextInt(revisoresDisponibles.size()));
            revisoresAsignados.add(revisor.getUsuario()); // Usar el nombre de usuario como identificador único
            Revision revision = new Revision(articulo.getCodigo(), revisor.getUsuario(), "", false);
            revisiones.add(revision);
            revisor.asignarArticulo(articulo);
            enviarCorreo(revisor, articulo);
        }
    }
    
    /**Simula el envío de un correo al revisor con la información del artículo.
    *@param revisor Metodo que invoca al revisor.
    *@param articulo Metodo que invoca al articulo.
    **/
    private static void enviarCorreo(Revisor revisor, Articulo articulo) {
        System.out.println("Enviando correo a " + revisor.getEmail() + " sobre el articulo " + articulo.getTitulo() + ".");
        System.out.println(" ");

    }
}
