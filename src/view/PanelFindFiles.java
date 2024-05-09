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
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelFindFiles extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCarpeta;
	private JTextField jtfTexto;
	private JFileChooser jfileChooser;
	private JTable table;
	private JScrollPane scrollPane;
	private List<File> ficheros = null;

	/**
	 * Create the panel.
	 */
	public PanelFindFiles() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Búsqueda de Ficheros");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(15, 0, 20, 0);
		gbc_lblNewLabel.gridwidth = 3;
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
		gbc_btnSelect.insets = new Insets(0, 0, 5, 10);
		gbc_btnSelect.gridx = 2;
		gbc_btnSelect.gridy = 1;
		add(btnSelect, gbc_btnSelect);
		
		JLabel lblNewLabel_1_1 = new JLabel("Texto en nombre:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 2;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		jtfTexto = new JTextField();
		jtfTexto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		jtfTexto.setColumns(10);
		GridBagConstraints gbc_jtfTexto = new GridBagConstraints();
		gbc_jtfTexto.insets = new Insets(0, 0, 5, 5);
		gbc_jtfTexto.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfTexto.gridx = 1;
		gbc_jtfTexto.gridy = 2;
		add(jtfTexto, gbc_jtfTexto);
		
		JButton btnFind = new JButton("Buscar Ficheros");
		btnFind.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loadFilesIntoTable();
			}
		});
		btnFind.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 2;
		gbc_btnFind.gridy = 2;
		add(btnFind, gbc_btnFind);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(10, 10, 10, 10);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);

	}
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	private boolean isFileNameValid(File f) {
		String str = this.jtfTexto.getText();
		
		// Si el campo está vacío, se cargará el fichero actual
		// en la tabla.
		if (str.trim().isEmpty()) {
			return true;
		}
		
		// Si el nombre del fichero contiene la cadena de texto
		// del filtrado, se cargará en la tabla.
		if (f.getName().toUpperCase()
				.contains(str.trim().toUpperCase())) {
			return true;
		} else {
			return false;
		}
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
	}
	
	/**
	 * Método que recoge los posibles ficheros de una carpeta 
	 * y los devuelve agrupados en una lista parametrizada.
	 * @param folderName Nombre de la carpeta (ruta)
	 * @return Lista parametrizada(File)
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
				// Si el elemento actual es un fichero Y
				// cumple con el filtrado indicado, se agregará
				// a la lista.
				if (file.isFile() && isFileNameValid(file)) {
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
	}

}
