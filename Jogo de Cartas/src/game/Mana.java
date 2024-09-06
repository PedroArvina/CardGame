package game;

public class Mana {

    private int manaAtual;
    private int manaTotal;
    private static final int MAX_MANA = 10;
    private ManaListener listener;

    public Mana() {
        this.manaTotal = 0;
        this.manaAtual = 0;
    }

    public void setManaListener(ManaListener listener) {
        this.listener = listener;
    }

    
    public void aumentarMana() {
        if (manaTotal < MAX_MANA) {
            manaTotal++;
        }
        recarregarMana();
        System.out.println("Mana Atual: " + manaAtual + " / Mana Total: " + manaTotal); // Para fins de depuração
        if (listener != null) {
            listener.onManaUpdated(manaAtual);
        }
    }

    
    public void recarregarMana() {
        this.manaAtual = this.manaTotal;
        if (listener != null) {
            listener.onManaUpdated(manaAtual);
        }
    }

    
    public void reduzirMana(int custo) {
        this.manaAtual -= custo;
        if (listener != null) {
            listener.onManaUpdated(manaAtual);
        }
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public int getManaTotal() {
        return manaTotal;
    }

    
    public interface ManaListener {
        void onManaUpdated(int mana);
    }
}
