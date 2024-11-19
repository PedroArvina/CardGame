package game;

import cards.Carta;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DropTarget;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DnDConstants;


public class Estrutura {

    private JPanel painelMaoJogador1;
    private JPanel painelMaoJogador2;
    private JPanel painelCampoJogador1;
    private JPanel painelCampoJogador2;
    private Acessorio acessorioJogador1;
    private Acessorio acessorioJogador2;
    private Mana manaJogador1;
    private Mana manaJogador2;
    private ControleTurnos controleTurnos;
    private List<Carta> cemiterio;
    public Map<Carta, String> donoCartasCemiterio = new HashMap<>();
    private Habilidades habilidades;

    private Map<Carta, MolduraCarta> cartasCampoJogador1;
    private Map<Carta, MolduraCarta> cartasCampoJogador2;

    public Estrutura(Acessorio acessorioJogador1, Acessorio acessorioJogador2, Mana manaJogador1, Mana manaJogador2, ControleTurnos controleTurnos) {
        this.acessorioJogador1 = acessorioJogador1;
        this.acessorioJogador2 = acessorioJogador2;
        this.manaJogador1 = manaJogador1;
        this.manaJogador2 = manaJogador2;
        this.controleTurnos = controleTurnos;
        this.habilidades = new Habilidades();
        this.cemiterio = new ArrayList<>();
        

        cartasCampoJogador1 = new HashMap<>();
        cartasCampoJogador2 = new HashMap<>();

        criarPainelMaoJogador1();
        criarPainelMaoJogador2();
        criarPainelCampoJogador1();
        criarPainelCampoJogador2();

        atualizarMaoJogador1();
        atualizarMaoJogador2();
    }

    // Método para atualizar o estado das mãos de acordo com o turno
    public void atualizarEstadoPainelMaoJogador(boolean turnoDoJogador1) {
        painelMaoJogador1.setEnabled(turnoDoJogador1); // Habilita a mão do jogador 1 se for o turno dele
        painelMaoJogador2.setEnabled(!turnoDoJogador1); // Habilita a mão do jogador 2 se não for o turno do jogador 1
    }
    public void atualizarPainel(JPanel painel) {
        painel.revalidate();
        painel.repaint();
    }

    private void criarPainelMaoJogador1() {
        painelMaoJogador1 = new JPanel(new FlowLayout(FlowLayout.CENTER, -10, 0));
        painelMaoJogador1.setBackground(new Color(102, 51, 0));
        painelMaoJogador1.setPreferredSize(new Dimension(800, 200));
    }

    private void criarPainelMaoJogador2() {
        painelMaoJogador2 = new JPanel(new FlowLayout(FlowLayout.CENTER, -10, 0));
        painelMaoJogador2.setBackground(new Color(102, 51, 0));
        painelMaoJogador2.setPreferredSize(new Dimension(800, 200));
    }

    public void adicionarCartaAoCemiterio(Carta carta, boolean isJogador1) {
        String dono = isJogador1 ? "Jogador 1" : "Jogador 2";
        cemiterio.add(carta);
        donoCartasCemiterio.put(carta, dono);
        System.out.println("Carta " + carta.getNome() + " (" + dono + ") foi enviada ao cemitério.");
    }

    public List<Carta> getCemiterio() {
        return cemiterio;
    }

    private void criarPainelCampoJogador1() {
        painelCampoJogador1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelCampoJogador1.setBackground(new Color(34, 28, 24));

        // Adiciona suporte a drop
        new DropTarget(painelCampoJogador1, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = evt.getTransferable();
                    Carta carta = (Carta) transferable.getTransferData(new DataFlavor(Carta.class, "Carta"));

                    // Adicionar a carta ao campo do jogador 1
                    adicionarCartaAoCampo(carta, true);
                    evt.dropComplete(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });
    }

