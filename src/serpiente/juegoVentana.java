package serpiente;

import javax.swing.JFrame;

public class juegoVentana extends JFrame {
	
	juegoVentana(){
		this.setTitle("Serpiente");
		this.add(new juegoContenido());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //siempre hay que cerrarlo
		this.setResizable(false); // Metodo para bloquear resize (el jugador no puede cambiar el tamaño de la ventana
		this.pack();
		this.setLocationRelativeTo(null); //Metodo para centrar ventana
		this.setVisible(true); //Metodo pada mostrar ventana
	}

}
