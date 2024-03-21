public class Bola {
    private int x;
    private int y;
    private int diametro;

    public Bola(int x, int y, int diametro, int par3, int par4) {
        this.x = x;
        this.y = y;
        this.diametro = diametro;
    }

    public void desenhar(java.awt.Graphics g) {
        g.setColor(java.awt.Color.WHITE);
        g.fillOval(x, y, diametro, diametro);
    }
}
