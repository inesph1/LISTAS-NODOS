import java.io.Serializable;

public class Lista<T> implements Serializable{
	private Nodo<T> nodoActual;

	public Lista() {
		this.nodoActual = null;
	}

	public Lista(Nodo<T> pNodo) {
		this.nodoActual = pNodo;
	}

	public void setInicio(Nodo<T> pInicio) {
		this.nodoActual = pInicio;
	}

	public Nodo<T> getInicio() {
		return this.nodoActual;
	}

	public void add(T pValor) {

		Nodo<T> nuevoNodo = new Nodo<T>(pValor);
		if (this.nodoActual == null) {
			this.nodoActual = nuevoNodo;
		} else {
			nuevoNodo.setSiguiente(nodoActual);
			this.nodoActual = nuevoNodo;
		}
	}

	public void borrarElemento(T pObjeto) {
		Nodo<T> aux = this.nodoActual;
		Nodo<T> anterior = null;

		while (aux != null) {

			if (aux.getInfo().equals(pObjeto)) {
				if (anterior == null) {
					this.nodoActual = this.nodoActual.getSiguiente();
					aux = aux.getSiguiente();
					
				} else {
					anterior.setSiguiente(aux.getSiguiente());
					
				}
				return; // termina la funcion al eliminar el elemento
			}
			anterior = aux;
			aux = aux.getSiguiente();
		}
	}

}
