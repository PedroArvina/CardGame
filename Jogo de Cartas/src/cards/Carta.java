package cards;

public abstract class Carta {

    private String nome;
    private int custoMana;
    private String descricao;

    public Carta(String nome, int custoMana, String descricao) {
        this.nome = nome;
        this.custoMana = custoMana;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public int getCustoMana() {
        return custoMana;
    }

    public String getDescricao() {
        return descricao;
    }

    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.println("Custo de Mana: " + custoMana);
        System.out.println("Descrição: " + descricao);
    }

    
}
