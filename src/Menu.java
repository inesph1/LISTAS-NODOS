import java.util.Scanner;
import static java.io.File.separator;

public class Menu {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		String directorioActual = System.getProperty("user.dir");
		String nombreFichero = directorioActual + separator + "usuarios";

		Iterador iniciador = new Iterador();

		Lista<Usuario> listaUsuarios = iniciador.cargarDatos(nombreFichero, Usuario.class);
		if (listaUsuarios == null || listaUsuarios.getInicio() == null) {
			listaUsuarios = new Lista<Usuario>();
		}

		nombreFichero = directorioActual + separator + "cestas";
		Lista<Cesta> arrayCestasUsuarios = iniciador.cargarDatos(nombreFichero, Cesta.class);
		if (arrayCestasUsuarios == null || arrayCestasUsuarios.getInicio() == null) {
			arrayCestasUsuarios = new Lista<Cesta>();
		}

		Producto p1 = new Producto(1, "OREOS", 2.58);
		Producto p2 = new Producto(2, "COCA-COLA", 2.8);
		Producto p3 = new Producto(3, "CACAHUETES", 3.01);
		Producto p4 = new Producto(4, "PESTO", 1.83);
		Producto p5 = new Producto(5, "LECHE", 0.97);
		Producto p6 = new Producto(6, "PRINGLES", 3.65);

