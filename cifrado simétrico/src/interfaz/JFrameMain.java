// Seguridad en Redes Telemáticas - Práctica 2
// Antonio Diego Barquero Cuadrado - 76043791H

package interfaz;

import cipher.Header;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Security;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.swing.JFileChooser;

public class JFrameMain extends javax.swing.JFrame {

	private String password = "";
	private File archivo = null;
	private String algoritmo = "PBEWITHMD5ANDDES";

	public JFrameMain() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jFileChooser = new javax.swing.JFileChooser();
		jDialogPassword = new javax.swing.JDialog();
		jLabelPassDialog = new javax.swing.JLabel();
		jLabelPassRepDialog = new javax.swing.JLabel();
		jButtonOKPassDialog = new javax.swing.JButton();
		jButtonResetPassDialog = new javax.swing.JButton();
		jPasswordFieldPassDialog = new javax.swing.JPasswordField();
		jPasswordFieldRepPassDialog = new javax.swing.JPasswordField();
		jDialogAlg = new javax.swing.JDialog();
		jLabelAlgoritmos = new javax.swing.JLabel();
		jButtonAlgoritmosCancelar = new javax.swing.JButton();
		jButtonAlgoritmosAceptar = new javax.swing.JButton();
		jComboBoxAlgoritmos = new javax.swing.JComboBox();
		jDialogAcercaDe = new javax.swing.JDialog();
		jLabelAsignatura = new javax.swing.JLabel();
		jLabelCurso = new javax.swing.JLabel();
		jLabelAutor = new javax.swing.JLabel();
		jButtonSalirAcercaDe = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaInformacion = new javax.swing.JTextArea();
		jMenuBar = new javax.swing.JMenuBar();
		jMenuFichero = new javax.swing.JMenu();
		jMenuItemCifrar = new javax.swing.JMenuItem();
		jMenuItemDescifrar = new javax.swing.JMenuItem();
		jMenuItemSalir = new javax.swing.JMenuItem();
		jMenuOpciones = new javax.swing.JMenu();
		jMenuItemOpcionesCif = new javax.swing.JMenuItem();
		jMenuItemVerInfo = new javax.swing.JMenuItem();
		jMenuAyuda = new javax.swing.JMenu();
		jMenuItemAcercaDe = new javax.swing.JMenuItem();

		jFileChooser.setCurrentDirectory(new java.io.File("."));

		jDialogPassword.setModal(true);
		jDialogPassword
				.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		jDialogPassword
				.setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
		jDialogPassword.setResizable(false);

		jLabelPassDialog.setText("Contraseña");

		jLabelPassRepDialog.setText("Repetir contraseña");

