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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JProgressBar;

public class PanelCopyDirectories extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCOrigen;
	private JTextField jtfCDestino;
	private JFileChooser jfileChooser;
	private JProgressBar jpbPorcentaje;

	/**
	 * Create the panel.
	 */
	public PanelCopyDirectories() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCopiadoDeFicheros = new JLabel("Copiado del Contenido de Carpetas");
		lblCopiadoDeFicheros.setFont(new Font("Dialog", Font.BOLD, 18));
		GridBagConstraints gbc_lblCopiadoDeFicheros = new GridBagConstraints();
		gbc_lblCopiadoDeFicheros.insets = new Insets(10, 0, 25, 0);
		gbc_lblCopiadoDeFicheros.gridwidth = 3;
		gbc_lblCopiadoDeFicheros.gridx = 0;
		gbc_lblCopiadoDeFicheros.gridy = 0;
		add(lblCopiadoDeFicheros, gbc_lblCopiadoDeFicheros);
		
		JLabel lblNewLabel = new JLabel("Carpeta Origen:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		jtfCOrigen = new JTextField();
		jtfCOrigen.setFont(new Font("Dialog", Font.PLAIN, 15));
		GridBagConstraints gbc_jtfCOrigen = new GridBagConstraints();
		gbc_jtfCOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCOrigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCOrigen.gridx = 1;
		gbc_jtfCOrigen.gridy = 1;
		add(jtfCOrigen, gbc_jtfCOrigen);
		jtfCOrigen.setColumns(10);
		
		JButton btnSCOrigen = new JButton("Seleccionar Carpeta Origen");
		btnSCOrigen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDirectoryOrigen();
			}
		});
		GridBagConstraints gbc_btnSCOrigen = new GridBagConstraints();
		gbc_btnSCOrigen.insets = new Insets(0, 0, 5, 0);
		gbc_btnSCOrigen.gridx = 2;
		gbc_btnSCOrigen.gridy = 1;
		add(btnSCOrigen, gbc_btnSCOrigen);
		
		JLabel lblCarpetaDestino = new JLabel("Carpeta Destino:");
		GridBagConstraints gbc_lblCarpetaDestino = new GridBagConstraints();
		gbc_lblCarpetaDestino.anchor = GridBagConstraints.EAST;
		gbc_lblCarpetaDestino.insets = new Insets(0, 10, 5, 5);
		gbc_lblCarpetaDestino.gridx = 0;
		gbc_lblCarpetaDestino.gridy = 2;
		add(lblCarpetaDestino, gbc_lblCarpetaDestino);
		
		jtfCDestino = new JTextField();
		jtfCDestino.setFont(new Font("Dialog", Font.PLAIN, 15));
		jtfCDestino.setColumns(10);
		GridBagConstraints gbc_jtfCDestino = new GridBagConstraints();
		gbc_jtfCDestino.insets = new Insets(0, 0, 5, 5);
		gbc_jtfCDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_jtfCDestino.gridx = 1;
		gbc_jtfCDestino.gridy = 2;
		add(jtfCDestino, gbc_jtfCDestino);
		
		JButton btnSCDestino = new JButton("Seleccionar Carpeta Destino");
		btnSCDestino.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectDirectoryDestino();
			}
		});
		GridBagConstraints gbc_btnSCDestino = new GridBagConstraints();
		gbc_btnSCDestino.insets = new Insets(0, 0, 5, 10);
		gbc_btnSCDestino.gridx = 2;
		gbc_btnSCDestino.gridy = 2;
		add(btnSCDestino, gbc_btnSCDestino);
		
		JButton btnCopiar = new JButton("Copiar Ficheros");
		btnCopiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					copyDirectories(new File(jtfCOrigen.getText()),
							new File(jtfCDestino.getText()));
					
				} catch (IOException ex) {
					
					JOptionPane.showMessageDialog(null,
							"No se ha realizado el proceso de copiado");
					ex.printStackTrace();
				}
			}
		});
		btnCopiar.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.gridwidth = 3;
		gbc_btnCopiar.insets = new Insets(20, 0, 5, 0);
		gbc_btnCopiar.gridx = 0;
		gbc_btnCopiar.gridy = 3;
		add(btnCopiar, gbc_btnCopiar);
		
		jpbPorcentaje = new JProgressBar();
		jpbPorcentaje.setStringPainted(true);
		jpbPorcentaje.setFont(new Font("Dialog", Font.BOLD, 15));
		GridBagConstraints gbc_jpbPorcentaje = new GridBagConstraints();
		gbc_jpbPorcentaje.fill = GridBagConstraints.HORIZONTAL;
		gbc_jpbPorcentaje.gridwidth = 3;
		gbc_jpbPorcentaje.insets = new Insets(15, 10, 0, 10);
		gbc_jpbPorcentaje.gridx = 0;
		gbc_jpbPorcentaje.gridy = 4;
		add(jpbPorcentaje, gbc_jpbPorcentaje);

	}
	
	/**
	 * Método que selecciona (texto) la carpeta de origen.
	 */
	private void selectDirectoryOrigen() {
		
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
				return "Seleccione una carpeta de origen";
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
			this.jtfCOrigen.setText(dirOrigen.getAbsolutePath());
		}
	}
	
	/**
	 * Método que selecciona (texto) la carpeta de destino.
	 */
	private void selectDirectoryDestino() {
		
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
				return "Seleccione una carpeta de destino";
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
			File dirDestino = this.jfileChooser.getSelectedFile();
			
			// Vuelco el nombre del fichero sobre el JTextField
			this.jtfCDestino.setText(dirDestino.getAbsolutePath());
		}
	}
	
	/**
	 * 
	 * @param origen
	 * @param destino
	 * @throws IOException
	 */
	private void copyDirectories(File origen, File destino) throws IOException {
		
		// Si no se ha seleccionado ningún directorio de origen,
		// avisamos y salimos del método.
		if (this.jtfCOrigen.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Seleccione una carpeta de origen");
			return;
		}
		
		// Si no se ha seleccionado ningún directorio de destino,
		// avisamos y salimos del método.
		if (this.jtfCDestino.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null,
					"Seleccione una carpeta de destino");
			return;
		}
		
		// En caso de escribir en el jtfCOrigen una carpeta inexistente,
		// se mostrará un aviso y saldremos del método.
		if (!origen.exists()) {
			JOptionPane.showMessageDialog(null,
					"La carpeta de origen indicada no existe");
			return;
		}
		
		// En caso de escribir en el jtfCDestino una carpeta inexistente,
		// se mostrará un aviso y saldremos del método.
		if (!destino.exists()) {
			JOptionPane.showMessageDialog(null,
					"La carpeta de destino indicada no existe");
			return;
		}
		
		// Obtengo la lista de archivos de la carpeta origen
	    File[] files = origen.listFiles();
	    // Calcula el número total de archivos a copiar
	    int totalFiles = files.length;
	    // Inicializo el progreso de la barra
	    int progreso = 0;
		
		// Hago un for-each para iterar sobre los archivos en la carpeta origen
		for (File file : origen.listFiles()) {
			// Crea la ruta de destino para el archivo
			Path destPath = new File(destino, file.getName()).toPath();

	        // Verifico si el archivo ya existe en el destino
	        if (Files.exists(destPath)) {
	            // Si ya existe, pregunta al usuario si desea reemplazarlo
	            int opcion = JOptionPane.showConfirmDialog(null,
	            		"El archivo " + file.getName() + " ya existe en la carpeta "
	            		+ "de destino. ¿Desea reemplazarlo?", "Confirmar reemplazo",
	            		JOptionPane.YES_NO_OPTION);
	            
	            // Si el usuario elige no reemplazar, salta este archivo y continúa con el siguiente
	            if (opcion != JOptionPane.YES_OPTION) {
	                continue;
	            }
	        }
					
			// Copio el archivo a la carpeta de destino  (añado el StandardCopyOption por si es necesario reemplazar el archivo).
			Files.copy(file.toPath(), destPath,
					StandardCopyOption.REPLACE_EXISTING);
			
			// Incrementa el progreso
	        progreso++;
	        // Calcula el porcentaje completado
	        int percentage = (int) ((double) progreso / totalFiles * 100);
	        // Actualiza el valor del progreso en el JProgressBar
	        jpbPorcentaje.setValue(percentage);
		}
		
		JOptionPane.showMessageDialog(null,
				"Copiado de Ficheros realizado con éxito");
		
		// Volvemos a establecer la JProgressBar a cero.
		this.jpbPorcentaje.setValue(0);
	}

}
