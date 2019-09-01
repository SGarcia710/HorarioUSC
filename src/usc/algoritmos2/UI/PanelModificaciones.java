package usc.algoritmos2.UI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import usc.algoritmos2.modelo.Horario;

public class PanelModificaciones extends JPanel implements ActionListener {

	// Constantes

	private static final String HORA = "HORA";
	private static final String HORAS = "HORAS";
	private static final String DIA = "DIA";
	private static final String NOMBRE = "NOMBRE";
	private static final String ELIMINAR = "ELIMINAR";

	// atributos de mi clase
	private PanelOpciones principal;

	// atributos interfaz
	private GridBagLayout gridBag;

	private JTextField nomMatText;
	private JComboBox intenCombo;
	private JButton modCantHoras;

	private JTextField nomMatText1;

	private JComboBox horasCombo;
	private JComboBox amCombo;
	private JButton modCambHora;

	private JTextField nomMatText2;
	private JComboBox diaCombo;
	private JButton modCambDia;

	private JTextField nomMatText3;
	private JTextField nomMatText3new;
	private JButton modCambNom;

	private JTextField nomMatText4;
	private JButton modEliminar;

	public PanelModificaciones(PanelOpciones principal) {
		this.principal = principal;

		gridBag = new GridBagLayout();
		setLayout(gridBag);

		setBorder(new TitledBorder("Modificaciones"));

		GridBagConstraints constraints = new GridBagConstraints();

		// Seccion cambiar hora
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.anchor = constraints.WEST;
		add(new JLabel("Hora de"), constraints);
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		nomMatText1 = new JTextField();
		nomMatText1.setColumns(7);
		add(nomMatText1, constraints);
		constraints.gridx = 9;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("a"), constraints);
		constraints.gridx = 10;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		String[] horas = new String[Horario.getFil()];
		for (int i = 0; i < horas.length; i++) {
			if ((7 + i) < 12) {
				horas[i] = (7 + i) + ":00";
			} else if (i == 5) {
				horas[i] = "12:00";
			} else {
				horas[i] = ((5 - i) * -1) + ":00";
			}
		}
		horasCombo = new JComboBox(horas);
		add(horasCombo, constraints);
		constraints.gridx = 11;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		String ampm[] = { "a.m.", "p.m." };
		amCombo = new JComboBox(ampm);
		add(amCombo, constraints);
		constraints.gridx = 12;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		modCambHora = new JButton("Modificar");
		add(modCambHora, constraints);
		modCambHora.setActionCommand(HORA);
		modCambHora.addActionListener(this);

