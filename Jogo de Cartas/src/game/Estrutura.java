package game;

import cards.Carta;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estrutura {

    private JPanel painelMaoJogador;
    private JPanel painelCampoJogador;
    private JPanel painelCampoInimigo;
    private Acessorio acessorio;
    private Mana mana;

    // Mapas para armazenar as cartas no campo do jogador e do inimigo com seus estados preservados
    private Map<Carta, MolduraCarta> cartasCampoJogador;
    private Map<Carta, MolduraCarta> cartasCampoInimigo;

    public Estrutura(Acessorio acessorio, Mana mana) {
        this.acessorio = acessorio;
        this.mana = mana;

        // Inicialização dos mapas de cartas no campo
        cartasCampoJogador = new HashMap<>();
        cartasCampoInimigo = new HashMap<>();

        criarPainelMaoJogador();
        criarPainelCampoJogador();
        criarPainelCampoInimigo();
    }

    private void criarPainelMaoJogador() {
        painelMaoJogador = new JPanel(new FlowLayout(FlowLayout.CENTER, -10, 0));
        painelMaoJogador.setBackground(new Color(102, 51, 0));
        painelMaoJogador.setPreferredSize(new Dimension(800, 200));
    }

    private void criarPainelCampoJogador() {
        painelCampoJogador = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelCampoJogador.setBackground(new Color(34, 28, 24));

        painelCampoJogador.setTransferHandler(new TransferHandler("carta") {
            @Override
            public boolean canImport(TransferHandler.TransferSupport support) {
                try {
                    Carta carta = (Carta) support.getTransferable().getTransferData(new DataFlavor(Carta.class, "Carta"));
                    return carta.getMana() <= mana.getManaAtual();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

            @Override
            public boolean importData(TransferHandler.TransferSupport support) {
                try {
                    Carta carta = (Carta) support.getTransferable().getTransferData(new DataFlavor(Carta.class, "Carta"));
                    if (carta.getMana() <= mana.getManaAtual()) {
                        mana.reduzirMana(carta.getMana());
                        adicionarCartaAoCampoJogador(carta);
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    private void criarPainelCampoInimigo() {
        painelCampoInimigo = new JPanel(new GridLayout(1, 7, 10, 0));
        painelCampoInimigo.setBackground(new Color(34, 28, 24));
    }

    public void atualizarMaoJogador() {
        painelMaoJogador.removeAll();

        List<Carta> cartasMao = acessorio.getMaoJogador();
        for (Carta carta : cartasMao) {
            MolduraCarta molduraCarta = new MolduraCarta(carta);

            molduraCarta.setTransferHandler(new TransferHandler("carta") {
                @Override
                public int getSourceActions(JComponent c) {
                    return MOVE;
                }

                @Override
                protected Transferable createTransferable(JComponent c) {
                    return new TransferableCarta(carta);
                }

                @Override
                protected void exportDone(JComponent source, Transferable data, int action) {
                    if (action == MOVE) {
                        try {
                            Carta carta = (Carta) data.getTransferData(new DataFlavor(Carta.class, "Carta"));
                            acessorio.removerCartaDaMao(carta);

                            painelMaoJogador.remove(source);
                            painelMaoJogador.revalidate();
                            painelMaoJogador.repaint();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

            molduraCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    JComponent comp = (JComponent) e.getSource();
                    TransferHandler handler = comp.getTransferHandler();
                    handler.exportAsDrag(comp, e, TransferHandler.MOVE);
                }
            });

            painelMaoJogador.add(molduraCarta);
        }

        painelMaoJogador.revalidate();
        painelMaoJogador.repaint();
    }

    public void adicionarCartaAoCampoJogador(Carta carta) {
        if (painelCampoJogador.getComponentCount() < 9) {
            if (!cartasCampoJogador.containsKey(carta)) {
                MolduraCarta molduraCarta = new MolduraCarta(carta);
                molduraCarta.setPreferredSize(new Dimension(120, 160));
                cartasCampoJogador.put(carta, molduraCarta);  // Salva a moldura da carta
            }

            // Atualiza os atributos visuais da moldura com os valores atuais da carta
            MolduraCarta moldura = cartasCampoJogador.get(carta);
            moldura.atualizarAtributos(carta.getAtaque(), carta.getVida());

            painelCampoJogador.add(moldura);
            painelCampoJogador.revalidate();
            painelCampoJogador.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "Você não pode adicionar mais de 9 cartas no tabuleiro.");
        }
    }


    public void adicionarCartaAoCampoInimigo(Carta carta) {
        if (painelCampoInimigo.getComponentCount() < 9) {
            if (!cartasCampoInimigo.containsKey(carta)) {
                MolduraCarta molduraCarta = new MolduraCarta(carta);
                molduraCarta.setPreferredSize(new Dimension(120, 160));
                cartasCampoInimigo.put(carta, molduraCarta);  // Salva a moldura da carta
            }

            painelCampoInimigo.add(cartasCampoInimigo.get(carta));
            painelCampoInimigo.revalidate();
            painelCampoInimigo.repaint();
        } else {
            JOptionPane.showMessageDialog(null, "O inimigo não pode adicionar mais de 9 cartas no tabuleiro.");
        }
    }

    public void atualizarCampoJogador() {
        painelCampoJogador.removeAll();
        for (MolduraCarta molduraCarta : cartasCampoJogador.values()) {
            painelCampoJogador.add(molduraCarta);
        }
        painelCampoJogador.revalidate();
        painelCampoJogador.repaint();
    }

    public void atualizarCampoInimigo() {
        painelCampoInimigo.removeAll();
        for (MolduraCarta molduraCarta : cartasCampoInimigo.values()) {
            painelCampoInimigo.add(molduraCarta);
        }
        painelCampoInimigo.revalidate();
        painelCampoInimigo.repaint();
    }

    // Remover o método iniciarBatalha para evitar problemas com a vida das cartas

    protected void removerCartaDoCampo(Carta carta, JPanel painel) {
        Component[] componentes = painel.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof MolduraCarta) {
                MolduraCarta molduraCarta = (MolduraCarta) componente;
                if (molduraCarta.getCarta() == carta) {
                    painel.remove(molduraCarta);
                    break;
                }
            }
        }
        painel.revalidate();
        painel.repaint();
    }

    public JPanel getPainelMaoJogador() {
        return painelMaoJogador;
    }

    public JPanel getPainelCampoJogador() {
        return painelCampoJogador;
    }

    public JPanel getPainelCampoInimigo() {
        return painelCampoInimigo;
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
