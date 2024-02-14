package serpiente;

import java.awt.ActiveEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class juegoContenido extends JPanel implements ActionListener{

 // PANTALLA
	static final int PANTALLA = 600;
	static final int CUADRITO_SIZE = 25;
	static final int CUADRITOS_EN_PARALELO = (int)PANTALLA/CUADRITO_SIZE; //El int controla que la division resultante siempre sea un entero, si no no funcionaría 
	
 // SERPIENTE
	static final int TOTAL_CUERPO_SERPIENTE = (PANTALLA*PANTALLA)/CUADRITO_SIZE;
	int[] serpienteX = new int[TOTAL_CUERPO_SERPIENTE];
	int[] serpienteY = new int[TOTAL_CUERPO_SERPIENTE];
	int cuerpo_serpiente = 3;
	char direccion = 'd'; //awsd (teclas a utilizar para dirigir la serpiente
	
	
 // COMIDA
	int comidaX;
	int comidaY;
	
 // TIMER
	boolean running = true;  //Indica si el juego se terminó o no
	static final int DELAY = 100; //Ritmo con el que se va mover la serpiente (en miilisegundos)
	Timer timer; //reloj
	
 // OTROS
	Random random = new Random(); //Para que aparezca la comida de manera aleatoria
	
 //CONSTRUCTOR
	juegoContenido(){
		
		this.setPreferredSize(new Dimension(PANTALLA, PANTALLA));
		this.setBackground(Color.black); //Pone el fondo de color negro (asi no se ven los cuadritos creados en el método de abajo)
		this.setFocusable(true);
		this.addKeyListener(new Controles());
		iniciarJuego();
		
	}
	
 // CREAMOS LOS METODOS A NECESITAR (iniciarJuego, agregarComida, moverSerpiente, checarComida, checarColisiones)
	
	public void iniciarJuego() {
		agregarComida();
		timer = new Timer(DELAY,this);
		timer.start(); // Es muy importante para que se redibuje la pantalla de juego y podamos ver que hace en tiempo real nuestro juego
	}
	
	public void agregarComida() {
		comidaX = random.nextInt(CUADRITOS_EN_PARALELO)*CUADRITO_SIZE; //Indicamos a la comida que sólo puede aparecer en este rango 
		comidaY = random.nextInt(CUADRITOS_EN_PARALELO)*CUADRITO_SIZE;
	}
	
	public void moverSerpiente() {
		//movemos el cuerpo de la serpiente
		for(int i=cuerpo_serpiente; i>0; i--) {
			serpienteX[i] = serpienteX[i-1];
			serpienteY[i] = serpienteY[i-1];
		}
		
		//Movemos la cabeza de la serpiente
		switch(direccion) {
			case 'd':
				serpienteX[0]=serpienteX[0]+CUADRITO_SIZE;
			case 'a':
				serpienteX[0]=serpienteX[0]-CUADRITO_SIZE;
			case 'w':
				serpienteY[0]=serpienteY[0]-CUADRITO_SIZE;
			case 's':
				serpienteY[0]=serpienteY[0]+CUADRITO_SIZE;
		}
		
	}
	
	public void checarComida() {
		
	}
	
	public void checarColisiones() {
		
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			moverSerpiente();
			checarComida();
			checarColisiones();
		}
		repaint();
		
	}
	@Override
	// En este método damos la apariencia al juego 
	public void paintComponent (Graphics g) { 
		super.paintComponent(g);
		
		 // Dibujamos la pantalla 
		for(int i=0; i<CUADRITOS_EN_PARALELO; i++) {
			g.drawLine(0, CUADRITO_SIZE*i, PANTALLA, CUADRITO_SIZE*i); //Esto dibuja lineas horizontales
			g.drawLine(CUADRITO_SIZE*i, 0, CUADRITO_SIZE*i, PANTALLA); //Esto dibuja lineas verticales
		}
		
		// Esto da aspecto a lo que sería la comida, ya que más arriba solo estamos indicando donde tiene que aparecer
		g.setColor(Color.red); 
		g.fillOval(comidaX, comidaY, CUADRITO_SIZE, CUADRITO_SIZE);
		
		// Dibujamos la serpiente
		g.setColor(Color.green);
		for(int i=0; i<cuerpo_serpiente; i++) {
			g.fillRect(serpienteX[i], serpienteY[i], CUADRITO_SIZE, CUADRITO_SIZE);
		}
		
	}
	
	public class Controles extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			
		}
	}
	
	
	
}

