package TP5;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {
	
	private JPanel contentPane;
	protected static DefaultListModel<Pelicula> listModel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					listModel = new DefaultListModel<Pelicula>();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Principal() {
		setTitle("Programa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Peliculas");
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		menuBar.add(menu);
		
		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mntmAgregar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		mntmAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				PanelIngresoPeliculas panelAgregar = new PanelIngresoPeliculas();
				panelAgregar.setDefaultListModel(listModel);
				contentPane.add(panelAgregar);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		
		menu.add(mntmAgregar);
		
		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				PanelListadoPeliculas panelListado = new PanelListadoPeliculas();
				panelListado.setDefaultListModel(listModel);
				contentPane.add(panelListado);
				contentPane.repaint();
				contentPane.revalidate();
			}
		});
		menu.add(mntmListar);
				
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
}
