package br.com.caelum.casadocodigo.services;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LivroService {

    @GET("listarLivros?qtdLivros=20&indicePrimeiroLivro=0")
    Call<ArrayList<Livro>> buscaLivros();
}
