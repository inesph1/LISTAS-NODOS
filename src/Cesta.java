import java.io.Serializable;
public class Cesta  implements Serializable, identificador {
	private Usuario user;
	private Lista<Producto> cestaCompra;
	
	public Cesta() {
		this.user=null;
		this.cestaCompra=null;
	}
	
	public Cesta(Lista pCestaCompra) {
		this.user=null;
		this.cestaCompra=pCestaCompra;
	}
	
	public Cesta(Usuario pUser, Lista pCestaCompra) {
		this.user=pUser;
		this.cestaCompra=pCestaCompra;
	}
	
	public void setCompra(Lista<Producto> pCesta) {
		this.cestaCompra = pCesta;
	}
	public Usuario getUsuario() {
		return this.user;
	}
	
	public Lista<Producto> obtenerCompra() {
		return this.cestaCompra;
	}
	
	
	@Override
	public String obtId() {
		return this.user.getDni();
	}
			
			
	
}
