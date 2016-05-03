// Seguridad en Redes Telemáticas - Práctica 4
// Antonio Diego Barquero Cuadrado
// Antonio Manuel Mateos Cruz

package interfaz;

import header.HashFileHeader;

import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.swing.JFileChooser;

public class JFrameMain extends javax.swing.JFrame {

	private String password = "";
	private File archivo = null;
	private String cifrado = "RSA";
	private String firma = "SHA1withRSA";
	private String claves = "claves.key";
	private KeyPair keyPair = null;

	private static String FIRM_EXT = ".firm";
	private static String CIF_EXT = ".cif";
	private static String VERIF = ".ver";
	private static String DESCIF = ".cla";

	public JFrameMain() {
		initComponents();
	}

	@SuppressWarnings("unchecked")
	private void initComponents() {

		jFileChooser = new javax.swing.JFileChooser();
		jDialogSecret = new javax.swing.JDialog();
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
		jComboBoxCifrado = new javax.swing.JComboBox();
		jComboBoxFirma = new javax.swing.JComboBox();
		jLabelAlgoritmos1 = new javax.swing.JLabel();
		jLabelAlgoritmos2 = new javax.swing.JLabel();
		jLabelAlgoritmos3 = new javax.swing.JLabel();
		jPasswordFieldClaves = new javax.swing.JPasswordField();
		jTextFieldClaves = new javax.swing.JTextField();
		jDialogAcercaDe = new javax.swing.JDialog();
		jLabelAsignatura = new javax.swing.JLabel();
		jLabelCurso = new javax.swing.JLabel();
		jLabelAutor = new javax.swing.JLabel();
		jButtonSalirAcercaDe = new javax.swing.JButton();
		jLabelAutor1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextAreaInformacion = new javax.swing.JTextArea();
		jMenuBar = new javax.swing.JMenuBar();
		jMenuFichero = new javax.swing.JMenu();
		jMenuItemCifrar = new javax.swing.JMenuItem();
		jMenuItemDescifrar = new javax.swing.JMenuItem();
		jMenuItemFirmar = new javax.swing.JMenuItem();
		jMenuItemVerFirma = new javax.swing.JMenuItem();
		jMenuItemSalir = new javax.swing.JMenuItem();
		jMenuOpciones = new javax.swing.JMenu();
		jMenuItemOpClaves = new javax.swing.JMenuItem();
		jMenuItemGenClaves = new javax.swing.JMenuItem();
		jMenuItemCargarClaves = new javax.swing.JMenuItem();
		jMenuItemGuardarClaves = new javax.swing.JMenuItem();
		jMenuItemVerClaves = new javax.swing.JMenuItem();
		jMenuAyuda = new javax.swing.JMenu();
		jMenuItemAcercaDe = new javax.swing.JMenuItem();

		jFileChooser.setCurrentDirectory(new java.io.File("."));

		jDialogSecret.setModal(true);
		jDialogSecret
				.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
		jDialogSecret
				.setModalityType(java.awt.Dialog.ModalityType.DOCUMENT_MODAL);
		jDialogSecret.setResizable(false);

		jLabelPassDialog.setText("Secreto");

		jLabelPassRepDialog.setText("Repetir secreto");

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

		javax.swing.GroupLayout jDialogSecretLayout = new javax.swing.GroupLayout(
				jDialogSecret.getContentPane());
		jDialogSecret.getContentPane().setLayout(jDialogSecretLayout);
		jDialogSecretLayout
				.setHorizontalGroup(jDialogSecretLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogSecretLayout.createSequentialGroup()
										.addGap(176, 176, 176)
										.addComponent(jButtonOKPassDialog)
										.addGap(18, 18, 18)
										.addComponent(jButtonResetPassDialog)
										.addContainerGap(125, Short.MAX_VALUE))
						.addGroup(
								jDialogSecretLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogSecretLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelPassRepDialog)
														.addComponent(
																jLabelPassDialog))
										.addGap(31, 31, 31)
										.addGroup(
												jDialogSecretLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPasswordFieldRepPassDialog)
														.addComponent(
																jPasswordFieldPassDialog))
										.addContainerGap()));
		jDialogSecretLayout
				.setVerticalGroup(jDialogSecretLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogSecretLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogSecretLayout
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
												jDialogSecretLayout
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
												jDialogSecretLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonOKPassDialog)
														.addComponent(
																jButtonResetPassDialog))
										.addContainerGap()));

		jLabelAlgoritmos.setText("Algoritmo de cifrado");
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

		jComboBoxCifrado.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "RSA" }));

		jComboBoxFirma.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "SHA1withRSA", "MD2withRSA", "MD5withRSA" }));

		jLabelAlgoritmos1.setText("Algoritmo de firma");
		jLabelAlgoritmos1.setToolTipText("");

		jLabelAlgoritmos2.setText("Fichero de claves");
		jLabelAlgoritmos2.setToolTipText("");

		jLabelAlgoritmos3.setText("Contraseña");
		jLabelAlgoritmos3.setToolTipText("");

		jPasswordFieldClaves.setText("jPasswordField1");

		jTextFieldClaves.setText("claves.key");
	
		javax.swing.GroupLayout jDialogAlgLayout = new javax.swing.GroupLayout(
				jDialogAlg.getContentPane());
		jDialogAlg.getContentPane().setLayout(jDialogAlgLayout);
		jDialogAlgLayout
				.setHorizontalGroup(jDialogAlgLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogAlgLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jDialogAlgLayout
																		.createSequentialGroup()
																		.addComponent(
																				jButtonAlgoritmosAceptar)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				jButtonAlgoritmosCancelar))
														.addGroup(
																jDialogAlgLayout
																		.createSequentialGroup()
																		.addGroup(
																				jDialogAlgLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jLabelAlgoritmos3)
																						.addComponent(
																								jLabelAlgoritmos2)
																						.addComponent(
																								jLabelAlgoritmos)
																						.addComponent(
																								jLabelAlgoritmos1))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																				40,
																				Short.MAX_VALUE)
																		.addGroup(
																				jDialogAlgLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								jComboBoxFirma,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jComboBoxCifrado,
																								0,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								jPasswordFieldClaves)
																						.addComponent(
																								jTextFieldClaves))))
										.addContainerGap()));
		jDialogAlgLayout
				.setVerticalGroup(jDialogAlgLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogAlgLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAlgoritmos)
														.addComponent(
																jComboBoxCifrado,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(3, 3, 3)
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAlgoritmos1)
														.addComponent(
																jComboBoxFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAlgoritmos2)
														.addComponent(
																jTextFieldClaves,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAlgoritmos3)
														.addComponent(
																jPasswordFieldClaves,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												10, Short.MAX_VALUE)
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonAlgoritmosAceptar)
														.addComponent(
																jButtonAlgoritmosCancelar))
										.addContainerGap()));

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

		jLabelAutor1.setText("Antonio Manuel Mateos Cruz");

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
																								jLabelAsignatura)
																						.addGroup(
																								jDialogAcercaDeLayout
																										.createSequentialGroup()
																										.addGap(10,
																												10,
																												10)
																										.addComponent(
																												jLabelAutor1))))
														.addGroup(
																jDialogAcercaDeLayout
																		.createSequentialGroup()
																		.addGap(120,
																				120,
																				120)
																		.addComponent(
																				jButtonSalirAcercaDe)))
										.addContainerGap(63, Short.MAX_VALUE)));
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
												jLabelAutor1,
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
										.addGap(49, 49, 49)));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Seguridad en Redes Telemáticas - Cifrado y firma con clave pública/privada");
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

		jMenuItemFirmar.setText("Firmar");
		jMenuItemFirmar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemFirmarActionPerformed(evt);
			}
		});
		jMenuFichero.add(jMenuItemFirmar);

		jMenuItemVerFirma.setText("Verificar firma");
		jMenuItemVerFirma
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemVerFirmaActionPerformed(evt);
					}
				});
		jMenuFichero.add(jMenuItemVerFirma);

		jMenuItemSalir.setText("Salir");
		jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemSalirActionPerformed(evt);
			}
		});
		jMenuFichero.add(jMenuItemSalir);

		jMenuBar.add(jMenuFichero);

		jMenuOpciones.setText("Opciones");

		jMenuItemOpClaves.setText("Opciones de claves");
		jMenuItemOpClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemOpClavesActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemOpClaves);

		jMenuItemGenClaves.setText("Generar nuevas claves");
		jMenuItemGenClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemGenClavesActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemGenClaves);

		jMenuItemCargarClaves.setText("Cargar claves desde fichero");
		jMenuItemCargarClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemCargarClavesActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemCargarClaves);

		jMenuItemGuardarClaves.setText("Guardar claves en fichero");
		jMenuItemGuardarClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemGuardarClavesActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemGuardarClaves);

		jMenuItemVerClaves.setText("Ver claves actuales");
		jMenuItemVerClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemVerClavesActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemVerClaves);

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

	private void jMenuItemOpClavesActionPerformed(java.awt.event.ActionEvent evt) {

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

		if (archivo != null) {
			cifrar();
		}
	}

	private void jMenuItemDescifrarActionPerformed(
			java.awt.event.ActionEvent evt) {
		archivo = getFile();

		if (archivo != null) {
			if (archivo.getName().endsWith(CIF_EXT)) {
				descifrar();
			} else {
				showInfo("El fichero no está cifrado");
			}
		}
	}

	private void jMenuItemGenClavesActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			showInfo("Obteniendo instancia");
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			showInfo("Iniciando el generador");
			kpg.initialize(512);
			showInfo("Generando claves");
			keyPair = kpg.generateKeyPair();
			showInfo("Claves generadas");
		} catch (Exception ex) {
			showInfo("Error al generar claves");
		}

	}

	private void jButtonOKPassDialogActionPerformed(
			java.awt.event.ActionEvent evt) {
		String pass = String.valueOf(jPasswordFieldPassDialog.getPassword());
		String passRep = String.valueOf(jPasswordFieldRepPassDialog
				.getPassword());

		if (pass.compareTo(passRep) == 0 && pass.compareTo("") != 0
				&& passRep.compareTo("") != 0) {
			password = pass;
			jDialogSecret.setVisible(false);
			showInfo("Contraseña introducida correctamente para el fichero: "
					+ archivo.getName());
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

		cifrado = (String) jComboBoxCifrado.getSelectedItem();
		firma = (String) jComboBoxFirma.getSelectedItem();
		claves = (String) jTextFieldClaves.getText();
		showInfo("Algoritmo de cifrado seleccionado: " + cifrado);
		showInfo("Algoritmo fima seleccionado: " + firma);
		showInfo("Fichero de claves seleccionado: " + claves);
		jDialogAlg.setVisible(false);

	}

	private void jButtonSalirAcercaDeActionPerformed(
			java.awt.event.ActionEvent evt) {

		jDialogAcercaDe.setVisible(false);
	}

	private void jMenuItemFirmarActionPerformed(java.awt.event.ActionEvent evt) {
		archivo = getFile();
		if (archivo != null) {
			firmar();
		}
	}

	private void jMenuItemVerFirmaActionPerformed(java.awt.event.ActionEvent evt) {

		archivo = getFile();

		if (archivo != null) {
			if (archivo.getName().endsWith(FIRM_EXT)) {
				verificar();
			} else {
				showInfo("El fichero no está firmado");
			}
		}
	}

	private void jMenuItemGuardarClavesActionPerformed(
			java.awt.event.ActionEvent evt) {
		try {
			FileOutputStream fos = new FileOutputStream(claves);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(keyPair);
			oos.close();
			fos.close();
			showInfo("Fichero de claves guardado");
		} catch (Exception ex) {
			showInfo("Error al guardar el archivo de claves");
		}
	}

	private boolean cargarClaves() {
		boolean exito = false;
		try {
			FileInputStream fis = new FileInputStream(claves);
			ObjectInputStream ois = new ObjectInputStream(fis);
			keyPair = (KeyPair) ois.readObject();
			ois.close();
			fis.close();
			showInfo("Fichero de claves cargado");
			exito = true;
		} catch (Exception ex) {
			showInfo("Error al cargar el archivo de claves");
		}

		return exito;
	}

	private void jMenuItemCargarClavesActionPerformed(
			java.awt.event.ActionEvent evt) {
		cargarClaves();
	}

	private void jMenuItemVerClavesActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (keyPair == null) {
			showInfo("No hay claves para mostrar");
		} else {
			showInfo("Clave pública: " + keyPair.getPublic().toString());
			showInfo("Clave privada: " + keyPair.getPrivate().toString());
		}
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
		jDialogSecret.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jDialogSecret.setSize(500, 150);
		jDialogSecret.setLocationRelativeTo(this);
		jDialogSecret.setResizable(false);
		jDialogSecret.setVisible(true);

	}

	private void algorithmDialog() {
		jDialogAlg.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jDialogAlg.setSize(300, 200);
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

	private void firmar() {
		showInfo("Proceso de firma de " + archivo.getName() + " con " + firma);
		try {
			FileInputStream input = new FileInputStream(archivo);
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + FIRM_EXT);
			Signature dsa = Signature.getInstance(firma);

			if (keyPair != null) {
				dsa.initSign(keyPair.getPrivate());
				int i, j = 0;
				while ((i = input.read()) != -1) {
					dsa.update((byte) i);
					j++;
				}

				byte[] sig = dsa.sign();
				showInfo("Bytes leídos: " + j);
				showInfo("Firma: " + Arrays.toString(sig));
				input.close();

				HashFileHeader hashHeader = new HashFileHeader(firma, sig);
				hashHeader.save(output);

				input = new FileInputStream(archivo);
				while ((i = input.read()) != -1) {
					output.write(i);
				}
				showInfo("Firmado completado");
			} else {
				if (cargarClaves()) {
					firmar();
				} else {
					showInfo("Las claves no se encuentran generadas");
				}
			}

		} catch (Exception ex) {
			showInfo("Error al firmar el fichero");
		}
	}

	private void verificar() {

		try {
			HashFileHeader hashHeader = new HashFileHeader();
			FileInputStream input = new FileInputStream(archivo);
			hashHeader.load(input);
			showInfo("Proceso de verificación de firma de " + archivo.getName()
					+ " con " + hashHeader.getAlgorithm());
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + VERIF);

			if (keyPair != null) {
				Signature dsa = Signature
						.getInstance(hashHeader.getAlgorithm());
				dsa.initVerify(keyPair.getPublic());
				int i, j = 0;
				while ((i = input.read()) != -1) {
					j++;
					dsa.update((byte) i);
					output.write(i);
				}
				showInfo("Bytes leídos: " + Integer.toString(j));

				if (dsa.verify(hashHeader.getHash())) {
					showInfo("Firma correcta");

				} else {
					showInfo("Firma incorrecta");
				}

				input.close();
				output.close();
			} else {
				if (cargarClaves()) {
					verificar();
				} else {
					showInfo("Las claves no se encuentran generadas");
				}
			}

			showInfo("Verificación completada");
		} catch (Exception ex) {
			showInfo("Error al verificar la firma");
		}
	}

	private void cifrar() {
		try {
			if (keyPair != null) {
				showInfo("Cifrando " + archivo.getName());
				FileOutputStream output = new FileOutputStream(archivo
						.getName().replaceAll("\\.\\w+", "") + CIF_EXT);
				FileInputStream input = new FileInputStream(archivo);
				Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				c.init(c.ENCRYPT_MODE, keyPair.getPublic());
				byte[] block = new byte[53];
				int i = 0;
				while ((i = input.read(block)) != -1) {
					output.write(c.doFinal(block,0,i));
				}

				output.flush();
				input.close();
				output.close();
				showInfo("Cifrado completado");

			} else {
				if (cargarClaves()) {
					cifrar();
				} else {
					showInfo("Las claves no se encuentran generadas");
				}
			}

		} catch (Exception ex) {
			showInfo("Error al cifrar el fichero");
		}
	}

	private void descifrar() {
		try {
			if (keyPair != null) {
				showInfo("Descifrando " + archivo.getName());
				FileOutputStream output = new FileOutputStream(archivo
						.getName().replaceAll("\\.\\w+", "") + DESCIF);
				FileInputStream input = new FileInputStream(archivo);
				Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				c.init(c.DECRYPT_MODE, keyPair.getPrivate());
				byte[] block = new byte[64];
				int i = 0;
				while ((i = input.read(block)) != -1) {
					output.write(c.doFinal(block,0,i));
				}
				
				output.flush();
				output.close();
				input.close();
				showInfo("Descifrado completado");

			} else {
				if (cargarClaves()) {
					descifrar();
				} else {
					showInfo("Las claves no se encuentran generadas");
				}
			}

		} catch (Exception ex) {
			showInfo("Error al descifrar el fichero");
		}

	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
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
	private javax.swing.JComboBox jComboBoxCifrado;
	private javax.swing.JComboBox jComboBoxFirma;
	private javax.swing.JDialog jDialogAcercaDe;
	private javax.swing.JDialog jDialogAlg;
	private javax.swing.JDialog jDialogSecret;
	private javax.swing.JFileChooser jFileChooser;
	private javax.swing.JLabel jLabelAlgoritmos;
	private javax.swing.JLabel jLabelAlgoritmos1;
	private javax.swing.JLabel jLabelAlgoritmos2;
	private javax.swing.JLabel jLabelAlgoritmos3;
	private javax.swing.JLabel jLabelAsignatura;
	private javax.swing.JLabel jLabelAutor;
	private javax.swing.JLabel jLabelAutor1;
	private javax.swing.JLabel jLabelCurso;
	private javax.swing.JLabel jLabelPassDialog;
	private javax.swing.JLabel jLabelPassRepDialog;
	private javax.swing.JMenu jMenuAyuda;
	private javax.swing.JMenuBar jMenuBar;
	private javax.swing.JMenu jMenuFichero;
	private javax.swing.JMenuItem jMenuItemAcercaDe;
	private javax.swing.JMenuItem jMenuItemCargarClaves;
	private javax.swing.JMenuItem jMenuItemCifrar;
	private javax.swing.JMenuItem jMenuItemDescifrar;
	private javax.swing.JMenuItem jMenuItemFirmar;
	private javax.swing.JMenuItem jMenuItemGenClaves;
	private javax.swing.JMenuItem jMenuItemGuardarClaves;
	private javax.swing.JMenuItem jMenuItemOpClaves;
	private javax.swing.JMenuItem jMenuItemSalir;
	private javax.swing.JMenuItem jMenuItemVerClaves;
	private javax.swing.JMenuItem jMenuItemVerFirma;
	private javax.swing.JMenu jMenuOpciones;
	private javax.swing.JPasswordField jPasswordFieldClaves;
	private javax.swing.JPasswordField jPasswordFieldPassDialog;
	private javax.swing.JPasswordField jPasswordFieldRepPassDialog;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaInformacion;
	private javax.swing.JTextField jTextFieldClaves;
}
