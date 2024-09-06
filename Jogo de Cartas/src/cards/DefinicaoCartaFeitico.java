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
        feiticosDefinidos.put(45, new Feitico("Bola de Energia", 4, "Causa 2 de dano a todos do tabuleiro", "Causa 2 de dano a geral", 0, 6, "src/fotos/46.png") {});
        feiticosDefinidos.put(46, new Feitico("Bola de Energia", 4, "Causa 2 de dano a todos do tabuleiro", "Causa 2 de dano a geral", 0, 6, "src/fotos/47.png") {});
        feiticosDefinidos.put(47, new Feitico("Bola de Energia", 4, "Causa 2 de dano a todos do tabuleiro", "Causa 2 de dano a geral", 0, 6, "src/fotos/48.png") {});
        feiticosDefinidos.put(48, new Feitico("Proteção Arcana", 3, "Aumenta 3 de vida a todos", "Aumenta a vida geral em 3", 4, 0, "src/fotos/49.png") {});
        feiticosDefinidos.put(49, new Feitico("Armagedom", 7, "Mata todos do Tabuleiro", "Kill @e", 0, 0, "src/fotos/50.png") {});
    }

    public Feitico buscarFeitico(int id) {
        return feiticosDefinidos.get(id);
    }
}
