package view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;

public class PanelPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField jtfCOrigen;
	private JTextField jtfCDestino;

	/**
	 * Create the panel.
	 */
	public PanelPrincipal() {
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
		GridBagConstraints gbc_btnSCDestino = new GridBagConstraints();
		gbc_btnSCDestino.insets = new Insets(0, 0, 5, 10);
		gbc_btnSCDestino.gridx = 2;
		gbc_btnSCDestino.gridy = 2;
		add(btnSCDestino, gbc_btnSCDestino);
		
		JButton btnCopiar = new JButton("Copiar Ficheros");
		btnCopiar.setFont(new Font("Dialog", Font.BOLD, 14));
		GridBagConstraints gbc_btnCopiar = new GridBagConstraints();
		gbc_btnCopiar.gridwidth = 3;
		gbc_btnCopiar.insets = new Insets(20, 0, 5, 0);
		gbc_btnCopiar.gridx = 0;
		gbc_btnCopiar.gridy = 3;
		add(btnCopiar, gbc_btnCopiar);
		
		JProgressBar jpbPorcentaje = new JProgressBar();
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

}
