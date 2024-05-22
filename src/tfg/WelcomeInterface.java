package tfg;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WelcomeInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tFNombre;
	private JTextField tFParticipantes;
	private JTextField tFNumeroGrupos;
	private JTextField tFLimitePersonas;

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
		setTitle("Inicio de la aplicaci\u00F3n");
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
		lblNombre.setBounds(24, 102, 50, 14);
		contentPane.add(lblNombre);

		tFNombre = new JTextField();
		tFNombre.setBounds(90, 99, 164, 20);
		contentPane.add(tFNombre);
		tFNombre.setColumns(10);

		JLabel lblValor = new JLabel("Participantes (6-50):");
		lblValor.setBounds(25, 142, 115, 14);
		contentPane.add(lblValor);

		tFParticipantes = new JTextField();
		tFParticipantes.setBounds(150, 139, 50, 20);
		contentPane.add(tFParticipantes);
		tFParticipantes.setColumns(10);
		tFParticipantes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
				if (tFParticipantes.getText().length() >= 2) {
					e.consume();
				}
			}
		});

		JLabel lblnumeroGrupos = new JLabel("Numero de grupos:");
		lblnumeroGrupos.setBounds(25, 182, 110, 14);
		contentPane.add(lblnumeroGrupos);

		tFNumeroGrupos = new JTextField();
		tFNumeroGrupos.setBounds(150, 179, 50, 20);
		contentPane.add(tFNumeroGrupos);
		tFNumeroGrupos.setColumns(10);
		tFNumeroGrupos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
				if (tFNumeroGrupos.getText().length() >= 2) {
					e.consume();
				}
			}
		});

		JLabel lblLimitePersonas = new JLabel("Personas por grupo:");
		lblLimitePersonas.setBounds(224, 182, 123, 14);
		contentPane.add(lblLimitePersonas);

		tFLimitePersonas = new JTextField();
		tFLimitePersonas.setBounds(349, 179, 60, 20);
		contentPane.add(tFLimitePersonas);
		tFLimitePersonas.setColumns(10);
		tFLimitePersonas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0' && c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
				if (tFLimitePersonas.getText().length() >= 2) {
					e.consume();
				}
			}
		});

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(170, 227, 100, 23);
		contentPane.add(btnGuardar);

		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarDatos();
			}
		});
	}

	protected void guardarDatos() {
		String nombre = tFNombre.getText();
		int participantes;
		int nGrupos;
		int nLimite;
		try {
			participantes = Integer.parseInt(tFParticipantes.getText());
			nGrupos = Integer.parseInt(tFNumeroGrupos.getText());
			nLimite = Integer.parseInt(tFLimitePersonas.getText());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Ingrese números válidos en los campos correspondientes.");
			return;
		}

		if (nGrupos * nLimite != participantes) {
			JOptionPane.showMessageDialog(this,
					"El producto de número de grupos y límite de personas no coincide con el número de participantes totales. Revise los datos insertados");
			return;
		}

		if (participantes < 6 || participantes > 50) {
			JOptionPane.showMessageDialog(this, "El límite de participantes es entre 6 y 50");
			return;
		}

		Informe informe = new Informe(nombre, participantes);
		JOptionPane.showMessageDialog(this, "Datos guardados:\n" + informe);
		MainInterface mainInterface = new MainInterface(participantes, nGrupos, nLimite);
		mainInterface.setTitle("Organización del evento de " + nombre);
		mainInterface.setVisible(true);
		dispose();
	}
}
