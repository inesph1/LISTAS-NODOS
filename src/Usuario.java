import java.io.Serializable;
public class Usuario implements Serializable, identificador{
	private String dni;
	private String nombreApellidos;
	private String password;
	
	public Usuario() {
		this.dni="";
		this.nombreApellidos="";
		this.password="";
	}
	
	public Usuario(String pDNI, String pNombre, String pPass) {
		this.dni=pDNI;
		this.nombreApellidos=pNombre;
		this.password=pPass;
	}
	
	public String getDni() {
		return this.dni;
	}
	public String getNombre() {
		return this.nombreApellidos;
	}
	
	public String getPass() {
		return this.password;
	}

	
	@Override
	public String obtId() {
		return this.dni;
	}
	public String toString() {
		return "DNI "+this.dni+" Nombre:"+this.nombreApellidos;
	}
}
