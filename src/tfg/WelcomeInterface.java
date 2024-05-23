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
		setTitle("Inicio de la aplicación");
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

		JLabel lblValor = new JLabel("Participantes (6-25):");
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

		if (participantes < 6 || participantes > 25) {
			JOptionPane.showMessageDialog(this, "El límite de participantes es entre 6 y 25");
			return;
		}

		int gruposOptimos = (int) Math.ceil((double) participantes / nLimite);
		int tamanioOptimo = (int) Math.ceil((double) participantes / nGrupos);
		if (nGrupos > gruposOptimos || nLimite > tamanioOptimo) {
			Object[] options = { "Optimizar grupos", "Optimizar tamaño", "No" };
			int respuesta = JOptionPane.showOptionDialog(this,
					"El número de grupos ingresado o el tamaño de los grupos es mayor al óptimo. Crear grupos descompensados puede afectar la organización.\n"
							+ "¿Desea optimizar el número de grupos a " + gruposOptimos
							+ " o el tamaño de los grupos a " + tamanioOptimo + "?",
					"Optimizar grupos o tamaño", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					options, options[2]);

			if (respuesta == JOptionPane.YES_OPTION) {
				nGrupos = gruposOptimos;
			} else if (respuesta == JOptionPane.NO_OPTION) {
				nLimite = tamanioOptimo;
			}
		}

		JOptionPane.showMessageDialog(this,
				"Ahora se procederá a establecer el nivel de animosidad entre los diferentes participantes. "
				+ "\nSe mostrará una tabla con cada uno de ellos en una fila y columna, de tal manera que la columna del Invitado 1 "
				+ "mostrará su nivel de animosidad con el resto de participantes y lo mismo sucederá con las filas. "
				+ "\nDichos niveles están definidos como:"
				+ "\n- 1: Gran apego con la otra persona, busca mantenerse en el mismo grupo en la medida de lo posible."
				+ "\n- 2: Hay lazos de amistad o cordialidad con la otra persona"
				+ "\n- 3: Aunque no se agraden en gran medida, pueden compartir espacio de manera respetuosa"
				+ "\n- 4: Buscan mantenerse alejados unos de otros por todos los medios"
				+ "\n- 0: Indiferencia por desconocimiento entre las personas u otro motivo");
		MainInterface mainInterface = new MainInterface(nombre, participantes, nGrupos, nLimite);
		mainInterface.setTitle("Organización del evento de " + nombre);
		mainInterface.setVisible(true);
		dispose();
	}
}
