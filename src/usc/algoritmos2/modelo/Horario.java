package usc.algoritmos2.modelo;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import usc.algoritmos2.UI.CustomException;

public class Horario {
	// Atributos de mi horario
	private String nombre; // Nombre de mi horario

	// Constantes
	private static final int COL = 6;// Cantidad de columnas para mi horario(dias)
	private static final int FIL = 16;// Cantidad de filas para mi horario(horas)
	/**
	 * Referencias
	 * https://docs.oracle.com/javase/tutorial/java/javaOO/classvars.html
	 * https://www.journaldev.com/864/java-open-file
	 */

	// Relaciones
	private Materia materias[][]; // Mi horario sera una matriz de clases

	// Constructor de mi horario
	public Horario(String nombre) {
		this.nombre = nombre;
		materias = new Materia[FIL][COL];
		cargarDatos("./data/datos.txt");
		// reporte();
	}

	// Mis metodos
	public boolean hayEspacio(int hora, boolean am, int dia, int cantHoras) throws CustomException {
		// Metodo que verifica que haya espacio en el dia donde se agregara la materia
		try {
			int contadorEspacios = 0;
			int posx = calcularX(hora, am);
			if (cantHoras <= FIL && (posx + cantHoras) <= FIL) {// Se mira que la cantidad de horas de la materia sea
																// menor a la capacidad de horas del horario
																// Tambien se verifica que la hora final esta en el
																// rango de capacidad del horario.
				for (int i = posx; i < posx + cantHoras; i++) { // Recorro las horas elegidas del dia en cuestion
					if (materias[i][dia - 1] == null) {
						contadorEspacios++;
					}
				}
				return (contadorEspacios == cantHoras) ? true : false;// Se mira que este vacio el espacio que ocupara
																		// la
																		// materia
			} else {
				return false;// No hay espacio
			}
		} catch (IndexOutOfBoundsException e) {
			throw new CustomException("La hora ingresada no es valida.");
		}

	}

	public String[] calcularNuevaHora(int posXVieja, int cantHorasVieja) {// metodo exclusivo para cambiar horas, me da
																			// la hora siguiente a la ultima hora
																			// ocupada por la materia, con su respectivo
																			// am o pm si cambia.
		String[] datos = new String[2];// lo almaceno en un arreglo para su facil manipulacion
		posXVieja += cantHorasVieja;
		// esto funciona con la logica de horas/filas de mi programa (16 horas, de 7 am
		// 10pm, que son 16 filas, de 0 a 15)
		if (posXVieja >= 0 && posXVieja <= 4) {
			datos[0] = Integer.toString(posXVieja + 7);
			datos[1] = "true";
		} else if (posXVieja == 5) {
			datos[0] = Integer.toString(12);
			datos[1] = "false";
		} else {
			datos[0] = Integer.toString(posXVieja - 5);
			datos[1] = "false";
		}
		return datos;
	}

	public void agregarMateria(String nombre, int hora, boolean am, int cantHoras, int dia) throws CustomException {
		if ((hora == 12 && am) || (hora == 11 && !am)) {
			throw new CustomException("La hora ingresada no es valida.");
		} else {
			if (buscarMateria(nombre) == null) {
				if (hayEspacio(hora, am, dia, cantHoras)) {// El metodo verifica que haya espacio para agregar la
															// materia
					int posXini = calcularX(hora, am);// Genera la posicion donde la guardara
					materias[posXini][dia - 1] = new Materia(nombre, hora, am, cantHoras, dia);// La crea
					if (cantHoras > 1) {// si la materia dura mas de una hora, crea ciclicamente referencias del objeto
										// creado en las siguientes "x" horas que se vea
						for (int i = posXini + 1; i < posXini + cantHoras; i++) {
							materias[i][dia - 1] = materias[posXini][dia - 1];// referencia al objeto ya creado
						}
					}
					// Materia agregada con exito
				} else {
					throw new CustomException("No hay espacio en este horario para la materia");// No hay espacio en
																								// este horario para la
																								// materia
				}
			} else {
				throw new CustomException("La materia ya existe.");
			}
		}
	}

	public Materia buscarMateria(String nombre) {
		Materia materia = null;
		String nombreAct = " ";
		for (int i = 0; i < COL; i++) {
			for (int j = 0; j < FIL; j++) {
				if (materias[j][i] != null) {
					nombreAct = materias[j][i].getNombre().toLowerCase();
					if (nombreAct.equals(nombre.toLowerCase())) {// Busca la materia por dia
						materia = materias[j][i];// Si la encuentra la almacena
						break;// y rompe el ciclo
					}
				}
			}
		}
		return materia;// Devuelve la materia para manipularla en otros metodos en caso de que no
						// exista devuelve null
	}

