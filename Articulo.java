
package proyecto1final;

public class Articulo {
    private String codigo;
    private String titulo;
    private String resumen;
    private String contenido;
    private String palabrasClave;
    private String autorId;

    // Constructor para inicializar un objeto Articulo
    public Articulo(String codigo, String titulo, String resumen, String contenido, String palabrasClave, String autorId) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.resumen = resumen;
        this.contenido = contenido;
        this.palabrasClave = palabrasClave;
        this.autorId = autorId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(String palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getAutorId() {
        return autorId;
    }

    public void setAutorId(String autorId) {
        this.autorId = autorId;
    }

    @Override
    public String toString() {
        return "Articulo{" + "Codigo='" + codigo + '\'' + ", Titulo='" + titulo + '\'' + ", Resumen='" + resumen + '\'' + ", Contenido='" + contenido + '\'' + ", PalabrasClave='" + palabrasClave + '\'' + ", AutorId='" + autorId + '\'' + '}';
    }
}
