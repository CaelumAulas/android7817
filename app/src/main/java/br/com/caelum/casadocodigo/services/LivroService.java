package br.com.caelum.casadocodigo.services;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LivroService {

    @GET("listarLivros")
    Call<ArrayList<Livro>> buscaLivros(
            @Query("qtdLivros") int qtdLivros,
            @Query("indicePrimeiroLivro") int indicePrimeiroLivro);
}
