package Scripts.Comun;
import resources.Scripts.Comun.GUIHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
//import Logica.Profesor;
//import Logica.Vector;
/**
 * Description   : Functional Test Script
 * @author Sandra
 * Script Name   : <b>GUI</b>
 * @since  2016/07/08
 */
public class GUI extends GUIHelper
{
	public void testMain(Object[] args) 
	{
//		package Presentacion;
//		public class formProfesores extends JFrame implements KeyListener, ActionListener, MouseListener {

		    private JLabel labVista1, labVista2, cedula, nombre, categoriaCombo, categoriaS;
		    private JTextField txtCedula, txtNombre, txtCategoria, txtProvincia;
		    private JComboBox comboCategoria;
		    private JButton bNuevo, bModificar, bEliminar, volver, filtar;
		    private ImageIcon imagenNuevo, imagenModificar, imagenEliminar, imagenVolver;
		    private Box boxCedula, boxNombre, boxCategoriatxt, boxCategoriaCombo;
		    private JPanel panelCedula, panelNombre, panelCategoriatxt, panelCategoriaCombo, panelBotonesIMEC;
		    private JPanel boxG;
		    private Box boxVCamposVentana;
		    private Box boxVContenidoVerticalVentana;
		    private JPanel panelTabla;
		    private JTable table;
		    private DefaultTableModel modelo;
		    private JScrollPane scrollPane;
		    final TableRowSorter<TableModel> sorter;
		    private int cont;
//		    Profesor profesor;
//		    Vector vector;
		    private JMenuBar menuBar;
		    private JMenu menu1;
		    private JMenuItem menuItemNuevo;
		    private JMenuItem menuItemModificar;
		    private JMenuItem menuItemEliminar;
		    private JMenuItem menuItemVolver;

		    public formProfesores() {
		        vector = new Vector();
		        profesor = new Profesor();
		        menuBar = new JMenuBar();
		        menu1 = new JMenu("IMEC");
		        menuBar.add(menu1);
		        menuItemNuevo = new JMenuItem("Nuevo          ", KeyEvent.VK_N);
		        menuItemNuevo.setMnemonic(KeyEvent.VK_S);
		        menuItemNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		        menuItemNuevo.addActionListener(this);
		        menu1.add(menuItemNuevo);
		        menuItemModificar = new JMenuItem("Modificar          ", KeyEvent.VK_M);
		        menuItemModificar.setMnemonic(KeyEvent.VK_M);
		        menuItemModificar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
		        menuItemModificar.addActionListener(this);
		        menu1.add(menuItemModificar);
		        menuItemEliminar = new JMenuItem("Eliminar          ", KeyEvent.VK_E);
		        menuItemEliminar.setMnemonic(KeyEvent.VK_E);
		        menuItemEliminar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		        menuItemEliminar.addActionListener(this);
		        menu1.add(menuItemEliminar);

		        menuItemVolver = new JMenuItem("Volver          ", KeyEvent.VK_V);
		        menuItemVolver.setMnemonic(KeyEvent.VK_V);
		        menuItemVolver.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
		        menuItemVolver.addActionListener(this);
		        menu1.add(menuItemVolver);


		        setJMenuBar(menuBar);


		        labVista1 = new JLabel("Busqueda de Profesores");
		        labVista1.setBorder(BorderFactory.createEmptyBorder(20, 70, 10, 20));

		        labVista2 = new JLabel("IMEC de Profesores");
		        labVista2.setBorder(BorderFactory.createEmptyBorder(20, 70, 10, 20));

		        boxVContenidoVerticalVentana = Box.createVerticalBox();

		        //Cedula
		        boxCedula = Box.createHorizontalBox();
		        cedula = new JLabel("Cedula: ");
		        txtCedula = new JTextField(15);
		        txtCedula.setToolTipText("Ingrese solo numero sin dejar espacios");
		        txtCedula.addKeyListener(this);
		        txtProvincia = new JTextField(10);
		        txtProvincia.setToolTipText("Residencia del Profesor");
		        txtProvincia.setEnabled(false);
		        boxCedula.add(Box.createHorizontalStrut(10));
		        boxCedula.add(cedula);
		        boxCedula.add(Box.createHorizontalStrut(15));
		        boxCedula.add(txtCedula);
		        boxCedula.add(Box.createHorizontalStrut(75));
		        boxCedula.add(txtProvincia);
		        panelCedula = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        panelCedula.add(boxCedula);

		        //Nombre
		        boxNombre = Box.createHorizontalBox();
		        nombre = new JLabel("Nombre: ");
		        txtNombre = new JTextField(15);
		        txtNombre.setToolTipText("Ingrese solo letras");
		        imagenNuevo = new ImageIcon(getClass().getResource("ingresar.PNG"));
		        bNuevo = new JButton("Nuevo", imagenNuevo);
		        bNuevo.setToolTipText("Ingresar al profesor");
		        bNuevo.addActionListener(this);
		        boxNombre.add(Box.createHorizontalStrut(10));//separacion del borde
		        boxNombre.add(nombre);
		        boxNombre.add(Box.createHorizontalStrut(10));//separacion delJLabel
		        boxNombre.add(txtNombre);
		        boxNombre.add(Box.createHorizontalStrut(80));
		        boxNombre.add(bNuevo);
		        panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        panelNombre.add(boxNombre);

		        //Categoria con combo box
		        boxCategoriaCombo = Box.createHorizontalBox();
		        categoriaCombo = new JLabel("Categoria ");
		        String cat[] = {"Categoria 1  ", "Categoria 2  ", "Categoria 3", "Categoria 4"};
		        filtar = new JButton("Filtrar");
		        filtar.addActionListener(this);
		        comboCategoria = new JComboBox(cat);
		        boxCategoriaCombo.add(Box.createHorizontalStrut(10));//separacion del borde
		        boxCategoriaCombo.add(categoriaCombo);
		        boxCategoriaCombo.add(Box.createHorizontalStrut(10));//separacion delJLabel
		        boxCategoriaCombo.add(comboCategoria);
		        boxCategoriaCombo.add(Box.createHorizontalStrut(150));
		        boxCategoriaCombo.add(filtar);
		        panelCategoriaCombo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        panelCategoriaCombo.add(boxCategoriaCombo);

		        //Categoria con el txt
		        boxCategoriatxt = Box.createHorizontalBox();
		        categoriaS = new JLabel("Categoria ");
		        txtCategoria = new JTextField(15);
		        boxCategoriatxt.add(Box.createHorizontalStrut(10));//separacion del borde
		        boxCategoriatxt.add(categoriaS);
		        boxCategoriatxt.add(Box.createHorizontalStrut(10));//separacion delJLabel
		        boxCategoriatxt.add(txtCategoria);
		        panelCategoriatxt = new JPanel(new FlowLayout(FlowLayout.LEFT));
		        panelCategoriatxt.add(boxCategoriatxt);
		        panelCategoriatxt.setVisible(false);

		        //panel tabla
		        panelTabla = new JPanel();
		        String[] nombreColumnas = {"Cedula", "Nombre", "Residencia", "Categoria"};
		        Object[] fila0 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila1 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila2 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila3 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila4 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila5 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila6 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila7 = {" ", " ", " ", " ", " ", " "};
		        Object[] fila8 = {" ", " ", " ", " ", " ", " "};
		        modelo = new DefaultTableModel(null, nombreColumnas) {

		            public Class getColumnClass(int column) {
		                Class returnValue;
		                if ((column >= 0) && (column < getColumnCount())) {
		                    returnValue = getValueAt(0, column).getClass();
		                } else {
		                    returnValue = Object.class;
		                }
		                return returnValue;
		            }
		        };
		        modelo.addRow(fila0);
		        modelo.addRow(fila1);
		        modelo.addRow(fila2);
		        modelo.addRow(fila3);
		        modelo.addRow(fila4);
		        modelo.addRow(fila5);
		        modelo.addRow(fila6);
		        modelo.addRow(fila7);
		        modelo.addRow(fila8);
		        table = new JTable(modelo);
		        sorter = new TableRowSorter<TableModel>(modelo);
		        table.setRowSorter(sorter);
		        scrollPane = new JScrollPane();
		        scrollPane.setViewportView(table);
		        table.addMouseListener(this);
		        panelTabla.add(scrollPane, BorderLayout.CENTER);



		        //agregacion de los paneles
		        boxVCamposVentana = Box.createVerticalBox();
		        boxVCamposVentana.add(panelCedula);
		        boxVCamposVentana.add(Box.createVerticalStrut(20));//espacio entre uno y otros vertical
		        boxVCamposVentana.add(panelNombre);
		        boxVCamposVentana.add(Box.createVerticalStrut(20));
		        boxVCamposVentana.add(panelCategoriaCombo);
		        boxVCamposVentana.add(Box.createVerticalStrut(20));
		        boxVCamposVentana.add(panelTabla);//****tabla
		        boxVCamposVentana.add(Box.createVerticalStrut(20));


		        boxVContenidoVerticalVentana.add(Box.createVerticalStrut(30));//espacio del borde de arriba
		        boxVContenidoVerticalVentana.add(boxVCamposVentana);


		        boxG = new JPanel();
		        boxG.add(boxVContenidoVerticalVentana);
		        add(boxG, BorderLayout.WEST);
		        add(labVista1, BorderLayout.NORTH);




		        //Botones IMEC
		        panelBotonesIMEC = new JPanel();
		        imagenModificar = new ImageIcon(getClass().getResource("modificar.PNG"));
		        bModificar = new JButton("Modificar", imagenModificar);
		        imagenEliminar = new ImageIcon(getClass().getResource("eliminar.PNG"));
		        bEliminar = new JButton("Eliminar", imagenEliminar);
		        bEliminar.addActionListener(this);
		        imagenVolver = new ImageIcon(getClass().getResource("volver.PNG"));
		        volver = new JButton("Volver", imagenVolver);
		        panelBotonesIMEC.add(bModificar);
		        bModificar.addActionListener(this);
		        panelBotonesIMEC.add(bEliminar);
		        panelBotonesIMEC.add(volver);
		        volver.addActionListener(this);
		        panelBotonesIMEC.setLayout(new FlowLayout());
		        add(panelBotonesIMEC, BorderLayout.SOUTH);
		        panelBotonesIMEC.setVisible(false);


		        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    }

		    public void keyTyped(KeyEvent e) {
		    }

		    public void keyReleased(KeyEvent e) {
		    }

		    public void keyPressed(KeyEvent e) {
		        if (cont < 1) {
		            if (e.getSource() == txtCedula) {
		                if (e.getKeyCode() == KeyEvent.VK_1) {
		                    txtProvincia.setText("San Jose");
		                    cont++;
		                }
		                if (e.getKeyCode() == KeyEvent.VK_2) {
		                    txtProvincia.setText("Alajuela");
		                    cont++;
		                }
		                if (e.getKeyCode() == KeyEvent.VK_3) {
		                    txtProvincia.setText("Cartago");
		                    cont++;
		                }
		                if (e.getKeyCode() == KeyEvent.VK_4) {
		                    txtProvincia.setText("Heredia");
		                    cont++;
		                }
		                if (e.getKeyCode() == KeyEvent.VK_5) {
		                    txtProvincia.setText("Guanacaste");
		                    cont++;
		                }
		                if (e.getKeyCode() == KeyEvent.VK_6) {
		                    txtProvincia.setText("Puntarenas");
		                    cont++;
		                }
		                if (e.getKeyCode() == KeyEvent.VK_7) {
		                    txtProvincia.setText("Limon");
		                    cont++;

		                }
		            }
		        }
		        repaint();

		    }

		    public void actionPerformed(ActionEvent e) {
		        //getContentPane().setFocusable(isFocusable());
		        if (filtar == e.getSource()) {
		            if (txtCedula.getText().length() == 0) {
		                sorter.setRowFilter(null);
		            } else {
		                sorter.setRowFilter(RowFilter.regexFilter(txtCedula.getText()));
		            }

		        }

		        if (bNuevo == e.getSource() || menuItemNuevo == e.getSource()) {
		            int opcion = comboCategoria.getSelectedIndex();
		            String categ = new String();
		            if (opcion == 0) {
		                categ = "Categoria 1";
		            }
		            if (opcion == 1) {
		                categ = "Categoria 2";
		            }
		            if (opcion == 2) {
		                categ = "Categoria 3";
		            }
		            if (opcion == 3) {
		                categ = "Categoria 4";
		            }

		            profesor.setCedula(txtCedula.getText());
		            profesor.setNombre(txtNombre.getText());
		            profesor.setCategoria(categ);
		            profesor.setProvincia(txtCedula.getText());


		            vector.Agregar(profesor);

		            for (int i = 0; i < vector.Tamano(); i++) {
		                int j = 0;
		                modelo.setValueAt(vector.Recuperar(i).getCedula(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getNombre(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getProvincia(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getCategoria(), i, j++);

		            }

		            JOptionPane.showMessageDialog(null, "El profesor se a agregado correctamente");

		            txtCedula.setText("");
		            txtNombre.setText("");
		            txtProvincia.setText("");
		            comboCategoria.setSelectedIndex(0);

		            profesor = new Profesor();

		        }
		        if (bEliminar == e.getSource() || menuItemEliminar == e.getSource()) {
		            String cedula = txtCedula.getText();
		            vector.Eliminar2(cedula);

		            for (int i = 0; i < vector.Tamano(); i++) {
		                int j = 0;
		                modelo.setValueAt("", i, j++);
		                modelo.setValueAt("", i, j++);
		                modelo.setValueAt("", i, j++);
		                modelo.setValueAt("", i, j++);

		            }

		            for (int i = 0; i < vector.Tamano(); i++) {
		                int j = 0;
		                modelo.setValueAt(vector.Recuperar(i).getCedula(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getNombre(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getProvincia(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getCategoria(), i, j++);

		            }

		            JOptionPane.showMessageDialog(null, "El profesor a sido Eliminado");
		        }

		        if (volver == e.getSource() || menuItemVolver == e.getSource()) {
		            txtCedula.setEnabled(true);
		            panelBotonesIMEC.setVisible(false);
		            labVista2.setVisible(false);
		            panelCategoriatxt.setVisible(false);
		            add(labVista1, BorderLayout.NORTH);
		            txtProvincia.setVisible(true);
		            bNuevo.setVisible(true);
		            scrollPane.setVisible(true);
		            panelCategoriaCombo.setVisible(true);
		            txtCedula.setText("");
		            txtNombre.setText("");
		            txtProvincia.setText("");
		            comboCategoria.setSelectedIndex(0);
		        }

		        if (bModificar == e.getSource() || menuItemModificar == e.getSource()) {
		            String cedula = txtCedula.getText();
		            vector.Eliminar2(cedula);


		            profesor = new Profesor();
		            txtCedula.setEnabled(false);
		            profesor.setCedula(txtCedula.getText());
		            profesor.setNombre(txtNombre.getText());
		            profesor.setCategoria(txtCategoria.getText());
		            vector.Agregar(profesor);


		            for (int i = 0; i < vector.Tamano(); i++) {
		                int j = 0;
		                modelo.setValueAt("", i, j++);
		                modelo.setValueAt("", i, j++);
		                modelo.setValueAt("", i, j++);
		                modelo.setValueAt("", i, j++);

		            }

		            for (int i = 0; i < vector.Tamano(); i++) {
		                int j = 0;
		                modelo.setValueAt(vector.Recuperar(i).getCedula(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getNombre(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getProvincia(), i, j++);
		                modelo.setValueAt(vector.Recuperar(i).getCategoria(), i, j++);

		                txtCedula.setText("");
		                txtNombre.setText("");
		                txtProvincia.setText("");
		                comboCategoria.setSelectedIndex(0);

		            }

		            JOptionPane.showMessageDialog(null, "El profesor a sido modificado");

		        }

		    }

		    public void mouseClicked(MouseEvent e) {

		        int fila = table.rowAtPoint(e.getPoint());
		        int columna = table.columnAtPoint(e.getPoint());
		        labVista1.setVisible(false);
		        add(labVista2, BorderLayout.NORTH);
		        txtProvincia.setVisible(false);
		        bNuevo.setVisible(false);
		        scrollPane.setVisible(false);
		        panelCategoriaCombo.setVisible(false);
		        boxVCamposVentana.add(Box.createVerticalStrut(20));
		        boxVCamposVentana.add(panelCategoriatxt);
		        panelCategoriatxt.setVisible(true);
		        panelBotonesIMEC.setVisible(true);

		        txtCedula.setEnabled(false);
		        txtCedula.setText(table.getValueAt(fila, columna).toString());
		        txtNombre.setText(table.getValueAt(fila, 1).toString());
		        txtCategoria.setText(table.getValueAt(fila, 3).toString());


		    }

		    public void mousePressed(MouseEvent e) {
		    }

		    public void mouseReleased(MouseEvent e) {
		    }

		    public void mouseEntered(MouseEvent e) {
		    }

		    public void mouseExited(MouseEvent e) {
		    }
		}

	}
}