	public void cambiarHora(String nombre, int hora, boolean am) throws CustomException {// Preguntar como acomodar con
		if ((hora == 12 && am) || (hora == 11 && !am) || (hora >= 1 && hora <= 6) && am) {
			throw new CustomException("La hora ingresada no es valida.");
		} else { // excepciones
			Materia miMateria = buscarMateria(nombre);
			if (miMateria != null) {
				int posXvieja = calcularX(miMateria.getHora(), miMateria.isAm());
				int posXnueva = calcularX(hora, am);
				if ((posXnueva + miMateria.getCantHoras()) <= FIL) {// No la puede agregar si se sale del rango
					int cont = 0;
					for (int i = posXnueva; i < posXnueva + miMateria.getCantHoras(); i++) {// Metodo que busca que el
																							// nuevo
																							// espacio este vacio o sea
																							// ocupado por un
																							// objeto(materia) del mismo
																							// tipo)
						if (materias[i][miMateria.getDia() - 1] == null
								|| materias[i][miMateria.getDia() - 1] == materias[posXvieja][miMateria.getDia() - 1]) {
							cont++;
						}
					}
					if (cont == miMateria.getCantHoras()) {// Tampoco la puede agregar si se cruza en el horario de otra
															// materia
						for (int i = posXvieja; i < posXvieja + miMateria.getCantHoras(); i++) {
							materias[i][miMateria.getDia() - 1] = null;
						}
						materias[posXnueva][miMateria.getDia() - 1] = miMateria;
						materias[posXnueva][miMateria.getDia() - 1].setAm(am);
						materias[posXnueva][miMateria.getDia() - 1].setHora(hora);
						for (int i = posXnueva + 1; i < posXnueva + miMateria.getCantHoras(); i++) {
							materias[i][miMateria.getDia() - 1] = materias[posXnueva][miMateria.getDia() - 1];
						}
					} else {
						throw new CustomException("Horario ocupado por otra materia.");
					}
				} else {
					throw new CustomException("El nuevo horario excede el rango admitdo por nuestra aplicacion.");
				}
			} else {

				throw new CustomException("La materia " + nombre + " no existe.");
			}
		}

	}

	public void cambiarCantHoras(String nombre, int cantHoras) throws CustomException {
		Materia miMateria = buscarMateria(nombre);
		if (miMateria != null) {// Verifico que si exista esa materia
			if (cantHoras >= 1) {// Verifico que no sea 0 o menor la nueva cantidad
				int posX = calcularX(miMateria.getHora(), miMateria.isAm());// Saco la fila donde va esta materia
				if (posX + cantHoras <= FIL) {// verifico que la cantidad no se salga del limite
					// Aqui pasa
					if (cantHoras < miMateria.getCantHoras()) {// Si la nueva cantidad es menor a la que ya habia, se
																// hace directamente la edicion borrando las sobrantes
						for (int i = posX + cantHoras; i < posX + miMateria.getCantHoras(); i++) {
							materias[i][miMateria.getDia() - 1] = null;
						}
						materias[posX][miMateria.getDia() - 1].setCantHoras(cantHoras);// Se establece la nueva cantidad
																						// de horas
					} else if (cantHoras > miMateria.getCantHoras()) {// De lo contrario si la cantidad de horas es
																		// mayor, se debe verificar que haya
																		// espacios por delante
						String[] horanueva = calcularNuevaHora(posX, miMateria.getCantHoras());// saco el calculo de lo
																								// que quiero verificar
						if (hayEspacio(Integer.parseInt(horanueva[0]), Boolean.parseBoolean(horanueva[1]),
								miMateria.getDia(), (cantHoras - miMateria.getCantHoras()))) {// con mi metodo hay
																								// espacio verifico el
																								// espacio que quiero
																								// ocupar adicional
							for (int i = posX + miMateria.getCantHoras(); i < posX + cantHoras; i++) {// Como si hay
																										// espacio, paso
																										// a recorrer
																										// los espacios
																										// nuevos que
																										// ocupara la
																										// materia
								materias[i][miMateria.getDia() - 1] = materias[posX][miMateria.getDia() - 1];// y
																												// referencio
																												// la
																												// materia
																												// original

							}
							materias[posX][miMateria.getDia() - 1].setCantHoras(cantHoras);// dejo seteada la nueva
																							// cantidad de horas
						} else {
							throw new CustomException("El nuevo horario esta ocupado por otra materia.");
						}

					}

				} else {
					throw new CustomException("El nuevo horario excede el rango admitido por nuestra aplicacion.");
				}

			} else {
				throw new CustomException("La cantidad de horas no puede ser menor a 1.");
			}
		} else {
			throw new CustomException("La materia " + nombre + " no existe.");
		}
	}

