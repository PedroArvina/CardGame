package game;

import cards.Carta;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.util.HashMap;
import java.util.Map;

public class MolduraCarta extends JPanel implements DragGestureListener, DragSourceListener {

    private Carta carta;
    private DragSource dragSource;
    private boolean jaAtacou; 
    private JLabel labelAtaque;
    private JLabel labelVida;
    private Timer timer; 

    
    private static final Map<String, Integer> imagemParaID = new HashMap<>();

    static {
        // Mapeando as imagens para os respectivos IDs
        imagemParaID.put("src/fotos/1.png", 0);
        imagemParaID.put("src/fotos/2.png", 1);
        imagemParaID.put("src/fotos/3.png", 2);
        imagemParaID.put("src/fotos/4.png", 3);
        imagemParaID.put("src/fotos/5.png", 4);
        imagemParaID.put("src/fotos/6.png", 5);
        imagemParaID.put("src/fotos/7.png", 6);
        imagemParaID.put("src/fotos/8.png", 7);
        imagemParaID.put("src/fotos/9.png", 8);
        imagemParaID.put("src/fotos/10.png", 9);
        imagemParaID.put("src/fotos/11.png", 10);
        imagemParaID.put("src/fotos/12.png", 11);
        imagemParaID.put("src/fotos/13.png", 12);
        imagemParaID.put("src/fotos/14.png", 13);
        imagemParaID.put("src/fotos/15.png", 14);
        imagemParaID.put("src/fotos/16.png", 15);
        imagemParaID.put("src/fotos/17.png", 16);
        imagemParaID.put("src/fotos/18.png", 17);
        imagemParaID.put("src/fotos/19.png", 18);
        imagemParaID.put("src/fotos/20.png", 19);
        imagemParaID.put("src/fotos/21.png", 20);
        imagemParaID.put("src/fotos/22.png", 21);
        imagemParaID.put("src/fotos/23.png", 22);
        imagemParaID.put("src/fotos/24.png", 23);
        imagemParaID.put("src/fotos/25.png", 24);
        imagemParaID.put("src/fotos/26.png", 25);
        imagemParaID.put("src/fotos/27.png", 26);
        imagemParaID.put("src/fotos/28.png", 27);
        imagemParaID.put("src/fotos/29.png", 28);
        imagemParaID.put("src/fotos/30.png", 29);
        imagemParaID.put("src/fotos/31.png", 30);
        imagemParaID.put("src/fotos/32.png", 31);
        imagemParaID.put("src/fotos/33.png", 32);
        imagemParaID.put("src/fotos/34.png", 33);
    }

    public MolduraCarta(Carta carta) {
        this.carta = carta;
        this.jaAtacou = false; 

        setLayout(new BorderLayout());
        setBackground(new Color(139, 69, 19));
        setPreferredSize(new Dimension(120, 160));

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

                    
                    realizarCombate(cartaAtacante, carta);
                    evt.dropComplete(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    evt.dropComplete(false);
                }
            }
        });
    }

    public Carta getCarta() {
        return carta;
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

    
    private void realizarCombate(Carta cartaAtacante, Carta cartaAtacada) {
        if (cartaAtacante != null && cartaAtacada != null) {
            
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);  
            int alturaJanela = frame.getHeight();  // Altura total da janela
            Point posicaoCartaAtacante = getLocationOnScreen();  

            if (posicaoCartaAtacante.y <= (alturaJanela / 2)) {  
                
                Combate combate = new Combate();
                combate.realizarCombate(cartaAtacante, cartaAtacada, true);

                
                atualizarAtributos(cartaAtacada.getAtaque(), cartaAtacada.getVida());

                
                if (cartaAtacada.getVida() <= 0) {
                    JOptionPane.showMessageDialog(this, cartaAtacada.getNome() + " foi destruída!");
                    removerCartaDoCampo();
                }

                
                if (cartaAtacante.getVida() <= 0) {
                    JOptionPane.showMessageDialog(this, cartaAtacante.getNome() + " foi destruída!");
                }
            } else {
                
                JOptionPane.showMessageDialog(this, "A carta precisa estar acima de 50% da altura da janela para atacar.");
            }
        }
    }




    
    public void atualizarAtributos(int ataque, int vida) {
        labelAtaque.setText(String.valueOf(ataque));  // Atualiza o texto do rótulo de ataque
        labelVida.setText(String.valueOf(vida));      // Atualiza o texto do rótulo de vida
        repaint();  
    }

    
 
    private void removerCartaDoCampo() {
        if (carta.getVida() <= 0) {  
            if (getParent() != null) {  
                Container parent = getParent();  
                parent.remove(this);  
                parent.revalidate();  
                parent.repaint();  
            }
            timer.stop();  
        }
    }


    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        if (!jaAtacou) { // Só permite arrastar se a carta ainda não tiver atacado no turno
            Transferable transferable = new TransferableCarta(carta);
            dragSource.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);
        } else {
            System.out.println("Esta carta já atacou neste turno.");
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
