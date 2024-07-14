
package proyecto1final;

/**
 * 
 * @author Grupo3
 * 
 * 
 */

public class Articulo {
    //Artributos especificos para la clase Articulo.
    private String codigo;
    private String titulo;
    private String resumen;
    private String contenido;
    private String palabrasClave;
    private String autorId;

    /**Constructor para inicializar un objeto Articulo.
    *@param codigo El codigo del articulo.
    *@param titulo El titulo del articulo.
    *@param resumen El resumen del articulo.
    *@param contenido El contenido del que trata el articulo.
    *@param palabrasClave Las palabras clave para hallar el articulo.
    *@param autorId. El ID del autor.
    **/
    public Articulo(String codigo, String titulo, String resumen, String contenido, String palabrasClave, String autorId) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.resumen = resumen;
        this.contenido = contenido;
        this.palabrasClave = palabrasClave;
        this.autorId = autorId;
    }

    /**Sirve para recibir el codigo del articulo.
    *@return String Devuelve el codigo del articulo. 
    **/
    public String getCodigo() {
        return codigo;
    }

    /**Establece el codigo del articulo.
    *@param codigo El codigo del articulo.
    **/
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**Sirve para recibir el titulo del articulo.
    *@return String Devuelve el titulo del articulo. 
    **/
    public String getTitulo() {
        return titulo;
    }

    /**Establece el el titulo del articulo.
    *@param titulo El titulo del articulo.
    **/
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**Sirve para recibir el resumen del articulo.
    *@return String Devuelve el resumen del articulo. 
    **/
    public String getResumen() {
        return resumen;
    }

    /**Establece el el resumen del articulo.
    *@param resumen El resumen del articulo.
    **/
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    /**Sirve para recibir el contenido del articulo.
    *@return String Devuelve el contenido del articulo. 
    **/
    public String getContenido() {
        return contenido;
    }

    /**Establece el contenido del articulo.
    *@param contenido El contenido del que trata el articulo.
    **/
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**Sirve para recibir las palabras del articulo.
    *@return String Devuelve las palabras del articulo. 
    **/
    public String getPalabrasClave() {
        return palabrasClave;
    }

    /**Establece las palabras del articulo.
    *@param palabrasClave Las palabras clave para hallar el articulo.
    **/
    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    /**Sirve para recibir el ID del autor.
    *@return String Devuelve el ID del autor. 
    **/
    public String getAutorId() {
        return autorId;
    }

    /**Establece el el ID del autor.
    *@param autorId. El ID del autor.
    **/
    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }
}
