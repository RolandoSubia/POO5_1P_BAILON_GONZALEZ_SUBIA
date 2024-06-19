public class Autor{
  private String institucion;
  private String campo;
  private String usuario;
  private String contrasenia;
  private static char rol;
  
    public Autor{
      this.institucion = institucion;
      this.campo = campo;
      this.usuario = usuario;
      this.contrasenia = contrasenia;
      rol = 'A';
    } 
    
    public void ingresarDatosAutor(String usuario, String nuevaContrasenia, String nuevaInstitucion, String nuevoCampo){
      this.user = usuario;
      this.contrasenia = nuevaContrasenia;
      this.institucion = nuevaInstitucion;
      this.campo = nuevoCampo;
    }

    //public void registrarDatosAutor(String usuario, String nuevaContrasenia, String nuevaInstitucion, String nuevoCampo){
    //}
    
    public boolean someterRevision(){
      return true;
    }
    
    

