import java.io.Serializable;

public class Nodo <T>implements Serializable{
	private T info; //tipo de objeto
	private Nodo<T> siguiente;
	
	public Nodo() {
		this.info=null;
		this.siguiente=null;
	}
	
	public Nodo(T pInfo) { 
		this.info=pInfo;
		this.siguiente=null;
	}
	
	public Nodo(T pInfo, Nodo<T> pSiguiente) { 
		this.info=pInfo;
		this.siguiente=pSiguiente;
	}
	
	public void setInfo(T pInfo) {
		this.info = pInfo;
	}
	public T getInfo() {
		return this.info;
	}
	
	public void setSiguiente(Nodo<T> pSiguiente) { 
		this.siguiente = pSiguiente;
	}
	
	public Nodo<T> getSiguiente() { 
		return this.siguiente;
	}
	

	
}