		// seccion cantidad de horas
		constraints.anchor = constraints.WEST;
		constraints.insets = new Insets(10, 0, 0, 0);// padding
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("Horas de"), constraints);
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		nomMatText = new JTextField();
		nomMatText.setColumns(7);
		add(nomMatText, constraints);
		constraints.gridx = 9;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("a"), constraints);
		constraints.anchor = constraints.CENTER;
		constraints.gridx = 10;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		String[] intensidades = new String[16];
		for (int i = 0; i < intensidades.length; i++) {
			if (i == 0) {
				intensidades[i] = (i + 1) + " Hora";
			} else {
				intensidades[i] = (i + 1) + " Horas";
			}
		}
		intenCombo = new JComboBox(intensidades);
		add(intenCombo, constraints);
		constraints.gridx = 12;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		modCantHoras = new JButton("Modificar");
		add(modCantHoras, constraints);
		modCantHoras.setActionCommand(HORAS);
		modCantHoras.addActionListener(this);

		// Seccion cambiar dia

		constraints.anchor = constraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("Dia de"), constraints);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		nomMatText2 = new JTextField();
		nomMatText2.setColumns(7);
		add(nomMatText2, constraints);
		constraints.gridx = 9;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("a"), constraints);
		constraints.anchor = constraints.CENTER;
		constraints.gridx = 10;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		String dias[] = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		diaCombo = new JComboBox(dias);
		add(diaCombo, constraints);
		constraints.gridx = 12;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		modCambDia = new JButton("Modificar");
		add(new JLabel(""));
		add(modCambDia, constraints);
		modCambDia.setActionCommand(DIA);
		modCambDia.addActionListener(this);

		// Seccion Cambiar Nombre
		constraints.anchor = constraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("Nombre de"), constraints);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		nomMatText3 = new JTextField();
		nomMatText3.setColumns(7);
		add(nomMatText3, constraints);
		constraints.gridx = 9;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		add(new JLabel("a"), constraints);
		constraints.anchor = constraints.CENTER;
		constraints.gridx = 10;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		nomMatText3new = new JTextField();
		nomMatText3new.setColumns(9);
		add(nomMatText3new, constraints);
		constraints.gridx = 12;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		modCambNom = new JButton("Modificar");
		add(modCambNom, constraints);
		modCambNom.setActionCommand(NOMBRE);
		modCambNom.addActionListener(this);

		// Seccion eliminar
		constraints.anchor = constraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		add(new JLabel("Eliminar a"), constraints);
		constraints.gridx = 3;
		constraints.gridy = 4;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		nomMatText4 = new JTextField();
		nomMatText4.setColumns(7);
		add(nomMatText4, constraints);
		constraints.gridx = 12;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		modEliminar = new JButton("Realizar");
		constraints.anchor = constraints.CENTER;
		add(modEliminar, constraints);
		modEliminar.setActionCommand(ELIMINAR);
		modEliminar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals(HORA)) {
			// Recojo los datos:
			String nombre = nomMatText1.getText();
			String hora = (String) horasCombo.getSelectedItem();
			String[] horita = hora.split(":");
			hora = horita[0];
			String ampm = (String) amCombo.getSelectedItem();
			boolean ambool = true;
			switch (ampm) {
			case "a.m.":
				ambool = true;
				break;
			case "p.m.":
				ambool = false;
				break;
			}

			if (nombre.indexOf(' ') != -1) {
				JOptionPane.showMessageDialog(this, "No debe incluir espacios", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la materia a modificar.", "Error", JOptionPane.ERROR_MESSAGE);
			}  else {// uso el metodo
				principal.getPrincipal().cambiarHora(nombre, Integer.parseInt(hora), ambool);
			}
		}
		if (e.getActionCommand().equals(HORAS)) {
			// recojo los datos;
			String nombre = nomMatText.getText();
			String intensidad = (String) intenCombo.getSelectedItem();
			String[] intensidadcita = intensidad.split(" ");
			intensidad = intensidadcita[0];
			if (nombre.indexOf(' ') != -1) {
				JOptionPane.showMessageDialog(this, "No debe incluir espacios", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la materia a modificar.", "Error", JOptionPane.ERROR_MESSAGE);
			}  else {
				principal.getPrincipal().cambiarHoras(nombre, Integer.parseInt(intensidad));
			}
		}
		if (e.getActionCommand().equals(DIA)) {
			//recojo los datos
			String nombre = nomMatText2.getText( );
			String dia = (String) diaCombo.getSelectedItem();
			if (nombre.indexOf(' ') != -1) {
				JOptionPane.showMessageDialog(this, "No debe incluir espacios", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la materia a modificar.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
	        	int dianum = 0;
	        	switch(dia) {
	        	case "Lunes":
	        		dianum=1;
	        		break;
	        	case "Martes":
	        		dianum=2;
	        		break;
	        	case "Miercoles":
	        		dianum=3;
	        		break;
	        	case "Jueves":
	        		dianum=4;
	        		break;
	        	case "Viernes":
	        		dianum=5;
	        		break;
	        	case "Sabado":
	        		dianum=6;
	        		break;	
	        	}
				principal.getPrincipal().cambiarDia(nombre, dianum);
			}
		}
		if (e.getActionCommand().equals(NOMBRE)) {
			//recojo los datos
			String nombreViejo = nomMatText3.getText();
			String nombreNuevo = nomMatText3new.getText();
			if (nombreViejo.indexOf(' ') != -1 && nombreNuevo.indexOf(' ') != -1) {
				JOptionPane.showMessageDialog(this, "No debe incluir espacios.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if(nombreViejo.equals("") ||nombreNuevo.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la materia a cambiar y el nuevo nombre.", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				principal.getPrincipal().cambiarNombre(nombreViejo, nombreNuevo);
			}
		}
		if(e.getActionCommand().equals(ELIMINAR)){
			String nombre = nomMatText4.getText();
			if (nombre.indexOf(' ') != -1 ) {
				JOptionPane.showMessageDialog(this, "No debe incluir espacios.", "Error", JOptionPane.ERROR_MESSAGE);
			} else if(nombre.equals("")) {
				JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la materia a eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
			}else {
				principal.getPrincipal().eliminar(nombre);
			}
		}
	}
}	