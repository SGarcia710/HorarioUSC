package usc.algoritmos2.UI;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTitulo extends JPanel {
	
	private JLabel imagen;
	private JLabel titulo;
	private ImageIcon icono;
	private GridLayout grid;
	
	public PanelTitulo() {
		//setSize(1900,200);
		imagen = new JLabel();
		icono = new ImageIcon("./images/usclogo.png");
		imagen.setIcon(icono);
		titulo = new JLabel("Horario");
		titulo.setFont(new Font("Tahoma",Font.PLAIN,45));
		grid = new GridLayout(1, 2);
		
		setLayout(grid);
		add(imagen);
		add(titulo);		
	}
	
}
