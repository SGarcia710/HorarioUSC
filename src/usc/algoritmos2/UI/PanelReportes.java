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
import javax.swing.border.TitledBorder;

import usc.algoritmos2.modelo.Horario;

public class PanelReportes extends JPanel implements ActionListener{
	
	//CONSTANTES
	

	//atributos de mi clase
	private PanelOpciones principal;
	//Atributos de mi Interfaz
	private GridBagLayout gridBag;
	
	private JComboBox diaCombo;
	private JLabel cantMateriasDia;
	

	private JComboBox horasCombo;
	private JComboBox amCombo;
	private JLabel cantMateriasHora;
	
	private JLabel cantMateriasTotal;
	
	private JButton genReporte;
	
	public PanelReportes(PanelOpciones principal){
		this.principal=principal;//instancio mi objeto de tipo principal
		
		
		gridBag = new GridBagLayout();
		setLayout(gridBag);
		
		setBorder( new TitledBorder( "Datos de mi Horario:" ) );//Establezco un border con titulo
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		//seccion materias del dia
		constraints.insets = new Insets(12,8,12,5);//padding
		constraints.anchor= constraints.WEST;
		constraints.gridx = 0; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		add(new JLabel("Materias del dia"),constraints);
		constraints.anchor= constraints.CENTER;
		constraints.gridx = 1; 
		constraints.gridy = 0; 
		constraints.gridwidth = 2; 
		constraints.gridheight = 1; 
		String dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
		diaCombo = new JComboBox(dias);
		diaCombo.setActionCommand("DIACOMBO");
		diaCombo.addActionListener(this);
		add(diaCombo,constraints);
		constraints.anchor= constraints.EAST;
		constraints.gridx = 3; 
		constraints.gridy = 0; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1;
    	int dianum = getNumDia();
		cantMateriasDia= new JLabel("=> "+principal.getPrincipal().getMateriasDia(dianum));
		add(cantMateriasDia,constraints);
		
		//Seccion materias de la hora
		constraints.anchor= constraints.WEST;
		constraints.gridx = 0; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		add(new JLabel("Materias de la hora"),constraints);
		constraints.anchor= constraints.CENTER;
		constraints.gridx = 1; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
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
		horasCombo.setActionCommand("HORASCOMBO");
		horasCombo.addActionListener(this);
		add(horasCombo,constraints);
		constraints.gridx = 2; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1;
		String ampm[] = {"a.m.","p.m."};
		amCombo = new JComboBox(ampm);
		amCombo.setActionCommand("HORASCOMBO2");
		amCombo.addActionListener(this);
		add(amCombo,constraints); 
		constraints.anchor= constraints.EAST;
		constraints.gridx = 3; 
		constraints.gridy = 1; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1;
        String hora = (String) horasCombo.getSelectedItem();
        String[] horita = hora.split(":");
        hora=horita[0];
		boolean ambool = true;
		String am = (String) amCombo.getSelectedItem();
		switch(am) {
    	case "a.m.":
    		ambool=true;
    		break;
    	case "p.m.":
    		ambool=false;
    		break;
    	}		
		cantMateriasHora=new JLabel("=> "+principal.getPrincipal().getMateriasHora(Integer.parseInt(hora), ambool));
		add(cantMateriasHora,constraints);
		
		//Seccion materias total
		constraints.anchor= constraints.WEST;
		constraints.gridx = 0; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		add(new JLabel("Total de materias"),constraints);
		constraints.anchor= constraints.EAST;
		constraints.gridx = 3; 
		constraints.gridy = 2; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		cantMateriasTotal= new JLabel("=> "+principal.getPrincipal().getMateriasTotal());
		add(cantMateriasTotal,constraints);
		
		//boton
		constraints.anchor= constraints.CENTER;
		constraints.gridx = 3; 
		constraints.gridy = 3; 
		constraints.gridwidth = 1; 
		constraints.gridheight = 1; 
		genReporte=new JButton("Reporte");
		genReporte.setActionCommand("REPORTE");
		genReporte.addActionListener(this);
		add(genReporte,constraints);
		
		
	}


	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if(comando.equals("DIACOMBO")) {
			refrescarDia();
		}
		if(comando.equals("HORASCOMBO") || comando.equals("HORASCOMBO2")) {
			refrescarHora();
		}
		if(comando.equals("REPORTE")) {
			principal.getPrincipal().generarReporte();
		}
	}
	
	private void refrescarDia() {
		int dianum = getNumDia();
		cantMateriasDia.setText("=> "+principal.getPrincipal().getMateriasDia(dianum));
	}
	
	private void refrescarHora() {
		boolean ambool = true;
		String am = (String) amCombo.getSelectedItem();
		switch(am) {
    	case "a.m.":
    		ambool=true;
    		break;
    	case "p.m.":
    		ambool=false;
    		break;
    	}
        String hora = (String) horasCombo.getSelectedItem();
        String[] horita = hora.split(":");
        hora=horita[0];
        if(Integer.parseInt(hora)==12 && ambool) {
        	JOptionPane.showMessageDialog(this, "La hora ingresada no es valida.", "Error",JOptionPane.ERROR_MESSAGE);
        }else {
        	 try {
             	cantMateriasHora.setText("=> "+principal.getPrincipal().getMateriasHora(Integer.parseInt(hora), ambool));
     		} catch (IndexOutOfBoundsException e) {
     			JOptionPane.showMessageDialog(this, "La hora ingresada no es valida.", "Error",JOptionPane.ERROR_MESSAGE);
     		}
        }
        
       
	}
	private void refrescarTotal() {
		cantMateriasTotal.setText("=> "+principal.getPrincipal().getMateriasTotal());
	}
	
	public void refrescarPanel() {
		refrescarDia();
		refrescarHora();
		refrescarTotal();
	}
	
	public int getNumDia() {
    	int dianum = 0;
    	switch((String)diaCombo.getSelectedItem()) {
    	case "Lunes":
    		return 1;
    	case "Martes":
    		return 2;
    	case "Miercoles":
    		return 3;
    	case "Jueves":
    		return 4;
    	case "Viernes":
    		return 5;
    	case "Sabado":
    		return 6;
    	default:
    		return 0;
    	}
	}
}
