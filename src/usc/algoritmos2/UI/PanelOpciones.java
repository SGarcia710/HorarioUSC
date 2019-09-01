package usc.algoritmos2.UI;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class PanelOpciones extends JPanel {
	private PanelAgregarMateria panelcitoAgregar;
	private PanelModificaciones panelcitoModif;
	private PanelReportes panelcitoRepor;
	private GridLayout grid;
	private Principal principal;
	
	public PanelOpciones( Principal principal) {
		this.principal = principal;
		grid = new GridLayout(3, 1);
		setPreferredSize(new Dimension(366,600));
		setLayout(grid);
		panelcitoAgregar = new PanelAgregarMateria(this);
		panelcitoModif = new PanelModificaciones(this);
		panelcitoRepor = new PanelReportes(this);
		add(panelcitoAgregar);
		add(panelcitoModif);
		add(panelcitoRepor);
		
	}
	//metodos hacia afuera
	public void refrescarPanel() {
		panelcitoRepor.refrescarPanel();
	}
	//metodos hacia adentro
	public Principal getPrincipal(){
		return principal;
	}
	
}
