package cards;

import java.util.HashMap;
import java.util.Map;

public class DefinicaoCartaEncantamento {

    private Map<Integer, Encantamento> encantamentosDefinidos;

    public DefinicaoCartaEncantamento() {
        encantamentosDefinidos = new HashMap<>();
        definirEncantamentos();
    }

    private void definirEncantamentos() {
        // Atualizando os IDs para a faixa correta de 36 a 45
        encantamentosDefinidos.put(36, new Encantamento("Pergaminho do Mago", 2, "Aumenta a vida de Aliados em 2", 1, 1, "src/fotos/36.png", 36));
        encantamentosDefinidos.put(37, new Encantamento("Pergaminho do Mago", 2, "Aumenta a vida de Aliados em 2", 1, 1, "src/fotos/37.png", 37));
        encantamentosDefinidos.put(38, new Encantamento("Pergaminho do Mago", 2, "Aumenta a vida de Aliados em 2", 1, 1, "src/fotos/38.png", 38));
        encantamentosDefinidos.put(39, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida de Aliados em 4", 1, 1, "src/fotos/39.png", 39));
        encantamentosDefinidos.put(40, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida de Aliados em 4", 1, 1, "src/fotos/40.png", 40));
        encantamentosDefinidos.put(41, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida de Aliados em 4", 1, 1, "src/fotos/41.png", 41));
        encantamentosDefinidos.put(42, new Encantamento("Cristal do Pensamento", 3, "Aumenta o ataque de Aliados em 2", 1, 1, "src/fotos/42.png", 42));
        encantamentosDefinidos.put(43, new Encantamento("Cristal do Pensamento", 3, "Aumenta o ataque de Aliados em 2", 1, 1, "src/fotos/43.png", 43));
        encantamentosDefinidos.put(44, new Encantamento("Cristal do Pensamento", 3, "Aumenta o ataque de Aliados em 2", 1, 1, "src/fotos/44.png", 44));
        encantamentosDefinidos.put(45, new Encantamento("Escudo Celestial", 10, "Aumenta a vida de Aliados em 9", 1, 1, "src/fotos/45.png", 45));
    }

    public Encantamento buscarEncantamento(int id) {
        return encantamentosDefinidos.get(id);
    }
}
