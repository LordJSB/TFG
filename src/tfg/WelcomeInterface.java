package tfg;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldNombre;
	private JSpinner spinnerValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeInterface frame = new WelcomeInterface();
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
	public WelcomeInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblBienvenida = new JLabel("Bienvenido, por favor ingrese sus datos:");
		lblBienvenida.setBounds(10, 11, 414, 14);
		contentPane.add(lblBienvenida);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 50, 100, 14);
		contentPane.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(120, 47, 150, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblValor = new JLabel("Valor (6-50):");
		lblValor.setBounds(10, 100, 100, 14);
		contentPane.add(lblValor);

		spinnerValor = new JSpinner();
		spinnerValor.setModel(new SpinnerNumberModel(6, 6, 50, 1));
		spinnerValor.setBounds(120, 97, 50, 20);
		contentPane.add(spinnerValor);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(10, 150, 100, 23);
		contentPane.add(btnGuardar);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatos();
			}
		});
	}

	protected void guardarDatos() {
		String nombre = textFieldNombre.getText();
		int valor = (int) spinnerValor.getValue();
		Informe informe = new Informe(nombre, valor);

		JOptionPane.showMessageDialog(this, "Datos guardados:\n" + informe);
		MainInterface mainInterface = new MainInterface();
		mainInterface.setTitle("Organización del evento de "+nombre);
		mainInterface.setVisible(true);
		dispose();
	}
}
