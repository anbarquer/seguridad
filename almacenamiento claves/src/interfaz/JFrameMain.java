// Seguridad en Redes Telemáticas - Práctica 5
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
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Arrays;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.swing.JFileChooser;

public class JFrameMain extends javax.swing.JFrame {

	private String password = "";
	private File archivo = null;
	private String cifrado = "RSA";
	private String firma = "SHA1withRSA";
	private String claves = "claves.key";
	private String almacen = "ksprac5";
	private KeyPair keyPair = null;
	private KeyStore keyStore = null;
	private String ksPass = "123456";
	private PublicKey publicKey = null;
	private PrivateKey privateKey = null;

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
		jButtonAlgoritmosCancelar = new javax.swing.JButton();
		jButtonAlgoritmosAceptar = new javax.swing.JButton();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jLabelAlgoritmos = new javax.swing.JLabel();
		jLabelAlgoritmos1 = new javax.swing.JLabel();
		jComboBoxCifrado = new javax.swing.JComboBox();
		jComboBoxFirma = new javax.swing.JComboBox();
		jPanel2 = new javax.swing.JPanel();
		jLabelAlgoritmos2 = new javax.swing.JLabel();
		jTextFieldClaves = new javax.swing.JTextField();
		jLabelAlgoritmos3 = new javax.swing.JLabel();
		jPasswordFieldClaves = new javax.swing.JPasswordField();
		jPanel3 = new javax.swing.JPanel();
		jTextFieldAlm = new javax.swing.JTextField();
		jPasswordFieldAlmClaves = new javax.swing.JPasswordField();
		jLabelAlmacen = new javax.swing.JLabel();
		jLabelPassAlm = new javax.swing.JLabel();
		jButtonAlmExaminar = new javax.swing.JButton();
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
		jMenuClaves = new javax.swing.JMenu();
		jMenuItemGenClaves = new javax.swing.JMenuItem();
		jMenuItemCargarClaves = new javax.swing.JMenuItem();
		jMenuItemGuardarClaves = new javax.swing.JMenuItem();
		jMenuItemVerClaves = new javax.swing.JMenuItem();
		jMenuAlmacen = new javax.swing.JMenu();
		jMenuItemImportAlm = new javax.swing.JMenuItem();
		jMenuItemVerAlm = new javax.swing.JMenuItem();
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

		jLabelAlgoritmos.setText("Algoritmo de cifrado");
		jLabelAlgoritmos.setToolTipText("");

		jLabelAlgoritmos1.setText("Algoritmo de firma");
		jLabelAlgoritmos1.setToolTipText("");

