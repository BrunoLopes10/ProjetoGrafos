import java.awt.Color;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindo a Java!");

        
        Bola bola = new Bola(750, 350, 20, 1, 1); 

        
        Paleta paletaJogador1 = new Paleta(10, 300, 20, 100, 5, Color.WHITE); 
        Paleta paletaJogador2 = new Paleta(1500, 300, 20, 100, 5, Color.WHITE); 

      
        Monitor monitor = new Monitor(bola, paletaJogador1, paletaJogador2);
        monitor.run();
    }
}
