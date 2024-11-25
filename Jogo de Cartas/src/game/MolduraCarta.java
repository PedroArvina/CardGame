package game;

import cards.Carta;
import exceptions.CartaInvalidaException;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;

public class MolduraCarta extends JPanel implements DragGestureListener, DragSourceListener {

    private Carta carta;
    private DragSource dragSource;
    private boolean jaAtacou;
    private boolean dono; // true para jogador, false para inimigo
    private JLabel labelAtaque;
    private JLabel labelVida;
    private Timer timer;
    private Estrutura estrutura;
    private ControleTurnos controleTurnos; 
    private JLabel jogador1VidaLabel; // Atributo para exibir a vida do Jogador 1
    private JLabel jogador2VidaLabel;// Novo campo para ControleTurnos
    private int vidaJogador1 = 30;
    private int vidaJogador2 = 30;

    // Construtor atualizado para aceitar ControleTurnos
    public MolduraCarta(Carta carta, Estrutura estrutura, ControleTurnos controleTurnos, boolean dono) {
        this.carta = carta;
        this.estrutura = estrutura;
        this.controleTurnos = controleTurnos;
        this.jaAtacou = false;
        this.dono = dono;

        setLayout(new BorderLayout());
        setBackground(new Color(139, 69, 19));
        setPreferredSize(new Dimension(120, 180));

        JLabel labelMana = new JLabel(String.valueOf(carta.getMana()));
        labelMana.setFont(new Font("Serif", Font.BOLD, 20));
        labelMana.setForeground(Color.BLUE);
        labelMana.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelMana, BorderLayout.NORTH);

        String imagemPath = carta.getImagem();
        JLabel imagemLabel = new JLabel(new ImageIcon(new ImageIcon(imagemPath).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
        imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imagemLabel, BorderLayout.CENTER);

        JPanel painelAtributos = new JPanel();
        painelAtributos.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelAtributos.setOpaque(false);

        labelAtaque = new JLabel(String.valueOf(carta.getAtaque()));
        labelAtaque.setFont(new Font("Serif", Font.BOLD, 16));
        labelAtaque.setForeground(Color.RED);
        painelAtributos.add(labelAtaque);

        labelVida = new JLabel(String.valueOf(carta.getVida()));
        labelVida.setFont(new Font("Serif", Font.BOLD, 16));
        labelVida.setForeground(Color.GREEN);
        painelAtributos.add(labelVida);

        add(painelAtributos, BorderLayout.SOUTH);

        setToolTipText("<html><b>" + carta.getNome() + "</b><br/>" + carta.getDescricao() + "</html>");

        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);

        timer = new Timer(1000, e -> {
            atualizarAtributos(carta.getAtaque(), carta.getVida());

            if (carta.getVida() <= 0) {
                removerCartaDoCampo();
            }
        });
        timer.start();

