package game;

import javax.swing.*;
import java.awt.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;

import cards.Carta;

public class MolduraCarta extends JPanel implements DragGestureListener, DragSourceListener {

    private Carta carta;
    private DragSource dragSource;

    public MolduraCarta(Carta carta) {
        this.carta = carta;
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

        JLabel labelAtaque = new JLabel(String.valueOf(carta.getAtaque()));
        labelAtaque.setFont(new Font("Serif", Font.BOLD, 16));
        labelAtaque.setForeground(Color.RED);
        painelAtributos.add(labelAtaque);

        JLabel labelVida = new JLabel(String.valueOf(carta.getVida()));
        labelVida.setFont(new Font("Serif", Font.BOLD, 16));
        labelVida.setForeground(Color.GREEN);
        painelAtributos.add(labelVida);

        add(painelAtributos, BorderLayout.SOUTH);

        
        setToolTipText("<html><b>" + carta.getNome() + "</b><br/>" + carta.getDescricao() + "</html>");

        
        dragSource = new DragSource();
        dragSource.createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_MOVE, this);
    }

    public Carta getCarta() {
        return carta;
    }

    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        Transferable transferable = new TransferableCarta(carta);
        dragSource.startDrag(dge, DragSource.DefaultMoveDrop, transferable, this);
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
    public void dragDropEnd(DragSourceDropEvent dsde) {}

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
