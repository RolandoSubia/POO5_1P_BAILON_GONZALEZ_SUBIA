import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author Grupo3
 * 
 * 
 */

public class Proyecto1Final {

    private static final String USUARIOS_FILE = "usuarios.txt";
    private static final String AUTORES_FILE = "autores.txt";
    private static final String ARTICULOS_FILE = "articulos.txt";
    private static final String REVISIONES_FILE = "revisiones.txt";

//    // Listas para almacenar los diferentes tipos de datos
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Autor> autores = new ArrayList<>();
    private static List<Articulo> articulos = new ArrayList<>();
    private static List<Revision> revisiones = new ArrayList<>();

    /**
    * Es el punto de entrada de la aplicación. Inicializa los datos y proporciona un menú interactivo para el usuario.
    *@param args Recibe los argumentos para funcionar el metodo main
    **/
    public static void main(String[] args) {
        inicializarDatos();

        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        try{
        while(continuar){
            System.out.println("|--------------------------------------------------------------|");
            System.out.println("|BIENVENIDO A LA APLICACION DE GESTION DE ARTICULOS CIENTIFICOS|");
            System.out.println("|--------------------------------------------------------------|");
            System.out.println("|                   Que quiere hacer hoy?                      |");
            System.out.println("|                   1. Someter articulo                        |");
            System.out.println("|                   2. Iniciar sesion                          |");
            System.out.println("|--------------------------------------------------------------|");
            System.out.print  ("                           Elige: ");
            int opcion = sc.nextInt();
            sc.nextLine(); //  salto de linea
            System.out.println(" ");
            
            switch (opcion) {
                case 1:
                    Autor.gestionarSometerArticulo(sc, autores, articulos, revisiones, usuarios);
                    break;
                case 2:
                    Usuario.gestionarIniciarSesion(sc, usuarios, revisiones, articulos, autores);
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
                        
            // Preguntar si desea continuar ingresando datos
            System.out.print("Desea continuar ingresando datos? (si/no): ");
            String respuesta = sc.nextLine();
            continuar = respuesta.equalsIgnoreCase("si");
            System.out.println(" ");
        }
        System.out.println("Programa finalizado.");
        }catch(Exception e){
                System.out.println("ERROR.VUELVA A INICIAR EL PROGRAMA");
            }
    }
    
    /**Inicializa los datos leyendo desde archivos y cargando la información en las listas correspondientes.
    **/
    private static void inicializarDatos() {
        try {
            // Leer datos de usuarios
            if (Files.exists(Paths.get(USUARIOS_FILE))) {
                List<String> usuariosLeer = Files.readAllLines(Paths.get(USUARIOS_FILE));
                for (String leer : usuariosLeer) {
                    String[] parts = leer.split(",");
                    if (parts.length > 5) {
                        String rol = parts[0];
                        if (rol.equals("E")) {
                            usuarios.add(new Editor(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
                        } else if (rol.equals("R")) {
                            usuarios.add(new Revisor(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]));
                        }
                    } 
                }
            }

            //  datos de autores
            if (Files.exists(Paths.get(AUTORES_FILE))) {
                List<String> autoresLeer = Files.readAllLines(Paths.get(AUTORES_FILE));
                for (String leer : autoresLeer) {
                    String[] parts = leer.split(",");
                    if (parts.length == 6) {
                        autores.add(new Autor(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    } 
                }
            }

            //  datos de artículos
            if (Files.exists(Paths.get(ARTICULOS_FILE))) {
                List<String> articulosLeer = Files.readAllLines(Paths.get(ARTICULOS_FILE));
                for (String leer : articulosLeer) {
                    String[] parts = leer.split(",");
                    if (parts.length == 6) {
                        articulos.add(new Articulo(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                    } 
                }
            }

            // Leer datos de revisiones
            if (Files.exists(Paths.get(REVISIONES_FILE))) {
                List<String> revisionesLeer = Files.readAllLines(Paths.get(REVISIONES_FILE));
                for (String leer : revisionesLeer) {
                    String[] parts = leer.split(",");
                    if (parts.length == 4) {
                        revisiones.add(new Revision(parts[0], parts[1], parts[2], Boolean.parseBoolean(parts[3])));
                    } 
                }
            }

        } catch (IOException e) {
            System.err.println("Error al leer los archivos de datos: " + e.getMessage());
        }
    }
}
