package main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import utils.Apariencia;
import view.PanelCopyDirectories;
import view.PanelFindFiles;
import view.PanelSelectFilesBySize;
import view.PanelSelectFilesBySizeAndFilter;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private static Principal instance = null;
	private JTabbedPane tabbedPane = null;
	
	static {
		Apariencia.setAparienciasOrdenadas(Apariencia.aparienciasOrdenadas);
	}

	/**
	 * Constructor.
	 */
	public Principal() {
		super("Manejo de Ficheros - Entrada y Salida");
		
		this.setBounds(100, 100, 800, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		PanelCopyDirectories panelCD = new PanelCopyDirectories();
		PanelFindFiles panelFF = new PanelFindFiles();
		PanelSelectFilesBySize panelSFS = new PanelSelectFilesBySize();
		PanelSelectFilesBySizeAndFilter panel2Filter = new PanelSelectFilesBySizeAndFilter();
		
		getJTabbedPane().addTab("Copiado de Carpetas", panelCD);
		getJTabbedPane().addTab("Búsqueda de Ficheros en Carpeta", panelFF);
		getJTabbedPane().addTab("Selección de Ficheros por Tamaño", panelSFS);
		getJTabbedPane().addTab("Selección de Ficheros por Tamaño y Filtrado Texto", panel2Filter);
		getJTabbedPane().setSelectedIndex(0);
		
		this.getContentPane().add(tabbedPane);
	}
	
	/**
	 * Singleton JTabbedPane.
	 * @return
	 */
	public JTabbedPane getJTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
		}
		return tabbedPane;
	}
	
	/**
	 * Singleton Principal.
	 * @return
	 */
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}
	
	/**
	 * Método Principal.
	 * @param args
	 */
	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

}
