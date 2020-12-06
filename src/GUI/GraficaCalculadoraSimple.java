package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.*;

import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class GraficaCalculadoraSimple extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private CalculadoraSimple calculadora;
	private List<PluginFunction> lista_plugins;
	private JComboBox<String> operaciones;
	private JTextField x1;
	private JTextField x2;
	private JLabel resultado;
	private JButton boton_calcular;
	private JButton boton_actualizar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraficaCalculadoraSimple frame = new GraficaCalculadoraSimple();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GraficaCalculadoraSimple() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.calculadora = new CalculadoraSimple();
		
		inicializarPanel();
		
	}
	
	/**
	 * Inicializa el panel general.
	 */
	private void inicializarPanel() {
		
		JLabel titulo = new JLabel("Calculadora Simple");
		titulo.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		this.contentPane.add(titulo, BorderLayout.NORTH);
		
		inicializarPanelOperaciones();
		
	}
	
	/**
	 * Inicializa el panel para las operaciones.
	 */
	private void inicializarPanelOperaciones() {
		
		this.panel = new JPanel();
		this.panel.setSize(450,200);
		this.contentPane.add(panel, BorderLayout.CENTER);
		this.panel.setLayout(null);
		this.panel.setBorder(null);
		
		//Se inicializan los TextField donde se deben ingresar los números.
		JLabel Text_Ingresar = new JLabel("Ingresa dos números:");
		Text_Ingresar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		Text_Ingresar.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(Text_Ingresar);
		Text_Ingresar.setLocation(20, 10);
		Text_Ingresar.setSize(130, 39);
		
		this.x1 = new JTextField();
		this.x1.setBounds(180, 20, 100, 20);
		this.x1.setColumns(10);
		panel.add(this.x1);
		
		this.x2 = new JTextField();
		this.x2.setBounds(300, 20, 100, 20);
		this.x2.setColumns(10);
		panel.add(this.x2);
		
		//Se inicializa el JComboBox para elegir la operación a realizar.
		JLabel Text_Operacion = new JLabel("Elige la operación a realizar:");
		Text_Operacion.setFont(new Font("Century Gothic", Font.PLAIN, 11));
		Text_Operacion.setHorizontalAlignment(SwingConstants.CENTER);
		Text_Operacion.setSize(200, 39);
		Text_Operacion.setLocation(0, 50);
		panel.add(Text_Operacion);
		
		this.operaciones = new JComboBox<String>();
		this.operaciones.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		this.operaciones.setSize(100, 20);
		this.operaciones.setLocation(240, 60);
		panel.add(this.operaciones);
		
		inicializarBoton_Calcular();
		
		inicializarBoton_Actualizar();
		
		cargarOperaciones();
		
	}
	
	/**
	 * Carga los plugins encontrados en el comboBox.
	 */
	private void cargarOperaciones() {		
		this.lista_plugins = this.calculadora.getPlugins();
		
		if(this.lista_plugins != null && !this.lista_plugins.isEmpty()) {
			for(PluginFunction plugin : this.lista_plugins) {
				this.operaciones.addItem(plugin.getPluginName());
			}
			this.boton_calcular.setEnabled(true);
			setEnabledComponents(true);
		}		
		else {
			this.resultado.setText("No se encontraron operaciones.");
			this.resultado.setLocation(100, 135);
			this.boton_calcular.setEnabled(false);
			setEnabledComponents(false);
		}
	}
	
	/**
	 * Habilita o deshabilita los textFields.
	 * @param b Boolean que indica si deben estar habilitadas o no.
	 */
	private void setEnabledComponents(boolean b) {
		
		this.x1.setEnabled(b);
		this.x2.setEnabled(b);
	}
	
	/**
	 * Inicializa el botón para calcular la operación deseada.
	 */
	private void inicializarBoton_Calcular() {		
		//Se inicializa el botón para calcular las operaciones.
		this.boton_calcular = new JButton("CALCULAR");
		this.boton_calcular.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		this.boton_calcular.setBounds(150, 90, 100, 40);
		this.panel.add(this.boton_calcular);
		
		//Se inicializa el label donde se muestra el cartel necesario.
		this.resultado = new JLabel();
		this.resultado.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		this.resultado.setSize(300, 20);
		this.panel.add(resultado);
		
		//Se crea el listener para el botón.
		this.boton_calcular.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {	
			   if(boton_calcular.getText().equals("CALCULAR")) {
				   //Una vez que se apretó calcular para volver a hacerlo es necesario reiniciar los valores a 0.
				   boton_calcular.setText("REINICIAR");				   			   
				   //Calcula la operación indicada si es posible.
				   calcular();
				   setEnabledComponents(false);
			   }
			   else {				   
				   //En caso de que se indique reiniciar se "vaciará" la calculadora.
				   borrarTodo();
				   boton_calcular.setText("CALCULAR");
				   setEnabledComponents(true);
			   }
		   }
		});				
	}
	
	/**
	 * Calcula una operación indicada si es posible.
	 */
	private void calcular() {		
		String text_x1;
		String text_x2;
		String operacionSeleccionada;
		PluginFunction operacion_a_realizar = null;
		Iterator<PluginFunction> it_lista = lista_plugins.iterator();
		PluginFunction plugin;
		boolean encontre_operacion = false;
			
		try {			
			text_x1 = x1.getText();
			text_x2 = x2.getText();
			
			operacionSeleccionada = (String) operaciones.getSelectedItem();
			    		   		
			while(it_lista.hasNext() && !encontre_operacion) {
				plugin = it_lista.next();
				if (plugin.getPluginName().equals(operacionSeleccionada)) {
					operacion_a_realizar = plugin;
					encontre_operacion = true;
				}
			}

			operacion_a_realizar.setParameter(Integer.parseInt(text_x1), Integer.parseInt(text_x2));
			float res = operacion_a_realizar.getResult();
			resultado.setText("RESULTADO: " + res);
			resultado.setLocation(155, 135);
			
		}
		catch(InvalidOperationException ex) {
			resultado.setText(ex.getMessage());
			resultado.setLocation(90, 135);
		}
		catch(NumberFormatException ex) {			  
			  resultado.setText("No se ingresaron los números necesarios.");
			  resultado.setLocation(80, 135);			  
		}
	}
			
	/**
	 * Inicializa el botón para actualizar las operaciones de la calculadora.
	 */
	private void inicializarBoton_Actualizar() {		
		//Se inicializa el boton actualizar.
		this.boton_actualizar = new JButton("ACTUALIZAR");
		this.boton_actualizar.setBounds(135, 170, 130, 40);
		this.boton_actualizar.setFont(new Font("Century Gothic", Font.PLAIN, 12));
		this.panel.add(this.boton_actualizar);
		
		//Se crea el listener para que el botón busque los plugins.
		this.boton_actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				borrarTodo();
				operaciones.removeAllItems();
				cargarOperaciones();
			}
		});		
	}
	
	/**
	 * Los elementos de la calculadora quedan vacíos.
	 */
	private void borrarTodo() {
		this.x1.setText("");
		this.x2.setText("");
		this.resultado.setText("");
	}
	
	

}