    private void criarPainelCampoJogador2() {
        painelCampoJogador2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        painelCampoJogador2.setBackground(new Color(34, 28, 24));

        // Adiciona suporte a drop
        new DropTarget(painelCampoJogador2, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_MOVE);
                    Transferable transferable = evt.getTransferable();
                    Carta carta = (Carta) transferable.getTransferData(new DataFlavor(Carta.class, "Carta"));

                    // Adicionar a carta ao campo do jogador 2
                    adicionarCartaAoCampo(carta, false);
                    evt.dropComplete(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });
    }

    public void atualizarMaoJogador1() {
        painelMaoJogador1.removeAll();

        List<Carta> cartasMao = acessorioJogador1.getMaoJogador();
        for (Carta carta : cartasMao) {
            MolduraCarta molduraCarta = new MolduraCarta(carta, this, controleTurnos, true);

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

                            if (carta == null) {
                                System.out.println("Erro: Carta nula durante exportDone.");
                                return;
                            }

                            boolean cartaNoTabuleiro = Estrutura.this.getMolduraCartaPorCarta(carta) != null;

                            if (cartaNoTabuleiro && source.getParent() != null) {
                                source.getParent().remove(source);
                                source.getParent().revalidate();
                                source.getParent().repaint();
                            } else {
                                System.out.println("Aviso: Componente não tem um pai ou carta não está no tabuleiro.");
                            }

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

            painelMaoJogador1.add(molduraCarta);
        }

        painelMaoJogador1.revalidate();
        painelMaoJogador1.repaint();
    }

    public void atualizarMaoJogador2() {
        painelMaoJogador2.removeAll();

        List<Carta> cartasMao = acessorioJogador2.getMaoJogador();
        for (Carta carta : cartasMao) {
            MolduraCarta molduraCarta = new MolduraCarta(carta, this, controleTurnos, false);

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

                            if (carta == null) {
                                System.out.println("Erro: Carta nula durante exportDone.");
                                return;
                            }

                            boolean cartaNoTabuleiro = Estrutura.this.getMolduraCartaPorCarta(carta) != null;

                            if (cartaNoTabuleiro && source.getParent() != null) { // Verificar se há pai
                                source.getParent().remove(source);
                                source.getParent().revalidate();
                                source.getParent().repaint();
                            } else {
                                System.out.println("Aviso: Componente não tem um pai ou carta não está no tabuleiro.");
                            }

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

            painelMaoJogador2.add(molduraCarta);
        }

        painelMaoJogador2.revalidate();
        painelMaoJogador2.repaint();
    }

    public void adicionarCartaAoCampo(Carta carta, boolean isJogador1) {
        JPanel painelCampo = isJogador1 ? painelCampoJogador1 : painelCampoJogador2;
        Mana manaAtual = isJogador1 ? manaJogador1 : manaJogador2;

        if (painelCampo.getComponentCount() >= 9) {
            JOptionPane.showMessageDialog(null, "Não é possível adicionar mais de 9 cartas no tabuleiro.");
            return;
        }

        if (manaAtual.getManaAtual() < carta.getMana()) {
            JOptionPane.showMessageDialog(null, "Mana insuficiente para jogar esta carta.");
            return;
        }

        manaAtual.reduzirMana(carta.getMana());

        MolduraCarta molduraCarta = new MolduraCarta(carta, this, controleTurnos, isJogador1);
        molduraCarta.setPreferredSize(new Dimension(120, 160));

        if (isJogador1) {
            cartasCampoJogador1.put(carta, molduraCarta);
        } else {
            cartasCampoJogador2.put(carta, molduraCarta);
        }

        painelCampo.add(molduraCarta);
        painelCampo.revalidate();
        painelCampo.repaint();

        // Ativar habilidade ao adicionar ao campo
        habilidades.ativarHabilidade(carta);
    }




    public void removerCartaDoCampo(Carta carta, JPanel painel) {
        Component[] componentes = painel.getComponents();
        for (Component componente : componentes) {
            if (componente instanceof MolduraCarta) {
                MolduraCarta molduraCarta = (MolduraCarta) componente;
                if (molduraCarta.getCarta() == carta) {
                    adicionarCartaAoCemiterio(carta, painel == painelCampoJogador1);
                    painel.remove(molduraCarta);
                    break;
                }
            }
        }
        painel.revalidate();
        painel.repaint();
    }

    public MolduraCarta getMolduraCartaPorCarta(Carta carta) {
        for (Component c : painelCampoJogador1.getComponents()) {
            if (c instanceof MolduraCarta) {
                MolduraCarta moldura = (MolduraCarta) c;
                if (moldura.getCarta().equals(carta)) {
                    return moldura;
                }
            }
        }

        for (Component c : painelCampoJogador2.getComponents()) {
            if (c instanceof MolduraCarta) {
                MolduraCarta moldura = (MolduraCarta) c;
                if (moldura.getCarta().equals(carta)) {
                    return moldura;
                }
            }
        }

        return null;
    }

    public JPanel getPainelMaoJogador1() {
        return painelMaoJogador1;
    }

    public JPanel getPainelMaoJogador2() {
        return painelMaoJogador2;
    }

    public JPanel getPainelCampoJogador1() {
        return painelCampoJogador1;
    }

    public JPanel getPainelCampoJogador2() {
        return painelCampoJogador2;
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
