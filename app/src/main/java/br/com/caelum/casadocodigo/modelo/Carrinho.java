package br.com.caelum.casadocodigo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrinho implements Serializable {

    private List<Item> itens = new ArrayList<>();

    public void adiciona(Item item) {
        itens.add(item);
    }

    public void remove(Item item) {
        itens.remove(item);
    }

    public void limpa() {
        itens.clear();
    }

    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

}
