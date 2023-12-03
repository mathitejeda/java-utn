package TP5;

import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import javax.swing.JLabel;

public class PanelListadoPeliculas extends JPanel {
	private JList<Pelicula> jList;
	private DefaultListModel<Pelicula> listModel;

	public PanelListadoPeliculas() {
		setLayout(null);
		
		JLabel lblListado = new JLabel("Peliculas");
		lblListado.setBounds(33, 71, 83, 14);
		add(lblListado);
		
		jList = new JList<Pelicula>();
		jList.setBounds(126, 33, 301, 233);
		add(jList);
		jList.setFont(new Font("Tahoma", Font.PLAIN, 12));
	}

	public void setDefaultListModel(DefaultListModel<Pelicula> listModel2)
	{
		this.listModel = listModel2;
		jList.setModel(this.listModel);
	}
}
