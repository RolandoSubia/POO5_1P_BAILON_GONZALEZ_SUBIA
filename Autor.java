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

public class Autor {
    private String id;
    private String nombre;
    private String apellido;
    private String email;
    private String institucion;
    private String campoInvestigacion;

    // Constructor para inicializar un objeto Autor
    public Autor(String id, String nombre, String apellido, String email, String institucion, String campoInvestigacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.institucion = institucion;
        this.campoInvestigacion = campoInvestigacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getCampoInvestigacion() {
        return campoInvestigacion;
    }

    public void setCampoInvestigacion(String campoInvestigacion) {
        this.campoInvestigacion = campoInvestigacion;
    }

    // Método para gestionar el sometimiento de artículos por parte del autor
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
        System.out.println(" ");
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
        articulos.add(articulo);
        guardarArticulo(articulo);

        System.out.println("Articulo sometido para revision.");
        gestionarRevision(articulo, revisiones, usuarios);
    }

    private static void guardarAutor(Autor aut) {
        try {
            String data1 = String.join(",", aut.getId(), aut.getNombre(), aut.getApellido(), aut.getEmail(), aut.getInstitucion(), aut.getCampoInvestigacion());
            File file = new File("autores.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("autores.txt"), (data1 + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al guardar el autor: " + e.getMessage());
        }
    }

    private static void guardarArticulo(Articulo arti) {
        try {
            String data2 = String.join(",", arti.getCodigo(), arti.getTitulo(), arti.getResumen(), arti.getContenido(), arti.getPalabrasClave(), arti.getAutorId());
            File file = new File("articulos.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Files.write(Paths.get("articulos.txt"), (data2 + System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Error al guardar el articulo: " + e.getMessage());
        }
    }

    private static void gestionarRevision(Articulo art, List<Revision> revisiones, List<Usuario> usuarios) {
        // Asignar dos revisores automáticamente
        List<Revisor> revisoresDisponibles = usuarios.stream().filter(Revisor.class::isInstance).map(Revisor.class::cast).collect(Collectors.toList());

        if (revisoresDisponibles.size() < 2) {
            System.out.println("No hay suficientes revisores disponibles.");
            return;
        }

        Revisor revisor1 = revisoresDisponibles.get(0);
        Revisor revisor2 = revisoresDisponibles.get(1);

        Revision revision1 = new Revision(art.getCodigo(), revisor1.getUsuario(), "", false);
        Revision revision2 = new Revision(art.getCodigo(), revisor2.getUsuario(), "", false);

        revisiones.add(revision1);
        revisiones.add(revision2);

        enviarCorreo(revisor1, art);
        enviarCorreo(revisor2, art);
        guardarRevision(revision1);
        guardarRevision(revision2);
    }

    private static void guardarRevision(Revision revis) {
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

    private static void enviarCorreo(Usuario usuario, Articulo articulo) {
        System.out.println("Enviando correo a " + usuario.getEmail() + " sobre el articulo " + articulo.getTitulo());
        // Implementar lógica para enviar correos electrónicos reales
    }

    @Override
    public String toString() {
        return "Autor{" + "Id='" + id + '\'' + ", Nombre='" + nombre + '\'' + ", Apellido='" + apellido + '\'' + ", Email='" + email + '\'' + ", Institucion='" + institucion + '\'' + ", CampoInvestigacion='" + campoInvestigacion + '\'' + '}';
    }
}


    

