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
        encantamentosDefinidos.put(35, new Encantamento("Pergaminho do mago", 3, "Aumenta a vida da criatura em 1", 5, 0, "src/fotos/36.png") {
            
        });

        encantamentosDefinidos.put(36, new Encantamento("Pergaminho do mago", 3, "Aumenta a vida da criatura em 1", 5, 0, "src/fotos/37.png") {
            
            
        });

        encantamentosDefinidos.put(37, new Encantamento("Pergaminho do mago", 3, "Aumenta a vida da criatura em 1", 5, 0, "src/fotos/38.png") {
            
            
        });

        encantamentosDefinidos.put(38, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida da criatura em 3", 4, 0, "src/fotos/39.png") {
            
            
        });

        encantamentosDefinidos.put(39, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida da criatura em 3", 4, 0, "src/fotos/40.png") {
            
            
        });

        encantamentosDefinidos.put(40, new Encantamento("Proteção Ancestral", 3, "Aumenta a vida da criatura em 3", 4, 0, "src/fotos/41.png") {
            
        });

        encantamentosDefinidos.put(41, new Encantamento("Cristal do Pensamento", 3, "Aumenta o ataque da criatura em 2", 0, 3, "src/fotos/42.png") {
            
        });

        encantamentosDefinidos.put(42, new Encantamento("Cristal do Pensamento", 3, "Aumenta o ataque da criatura em 2", 0, 3, "src/fotos/43.png") {
            
        });

        encantamentosDefinidos.put(42, new Encantamento("Cristal do Pensamento", 3, "Aumenta o ataque da criatura em 2", 0, 3, "src/fotos/44.png") {
            
        });

        encantamentosDefinidos.put(44, new Encantamento("Escudo Celestial", 5, "Aumenta a vida da criatura em 9", 7, 0, "src/fotos/45.png") {
            
        });

       
    }

    public Encantamento buscarEncantamento(int id) {
        return encantamentosDefinidos.get(id);
    }
}
