import java.io.Serializable;
public class Producto implements Serializable, identificador {
	private int id;
	private String nombre;
	private String descripcion;
	private double precio;
	private int unidades;
	
	public Producto() {
		this.id=0;
		this.nombre="";
		this.descripcion="";
		this.precio=0;
		this.unidades=0;
	}
	
	public Producto(String pNombre, int pUnidad) {
		this.id=0;
		this.nombre=pNombre;
		this.descripcion="";
		this.precio=0;
		this.unidades=pUnidad;
	}
	
	public Producto(int pID, String pNombre, double pPrecio) {
		this.id=pID;
		this.nombre=pNombre;
		this.descripcion="";
		this.precio=pPrecio;
		this.unidades=0;
	}
	
	
	public Producto(int pID, String pNombre, String pDescipcion, double pPrecio, int pUnidades) {
		this.id=pID;
		this.nombre=pNombre;
		this.descripcion="";
		this.precio=pPrecio;
		this.unidades=pUnidades;
	}
	
	
	public void setUnidades() {
		this.unidades= this.unidades+1;
	}
	
	public int getUnidades() {
		return this.unidades;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String toString() {
		return "Producto "+this.nombre+" Cantidad: "+this.unidades;
	}
	
	@Override
	public String obtId() {
		return this.nombre;
	}
	

	

}