	public void cambiarDia(String nombre, int dia) throws CustomException {
		Materia miMateria = buscarMateria(nombre);
		if (miMateria != null) {
			if (dia <= 6) {
				if (hayEspacio(miMateria.getHora(), miMateria.isAm(), dia, miMateria.getCantHoras())) {
					// guardo los datos
					int hora = miMateria.getHora();
					boolean am = miMateria.isAm();
					int cantHoras = miMateria.getCantHoras();
					// la elimino
					eliminarMat(nombre);
					// La creo
					agregarMateria(nombre, hora, am, cantHoras, dia);

				} else {
					throw new CustomException("El nuevo horario esta ocupado por otra materia.");
				}
			} else {
				throw new CustomException("El nuevo horario excede el rango admitido por nuestra aplicacion.");
			}

		} else {
			throw new CustomException("La materia " + nombre + " no existe.");
		}
	}

	public void cambiarNombre(String nombreViejo, String nombreNuevo) throws CustomException {// cambio el nombre viejo
																								// de una materia por
																								// uno nuevo.
		Materia miMateria = buscarMateria(nombreViejo);// uso el nombre viejo para buscar la materia.
		if (miMateria != null) {
			if (buscarMateria(nombreNuevo) == null) {
				if (nombreNuevo.indexOf(' ') != -1) {
					throw new CustomException("No debe incluir espacios.");
				} else {// luego de hechas todas las verificaciones, seteo el nuevo nombre
					int i = calcularX(miMateria.getHora(), miMateria.isAm());
					materias[i][miMateria.getDia() - 1].setNombre(nombreNuevo);
				}
			} else {
				throw new CustomException("La materia " + nombreNuevo + " ya existe.");
			}

		} else {
			throw new CustomException("La materia " + nombreViejo + " no existe.");
		}
	}

	public void eliminarMat(String nombre) throws CustomException {
		Materia miMateria = buscarMateria(nombre);// instancio la materia en cuestion buscada por su nombre
		if (miMateria != null) {
			int canthoras = miMateria.getCantHoras(), hora = miMateria.getHora(), dia = miMateria.getDia();// saco los
																											// datos
			boolean am = miMateria.isAm();// necesarios para poder buscarla en el arreglo
			int posx = calcularX(hora, am);// calculo con los datos de la materia su posicion en la matriz
			for (int i = 0; i < canthoras; i++) {// elimino "canthoras" veces la materia y sus referencias en la matriz
				materias[posx + i][dia - 1] = null;
			}
		} else {
			throw new CustomException("La materia " + nombre + " no existe.");
		}
	}

	public int calcularX(int hora, boolean am) {// Metodo que convierte la hora humana a una ubicacion en mi matriz
		if (am) {
			return hora - 7;
		} else if (hora == 12) {
			return 5;
		} else {
			return 5 + hora;
		}

	}

