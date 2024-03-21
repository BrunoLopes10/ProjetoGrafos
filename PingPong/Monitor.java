import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Monitor implements WindowListener, ActionListener {
    private final JFrame minhaTela;
    private boolean sinal;
    private final JButton botaoIniciar;
    private final JButton botaoPausar;
    private final JLabel labelPontuacaoJogador1;
    private final JLabel labelPontuacaoJogador2;
    private final JPanel painelPlacar;
    private final BorderLayout layout;
    private int pontuacaoJogador1 = 0;
    private final int pontuacaoJogador2 = 0;
    private final Container container;
    private Paleta paletaJogador1;
    private Paleta paletaJogador2;
    private Bola bola;

    public Monitor(Bola bola, Paleta paletaJogador1, Paleta paletaJogador2) {
        this.bola = bola;
        this.paletaJogador1 = paletaJogador1;
        this.paletaJogador2 = paletaJogador2;

        this.minhaTela = new JFrame("Desenvolvimento de Jogos Digitais");
        this.minhaTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.painelPlacar = new JPanel();
        JPanel painelJogo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paletaJogador1.desenhar(g);
                paletaJogador2.desenhar(g);
                bola.desenhar(g);
            }
        };
        JPanel painelBotoes = new JPanel();
        this.botaoIniciar = new JButton("Iniciar");
        this.botaoIniciar.addActionListener(this);
        this.botaoPausar = new JButton("Pausar");
        this.botaoPausar.addActionListener(this);
        JLabel labelJogador1 = new JLabel("Jogador 1: ");
        this.labelPontuacaoJogador1 = new JLabel("" + this.pontuacaoJogador1);
        JLabel labelJogador2 = new JLabel("Jogador 2: ");
        this.labelPontuacaoJogador2 = new JLabel("" + this.pontuacaoJogador2);
        painelPlacar.setBackground(Color.GREEN);
        painelPlacar.add(labelJogador1);
        painelPlacar.add(labelPontuacaoJogador1);
        painelPlacar.add(labelJogador2);
        painelPlacar.add(labelPontuacaoJogador2);
        painelJogo.setBackground(Color.DARK_GRAY);
        painelBotoes.setBackground(Color.WHITE);
        painelBotoes.add(botaoIniciar);
        painelBotoes.add(botaoPausar);
        this.layout = new BorderLayout(5, 5);
        this.container = minhaTela.getContentPane();
        container.setLayout(this.layout);
        container.add(painelPlacar, BorderLayout.NORTH);
        container.add(painelJogo, BorderLayout.CENTER);
        container.add(painelBotoes, BorderLayout.SOUTH);
        this.minhaTela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.minhaTela.addWindowListener(this);
        this.sinal = false;
    }

    public void terminate() {
        this.sinal = false;
    }

    public void run() {
        this.sinal = true;
        this.load();
        while (this.sinal) {
            this.update();
            this.render();
        }
        this.unLoad();
    }

    public void load() {
        this.minhaTela.setVisible(true);
    }

    public void unLoad() {
        this.minhaTela.dispose();
    }

    public void update() {
    }

    public void render() {
        this.minhaTela.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == this.botaoIniciar) {
            this.pontuacaoJogador1++;
        } else {
            if (this.pontuacaoJogador1 > 0) {
                this.pontuacaoJogador1--;
            }
        }
        this.labelPontuacaoJogador1.setText("" + this.pontuacaoJogador1);
        if (this.pontuacaoJogador1 == 5) {
            final JDialog frame = new JDialog(this.minhaTela, "Jogo finalizado", true);
            frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            JPanel painel = new JPanel();
            JLabel label = new JLabel("O jogador 1 é o vencedor!");
            JButton botao = new JButton("Ok");
            painel.add(label);
            painel.add(botao);
            frame.getContentPane().add(painel);
            frame.pack();
            frame.setVisible(true);
            this.pontuacaoJogador1 = 0;
            this.labelPontuacaoJogador1.setText("" + this.pontuacaoJogador1);
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        this.terminate();
    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.terminate();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
}
