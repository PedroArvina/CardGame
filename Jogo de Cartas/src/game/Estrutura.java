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
    
    public Acessorio getAcessorioJogador1() {
        return acessorioJogador1;
    }

    public Acessorio getAcessorioJogador2() {
        return acessorioJogador2;
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

                    // Verificar se é o turno do jogador 1
                    if (!controleTurnos.isTurnoDoJogador1()) {
                        // Devolve a carta à mão do jogador 1, sem duplicar
                        List<Carta> maoJogador1 = acessorioJogador1.getMaoJogador();
                        if (!maoJogador1.contains(carta)) {
                            maoJogador1.add(carta);
                        }
                        atualizarMaoJogador1();
                        JOptionPane.showMessageDialog(null, "Não é o seu turno! A carta foi devolvida à sua mão.");
                        evt.dropComplete(false); // Ação inválida
                        return;
                    }

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

                    // Verificar se é o turno do jogador 2
                    if (controleTurnos.isTurnoDoJogador1()) {
                        // Devolve a carta à mão do jogador 2, sem duplicar
                        List<Carta> maoJogador2 = acessorioJogador2.getMaoJogador();
                        if (!maoJogador2.contains(carta)) {
                            maoJogador2.add(carta);
                        }
                        atualizarMaoJogador2();
                        JOptionPane.showMessageDialog(null, "Não é o seu turno! A carta foi devolvida à sua mão.");
                        evt.dropComplete(false); // Ação inválida
                        return;
                    }

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

    
    public List<MolduraCarta> getCartasNoCampo() {
        List<MolduraCarta> cartasNoCampo = new ArrayList<>();
        Component[] componentesCampo1 = painelCampoJogador1.getComponents();
        Component[] componentesCampo2 = painelCampoJogador2.getComponents();

        for (Component c : componentesCampo1) {
            if (c instanceof MolduraCarta) {
                cartasNoCampo.add((MolduraCarta) c);
            }
        }

        for (Component c : componentesCampo2) {
            if (c instanceof MolduraCarta) {
                cartasNoCampo.add((MolduraCarta) c);
            }
        }

        return cartasNoCampo;
    }



    public void atualizarMaoJogador1() {
        painelMaoJogador1.removeAll();

        List<Carta> cartasMao = acessorioJogador1.getMaoJogador();
        boolean turnoDoJogador1 = controleTurnos.isTurnoDoJogador1(); // Verifica o turno

        for (Carta carta : cartasMao) {
            MolduraCarta molduraCarta = new MolduraCarta(carta, this, controleTurnos, true);

            molduraCarta.setTransferHandler(new TransferHandler("carta") {
                @Override
                public int getSourceActions(JComponent c) {
                    // Permitir arrastar apenas no turno do jogador 1, e se a carta não estiver no tabuleiro
                    boolean cartaNoTabuleiro = Estrutura.this.getMolduraCartaPorCarta(carta) != null;
                    return (turnoDoJogador1 && !cartaNoTabuleiro) ? MOVE : NONE;
                }

                @Override
                protected Transferable createTransferable(JComponent c) {
                    if (!turnoDoJogador1) {
                        JOptionPane.showMessageDialog(null, "Não é seu turno! Você não pode jogar esta carta.");
                        return null; // Não permite arrastar no turno errado
                    }

                    boolean cartaNoTabuleiro = Estrutura.this.getMolduraCartaPorCarta(carta) != null;
                    if (cartaNoTabuleiro) {
                        JOptionPane.showMessageDialog(null, "Esta carta já está no tabuleiro.");
                        return null; // Não permite mover se a carta já estiver no tabuleiro
                    }

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

                            if (cartaNoTabuleiro) {
                                System.out.println("A carta já está no tabuleiro e não pode ser movida novamente para ele.");
                                return;
                            }

                            // Remove o componente da interface se for necessário
                            Container parent = source.getParent();
                            if (parent != null) {
                                parent.remove(source);
                                parent.revalidate();
                                parent.repaint();
                                System.out.println("Carta removida da mão com sucesso.");
                            } else {
                                System.out.println("Aviso: Componente não tem um pai.");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Erro ao realizar o movimento da carta.");
                        }
                    }
                }
            });

            molduraCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (turnoDoJogador1) {
                        JComponent comp = (JComponent) e.getSource();
                        TransferHandler handler = comp.getTransferHandler();
                        handler.exportAsDrag(comp, e, TransferHandler.MOVE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Não é seu turno! A carta não pode ser movida.");
                    }
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
        boolean turnoDoJogador2 = !controleTurnos.isTurnoDoJogador1(); // Verifica o turno

        for (Carta carta : cartasMao) {
            MolduraCarta molduraCarta = new MolduraCarta(carta, this, controleTurnos, false);

            molduraCarta.setTransferHandler(new TransferHandler("carta") {
                @Override
                public int getSourceActions(JComponent c) {
                    // Permitir arrastar apenas no turno do jogador 2, e se a carta não estiver no tabuleiro
                    boolean cartaNoTabuleiro = Estrutura.this.getMolduraCartaPorCarta(carta) != null;
                    return (turnoDoJogador2 && !cartaNoTabuleiro) ? MOVE : NONE;
                }

                @Override
                protected Transferable createTransferable(JComponent c) {
                    if (!turnoDoJogador2) {
                        JOptionPane.showMessageDialog(null, "Não é seu turno! Você não pode jogar esta carta.");
                        return null; // Não permite arrastar no turno errado
                    }

                    boolean cartaNoTabuleiro = Estrutura.this.getMolduraCartaPorCarta(carta) != null;
                    if (cartaNoTabuleiro) {
                        JOptionPane.showMessageDialog(null, "Esta carta já está no tabuleiro.");
                        return null; // Não permite mover se a carta já estiver no tabuleiro
                    }

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

                            if (cartaNoTabuleiro) {
                                System.out.println("A carta já está no tabuleiro e não pode ser movida novamente para ele.");
                                return;
                            }

                            // Remove o componente da interface se for necessário
                            Container parent = source.getParent();
                            if (parent != null) {
                                parent.remove(source);
                                parent.revalidate();
                                parent.repaint();
                                System.out.println("Carta removida da mão com sucesso.");
                            } else {
                                System.out.println("Aviso: Componente não tem um pai.");
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Erro ao realizar o movimento da carta.");
                        }
                    }
                }
            });

            molduraCarta.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (turnoDoJogador2) {
                        JComponent comp = (JComponent) e.getSource();
                        TransferHandler handler = comp.getTransferHandler();
                        handler.exportAsDrag(comp, e, TransferHandler.MOVE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Não é seu turno! A carta não pode ser movida.");
                    }
                }
            });

            painelMaoJogador2.add(molduraCarta);
        }

        painelMaoJogador2.revalidate();
        painelMaoJogador2.repaint();
    }


    public void adicionarCartaAoCampo(Carta carta, boolean isJogador1) {
        if (isJogador1 != controleTurnos.isTurnoDoJogador1()) {
            JOptionPane.showMessageDialog(null, "Não é seu turno para jogar esta carta.");
            return; // Bloqueia se não for o turno do jogador correto
        }

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
            acessorioJogador1.getMaoJogador().remove(carta); // Remove da mão
            atualizarMaoJogador1(); // Atualiza a mão visualmente
        } else {
            cartasCampoJogador2.put(carta, molduraCarta);
            acessorioJogador2.getMaoJogador().remove(carta); // Remove da mão
            atualizarMaoJogador2(); // Atualiza a mão visualmente
        }

        painelCampo.add(molduraCarta);
        painelCampo.revalidate();
        painelCampo.repaint();
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