	public void cargarDatos(String ruta) {// Metodo que carga los datos de un archivo de texto plano
		try {
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			int numMat = Integer.parseInt(br.readLine());// Cuento cuantas materias se agregaran
			String aux = "";
			String[] split = null;
			for (int i = 0; i < numMat; i++) {
				aux = br.readLine();
				split = aux.split("[,]");// divido los items
				agregarMateria(split[0], Integer.parseInt(split[1]), Boolean.parseBoolean(split[2]),
						Integer.parseInt(split[3]), Integer.parseInt(split[4]));
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CustomException e) {
			e.printStackTrace();
		}
	}

	// Requerimientos
	public int matXdia(int dia) {// metodo que recorre un dia y cuenta cuantas materias hay
		int matXdia = 0;
		for (int i = 0; i < FIL; i++) {
			if (materias[i][dia - 1] != null) {
				if (i == 0) {
					matXdia++;
				} else {
					if (materias[i][dia - 1] != materias[i - 1][dia - 1]) {
						matXdia++;
					}
				}
			}
		}
		return matXdia;
	}

	public int matXhora(int hora, boolean am) {// Metodo que recorre una hora y cuenta cuantas materias hay a esa hora
												// en toda la semana
		int matXhora = 0;
		int horaX = calcularX(hora, am);
		for (int i = 0; i < COL; i++) {
			if (materias[horaX][i] != null) {
				matXhora++;
			}
		}
		return matXhora;
	}

	public int totalMat() {// Metodo que cuenta cuantas materias hay en total
		int total = 0;
		for (int i = 1; i <= COL; i++) {
			total += matXdia(i);
		}
		return total;
	}

	public String diaMayor() {// Metodo que dice que dia tiene mas materias.
		int diaMayor[] = { matXdia(1), matXdia(2), matXdia(3), matXdia(4), matXdia(5), matXdia(6) };
		//System.out.println(diaMayor[0]+" "+diaMayor[1]+" "+diaMayor[2]+" "+diaMayor[3]+" "+diaMayor[4]+" "+diaMayor[5]);
		int aux = diaMayor[1], contador = 0;
		String cadena = "";
		//System.out.println("yo paso por aki");
		for (int i = 0; i < COL; i++) {
		//	System.out.println("yo paso por aki");
			if (diaMayor[i] == 0) {
				contador++;
				//System.out.println(contador);
			} else {
				break;
			}
		}
		if (contador == 6) {
			return "No hay ninguna materia en el horario.";
		}
		contador = 0;
		for (int i = 1; i < COL; i++) {
			if (diaMayor[i] == aux) {
				contador++;
			} else {
				break;
			}
		}
		if (contador == 6) {
			return "Todas los dias tienen la misma cantidad de materias.";
		}
		contador = 0;
		int aux1 = 0;// dia(en valor de posicion en la matriz(0 a 6)
		for (int i = 1; i < COL; i++) {
			if (diaMayor[i] > aux) {// se compara el dia 1 con todos, a ver si alguno es mayor
				aux = diaMayor[i];// si lo es se guarda ese dia como comparador y se compara con el resto
				aux1 = i;
			}
		}
		contador=0;
		for (int i = 0; i < COL; i++) {
			if(diaMayor[aux1]==diaMayor[i]) {
				contador++;
			}
		}
		if(contador>1) {
			return "La mayoria o todos los dias tienen la misma cantidad de Materias.";
		}
		switch (aux1) {
		case 0:
			cadena = "El dia con mas materias es el Lunes.";
			break;
		case 1:
			cadena = "El dia con mas materias es el Martes.";
			break;
		case 2:
			cadena = "El dia con mas materias es el Miercoles.";
			break;
		case 3:
			cadena = "El dia con mas materias es el Jueves.";
			break;
		case 4:
			cadena = "El dia con mas materias es el Viernes.";
			break;
		case 5:
			cadena = "El dia con mas materias es el Sabado.";
			break;

		}

		return cadena;

	}

	public void reporte() {
		File archivo = new File("./data/Reporte.txt");// Creamos el archivo donde se guardara el reporte
		try {
			PrintWriter pw = new PrintWriter(archivo);// Instanciamos el pw en el archivo
			// Guardamos datos
			String dias[] = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
			pw.println("Reporte de Materias");
			pw.println("Por dia:");
			// Guardamos el reporte de materias por dia de cada dia del horario
			for (int i = 1; i <= COL; i++) {
				pw.println("Materias el dia " + dias[i - 1] + ": " + matXdia(i));
			}
			pw.println("Por hora:");
			// Guardamos el reporte de materias por hora de cada hora del horario
			for (int i = 0; i < FIL; i++) {
				if (i < 5) {
					pw.println("Materias a las \t" + (7 + i) + " a.m.: " + matXhora(7 + i, true));
				} else if (i == 5) {
					pw.println("Materias a las \t12 p.m.: " + matXhora(12, false));
				} else {
					pw.println("Materias a las \t" + (i - 5) + " p.m.: " + matXhora(i - 5, false));
				}
			}
			pw.println(diaMayor());
			// Cerramos el printwriter
			pw.close();
			Desktop desktop = Desktop.getDesktop();
			desktop.open(archivo);// Esto funciona unicamente si cerramos el archivo.
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters y setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static int getCol() {
		return COL;
	}

	public static int getFil() {
		return FIL;
	}

	public Materia getMat(int i, int j) {// metodo que me sirve para acceder a un objeto del arreglo de materias.
		return materias[i][j];
	}

}