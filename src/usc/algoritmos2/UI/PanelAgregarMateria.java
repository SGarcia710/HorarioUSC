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


public class PanelAgregarMateria extends JPanel implements ActionListener{
	
	//Constantes
	private static final String AGREGAR = "AGREGAR";
	//Atributo de la clase (Instancio un objeto de tipo Clase principal porque este panel tiene botones)
	private PanelOpciones principal;
	//Atributos de mi Interfaz
	private GridBagLayout gridBag;
	private JTextField nomText;
	private JComboBox intenCombo;
	private JComboBox horasCombo;
	private JComboBox amCombo;
	private JComboBox diaCombo;
	private JButton botonAgregar;
	
	public PanelAgregarMateria(PanelOpciones principal) {
		
		this.principal=principal;

		gridBag = new GridBagLayout();//creo el layout
		setLayout(gridBag);//Establezco la grilla como Layout

		setBorder( new TitledBorder( "Agregar Materia:" ) );//Establezco un border con titulo
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		//Seccion Nombre
		constraints.anchor= constraints.WEST;
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		add(new JLabel("Nombre:"),constraints);
		constraints.anchor= constraints.CENTER;
		constraints.insets = new Insets(0,3,0,0);//padding
		constraints.gridx = 2; 
		constraints.gridy = 0; 
		constraints.gridwidth = 10;
		constraints.gridheight = 1;
		nomText = new JTextField();	
		nomText.setColumns(10);
		add(nomText,constraints);
		
		//Seccion intensidad
		constraints.anchor= constraints.WEST;
		constraints.insets = new Insets(0,30,0,0);//padding
		constraints.gridx = 13; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		add(new JLabel("Intensidad:"),constraints);
		constraints.anchor= constraints.CENTER;
		constraints.insets = new Insets(0,3,0,0);//padding
		constraints.gridx = 16;
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		String[]intensidades= new String[16];
		for (int i = 0; i < intensidades.length; i++) {
			if(i==0) {
				intensidades[i]= (i+1)+" Hora";
			}else {
			intensidades[i]= (i+1)+" Horas";
			}
		}
		intenCombo = new JComboBox(intensidades);
		add(intenCombo,constraints);
			
		//Seccion hora
		constraints.anchor= constraints.WEST;
		constraints.insets = new Insets(15,0,0,0);//padding
		constraints.gridx = 0; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		add(new JLabel("Hora:"),constraints);
		constraints.anchor= constraints.CENTER;
		constraints.insets = new Insets(15,3,0,0);//padding
		constraints.gridx = 2; 
		constraints.gridy = 1; 
		constraints.gridwidth = 2;
		constraints.gridheight = 1; 
		String[] horas= new String[Horario.getFil()];
		for (int i = 0; i < horas.length; i++) {
			if ((7 + i) < 12) {
				horas[i] = (7 + i) + ":00";
			} else if(i==5) {
				horas[i]="12:00";
			}
			else {
				horas[i] = ((5 - i)*-1) + ":00";
			}
		}
		horasCombo = new JComboBox(horas);
		add(horasCombo,constraints);
		constraints.gridx = 5; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		String ampm[] = {"a.m.","p.m."};
		amCombo = new JComboBox(ampm);
		add(amCombo,constraints); 
			
		//Seccion dia
		constraints.anchor= constraints.WEST;
		constraints.insets = new Insets(15,30,0,0);//padding
		constraints.gridx = 13;
		constraints.gridy = 1; 
		constraints.gridwidth = 1;
		constraints.gridheight = 1; 
		add(new JLabel("Dia:"),constraints);
		constraints.anchor= constraints.CENTER;
		constraints.insets = new Insets(15,3,0,0);//padding
		constraints.gridx = 16; 
		constraints.gridy = 1;
		constraints.gridwidth = 4; 
		constraints.gridheight = 1;
		String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
		diaCombo = new JComboBox(dias);
		add(diaCombo,constraints);
		
		//Seccion Boton
		constraints.insets = new Insets(20,0,0,0);//padding
		constraints.gridx = 16; 
		constraints.gridy = 2;
		constraints.gridwidth = 4; 
		constraints.gridheight = 1;
		botonAgregar = new JButton("Agregar");
		botonAgregar.setActionCommand( AGREGAR );
		botonAgregar.addActionListener( this );
		add(botonAgregar,constraints);
	}

	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand( ).equals(AGREGAR))
        {
        //esto hace el boton AGREGAR
			
        // Recojo los datos
        String nombre = nomText.getText( );
        String intensidad = (String) intenCombo.getSelectedItem();
        String[] intensidadcita=intensidad.split(" ");
        intensidad=intensidadcita[0];
        String hora = (String) horasCombo.getSelectedItem();
        String[] horita = hora.split(":");
        hora=horita[0];
        String ampm = (String) amCombo.getSelectedItem();
        String dia = (String) diaCombo.getSelectedItem();
        
        // Verifica que no tenga espacios
        if( nombre.indexOf( ' ' ) != -1)
        {
            JOptionPane.showMessageDialog( this, "No debe incluir espacios.", "Error", JOptionPane.ERROR_MESSAGE );
        }else if(nombre.equals("")){
        	JOptionPane.showMessageDialog( this, "Debe ingresar un nombre a la materia.", "Error", JOptionPane.ERROR_MESSAGE );
        }else{//uso el metodo para agregar materia con los datos recogidos ya verificados
        	//consigo el dia en numero dependiendo de la opcion seleccionada
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
        	boolean ambool=true;
        	switch(ampm) {
        	case "a.m.":
        		ambool=true;
        		break;
        	case "p.m.":
        		ambool=false;
        		break;
        	}
        	
            principal.getPrincipal().agregarMateria(nombre, Integer.parseInt(hora),ambool, Integer.parseInt(intensidad), dianum);

        }
        }
	}
	
	
}