        new DropTarget(this, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = evt.getTransferable();
                    Carta cartaAtacante = (Carta) transferable.getTransferData(new DataFlavor(Carta.class, "Carta"));

                    MolduraCarta molduraAtacante = estrutura.getMolduraCartaPorCarta(cartaAtacante);

                    if (molduraAtacante == null) {
                        JOptionPane.showMessageDialog(null, "Movimento inválido: a carta atacante não está no campo.");
                        evt.dropComplete(false);
                        return;
                    }

                    // Verificar se a carta não está na mão antes de permitir o ataque
                    if (molduraAtacante.getParent() == estrutura.getPainelMaoJogador1() || 
                        molduraAtacante.getParent() == estrutura.getPainelMaoJogador2() || 
                        getParent() == estrutura.getPainelMaoJogador1() || 
                        getParent() == estrutura.getPainelMaoJogador2()) {
                        JOptionPane.showMessageDialog(null, "Movimento inválido: cartas na mão não podem atacar ou ser atacadas.");
                        evt.dropComplete(false);
                        return;
                    }

                    try {
                        realizarCombate(cartaAtacante, carta);
                    } catch (CartaInvalidaException e) {
                        JOptionPane.showMessageDialog(null, "Erro durante o combate: " + e.getMessage());
                        evt.dropComplete(false);
                        return;
                    }

                    evt.dropComplete(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro inesperado: " + ex.getMessage());
                    ex.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });
    }

    public Carta getCarta() {
        return carta;
    }

    public boolean isDono() {
        return dono;
    }

    public boolean jaAtacouNoTurno() {
        return jaAtacou;
    }

    public void resetarAtaque() {
        this.jaAtacou = false;
    }

    public void realizarAtaque() {
        this.jaAtacou = true;
    }
    private void realizarCombate(Carta cartaAtacante, Carta cartaAtacada) throws CartaInvalidaException {
        MolduraCarta molduraAtacante = estrutura.getMolduraCartaPorCarta(cartaAtacante);
        MolduraCarta molduraAtacada = estrutura.getMolduraCartaPorCarta(cartaAtacada);

        if (molduraAtacante == null && molduraAtacada == null) {
            throw new CartaInvalidaException("Carta não encontrada no campo.");
        }

        // Verificar se é um ataque direto ao personagem "Bem" ou "Mal"
        if (molduraAtacada == null) {
            if (cartaAtacada.getNome().equals("Bem")) {
                if (!molduraAtacante.isDono()) {
                    reduzirVidaJogador1(cartaAtacante.getAtaque());
                } else {
                    throw new CartaInvalidaException("Você não pode atacar seu próprio personagem!");
                }
            } else if (cartaAtacada.getNome().equals("Mal")) {
                if (molduraAtacante.isDono()) {
                    reduzirVidaJogador2(cartaAtacante.getAtaque());
                } else {
                    throw new CartaInvalidaException("Você não pode atacar seu próprio personagem!");
                }
            } else {
                throw new CartaInvalidaException("Alvo inválido.");
            }
            return; // Fim do combate direto ao personagem
        }

        // Verificar se a carta atacante já atacou no turno
        if (molduraAtacante.jaAtacouNoTurno()) {
            throw new CartaInvalidaException("Esta carta já atacou neste turno!");
        }

        if (molduraAtacante.isDono() == molduraAtacada.isDono()) {
            throw new CartaInvalidaException("Você não pode atacar uma carta do mesmo lado!");
        }

        Combate combate = new Combate(estrutura);
        combate.realizarCombate(cartaAtacante, cartaAtacada, molduraAtacante.isDono());

        molduraAtacada.atualizarAtributos(cartaAtacada.getAtaque(), cartaAtacada.getVida());

        if (cartaAtacada.getVida() <= 0) {
            JOptionPane.showMessageDialog(this, cartaAtacada.getNome() + " foi destruída!");
            SwingUtilities.invokeLater(() -> {
                estrutura.adicionarCartaAoCemiterio(cartaAtacada, molduraAtacada.isDono());
                molduraAtacada.removerCartaDoCampo();
            });
        }

        if (cartaAtacante.getVida() <= 0) {
            JOptionPane.showMessageDialog(this, cartaAtacante.getNome() + " foi destruída!");
            SwingUtilities.invokeLater(() -> {
                estrutura.adicionarCartaAoCemiterio(cartaAtacante, molduraAtacante.isDono());
                molduraAtacante.removerCartaDoCampo();
            });
        }

        // Marcar a carta atacante como já tendo atacado
        molduraAtacante.realizarAtaque();
    }


    public void atualizarAtributos(int ataque, int vida) {
        labelAtaque.setText(String.valueOf(ataque));
        labelVida.setText(String.valueOf(vida));
        repaint();
    }

    private void removerCartaDoCampo() {
        if (carta.getVida() <= 0) {
            if (getParent() != null) {
                Timer removalTimer = new Timer(500, e -> {
                    Container parent = getParent();
                    if (parent != null) {
                        parent.remove(this);
                        parent.revalidate();
                        parent.repaint();
                    }
                    timer.stop();
                });
                removalTimer.setRepeats(false);
                removalTimer.start();
            }
        }
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        if ((dono && !controleTurnos.isTurnoDoJogador1()) || (!dono && controleTurnos.isTurnoDoJogador1())) {
            JOptionPane.showMessageDialog(null, "Não é seu turno para usar esta carta!");
            return;
        }

        Transferable transferable = new TransferableCarta(carta);
        dragSource.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);
    }
    
    private void reduzirVidaJogador1(int dano) {
        vidaJogador1 -= dano;
        jogador1VidaLabel.setText("Vida: " + vidaJogador1);

        if (vidaJogador1 <= 0) {
            JOptionPane.showMessageDialog(null, "O Jogador 2 venceu!");
            System.exit(0);
        }
    }

    private void reduzirVidaJogador2(int dano) {
        vidaJogador2 -= dano;
        jogador2VidaLabel.setText("Vida: " + vidaJogador2);

        if (vidaJogador2 <= 0) {
            JOptionPane.showMessageDialog(null, "O Jogador 1 venceu!");
            System.exit(0);
        }
    }




    @Override
    public void dragEnter(DragSourceDragEvent dsde) {}

    @Override
    public void dragOver(DragSourceDragEvent dsde) {}

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {}

    @Override
    public void dragExit(DragSourceEvent dse) {}

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        if (dsde.getDropSuccess()) {
            realizarAtaque();
        }
    }

    private class TransferableCarta implements Transferable {
        private Carta carta;

        public TransferableCarta(Carta carta) {
            this.carta = carta;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{new DataFlavor(Carta.class, "Carta")};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.getRepresentationClass() == Carta.class;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) {
            return carta;
        }
    }
}