		jButtonOKPassDialog.setText("OK");
		jButtonOKPassDialog
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonOKPassDialogActionPerformed(evt);
					}
				});

		jButtonResetPassDialog.setText("Borrar");
		jButtonResetPassDialog
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonResetPassDialogActionPerformed(evt);
					}
				});

		jPasswordFieldPassDialog.setText("jPasswordField1");

		jPasswordFieldRepPassDialog.setText("jPasswordField2");

		javax.swing.GroupLayout jDialogPasswordLayout = new javax.swing.GroupLayout(
				jDialogPassword.getContentPane());
		jDialogPassword.getContentPane().setLayout(jDialogPasswordLayout);
		jDialogPasswordLayout
				.setHorizontalGroup(jDialogPasswordLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogPasswordLayout.createSequentialGroup()
										.addGap(176, 176, 176)
										.addComponent(jButtonOKPassDialog)
										.addGap(18, 18, 18)
										.addComponent(jButtonResetPassDialog)
										.addContainerGap(125, Short.MAX_VALUE))
						.addGroup(
								jDialogPasswordLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogPasswordLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelPassRepDialog)
														.addComponent(
																jLabelPassDialog))
										.addGap(31, 31, 31)
										.addGroup(
												jDialogPasswordLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPasswordFieldRepPassDialog)
														.addComponent(
																jPasswordFieldPassDialog))
										.addContainerGap()));
		jDialogPasswordLayout
				.setVerticalGroup(jDialogPasswordLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogPasswordLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogPasswordLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelPassDialog)
														.addComponent(
																jPasswordFieldPassDialog,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jDialogPasswordLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelPassRepDialog)
														.addComponent(
																jPasswordFieldRepPassDialog,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												jDialogPasswordLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonOKPassDialog)
														.addComponent(
																jButtonResetPassDialog))
										.addContainerGap()));

		jLabelAlgoritmos.setText("Seleccionar algoritmo");
		jLabelAlgoritmos.setToolTipText("");

		jButtonAlgoritmosCancelar.setText("Cancelar");
		jButtonAlgoritmosCancelar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonAlgoritmosCancelarActionPerformed(evt);
					}
				});

		jButtonAlgoritmosAceptar.setText("Aceptar");
		jButtonAlgoritmosAceptar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonAlgoritmosAceptarActionPerformed(evt);
					}
				});

		jComboBoxAlgoritmos.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "PBEWITHMD5ANDDES", "PBEWITHSHA1ANDDESEDE",
						"PBEWITHSHA1ANDRC2_40", "PBEWITHMD5ANDTRIPLEDES" }));

		javax.swing.GroupLayout jDialogAlgLayout = new javax.swing.GroupLayout(
				jDialogAlg.getContentPane());
		jDialogAlg.getContentPane().setLayout(jDialogAlgLayout);
		jDialogAlgLayout
				.setHorizontalGroup(jDialogAlgLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jDialogAlgLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																jComboBoxAlgoritmos,
																javax.swing.GroupLayout.Alignment.LEADING,
																0, 224,
																Short.MAX_VALUE)
														.addGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																jDialogAlgLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonAlgoritmosAceptar)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButtonAlgoritmosCancelar)))
										.addContainerGap())
						.addGroup(
								jDialogAlgLayout
										.createSequentialGroup()
										.addGap(71, 71, 71)
										.addComponent(jLabelAlgoritmos)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jDialogAlgLayout
				.setVerticalGroup(jDialogAlgLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogAlgLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabelAlgoritmos)
										.addGap(18, 18, 18)
										.addComponent(
												jComboBoxAlgoritmos,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonAlgoritmosAceptar)
														.addComponent(
																jButtonAlgoritmosCancelar))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabelAsignatura.setText("Seguridad en Redes Telemáticas");

		jLabelCurso.setText("Curso 2014/2015");

		jLabelAutor.setText("Antonio Diego Barquero Cuadrado");

		jButtonSalirAcercaDe.setText("OK");
		jButtonSalirAcercaDe
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonSalirAcercaDeActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jDialogAcercaDeLayout = new javax.swing.GroupLayout(
				jDialogAcercaDe.getContentPane());
		jDialogAcercaDe.getContentPane().setLayout(jDialogAcercaDeLayout);
		jDialogAcercaDeLayout
				.setHorizontalGroup(jDialogAcercaDeLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogAcercaDeLayout.createSequentialGroup()
										.addGap(95, 95, 95)
										.addComponent(jLabelCurso)
										.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(
								jDialogAcercaDeLayout
										.createSequentialGroup()
										.addGroup(
												jDialogAcercaDeLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jDialogAcercaDeLayout
																		.createSequentialGroup()
																		.addGap(59,
																				59,
																				59)
																		.addGroup(
																				jDialogAcercaDeLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabelAutor)
																						.addComponent(
																								jLabelAsignatura)))
														.addGroup(
																jDialogAcercaDeLayout
																		.createSequentialGroup()
																		.addGap(120,
																				120,
																				120)
																		.addComponent(
																				jButtonSalirAcercaDe)))
										.addContainerGap(73, Short.MAX_VALUE)));
		jDialogAcercaDeLayout
				.setVerticalGroup(jDialogAcercaDeLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogAcercaDeLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jLabelAsignatura,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelCurso,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jLabelAutor,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jButtonSalirAcercaDe,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(69, 69, 69)));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Seguridad en Redes Telemáticas - Cifrador");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextAreaInformacion.setEditable(false);
		jTextAreaInformacion.setColumns(20);
		jTextAreaInformacion.setLineWrap(true);
		jTextAreaInformacion.setRows(5);
		jScrollPane1.setViewportView(jTextAreaInformacion);

		jMenuFichero.setText("Fichero");

		jMenuItemCifrar.setText("Cifrar");
		jMenuItemCifrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemCifrarActionPerformed(evt);
			}
		});
		jMenuFichero.add(jMenuItemCifrar);

		jMenuItemDescifrar.setText("Descifrar");
		jMenuItemDescifrar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemDescifrarActionPerformed(evt);
					}
				});
		jMenuFichero.add(jMenuItemDescifrar);

		jMenuItemSalir.setText("Salir");
		jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemSalirActionPerformed(evt);
			}
		});
		jMenuFichero.add(jMenuItemSalir);

		jMenuBar.add(jMenuFichero);

		jMenuOpciones.setText("Opciones");

		jMenuItemOpcionesCif.setText("Opciones de cifrado");
		jMenuItemOpcionesCif
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemOpcionesCifActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemOpcionesCif);

		jMenuItemVerInfo.setText("Ver información");
		jMenuItemVerInfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemVerInfoActionPerformed(evt);
			}
		});
		jMenuOpciones.add(jMenuItemVerInfo);

		jMenuBar.add(jMenuOpciones);

		jMenuAyuda.setText("Ayuda");

		jMenuItemAcercaDe.setText("Acerca de");
		jMenuItemAcercaDe
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemAcercaDeActionPerformed(evt);
					}
				});
		jMenuAyuda.add(jMenuItemAcercaDe);

		jMenuBar.add(jMenuAyuda);

		setJMenuBar(jMenuBar);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 204,
				Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
	}

	private void jMenuItemOpcionesCifActionPerformed(
			java.awt.event.ActionEvent evt) {

		algorithmDialog();
	}

	private void jMenuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {
		aboutDialog();
	}

	private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void jMenuItemCifrarActionPerformed(java.awt.event.ActionEvent evt) {
		archivo = getFile();

		if (archivo == null) {
			showInfo("No se puede abrir el archivo");
		} else {
			passDialog();
			if (password.compareTo("") != 0) {
				cifrar();
			}
		}
	}

	private void jMenuItemDescifrarActionPerformed(
			java.awt.event.ActionEvent evt) {

		archivo = getFile();

		if (archivo == null) {
			showInfo("No se puede abrir el archivo");
		} else {
			if (archivo.getName().endsWith(".cif")) {
				passDialog();
				if (password.compareTo("") != 0) {
					descifrar();
				}
			} else {
				showInfo("El fichero no tiene el formato correcto");
			}
		}
	}

	private void jMenuItemVerInfoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItemVerInfoActionPerformed
		Set<String> listadoAlgoritmos = Security.getAlgorithms("Cipher");
		String mensaje = "Listado de algoritmos de cifrado disponibles\n";
		int indice = 0;
		for (String algoritmo : listadoAlgoritmos) {
			mensaje += Integer.toString(indice + 1) + " : " + algoritmo + "\n";
			indice++;
		}

		showInfo(mensaje);

	}

	private void jButtonOKPassDialogActionPerformed(
			java.awt.event.ActionEvent evt) {
		String pass = String.valueOf(jPasswordFieldPassDialog.getPassword());
		String passRep = String.valueOf(jPasswordFieldRepPassDialog
				.getPassword());

		password = "";
		if (pass.compareTo(passRep) == 0 && pass.compareTo("") != 0
				&& passRep.compareTo("") != 0) {
			password = pass;
			jDialogPassword.setVisible(false);
			showInfo("Contraseña introducida correctamente para el fichero: "
					+ archivo.getName());
		} else {
			showInfo("La contraseña introducida no es correcta");
		}
	}

	private void jButtonResetPassDialogActionPerformed(
			java.awt.event.ActionEvent evt) {
		clearPasswordsFields();
	}

	private void jButtonAlgoritmosCancelarActionPerformed(
			java.awt.event.ActionEvent evt) {
		jDialogAlg.setVisible(false);
	}

	private void jButtonAlgoritmosAceptarActionPerformed(
			java.awt.event.ActionEvent evt) {

		algoritmo = (String) jComboBoxAlgoritmos.getSelectedItem();
		showInfo("Algoritmo seleccionado: " + algoritmo);
		jDialogAlg.setVisible(false);

	}

	private void jButtonSalirAcercaDeActionPerformed(
			java.awt.event.ActionEvent evt) {

		jDialogAcercaDe.setVisible(false);
	}

	private File getFile() {
		File file = null;
		int returnVal = -1;
		if ((returnVal = jFileChooser.showOpenDialog(this)) == JFileChooser.APPROVE_OPTION) {
			file = jFileChooser.getSelectedFile().getAbsoluteFile();
		}

		return file;
	}

	private void passDialog() {
		clearPasswordsFields();
		jDialogPassword.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jDialogPassword.setSize(500, 150);
		jDialogPassword.setLocationRelativeTo(this);
		jDialogPassword.setResizable(false);
		jDialogPassword.setVisible(true);

	}

	private void algorithmDialog() {
		jDialogAlg.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jDialogAlg.setSize(300, 150);
		jDialogAlg.setLocationRelativeTo(this);
		jDialogAlg.setResizable(false);
		jDialogAlg.setVisible(true);
	}

	private void aboutDialog() {
		jDialogAcercaDe.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jDialogAcercaDe.setSize(300, 150);
		jDialogAcercaDe.setLocationRelativeTo(this);
		jDialogAcercaDe.setResizable(false);
		jDialogAcercaDe.setVisible(true);
	}

	private void clearPasswordsFields() {
		jPasswordFieldPassDialog.setText("");
		jPasswordFieldRepPassDialog.setText("");
	}

	private void showInfo(String info) {
		jTextAreaInformacion.append(info + "\n");
	}

	private Header createHeader() {
		Header header = new Header();
		header.setAlgorithm(algoritmo);

		return header;
	}

	private void descifrar() {

		try {
			Header header = new Header();
			FileInputStream input = new FileInputStream(archivo);
			header.load(input);
			showInfo("Descifrando " + archivo.getName() + " con "
					+ header.getAlgorithm());
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + ".cla");
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			PBEParameterSpec pPS = new PBEParameterSpec(header.getSalt(), 1024);
			SecretKeyFactory kf = SecretKeyFactory.getInstance(header
					.getAlgorithm());
			SecretKey sKey = kf.generateSecret(pbeKeySpec);
			Cipher c = Cipher.getInstance(header.getAlgorithm());
			c.init(Cipher.DECRYPT_MODE, sKey, pPS);
			CipherInputStream cis = new CipherInputStream(input, c);
			int i;
			while ((i = cis.read()) != -1) {
				output.write(i);

			}

			cis.close();
			input.close();
			output.close();
			showInfo("Descifrado completado");
		} catch (Exception e) {
			e.printStackTrace();
			showInfo("Ha ocurrido un problema al descifrar el fichero");
		}

	}

	private void cifrar() {
		try {
			Header header = createHeader();
			showInfo("Cifrando " + archivo.getName() + " con algoritmo "
					+ algoritmo);
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + ".cif");
			FileInputStream input = new FileInputStream(archivo);
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			PBEParameterSpec pPS = new PBEParameterSpec(header.getSalt(), 1024);
			SecretKeyFactory kf = SecretKeyFactory.getInstance(algoritmo);
			SecretKey sKey = kf.generateSecret(pbeKeySpec);
			header.save(output);
			Cipher c = Cipher.getInstance(algoritmo);
			c.init(Cipher.ENCRYPT_MODE, sKey, pPS);
			CipherOutputStream cos = new CipherOutputStream(output, c);
			int i;
			while ((i = input.read()) != -1) {
				cos.write(i);
			}

			cos.close();
			output.close();
			input.close();
			showInfo("Cifrado completado");
		} catch (Exception ex) {
			showInfo("Ha ocurrido un problema al cifrar el fichero");
		}
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;

				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JFrameMain.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new JFrameMain().setVisible(true);

			}
		});
	}

	private javax.swing.JButton jButtonAlgoritmosAceptar;
	private javax.swing.JButton jButtonAlgoritmosCancelar;
	private javax.swing.JButton jButtonOKPassDialog;
	private javax.swing.JButton jButtonResetPassDialog;
	private javax.swing.JButton jButtonSalirAcercaDe;
	private javax.swing.JComboBox jComboBoxAlgoritmos;
	private javax.swing.JDialog jDialogAcercaDe;
	private javax.swing.JDialog jDialogAlg;
	private javax.swing.JDialog jDialogPassword;
	private javax.swing.JFileChooser jFileChooser;
	private javax.swing.JLabel jLabelAlgoritmos;
	private javax.swing.JLabel jLabelAsignatura;
	private javax.swing.JLabel jLabelAutor;
	private javax.swing.JLabel jLabelCurso;
	private javax.swing.JLabel jLabelPassDialog;
	private javax.swing.JLabel jLabelPassRepDialog;
	private javax.swing.JMenu jMenuAyuda;
	private javax.swing.JMenuBar jMenuBar;
	private javax.swing.JMenu jMenuFichero;
	private javax.swing.JMenuItem jMenuItemAcercaDe;
	private javax.swing.JMenuItem jMenuItemCifrar;
	private javax.swing.JMenuItem jMenuItemDescifrar;
	private javax.swing.JMenuItem jMenuItemOpcionesCif;
	private javax.swing.JMenuItem jMenuItemSalir;
	private javax.swing.JMenuItem jMenuItemVerInfo;
	private javax.swing.JMenu jMenuOpciones;
	private javax.swing.JPasswordField jPasswordFieldPassDialog;
	private javax.swing.JPasswordField jPasswordFieldRepPassDialog;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaInformacion;
}
