package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSlider;

public class PanelSelectFilesBySize extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpeta;
	private JFileChooser jfileChooser;
	private JTable table;
	private JScrollPane scrollPane;
	private JSlider slider;
	private JLabel lbSlider;
	private List<File> ficheros = null;
	private JLabel lbNumFiles;
	
	// Guardará el tamaño elegido en el JSlider.
	private int sizeSelected;

	/**
	 * Create the panel.
	 */
	public PanelSelectFilesBySize() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 69, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Búsqueda de Ficheros por Tamaño");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(15, 0, 20, 0);
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Carpeta:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		jtfCarpeta = new JTextField();
		jtfCarpeta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_jtfCarpeta = new GridBagConstraints();
		gbc_jtfCarpeta.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCarpeta.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCarpeta.gridx = 1;
		gbc_jtfCarpeta.gridy = 1;
		add(jtfCarpeta, gbc_jtfCarpeta);
		jtfCarpeta.setColumns(10);
		
		JButton btnSelect = new JButton("Selecciona Carpeta");
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDirectory();
			}
		});
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.gridwidth = 2;
		gbc_btnSelect.insets = new Insets(0, 0, 5, 10);
		gbc_btnSelect.gridx = 2;
		gbc_btnSelect.gridy = 1;
		add(btnSelect, gbc_btnSelect);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tamaño del fichero:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 2;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		// Configuramos el JSlider para las 6 opciones a elegir.
		// El valor inicial será el primero (0).
		slider = new JSlider(0, 5, 0);
		slider.setPaintTicks(true);
		// Establecemos los intervalos de desplazamiento.
		// Nos movemos uno a uno.
		slider.setMajorTickSpacing(1);
		slider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		// Agregamos un ChangeListener para actualizar el label
		// del JSlider según el valor del mismo.
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				getSizeSelected(slider.getValue());
				loadFilesIntoTable();
			}
		});
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.fill = GridBagConstraints.HORIZONTAL;
		gbc_slider.insets = new Insets(0, 0, 5, 5);
		gbc_slider.gridx = 1;
		gbc_slider.gridy = 2;
		add(slider, gbc_slider);
		
		lbSlider = new JLabel("");
		lbSlider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lbSlider = new GridBagConstraints();
		gbc_lbSlider.anchor = GridBagConstraints.EAST;
		gbc_lbSlider.insets = new Insets(0, 0, 5, 5);
		gbc_lbSlider.gridx = 2;
		gbc_lbSlider.gridy = 2;
		add(lbSlider, gbc_lbSlider);
		
		lbNumFiles = new JLabel("");
		lbNumFiles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lbNumFiles = new GridBagConstraints();
		gbc_lbNumFiles.anchor = GridBagConstraints.WEST;
		gbc_lbNumFiles.insets = new Insets(0, 0, 5, 0);
		gbc_lbNumFiles.gridx = 3;
		gbc_lbNumFiles.gridy = 2;
		add(lbNumFiles, gbc_lbNumFiles);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(10, 10, 10, 10);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

		// Al inicio, se mostrará el valor inicial del JSlider
		// como el número de ficheros mostrados (0).
		lbSlider.setText("> " + slider.getValue() + " KB");
		lbNumFiles.setText("(0 ficheros)");
	}
	
	/**
	 * 
	 */
	private void loadFilesIntoTable() {
		// Comprobamos si la ruta de carpeta está vacía.
		if (this.jtfCarpeta.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Seleccione una carpeta a visualizar");
			return;
		}
		
		// Obtenemos los posibles ficheros.
		ficheros = getFilesFromFolder(this.jtfCarpeta.getText());
		
		// Inicializamos la tabla con los ficheros obtenidos.
		table = new JTable(DatosDeTabla.getDatosDeTabla(ficheros),
				DatosDeTabla.getTitulosColumnas());
		scrollPane.setViewportView(table);
		
		// Mostramos el número de ficheros obtenidos.
		this.lbNumFiles.setText("(" + ficheros.size() + " ficheros)");
	}
	
	/**
	 * 
	 * @param option
	 */
	private void getSizeSelected(int option) {
	    // Información Slider del tamaño de los ficheros.
	    String txt = null;

	    // Elegimos el texto a mostrar según el valor
	    // del JSlider y el valor del tamaño elegido.
	    switch (option) {
	        case 0:
	            txt = "> 0 KB";
	            sizeSelected = 0; // 0 KB
	            break;
	        case 1:
	            txt = "> 100 KB";
	            sizeSelected = 100; // 100 KB
	            break;
	        case 2:
	            txt = "> 1 MB";
	            sizeSelected = 1024; // 1 MB en KB (1 MB = 1024 KB)
	            break;
	        case 3:
	            txt = "> 10 MB";
	            sizeSelected = 10 * 1024; // 10 MB en KB (1 MB = 1024 KB)
	            break;
	        case 4:
	            txt = "> 100 MB";
	            sizeSelected = 100 * 1024; // 100 MB en KB (1 MB = 1024 KB)
	            break;
	        case 5:
	            txt = "> 1 GB";
	            sizeSelected = 1024 * 1024; // 1 GB en KB (1 GB = 1024 MB)
	            break;
	    }

	    // Actualizamos el label del JSlider.
	    this.lbSlider.setText(txt);
	}
	
	/**
	 * 
	 * @param folderName
	 * @param size
	 * @return
	 */
	private List<File> getFilesFromFolder(String folderName) {
		List<File> ficheros = new ArrayList<File>();
		File folder = new File(folderName);
		
		// Comprobamos si el fichero existe y es una carpeta.
		if (!folder.isDirectory()) {
			JOptionPane.showMessageDialog(null, 
					"La ruta indicada no es una carpeta");
			return ficheros;
		}
		
		// Obtenemos los posibles ficheros de la carpeta.
		File[] files = folder.listFiles();
		if (files != null) {
			// Recorremos cada fichero.
			for (File file : files) {
				// Si el elemento actual es un fichero y
				// su tamaño (KB) es superior al elegido en el Slider,
				// se agregará a la lista.
				if (file.isFile() &&
						((float) file.length() / 1024) > sizeSelected) {
					ficheros.add(file);
				}
			}
		} else {
			System.err.println(
					"No se han podido obtener los ficheros "
					+ "de la carpeta seleccionada");
		}
		
		return ficheros;
	}
	
	/**
	 * Método que selecciona una carpeta en el equipo.
	 */
	private void selectDirectory() {
		
		// Configurando el componente
		this.jfileChooser = new JFileChooser();
		
		// Establecimiento de la carpeta de inicio
		this.jfileChooser.setCurrentDirectory(new File("C:\\"));
		
		// Tipo de selección que se hace en el diálogo
		this.jfileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		// Filtro del tipo de ficheros que puede abrir
		this.jfileChooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Seleccione una carpeta para mostrar su contenido";
			}
			
			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return false;
			}
		});
		
		// Abro el diálogo para la elección del usuario
		int seleccionUsuario = jfileChooser.showOpenDialog(null);
		
		if (seleccionUsuario == JFileChooser.APPROVE_OPTION) {
			File dirOrigen = this.jfileChooser.getSelectedFile();
			
			// Vuelco el nombre del fichero sobre el JTextField
			this.jtfCarpeta.setText(dirOrigen.getAbsolutePath());
		}
		
		// Muestro los registros en la tabla.
		loadFilesIntoTable();
	}

}
