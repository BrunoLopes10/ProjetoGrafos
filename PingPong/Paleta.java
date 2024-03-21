import java.awt.Color;
import java.awt.Graphics;

public class Paleta {
    private final int x;
    private int y;
    private final int largura;
    private final int altura;
    private final int velocidade;
    private final Color cor;

    public static final int PARA_CIMA = -1;
    public static final int PARA_BAIXO = 1;
    public static final int ALTURA_TELA = 600;

    public Paleta(int x, int y, int largura, int altura, int velocidade, Color cor) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.velocidade = velocidade;
        this.cor = cor;
    }

    public void mover(int direcao) {
        if (direcao == PARA_CIMA) {
            if (y - velocidade >= 0) {
                y -= velocidade;
            }
        } else if (direcao == PARA_BAIXO) {
            if (y + altura + velocidade <= ALTURA_TELA) {
                y += velocidade;
            }
        }
    }

    public void desenhar(Graphics g) {
        g.setColor(cor);
        g.fillRect(x, y, largura, altura);
    }
}
