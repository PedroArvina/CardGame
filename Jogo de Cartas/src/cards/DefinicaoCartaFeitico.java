package cards;

import java.util.HashMap;
import java.util.Map;

public class DefinicaoCartaFeitico {

    private Map<Integer, Feitico> feiticosDefinidos;

    public DefinicaoCartaFeitico() {
        feiticosDefinidos = new HashMap<>();
        definirFeiticos();
    }

    private void definirFeiticos() {
        // Atualizando os IDs para a faixa correta de 46 a 49
        feiticosDefinidos.put(46, new Feitico("Bola de Energia", 4, "Causa 2 de dano a todos do tabuleiro", "Causa 2 de dano a geral", 1, 1, "src/fotos/46.png", 46));
        feiticosDefinidos.put(47, new Feitico("Bola de Energia", 4, "Causa 2 de dano a todos do tabuleiro", "Causa 2 de dano a geral", 1, 1, "src/fotos/47.png", 47));
        feiticosDefinidos.put(48, new Feitico("Bola de Energia", 4, "Causa 2 de dano a todos do tabuleiro", "Causa 2 de dano a geral", 1, 1, "src/fotos/48.png", 48));
        feiticosDefinidos.put(49, new Feitico("Proteção Arcana", 3, "Aumenta 3 de vida a todos", "Aumenta a vida geral em 3", 1, 1, "src/fotos/49.png", 49));
        feiticosDefinidos.put(50, new Feitico("Armagedom", 7, "Mata todos do Tabuleiro", "Kill @e", 1, 1, "src/fotos/50.png", 50));
    }

    public Feitico buscarFeitico(int id) {
        return feiticosDefinidos.get(id);
    }
}