		jComboBoxCifrado.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "RSA" }));

		jComboBoxFirma.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "SHA1withRSA", "MD2withRSA", "MD5withRSA" }));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelAlgoritmos)
														.addComponent(
																jLabelAlgoritmos1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												126, Short.MAX_VALUE)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jComboBoxCifrado,
																0,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jComboBoxFirma,
																0, 212,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAlgoritmos)
														.addComponent(
																jComboBoxCifrado,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(7, 7, 7)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jComboBoxFirma,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelAlgoritmos1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																12,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(14, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Algoritmos", jPanel1);

		jLabelAlgoritmos2.setText("Fichero de claves");
		jLabelAlgoritmos2.setToolTipText("");
		jTextFieldClaves.setText("claves.key");
		jLabelAlgoritmos3.setText("Contraseña");
		jLabelAlgoritmos3.setToolTipText("");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelAlgoritmos2)
														.addComponent(
																jLabelAlgoritmos3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												139, Short.MAX_VALUE)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jPasswordFieldClaves)
														.addComponent(
																jTextFieldClaves,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																212,
																Short.MAX_VALUE))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel2Layout
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
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelAlgoritmos3)
														.addComponent(
																jPasswordFieldClaves,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(15, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Fichero de claves", jPanel2);

		jTextFieldAlm.setText("ksprac5");
		jPasswordFieldAlmClaves.setText("123456");
		jLabelAlmacen.setText("Almacén de claves");
		jLabelAlmacen.setToolTipText("");

		jLabelPassAlm.setText("Contraseña");
		jLabelPassAlm.setToolTipText("");

		jButtonAlmExaminar.setText("Examinar");
		jButtonAlmExaminar
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jButtonAlmExaminarActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabelAlmacen)
														.addComponent(
																jLabelPassAlm))
										.addGap(139, 139, 139)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jPasswordFieldAlmClaves)
														.addGroup(
																jPanel3Layout
																		.createSequentialGroup()
																		.addComponent(
																				jTextFieldAlm,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				120,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jButtonAlmExaminar)))
										.addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jTextFieldAlm,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jLabelAlmacen)
														.addComponent(
																jButtonAlmExaminar))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jLabelPassAlm)
														.addComponent(
																jPasswordFieldAlmClaves,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(12, Short.MAX_VALUE)));

		jTabbedPane1.addTab("KeyStore", jPanel3);

		javax.swing.GroupLayout jDialogAlgLayout = new javax.swing.GroupLayout(
				jDialogAlg.getContentPane());
		jDialogAlg.getContentPane().setLayout(jDialogAlgLayout);
		jDialogAlgLayout.setHorizontalGroup(jDialogAlgLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						jDialogAlgLayout
								.createSequentialGroup()
								.addGap(142, 142, 142)
								.addComponent(jButtonAlgoritmosAceptar)
								.addGap(18, 18, 18)
								.addComponent(jButtonAlgoritmosCancelar)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
				.addComponent(jTabbedPane1));
		jDialogAlgLayout
				.setVerticalGroup(jDialogAlgLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jDialogAlgLayout
										.createSequentialGroup()
										.addComponent(
												jTabbedPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												100,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												jDialogAlgLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																jButtonAlgoritmosCancelar)
														.addComponent(
																jButtonAlgoritmosAceptar))
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
		setTitle("Seguridad en Redes Telemáticas - Almacén de claves");
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

		jMenuClaves.setText("Claves");

		jMenuItemGenClaves.setText("Generar nuevas claves");
		jMenuItemGenClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemGenClavesActionPerformed(evt);
					}
				});
		jMenuClaves.add(jMenuItemGenClaves);

		jMenuItemCargarClaves.setText("Cargar claves desde fichero");
		jMenuItemCargarClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemCargarClavesActionPerformed(evt);
					}
				});
		jMenuClaves.add(jMenuItemCargarClaves);

		jMenuItemGuardarClaves.setText("Guardar claves en fichero");
		jMenuItemGuardarClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemGuardarClavesActionPerformed(evt);
					}
				});
		jMenuClaves.add(jMenuItemGuardarClaves);

		jMenuItemVerClaves.setText("Ver claves actuales");
		jMenuItemVerClaves
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemVerClavesActionPerformed(evt);
					}
				});
		jMenuClaves.add(jMenuItemVerClaves);

		jMenuOpciones.add(jMenuClaves);

		jMenuAlmacen.setText("Almacén de claves");

		jMenuItemImportAlm.setText("Importar claves desde almacén");
		jMenuItemImportAlm
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jMenuItemImportAlmActionPerformed(evt);
					}
				});
		jMenuAlmacen.add(jMenuItemImportAlm);

		jMenuItemVerAlm.setText("Ver el contenido del almacén de claves");
		jMenuItemVerAlm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItemVerAlmActionPerformed(evt);
			}
		});
		jMenuAlmacen.add(jMenuItemVerAlm);

		jMenuOpciones.add(jMenuAlmacen);

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
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
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

	private void cargarAlmacen() {
		try {
			ksPass = String.valueOf(jPasswordFieldAlmClaves.getPassword());
			keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream(almacen), ksPass.toCharArray());
			showInfo("Almacén de claves cargado");
		} catch (Exception ex) {
			showInfo("Error al cargar el almacén de claves");
		}
	}

	private void jButtonAlgoritmosAceptarActionPerformed(
			java.awt.event.ActionEvent evt) {

		cifrado = (String) jComboBoxCifrado.getSelectedItem();
		firma = (String) jComboBoxFirma.getSelectedItem();
		claves = (String) jTextFieldClaves.getText();
		showInfo("Algoritmo de cifrado seleccionado: " + cifrado);
		showInfo("Algoritmo fima seleccionado: " + firma);
		showInfo("Fichero de claves seleccionado: " + claves);
		if (archivo != null) {
			try {
				showInfo("Almacén de claves: " + almacen);
				cargarAlmacen();
			} catch (Exception ex) {
				showInfo("Error al cargar el almacén de claves");
			}

		} else {
			showInfo("No se ha seleccionado un almacén de claves");
		}

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
			publicKey = keyPair.getPublic();
			privateKey = keyPair.getPrivate();
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
		if (publicKey == null && privateKey == null) {
			showInfo("No hay claves para mostrar");
		} else {
			showInfo("Clave pública: " + publicKey.toString());
			showInfo("Clave privada: " + privateKey.toString());
		}
	}

	private void jMenuItemVerAlmActionPerformed(java.awt.event.ActionEvent evt) {
		if (keyStore != null) {
			Enumeration e;
			try {
				e = keyStore.aliases();
				while (e.hasMoreElements()) {
					String alias = (String) e.nextElement();
					if (keyStore.isKeyEntry(alias)) {
						privateKey = (PrivateKey) keyStore.getKey(alias,
								ksPass.toCharArray());
						keyStore.getKey(alias, ksPass.toCharArray());
						java.security.cert.Certificate cert = keyStore
								.getCertificate(alias);
						publicKey = cert.getPublicKey();
						showInfo("Clave pública: " + privateKey.toString());
						showInfo("Clave privada: " + publicKey.toString());
						showInfo("Certificado: " + cert.toString());
					}

				}
			} catch (Exception ex) {
				showInfo("Error al mostrar el almacén de claves");
			}

		} else {
			showInfo("No existe un almacén de claves cargado");
		}
	}

	private void jButtonAlmExaminarActionPerformed(
			java.awt.event.ActionEvent evt) {
		archivo = getFile();
		if (archivo != null) {
			jTextFieldAlm.setText(archivo.getName());
			almacen = archivo.getName();
		}
	}

	private void jMenuItemImportAlmActionPerformed(
			java.awt.event.ActionEvent evt) {
		if (almacen == null) {
			showInfo("Error al cargar el fichero de almacén de claves");
		} else {
			cargarAlmacen();
			Enumeration e;
			try {
				e = keyStore.aliases();
				while (e.hasMoreElements()) {
					String alias = (String) e.nextElement();
					if (keyStore.isKeyEntry(alias)) {
						privateKey = (PrivateKey) keyStore.getKey(alias,
								ksPass.toCharArray());
						keyStore.getKey(alias, ksPass.toCharArray());
						java.security.cert.Certificate cert = keyStore
								.getCertificate(alias);
						publicKey = cert.getPublicKey();
					}
				}
				showInfo("Claves de almacén importadas");
			} catch (Exception ex) {
				showInfo("Error al mostrar el almacén de claves");
			}
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
		jDialogAlg.setSize(459, 200);

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

			if (privateKey != null) {
				dsa.initSign(privateKey);
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

			if (publicKey != null) {
				Signature dsa = Signature
						.getInstance(hashHeader.getAlgorithm());
				dsa.initVerify(publicKey);
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
			if (publicKey != null) {
				showInfo("Cifrando " + archivo.getName());
				FileOutputStream output = new FileOutputStream(archivo
						.getName().replaceAll("\\.\\w+", "") + CIF_EXT);
				FileInputStream input = new FileInputStream(archivo);
				Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				c.init(c.ENCRYPT_MODE, publicKey);
				byte[] block = new byte[53];
				int i = 0;
				while ((i = input.read(block)) != -1) {
					output.write(c.doFinal(block, 0, i));
				}

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
			if (privateKey != null) {
				showInfo("Descifrando " + archivo.getName());
				FileOutputStream output = new FileOutputStream(archivo
						.getName().replaceAll("\\.\\w+", "") + DESCIF);
				FileInputStream input = new FileInputStream(archivo);
				Cipher c = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				c.init(c.DECRYPT_MODE, privateKey);
				byte[] block = new byte[64];
				int i = 0;
				while ((i = input.read(block)) != -1) {
					output.write(c.doFinal(block, 0, i));
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
	private javax.swing.JButton jButtonAlmExaminar;
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
	private javax.swing.JLabel jLabelAlmacen;
	private javax.swing.JLabel jLabelAsignatura;
	private javax.swing.JLabel jLabelAutor;
	private javax.swing.JLabel jLabelAutor1;
	private javax.swing.JLabel jLabelCurso;
	private javax.swing.JLabel jLabelPassAlm;
	private javax.swing.JLabel jLabelPassDialog;
	private javax.swing.JLabel jLabelPassRepDialog;
	private javax.swing.JMenu jMenuAlmacen;
	private javax.swing.JMenu jMenuAyuda;
	private javax.swing.JMenuBar jMenuBar;
	private javax.swing.JMenu jMenuClaves;
	private javax.swing.JMenu jMenuFichero;
	private javax.swing.JMenuItem jMenuItemAcercaDe;
	private javax.swing.JMenuItem jMenuItemCargarClaves;
	private javax.swing.JMenuItem jMenuItemCifrar;
	private javax.swing.JMenuItem jMenuItemDescifrar;
	private javax.swing.JMenuItem jMenuItemFirmar;
	private javax.swing.JMenuItem jMenuItemGenClaves;
	private javax.swing.JMenuItem jMenuItemGuardarClaves;
	private javax.swing.JMenuItem jMenuItemImportAlm;
	private javax.swing.JMenuItem jMenuItemOpClaves;
	private javax.swing.JMenuItem jMenuItemSalir;
	private javax.swing.JMenuItem jMenuItemVerAlm;
	private javax.swing.JMenuItem jMenuItemVerClaves;
	private javax.swing.JMenuItem jMenuItemVerFirma;
	private javax.swing.JMenu jMenuOpciones;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPasswordField jPasswordFieldAlmClaves;
	private javax.swing.JPasswordField jPasswordFieldClaves;
	private javax.swing.JPasswordField jPasswordFieldPassDialog;
	private javax.swing.JPasswordField jPasswordFieldRepPassDialog;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextArea jTextAreaInformacion;
	private javax.swing.JTextField jTextFieldAlm;
	private javax.swing.JTextField jTextFieldClaves;
}
