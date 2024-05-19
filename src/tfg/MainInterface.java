package tfg;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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
                    MainInterface frame = new MainInterface();
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
    public MainInterface() {
        setTitle("NOMBRE NO RECOGIDO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 650);

        panelPpal = new JPanel();
        panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panelPpal);

        tablaPpal = new JTable();
        tablaPpal.setBounds(10, 10, 600, 400);
        tablaPpal.setModel(new DefaultTableModel(
                new Object[][] { { "", "1", "2", "3", "4", "5", "6" }, { "1", null, null, null, null, null, null },
                        { "2", null, null, null, null, null, null }, { "3", null, null, null, null, null, null },
                        { "4", null, null, null, null, null, null }, { "5", null, null, null, null, null, null },
                        { "6", null, null, null, null, null, null } },
                new String[] { "", "1", "2", "3", "4", "5", "6" }) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return !(row == 0 || column == 0 || row == column);
            }
        });

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        tablaPpal.setDefaultRenderer(Object.class, centerRenderer);

        for (int i = 0; i < tablaPpal.getColumnCount(); i++) {
            tablaPpal.getColumnModel().getColumn(i).setPreferredWidth(100);
        }

        tablaPpal.setDefaultRenderer(Object.class, new DiagonalCellRenderer());
        tablaPpal.setDefaultEditor(Object.class, new SpinnerEditor());
        panelPpal.setLayout(null);
        panelPpal.add(tablaPpal);

        JButton button = new JButton("Botón");
        button.setBounds(774, 570, 100, 30);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                creaGrupos();
            }
        });
        panelPpal.add(button);
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
                c.setBackground(new Color(200, 200, 200));
            } else {
                c.setBackground(table.getBackground());
            }
            return c;
        }
    }

    private class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private static final long serialVersionUID = 1L;
        private final JSpinner spinner;

        public SpinnerEditor() {
            SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 4, 1);
            spinner = new JSpinner(model);
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value != null) {
                spinner.setValue(value);
            }
            return spinner;
        }
    }

    private void creaGrupos() {
        if (siCeldaVacia()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar datos en la tabla.");
            return;
        }
        int[][] tableData = getDatosTabla();
        List<List<Integer>> groups = matrizDatos(tableData);

        StringBuilder message = new StringBuilder();
        message.append("Grupos encontrados:\n");
        for (int i = 0; i < groups.size(); i++) {
            message.append("Grupo ").append(i + 1).append(": ").append(groups.get(i)).append("\n");
        }

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

    private List<List<Integer>> matrizDatos(int[][] tableData) {
        List<List<Integer>> groups = new ArrayList<>();
        for (int i = 1; i < tableData.length; i++) {
            for (int j = i + 1; j < tableData.length; j++) {
                for (int k = j + 1; k < tableData.length; k++) {
                    int sum = calculaSum(tableData[i]) + calculaSum(tableData[j]) + calculaSum(tableData[k]);
                    if (sum < 9) {
                        List<Integer> group = new ArrayList<>();
                        group.add(i);
                        group.add(j);
                        group.add(k);
                        groups.add(group);
                    }
                }
            }
        }

        return groups;
    }

    private int calculaSum(int[] row) {
        int sum = 0;
        for (int value : row) {
            sum += value;
        }
        return sum;
    }
}
