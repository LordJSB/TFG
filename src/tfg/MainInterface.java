package tfg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class MainInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPpal;
	private JTable tablaPpal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface frame = new MainInterface(4, 2, 2); // Testing. NO DEBE SALIR
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainInterface(int numPersonas, int nGrupos, int nLimite) {
		setTitle("NOMBRE NO RECOGIDO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1200, 900));
		panelPpal = new JPanel(new BorderLayout());
		panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPpal);

		DefaultTableModel model = new DefaultTableModel(numPersonas + 1, numPersonas + 1) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// No editable para celdas en la primera fila (0, n) y primera columna (n, 0)
				// No editable para celdas diagonales (n, n)
				if (row == 0 || column == 0 || row == column) {
					return false;
				}
				return true;
			}
		};

		for (int i = 1; i <= numPersonas; i++) {
			model.setValueAt("Persona " + i, 0, i);
			model.setValueAt("Persona " + i, i, 0);
		}

		// Poner 0 en cada celda (n, n)
		for (int i = 1; i <= numPersonas; i++) {
			model.setValueAt(0, i, i);
		}

		tablaPpal = new JTable(model);
		tablaPpal.setModel(model);
		tablaPpal.setDefaultRenderer(Object.class, new DiagonalCellRenderer());

		// Crear un ComboBox editor para la tabla
		JComboBox<ComboBoxItem> comboBox = new JComboBox<>();
		comboBox.addItem(new ComboBoxItem(0, "0 - Indiferencia"));
		comboBox.addItem(new ComboBoxItem(1, "1 - Muy cercano"));
		comboBox.addItem(new ComboBoxItem(2, "2 - Cercano"));
		comboBox.addItem(new ComboBoxItem(3, "3 - Apatia"));
		comboBox.addItem(new ComboBoxItem(4, "4 - Desprecio"));

		comboBox.setRenderer(new DefaultListCellRenderer() {
			private static final long serialVersionUID = 1L;

			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof ComboBoxItem) {
					setText(((ComboBoxItem) value).toString());
				}
				return this;
			}
		});

		DefaultCellEditor editor = new DefaultCellEditor(comboBox) {
			private static final long serialVersionUID = 1L;

			@Override
			public Object getCellEditorValue() {
				ComboBoxItem item = (ComboBoxItem) super.getCellEditorValue();
				return item.getValor();
			}
		};

		JScrollPane scrollPane = new JScrollPane(tablaPpal);
		tablaPpal.setDefaultEditor(Object.class, editor);
		panelPpal.add(scrollPane, BorderLayout.CENTER);

		JButton btnDistribuir = new JButton("Calcular grupos");
		btnDistribuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creaGrupos(nGrupos, nLimite);
				//crearArchivoXML();
			}
		});
		panelPpal.add(btnDistribuir, BorderLayout.SOUTH);

		int tamanioColumna = 70;
		TableColumn firstColumn = tablaPpal.getColumnModel().getColumn(0);
		firstColumn.setPreferredWidth(tamanioColumna);

		// Establecer anchura mínima de la primera celda de cada columna
		for (int i = 1; i < tablaPpal.getColumnCount(); i++) {
			TableColumn columna = tablaPpal.getColumnModel().getColumn(i);
			columna.setPreferredWidth(tamanioColumna);
		}
		rellenarCeros();
		pack(); // Ajusta el tamaño de la ventana automáticamente
		setLocationRelativeTo(null); // Centra la ventana en la pantalla
	}

	public int[][] getDatosTabla() {
		int rowCount = tablaPpal.getRowCount();
		int columnCount = tablaPpal.getColumnCount();
		int[][] data = new int[rowCount][columnCount];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (tablaPpal.getValueAt(i, j) != null) {
					data[i][j] = Integer.parseInt(tablaPpal.getValueAt(i, j).toString());
				}
			}
		}
		return data;
	}

	private class DiagonalCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			if (row == column) {
				c.setBackground(Color.DARK_GRAY);
			} else {
				c.setBackground(table.getBackground());
			}
			return c;
		}
	}

	private void rellenarValoresAleatorios() {
		Random random = new Random();
		int rowCount = tablaPpal.getRowCount();
		int columnCount = tablaPpal.getColumnCount();

		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (i != j) { // No rellenar la diagonal
					int valorAleatorio = random.nextInt(5); // Valores entre 0 y 4
					tablaPpal.setValueAt(valorAleatorio, i, j);
				}
			}
		}
	}

	private void rellenarCeros() {
		int rowCount = tablaPpal.getRowCount();
		int columnCount = tablaPpal.getColumnCount();

		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (i != j) { // No rellenar la diagonal
					int valorAleatorio = 0;
					tablaPpal.setValueAt(valorAleatorio, i, j);
				}
			}
		}
	}

	private void creaGrupos(int nGrupos, int nLimite) {
		if (siCeldaVacia()) {
			JOptionPane.showMessageDialog(this, "Falta ingresar datos en la tabla.");
			return;
		}
		int[][] tableData = getDatosTabla();
		List<List<Integer>> groups = new ArrayList<List<Integer>>();
		for (int[] nums : tableData) {
			if (tableData[0] != nums) {
				List<Integer> gr = new ArrayList<Integer>();
				for (int i = 1; i < nums.length; i++) {
					gr.add(nums[i]);
				}
				groups.add(gr);
			}
		}
		System.out.println(groups);
		List<Persona> personas = new ArrayList<Persona>();
		for (List<Integer> listaNumeros : groups) {
			int numero = groups.indexOf(listaNumeros) + 1;
			personas.add(new Persona(groups.indexOf(listaNumeros), "" + numero, listaNumeros));
		}
		StringBuilder message = new StringBuilder();
		message.append("Organización óptima:\n");

		message.append(Distribucion.distribuir(personas, nGrupos, nLimite));

		JOptionPane.showMessageDialog(this, message.toString());
	}

	private boolean siCeldaVacia() {
		int rowCount = tablaPpal.getRowCount();
		int columnCount = tablaPpal.getColumnCount();
		for (int i = 1; i < rowCount; i++) {
			for (int j = 1; j < columnCount; j++) {
				if (tablaPpal.getValueAt(i, j) == null) {
					return true;
				}
			}
		}
		return false;
	}
}

class ComboBoxItem {
	private int valor;
	private String tapadera;

	public ComboBoxItem(int valor, String tapadera) {
		this.valor = valor;
		this.tapadera = tapadera;
	}

	public int getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return tapadera;
	}
}