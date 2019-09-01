package usc.algoritmos2.modelo;

public class Materia {
	private String nombre; // Nombre de la materia
	private int hora; // Hora inicial de la materia
	private boolean am; // es la clase en la manana?
	private int cantHoras; // intensidad horaria de la materia
	private int dia;// dia donde se ve la materia

	// Constructor
	public Materia(String nombre, int hora, boolean am, int cantHoras, int dia) {
		this.nombre = nombre;
		this.hora = hora;
		this.am = am;
		this.cantHoras = cantHoras;
		this.dia = dia;
	}

	// Metodos get y set
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public boolean isAm() {
		return am;
	}

	public void setAm(boolean am) {
		this.am = am;
	}

	public int getCantHoras() {
		return cantHoras;
	}

	public void setCantHoras(int cantHoras) {
		this.cantHoras = cantHoras;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

}
