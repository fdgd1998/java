import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.InputEvent;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Dimension;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Cesar {

	private JFrame frmCifradoCsar;
	private String filename = "nuevo_documento.txt";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		};
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cesar window = new Cesar();
					window.frmCifradoCsar.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cesar() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCifradoCsar = new JFrame();
		frmCifradoCsar.setTitle("Cifrado C\u00E9sar");
		frmCifradoCsar.setIconImage(Toolkit.getDefaultToolkit().getImage(Cesar.class.getResource("/javax/swing/plaf/metal/icons/ocean/menu.gif")));
		frmCifradoCsar.setBounds(100, 100, 660, 456);
		frmCifradoCsar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCifradoCsar.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmCifradoCsar.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		panel.add(splitPane, BorderLayout.CENTER);
		splitPane.setDividerLocation(310);
		
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCesar = new JLabel("C\u00E9sar:");
		panel_3.add(lblCesar);
		
		JTextPane textPane = new JTextPane();
		panel_1.add(textPane, BorderLayout.CENTER);
		
		JTextPane textPane_1 = new JTextPane();
		
		JPanel panel_2 = new JPanel();
		splitPane.setRightComponent(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(textPane_1, BorderLayout.CENTER);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, null, 26, 1));
		spinner.setPreferredSize(new Dimension(40, 22));
		panel_3.add(spinner);
		
		JButton btnCifrar = new JButton("Cifrar");
		btnCifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto = textPane.getText();
				String hack = "";
				char c;
				int clave = (int)spinner.getValue();
				for (int i = 0; i < texto.length(); i++) {
					c = texto.charAt(i);
					if (c != '\n') {
						c = (char)((int)texto.charAt(i) + clave);
						hack += c;
					} else hack += '\n'; 
				}
				textPane_1.setText(hack);
			}
		});
		panel_3.add(btnCifrar);
		
		JPanel panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnDescrifrar = new JButton("Descifrar");
		btnDescrifrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String texto = textPane_1.getText();
				String hack = "";
				char c;
				int clave = (int)spinner.getValue();
				for (int i = 0; i < texto.length(); i++) {
					c = texto.charAt(i);
					if (c != '\n') {
						c = (char)((int)texto.charAt(i) - clave);
						hack += c;
					} else hack += '\n'; 
				}
				textPane.setText(hack);
			}
		});
		panel_4.add(btnDescrifrar);
		
		
		JMenuBar menuBar = new JMenuBar();
		frmCifradoCsar.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrirPlano = new JMenuItem("Abrir texto plano...");
		mntmAbrirPlano.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				String aux = "";
				String texto = "";
				try {
					FileFilter ff = new FileNameExtensionFilter("Documentos de texto", "txt");
					fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
					fc.addChoosableFileFilter(ff);
					fc.setFileFilter(ff);
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fc.showOpenDialog(frmCifradoCsar);
					File abrir = fc.getSelectedFile();
					filename = abrir.getName();
					
					if (abrir != null) {
						FileReader archivo = new FileReader(abrir);
						BufferedReader leer = new BufferedReader(archivo);
						while ((aux = leer.readLine()) != null)
							texto += aux + "\n";
						leer.close();
						archivo.close();
						textPane.setText(texto);
						frmCifradoCsar.setTitle(filename);
					}
				} catch (IOException ex) {
				JOptionPane.showMessageDialog(
						frmCifradoCsar, 
						"Se ha producido un error: "+ex, 
						"Error", 
						JOptionPane.ERROR_MESSAGE, 
						null
				);}
			}
		});
		
		mntmAbrirPlano.setIcon(new ImageIcon(Cesar.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		mnArchivo.add(mntmAbrirPlano);
		
		JMenuItem mntmAbrirCifrado = new JMenuItem("Abrir fichero cifrado...");
		mnArchivo.add(mntmAbrirCifrado);
		
		mntmAbrirCifrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				String aux = "";
				String texto = "";
				try {
					FileFilter ff = new FileNameExtensionFilter("Documentos de texto", "txt");
					fc.removeChoosableFileFilter(fc.getAcceptAllFileFilter());
					fc.addChoosableFileFilter(ff);
					fc.setFileFilter(ff);
					fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fc.showOpenDialog(frmCifradoCsar);
					File abrir = fc.getSelectedFile();
					filename = abrir.getName();
					
					if (abrir != null) {
						FileReader archivo = new FileReader(abrir);
						BufferedReader leer = new BufferedReader(archivo);
						while ((aux = leer.readLine()) != null)
							texto += aux + "\n";
						leer.close();
						archivo.close();
						textPane_1.setText(texto);
						frmCifradoCsar.setTitle(filename);
					}
				} catch (IOException ex) {
				JOptionPane.showMessageDialog(
						frmCifradoCsar, 
						"Se ha producido un error: "+ex, 
						"Error", 
						JOptionPane.ERROR_MESSAGE, 
						null
				);}
			}
		});
		
		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);
		
		JMenuItem mntmGuardarPlano = new JMenuItem("Guardar texto plano...");
		mntmGuardarPlano.setIcon(new ImageIcon(Cesar.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mnArchivo.add(mntmGuardarPlano);
		
		JMenuItem mntmGuardarCifradoComo = new JMenuItem("Guardar cifrado como...");
		mntmGuardarCifradoComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Specify a file to save");   
				 
				int userSelection = fc.showSaveDialog(frmCifradoCsar);
				 
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    FileWriter escribir;
				    File fichero;
					try {
						fichero = new File(fc.getSelectedFile().toString());
						escribir = new FileWriter(fichero);
						String[] texto = textPane_1.getText().split("\\r\n");
						for (int i = 0; i < texto.length; i++)
							escribir.write(texto[i]);
						escribir.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}   
				}
			}
		});
		
		JMenuItem mntmGuardarCifrado = new JMenuItem("Guardar cifrado...");
		mnArchivo.add(mntmGuardarCifrado);
		
		JMenuItem mntmGuardarPlanoComo = new JMenuItem("Guardar texto plano como...");
		mnArchivo.add(mntmGuardarPlanoComo);
		mnArchivo.add(mntmGuardarCifradoComo);
		
		JSeparator separator_1 = new JSeparator();
		mnArchivo.add(separator_1);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmSalir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mnAyuda.add(mntmAcercaDe);
	}

}