		Lista<Producto> p = new Lista<Producto>();
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p5);
		p.add(p6);

		// ITERADORES
		Iterador<Usuario> iterador = new Iterador<Usuario>(listaUsuarios);
		Iterador<Cesta> iteradorC = new Iterador<Cesta>(arrayCestasUsuarios);
		Iterador<Producto> iteradorP = new Iterador<Producto>(p);

		// PASA LOS DATOS AL MENU PRINCIPAL PARA TRABAJAR CON ELLOS
		menuPrincipal(teclado, p, arrayCestasUsuarios, listaUsuarios, iterador, iteradorC, iteradorP);
	}

	public static void menuPrincipal(Scanner teclado, Lista<Producto> p, Lista<Cesta> arrayCestasUsuarios,
			Lista<Usuario> listaUsuarios, Iterador<Usuario> iterador, Iterador<Cesta> iteradorC,
			Iterador<Producto> iteradorP) {
		// TODO Auto-generated method stub
		char var = 0;
		boolean programa = true;
		while (programa) {
			String dni = "";
			String nombre = "";
			String contraseña = "";

			do {
				System.out.println(
						"\nMENU PRINCIPAL\n1. Crear usuario\n2. Borrar usuario\n3. Listar usuarios\n4. Submenu compras\n5.Salir");
				var = teclado.next().charAt(0);
			} while (var != '1' && var != '2' && var != '3' && var != '4' && var != '5');

			teclado.nextLine();
			switch (var) {
			case '1':
				System.out.println("Introduzca el dni");
				dni = teclado.nextLine();
				System.out.println("Introduzca el nombre");
				nombre = teclado.nextLine();
				System.out.println("Introduzca el contraseña");
				contraseña = teclado.nextLine();

				Usuario u1 = new Usuario(dni, nombre, contraseña);
				listaUsuarios.add(u1);
				Cesta auxObjetoCesta = new Cesta(u1, null); // GUARDA EN EL ARRAY LOS USUARIOS CON UNA CESTA NULA
				arrayCestasUsuarios.add(auxObjetoCesta);
				iteradorC.inicializar();
				iterador.inicializar();
				break;

			case '2':
				System.out.println("Introduzca el dni del usuario que desea borrar");
				dni = teclado.nextLine();

				if (iteradorC.buscar(dni)) {
					Cesta aux = (Cesta) iteradorC.retornarObjeto(dni);
					aux.setCompra(null);
					arrayCestasUsuarios.borrarElemento(aux);
					System.out.println("Cesta eliminada con exito");
				}
				if (iterador.buscar(dni)) {
					Usuario auxUSer = (Usuario) iterador.retornarObjeto(dni);
					listaUsuarios.borrarElemento(auxUSer);
					System.out.println("Usuario eliminado con exito");
				} else {
					System.out.println("No se encuentran registros");
				}

				break;

			case '3':
				if (!iterador.esFin()) {
					iterador.imprimirLista(listaUsuarios);

				} else {
					System.out.println("\nLa lista esta vacía\n");
				}
				iterador.inicializar();
				break;

			case '4':
				menuCompras(teclado, p, arrayCestasUsuarios, listaUsuarios, iterador, iteradorC, iteradorP);
				break;

			case '5':
				iterador.escribirFichero("usuarios", listaUsuarios);
				programa = false;
				break;
			}

		}

		teclado.close();

	}

	public static void menuCompras(Scanner teclado, Lista<Producto> p, Lista<Cesta> arrayCestasUsuarios,
			Lista<Usuario> listaUsuarios, Iterador<Usuario> iterador, Iterador<Cesta> iteradorC,
			Iterador<Producto> iteradorP) {

		boolean programa2 = true;
		int var = 0;
		String dni = "";

		while (programa2) {

			do {
				System.out.println(
						"\nSUBMENU COMPRAS\n1. Crear cesta\n2. Borrar cesta\n3. Ver cestas\n4. Volver al menu principal");
				var = teclado.next().charAt(0);
			} while (var != '1' && var != '2' && var != '3' && var != '4');
			teclado.nextLine();

			switch (var) {
			case '1':
				
				System.out.println("Introduzca el dni para crear su cesta");
				dni = teclado.nextLine();

				Lista<Producto> compra = new Lista<Producto>();

				if (iteradorC.buscar(dni)) {
					Cesta aux = (Cesta) iteradorC.retornarObjeto(dni);
					compra = aux.obtenerCompra(); // obtiene la compra o null
					System.out.println("Cesta encontrada");
					iteradorP = new Iterador<Producto>(p);

					System.out.println("\nPRODUCTOS DISPONIBLES");
					while(!iteradorP.esFin()) {
						System.out.println(" -"+iteradorP.getNodoActual().getInfo().getNombre()); 
						iteradorP.next();
					}
					iteradorP.inicializar();
					
					
					System.out.println("Introduzca el producto que desea\n");
					String producto = teclado.nextLine();
					producto = producto.toUpperCase();

					if (iteradorP.buscar(producto)) {
						System.out.println("PRODUCTO INTRODUCIDO " + producto);
						Producto auxP = new Producto(producto, 1);
						if (compra == null) {
							compra = new Lista<Producto>();
							compra.add(auxP);
							aux.setCompra(compra);
							System.out.println("PRODUCTO AÑADIDO " + auxP.getNombre());
						} else {
							iteradorP = new Iterador<Producto>(compra);
							if (iteradorP.buscar(producto)) {
								Producto pAux = (Producto) iteradorP.retornarObjeto(producto);
								pAux.setUnidades();
								System.out.println("EL PRODUCTO " + pAux.getNombre() + " YA EXISTIA "
										+ pAux.getUnidades() + " UNIDADES ACTUALES");
							} else {
								compra.add(auxP);
								aux.setCompra(compra);
								System.out.println("PRODUCTO AÑADIDO " + auxP.getNombre());
							}

						}

					} else {
						System.out.println("El producto no existe");
					}

				} else {
					System.out.println("No existe el usuario");
				}
				iteradorC.inicializar();
				iterador.inicializar();
				
				break;

			case '2':
				System.out.println("Introduzca el dni para borrar su cesta");
				dni = teclado.nextLine();
				if (iteradorC.buscar(dni)) {
					Cesta c = (Cesta) iteradorC.retornarObjeto(dni);
					if (c.obtenerCompra() != null) {
						c.setCompra(null);
					}
				}

				break;

			case '3':
				System.out.println("Introduzca el dni para mostrar su cesta");
				dni = teclado.nextLine();

				if (iteradorC.buscar(dni)) {
					Cesta c = (Cesta) iteradorC.retornarObjeto(dni);
					if (c.obtenerCompra() != null) {
						iteradorP = new Iterador<Producto>(c.obtenerCompra());
						iteradorP.imprimirLista(c.obtenerCompra());
					} else {
						System.out.println("No hay productos");
					}
				} else {
					System.out.println("No hay cestas asociadas");
				}

				break;

			case '4':
				iteradorC.escribirFichero("cestas", arrayCestasUsuarios);
				programa2 = false;
				break;
			}
		}
	}
}
