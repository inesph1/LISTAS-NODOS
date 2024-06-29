import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Iterador<T> implements Serializable {
	private Nodo<T> actual;
	private Lista<T> lista;

	public Iterador() {
		this.actual = null;
		this.lista = null;
	}

	public Iterador(Nodo<T> p) {
		this.actual = p;
		this.lista = null;
	}

	public Iterador(Nodo<T> p, Lista<T> l) {
		this.actual = p;
		this.lista = l;
	}

	public Iterador(Lista<T> l) {
		this.actual = l.getInicio();
		this.lista = l;
	}

	public Nodo<T> getNodoActual() {
		return this.actual;
	}

	public Nodo<T> next() {

		this.actual = this.actual.getSiguiente();
		return this.actual;
	}

	public T getValorPuntero() {
		return this.actual.getInfo();
	}

	public void inicializar() {
		this.actual = this.lista.getInicio();
	}

	public boolean esFin() {
		boolean fin = false;
		if (this.actual == null) {
			fin = true;
		}
		return fin;
	}
	
	public void imprimirLista(Lista<T> lista) {
		Nodo<T> inicio = lista.getInicio();

		while (inicio != null) {
			System.out.println(inicio.getInfo().toString());
			inicio = inicio.getSiguiente();
		}

	}

	public boolean buscar(String p) {
		Nodo<T> aux = this.actual;
		boolean encontrado = false;

		while (aux != null) {
			if (aux.getInfo() instanceof Usuario) {
				Usuario u = (Usuario) aux.getInfo();

				if (u.obtId().equals(p)) {
					encontrado = true;
					break;
				}

			}

			else if (aux.getInfo() instanceof Producto) {
				Producto pp = (Producto) aux.getInfo();

				if (pp.obtId().equals(p)) {
					encontrado = true;
					break;
				}
			}
			
			else if (aux.getInfo() instanceof Cesta) {
				Cesta c = (Cesta) aux.getInfo();
				if (c.getUsuario().obtId().equals(p)) {
					encontrado = true;
					break;
				}
			}
			aux = aux.getSiguiente();
		}

		return encontrado;
	}
	
	public Object retornarObjeto(String p) {
		Nodo<T> aux = this.actual;


		while (aux != null) {
			if (aux.getInfo() instanceof Usuario) {
				Usuario u = (Usuario) aux.getInfo();

				if (u.obtId().equals(p)) {
					return u;
				}

			}

			else if (aux.getInfo() instanceof Producto) {
				Producto pp = (Producto) aux.getInfo();

				if (pp.obtId().equals(p)) {
					return pp;
				}
			}
			
			else if (aux.getInfo() instanceof Cesta) {
				Cesta c = (Cesta) aux.getInfo();
				if (c.getUsuario().obtId().equals(p)) {
					return c;
					
				}
			}
			aux = aux.getSiguiente();
		}

		return null;
	}

	public void escribirFichero(String pNombre, Lista<T> pLista) {

		String nombreFichero = pNombre;
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreFichero))) {

			Nodo<T> inicio = lista.getInicio();

			while (inicio != null) {
				oos.writeObject(inicio.getInfo());
				inicio = inicio.getSiguiente();
			}

			System.out.println("Elementos de la lista escritos en el fichero binario correctamente.");
		} catch (IOException e) {
			System.err.println("Error al escribir en el fichero binario: " + e.getMessage());
		}

	}

	public Lista<T> cargarDatos(String pNombre, Class<T> tipoDato) {
		String nombreFichero = pNombre;
		Lista<T> lista = new Lista();

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
			while (true) {
				try {

					Object objetoDeserializado = ois.readObject();

					if (objetoDeserializado == null) {
						break;
					}

					else if (objetoDeserializado instanceof Usuario) {
						T tipoObjeto = tipoDato.cast(objetoDeserializado); // se transforma en el tipo del objetoDes
						lista.add(tipoObjeto);

					} else if (objetoDeserializado instanceof Cesta) {

						T tipoObjeto = tipoDato.cast(objetoDeserializado);
						lista.add(tipoObjeto);
					}

				} catch (ClassNotFoundException e) {
					System.err.println("Error al deserializar objeto: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			// System.err.println("Error al leer el fichero binario: " + e.getMessage());
		}

		return lista;
	}

}