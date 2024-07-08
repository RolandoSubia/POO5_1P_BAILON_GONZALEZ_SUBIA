public class Revision {
    private String codigoArticulo;
    private String revisorId;
    private String comentarios;
    private boolean decision;

    // Constructor para inicializar un objeto Revision
    public Revision(String codigoArticulo, String revisorId, String comentarios, boolean decision) {
        this.codigoArticulo = codigoArticulo;
        this.revisorId = revisorId;
        this.comentarios = comentarios;
        this.decision = decision;
    }

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getRevisorId() {
        return revisorId;
    }

    public void setRevisorId(String revisorId) {
        this.revisorId = revisorId;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }
}
