/**
 * 
 * @author Grupo3
 * 
 * 
 */
public class Revision {
   //Atributos especificos para la clase Revision.
    private String codigoArticulo;
    private String revisorId;
    private String comentarios;
    private boolean decision;

    /**Constructor para inicializar un objeto Revision.
    *@param codigoArticulo El codigo del articulo.
    *@param revisorId El ID del revisor.
    *@param comentarios Los comentarios del articulo,
    *@param decision. La decision tomada por el revisor.
    **/
    public Revision(String codigoArticulo, String revisorId, String comentarios, boolean decision) {
        this.codigoArticulo = codigoArticulo;
        this.revisorId = revisorId;
        this.comentarios = comentarios;
        this.decision = decision;
    }

    /**Sirve para recibir el codigo del articulo.
    *@return String Devuelve el codigo del articulo. 
    **/
    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    /**Establece el codigo del articulo.
    *@param codigoArticulo. El codigo del articulo
    **/
    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    /**Sirve para recibir el ID del revisor.
    *@return Devuelve el ID del revisor. 
    **/
    public String getRevisorId() {
        return revisorId;
    }

    /**Establece el ID del revisor.
    *@param revisorId. El ID del revisor.
    **/
    public void setRevisorId(String revisorId) {
        this.revisorId = revisorId;
    }

    /**Sirve para recibir los comentarios del revisor.
    *@return String Devuelve los comentarios del revisor. 
    **/
    public String getComentarios() {
        return comentarios;
    }

    /**Establece los comentarios del revisor.
    *@param comentarios. Los comentarios dados.
    **/
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**Sirve para recibir la decision del revisor
    *@return boolean La decision del revisor. 
    **/
    public boolean isDecision() {
        return decision;
    }

    /**Establece la decision del revisor.
    *@param decision. La decision tomada por el revisor
    **/
    public void setDecision(boolean decision) {
        this.decision = decision;
    }
}
