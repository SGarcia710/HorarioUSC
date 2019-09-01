package usc.algoritmos2.UI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import usc.algoritmos2.modelo.Horario;

public class Principal extends JFrame {

	/**Referencias
	 *https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
	 *http://www.chuidiang.org/java/layout/GridBagLayout/GridBagLayout.php 
	 * 
	 */
	private PanelTitulo pTitulo;
	private PanelHorario pHorario;
	private PanelOpciones pOpciones;
	private BorderLayout border;
	private Horario miHorario;

	public Principal() {
		miHorario = new Horario("Horario USC");
		pTitulo = new PanelTitulo();
		pHorario = new PanelHorario(this);
		pOpciones = new PanelOpciones(this);
		border = new BorderLayout();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(border);
		add(pTitulo, BorderLayout.NORTH);
		add(pHorario, BorderLayout.CENTER);
		add(pOpciones, BorderLayout.EAST);

	}

	public static void main(String[] args) {
		Principal miVentana = new Principal();
		miVentana.setSize(1366, 800);
		miVentana.setResizable(false);
		miVentana.setTitle("Horario USC");
		miVentana.setVisible(true);
		miVentana.setLocationRelativeTo(null);
	}
	//metodos de mi JFrame
	public void agregarMateria(String nombre, int hora, boolean am, int cantHoras, int dia) {
		try {
			miHorario.agregarMateria(nombre, hora, am, cantHoras, dia);
			actualizarHorario();
			refrescarPanelOpciones();
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cambiarHora(String nombre, int hora, boolean am) {
		try {
			miHorario.cambiarHora(nombre, hora, am);
			 actualizarHorario();
			 refrescarPanelOpciones();
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cambiarHoras(String nombre, int cantHoras) {
		try {
			miHorario.cambiarCantHoras(nombre, cantHoras);
			actualizarHorario();
			refrescarPanelOpciones();
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cambiarDia(String nombre, int dia) {
		try {
			miHorario.cambiarDia(nombre, dia);
			actualizarHorario();
			refrescarPanelOpciones();
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void cambiarNombre(String nombreViejo, String nombreNuevo) {
		try {
			miHorario.cambiarNombre(nombreViejo, nombreNuevo);
			actualizarHorario();
			
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void actualizarHorario() {
		pHorario.refrescar();
	}	
	public Horario getMiHorario() {
		return miHorario;
	}
	public String getMatNombre(int i,int j) {
		return miHorario.getMat(i, j).getNombre();
	}
	public String getMateriasDia(int dia) {
		return ""+miHorario.matXdia(dia);
	}
	public String getMateriasHora(int hora, boolean am) {
		return ""+miHorario.matXhora(hora, am);
	}
	public String getMateriasTotal() {
		return ""+miHorario.totalMat();
	}
	public void refrescarPanelOpciones() {
		pOpciones.refrescarPanel();
	}
	public void generarReporte() {
		miHorario.reporte();
	}

	public void eliminar(String nombre) {
		try {
			miHorario.eliminarMat(nombre);
			actualizarHorario();
			refrescarPanelOpciones();
		} catch (CustomException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
