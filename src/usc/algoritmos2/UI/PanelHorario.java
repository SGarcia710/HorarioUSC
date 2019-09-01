package usc.algoritmos2.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import usc.algoritmos2.modelo.Horario;

public class PanelHorario extends JPanel {
	
	private Principal principal;
	
	private JLabel[][] materias;
	private GridLayout grid;

	public PanelHorario(Principal principal) {
		this.principal = principal;
		setPreferredSize(new Dimension(1000,600));
		
		materias = new JLabel[Horario.getFil() + 1][Horario.getFil() + 1];
		grid = new GridLayout(17, 7);
		setLayout(grid);
		
		llenarTabla();
		crearTabla();
		
	}

	private void llenarTabla() {
		//le doy estilo a la tabla
		for (int i = 1; i < Horario.getFil()+1; i++) {
			if ((6 + i) < 12) {
				materias[i][0] = new JLabel((6 + i) + ":00 AM");
				materias[i][0].setHorizontalAlignment(SwingConstants.CENTER);
				materias[i][0].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			} else if(i==6) {
				materias[i][0]=new JLabel("12:00 PM");
				materias[i][0].setHorizontalAlignment(SwingConstants.CENTER);
				materias[i][0].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			}
			else {
				materias[i][0] = new JLabel(((6 - i)*-1) + ":00 PM");
				materias[i][0].setHorizontalAlignment(SwingConstants.CENTER);
				materias[i][0].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			}
		}
		for (int i = 1; i < Horario.getCol()+1; i++) {
			switch (i) {
			case 1:
				materias[0][i] = new JLabel("Lunes");
				materias[0][i].setHorizontalAlignment(SwingConstants.CENTER);
				materias[0][i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				break;
			case 2:
				materias[0][i] = new JLabel("Martes");
				materias[0][i].setHorizontalAlignment(SwingConstants.CENTER);
				materias[0][i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				break;
			case 3:
				materias[0][i] = new JLabel("Miercoles");
				materias[0][i].setHorizontalAlignment(SwingConstants.CENTER);
				materias[0][i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				break;
			case 4:
				materias[0][i] = new JLabel("Jueves");
				materias[0][i].setHorizontalAlignment(SwingConstants.CENTER);
				materias[0][i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				break;
			case 5:
				materias[0][i] = new JLabel("Viernes");
				materias[0][i].setHorizontalAlignment(SwingConstants.CENTER);
				materias[0][i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				break;
			case 6:
				materias[0][i] = new JLabel("Sabado");
				materias[0][i].setHorizontalAlignment(SwingConstants.CENTER);
				materias[0][i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				break;
			}
			
		}
		for (int i = 1; i < Horario.getFil()+1; i++) {
			for (int j = 1; j < Horario.getCol()+1; j++) {
				if(principal.getMiHorario().getMat(i-1, j-1)!=null) {
					String nombreMat= principal.getMatNombre(i-1, j-1);
					materias[i][j]=  new JLabel(nombreMat) ;
					materias[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				}else {
					materias[i][j]=  new JLabel("");
				}
				materias[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			}
		}
		
	}

	private void crearTabla() {
		for (int i = 0; i < Horario.getFil()+1; i++) {
			for (int j = 0; j < Horario.getCol()+1; j++) {
				if(i==0 && j==0) {
					JLabel vacio = new JLabel("");
					add(vacio);
					
				}else {
					add(materias[i][j]);
				}

			}
		}
	}
	
	public void refrescar() {//refresco la matriz de JLabels
		for (int i = 1; i < Horario.getFil()+1; i++) {
			for (int j = 1; j < Horario.getCol()+1; j++) {
				if(principal.getMiHorario().getMat(i-1, j-1)!=null) {
					String nombreMat= principal.getMatNombre(i-1, j-1);
					materias[i][j].setText(nombreMat);//seteando el nuevo texto
					materias[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				}else {
					materias[i][j].setText("");
				}
			}
		}
	}
}
