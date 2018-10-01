package br.com.caelum.casadocodigo.interfaces;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;

public interface LivroDelegate {
    void lidaCom(Livro selecionado);

    void lidaCom(ArrayList<Livro> livros);

    void lidaCom(Throwable erro);
}
