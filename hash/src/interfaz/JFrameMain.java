// Seguridad en Redes Telemáticas - Práctica 3
// Antonio Diego Barquero Cuadrado
// Antonio Manuel Mateos Cruz

package interfaz;

import cipher.HashFileHeader;
import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.Security;
import java.util.Arrays;
import java.util.Set;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JFileChooser;

public class JFrameMain extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private String password = "";
	private File archivo = null;
	private String algoritmo = "MD2";
	private static String HASH_EXT = ".hsh";
	private static String VERIF = ".ver";
	private static byte[] salt = new byte[] { 0x7d, 0x60, 0x43, 0x5f, 0x02,
			(byte) 0xe9, (byte) 0xe0, (byte) 0xae };

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
		jComboBoxAlgoritmos = new javax.swing.JComboBox();
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
		jMenuItemHash = new javax.swing.JMenuItem();
		jMenuItemVerifHash = new javax.swing.JMenuItem();
		jMenuItemSalir = new javax.swing.JMenuItem();
		jMenuOpciones = new javax.swing.JMenu();
		jMenuItemOpcionesVerif = new javax.swing.JMenuItem();
		jMenuItemVerInfo = new javax.swing.JMenuItem();
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
				new String[] { "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384",
						"SHA-512", "HmacMD5", "HmacSHA1", "HmacSHA256",
						"HmacSHA384", "HmacSHA512" }));

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
		setTitle("Seguridad en Redes Telemáticas - Verificador");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		setResizable(false);

		jTextAreaInformacion.setEditable(false);
		jTextAreaInformacion.setColumns(20);
		jTextAreaInformacion.setLineWrap(true);
		jTextAreaInformacion.setRows(5);
		jScrollPane1.setViewportView(jTextAreaInformacion);

		jMenuFichero.setText("Fichero");

		jMenuItemHash.setText("Añadir hash");
		jMenuItemHash.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemHashActionPerformed(evt);
			}
		});
		jMenuFichero.add(jMenuItemHash);

		jMenuItemVerifHash.setText("Verificar hash");
		jMenuItemVerifHash
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemVerifHashActionPerformed(evt);
					}
				});
		jMenuFichero.add(jMenuItemVerifHash);

		jMenuItemSalir.setText("Salir");
		jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemSalirActionPerformed(evt);
			}
		});
		jMenuFichero.add(jMenuItemSalir);

		jMenuBar.add(jMenuFichero);

		jMenuOpciones.setText("Opciones");

		jMenuItemOpcionesVerif.setText("Opciones de verificado");
		jMenuItemOpcionesVerif
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemOpcionesVerifActionPerformed(evt);
					}
				});
		jMenuOpciones.add(jMenuItemOpcionesVerif);

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

	private void jMenuItemOpcionesVerifActionPerformed(
			java.awt.event.ActionEvent evt) {

		algorithmDialog();
	}

	private void jMenuItemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {
		aboutDialog();
	}

	private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	private void jMenuItemHashActionPerformed(java.awt.event.ActionEvent evt) {
		archivo = getFile();

		if (archivo != null) {
			passDialog();
			checkHash();
		}
	}

	private void jMenuItemVerifHashActionPerformed(
			java.awt.event.ActionEvent evt) {
		archivo = getFile();

		if (archivo != null) {
			if (archivo.getName().endsWith(HASH_EXT)) {
				passDialog();
				checkVerif();
			} else {
				showInfo("El fichero no está hasheado");
			}
		}
	}

	private void jMenuItemVerInfoActionPerformed(java.awt.event.ActionEvent evt) {
		Set<String> listadoAlgoritmos = Security.getAlgorithms("MessageDigest");
		String mensaje = "Listado de algoritmos de resumen disponibles\n";
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
		jDialogSecret.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jDialogSecret.setSize(500, 150);
		jDialogSecret.setLocationRelativeTo(this);
		jDialogSecret.setResizable(false);
		jDialogSecret.setVisible(true);

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
		jDialogAcercaDe.setSize(300, 155);
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

	private void checkVerif() {
		HashFileHeader hashHeader = new HashFileHeader();
		FileInputStream input = null;
		try {
			input = new FileInputStream(archivo);
			hashHeader.load(input);
		} catch (FileNotFoundException ex) {
			showInfo("No se encontró el fichero");
		}

		if (HashFileHeader.isHashAlgorithm(hashHeader.getAlgorithm())) {
			hashVerif(input, hashHeader);
		} else {
			HMacVerif(input, hashHeader);
		}

	}

	private void checkHash() {
		if (HashFileHeader.isHashAlgorithm(algoritmo)) {
			doHash();
		} else {
			doHMac();
		}
	}

	private void hashVerif(FileInputStream input, HashFileHeader hashHeader) {

		try {
			showInfo("Verificando " + archivo.getName() + " con "
					+ hashHeader.getAlgorithm());
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + VERIF);

			MessageDigest md = MessageDigest.getInstance(hashHeader
					.getAlgorithm());
			md.update(password.getBytes());
			DigestInputStream dis = new DigestInputStream(input, md);
			int i, j = 0;
			while ((i = dis.read()) != -1) {
				output.write(i);
				j++;
			}
			showInfo("Bytes leídos: " + Integer.toString(j));
			md = dis.getMessageDigest();
			byte[] resumen = md.digest();

			dis.close();

			if (Arrays.equals(resumen, hashHeader.getHash())) {
				showInfo("Digest calculado: " + Arrays.toString(resumen));
				showInfo("Digest almacenado: "
						+ Arrays.toString(hashHeader.getHash()));
				showInfo("Correcto");
			} else {
				showInfo("El fichero ha sido modificado o la contraseña es incorrecta");
			}

			input.close();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
			showInfo("Ha ocurrido un problema al verificar el fichero");
		}
	}

	private void doHash() {
		try {
			showInfo("Hasheando " + archivo.getName() + " con algoritmo "
					+ algoritmo);
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + HASH_EXT);
			FileInputStream input = new FileInputStream(archivo);
			MessageDigest md = MessageDigest.getInstance(algoritmo);
			md.update(password.getBytes());
			DigestInputStream dis = new DigestInputStream(input, md);
			int i, j = 0;
			while ((i = dis.read()) != -1) {
				j++;
			}
			showInfo("Bytes leídos: " + Integer.toString(j));
			md = dis.getMessageDigest();
			byte[] resumen = md.digest();
			dis.close();

			HashFileHeader hashHeader = new HashFileHeader(algoritmo, resumen);
			hashHeader.save(output);

			input.close();
			input = new FileInputStream(archivo);

			while ((i = input.read()) != -1) {
				output.write(i);
			}

			output.close();
			input.close();
			showInfo("Hasheado completado");
		} catch (Exception ex) {
			showInfo("Ha ocurrido un problema al hashear el fichero");
		}
	}

	private void doHMac() {
		try {
			showInfo("Hasheando " + archivo.getName() + " con algoritmo "
					+ algoritmo);
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + HASH_EXT);
			FileInputStream input = new FileInputStream(archivo);
			Mac mac = Mac.getInstance(algoritmo);
			SecretKeyFactory skf = SecretKeyFactory
					.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
					1024, mac.getMacLength());
			SecretKey secretKey = skf.generateSecret(spec);
			mac.init(secretKey);

			int i, j = 0;
			while ((i = input.read()) != -1) {
				j++;
				mac.update((byte) i);
			}
			showInfo("Bytes leídos: " + Integer.toString(j));
			byte[] resumen = mac.doFinal();

			HashFileHeader hashHeader = new HashFileHeader(algoritmo, resumen);
			hashHeader.save(output);
			input.close();
			input = new FileInputStream(archivo);

			while ((i = input.read()) != -1) {
				output.write(i);
			}

			output.close();
			input.close();
			showInfo("Hasheado completado");
		} catch (Exception ex) {
			showInfo("Ha ocurrido un problema al hashear el fichero");
		}

	}

	private void HMacVerif(FileInputStream input, HashFileHeader hashHeader) {
		try {
			showInfo("Verificando " + archivo.getName() + " con "
					+ hashHeader.getAlgorithm());
			FileOutputStream output = new FileOutputStream(archivo.getName()
					.replaceAll("\\.\\w+", "") + VERIF);
			Mac mac = Mac.getInstance(algoritmo);
			SecretKeyFactory skf = SecretKeyFactory
					.getInstance("PBKDF2WithHmacSHA1");
			PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt,
					1024, mac.getMacLength());
			SecretKey secretKey = skf.generateSecret(spec);
			mac.init(secretKey);
			int i, j = 0;
			while ((i = input.read()) != -1) {
				j++;
				output.write(i);
				mac.update((byte) i);
			}
			showInfo("Bytes leídos: " + Integer.toString(j));
			byte[] resumen = mac.doFinal();
			if (Arrays.equals(resumen, hashHeader.getHash())) {
				showInfo("Digest calculado: " + Arrays.toString(resumen));
				showInfo("Digest almacenado: "
						+ Arrays.toString(hashHeader.getHash()));
				showInfo("Correcto");
			} else {
				showInfo("El fichero ha sido modificado o la contraseña es incorrecta");
			}

			input.close();
			output.close();

		} catch (Exception ex) {
			showInfo("Ha ocurrido un problema al hashear el fichero");
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
	private javax.swing.JComboBox jComboBoxAlgoritmos;
	private javax.swing.JDialog jDialogAcercaDe;
	private javax.swing.JDialog jDialogAlg;
	private javax.swing.JDialog jDialogSecret;
	private javax.swing.JFileChooser jFileChooser;
	private javax.swing.JLabel jLabelAlgoritmos;
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
	private javax.swing.JMenuItem jMenuItemHash;
	private javax.swing.JMenuItem jMenuItemOpcionesVerif;
	private javax.swing.JMenuItem jMenuItemSalir;
	private javax.swing.JMenuItem jMenuItemVerInfo;
	private javax.swing.JMenuItem jMenuItemVerifHash;
	private javax.swing.JMenu jMenuOpciones;
	private javax.swing.JPasswordField jPasswordFieldPassDialog;
	private javax.swing.JPasswordField jPasswordFieldRepPassDialog;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea jTextAreaInformacion;
}
