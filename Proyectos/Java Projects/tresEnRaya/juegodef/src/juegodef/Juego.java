package juegodef;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Juego {
	JFrame ventana;
	JButton iniciar;
	JButton tablero [][];
	int jugador=-1;
	JLabel jugadorActivo;
	Color botonesColor;
	Container cp;
	
	public Juego() {
		crearComponentes();
        colocarComponentes();
        registrarEventos();
	}

	private void crearComponentes() {
		ventana = new JFrame();
		jugadorActivo = new JLabel("INICIO");
		iniciar = new JButton("INICIO");
		tablero = new JButton[3][3];
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				tablero[i][j]= new JButton();
			}
		}
		
	}

	private void colocarComponentes() {
		cp = ventana.getContentPane();
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
		ventana.setSize(500,500);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
		ventana.setTitle("Tres en Raya");
		ventana.setVisible(true);
		ventana.setLayout(null);
		jugadorActivo.setBounds(100, 10, 100, 100);
		ventana.add(jugadorActivo);
		iniciar.setBounds(175, 350, 150, 30);
		ventana.add(iniciar);
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				tablero[i][j].setBounds((i+1)*80+40,(j+1)*80,80,80);
				ventana.add(tablero[i][j]);
			}
		}
	}
	
	private void registrarEventos() {
		iniciar.addActionListener(new ManejadorEventos());
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				tablero[i][j].addActionListener(new ManejadorEventos());			}
		}
		
	}
	
	public static void main(String[] args) {
		Juego j = new Juego();
	}
	
	class ManejadorEventos implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==iniciar) {
				jugador = 0;
				jugadorActivo.setText("Jugador 1");
				limpiar();
			}else {
				JButton boton = (JButton)e.getSource();
				if(jugador==0) {
					if(boton.getText().equals("")) {
						boton.setBackground(Color.RED);
						boton.setText("x");
						boton.setEnabled(false);
						jugador = 1;
						jugadorActivo.setText("Jugador 2");
					}
				}else {
					if(boton.getText().equals("")) {
						boton.setBackground(Color.BLUE);
						boton.setText("o");
						boton.setEnabled(false);
						jugador = 0;
						jugadorActivo.setText("Jugador 1");
					}
				}
			}
			verificar();
		}
		public void limpiar() {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					tablero[i][j].setEnabled(true);
					tablero[i][j].setText("");
					tablero[i][j].setBackground(botonesColor);
				}
			}
		}
		
		public void verificar() {
			int ganador = 0;
			for(int i = 0; i < 3; i++) {
				if(tablero[i][0].getText().equals("x") && tablero[i][1].getText().equals("x") && tablero[i][2].getText().equals("x")){
					ganador=1;
				}else if(tablero[i][0].getText().equals("o") && tablero[i][1].getText().equals("o") && tablero[i][2].getText().equals("o")){
					ganador=2;
				}else if(tablero[0][i].getText().equals("x") && tablero[1][i].getText().equals("x") && tablero[2][i].getText().equals("x")){
					ganador=1;
				}else if(tablero[0][i].getText().equals("o") && tablero[1][i].getText().equals("o") && tablero[2][i].getText().equals("o")){
					ganador=2;
				}else if(tablero[0][0].getText().equals("x") && tablero[1][1].getText().equals("x") && tablero[2][2].getText().equals("x")) {
					ganador=1;
				}else if(tablero[0][0].getText().equals("o") && tablero[1][1].getText().equals("o") && tablero[2][2].getText().equals("o")) {
					ganador=2;
				}else if(tablero[0][2].getText().equals("x") && tablero[1][1].getText().equals("x") && tablero[2][0].getText().equals("x")) {
					ganador=1;
				}else if(tablero[0][2].getText().equals("o") && tablero[1][1].getText().equals("o") && tablero[2][0].getText().equals("o")) {
					ganador=2;
				}
			}
			if(ganador==1) {
				JOptionPane.showMessageDialog(ventana, "ganajugador1");
			}else if (ganador==2){
				JOptionPane.showMessageDialog(ventana, "ganajugador2");
			}
		}
		
	}

}
