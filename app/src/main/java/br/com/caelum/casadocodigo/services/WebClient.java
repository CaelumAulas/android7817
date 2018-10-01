package br.com.caelum.casadocodigo.services;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.interfaces.LivroDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebClient {

    private LivroDelegate delegate;

    public WebClient(LivroDelegate delegate) {
        this.delegate = delegate;
    }


    public void buscaLivros() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cdcmob.herokuapp.com/")
                //.addConverterFactory(GsonConverterFactory.create()) -> usando gson na vida :)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();


        LivroService service = retrofit.create(LivroService.class);


        Call<ArrayList<Livro>> chamada = service.buscaLivros();


        chamada.enqueue(new Callback<ArrayList<Livro>>() {
            @Override
            public void onResponse(Call<ArrayList<Livro>> call, Response<ArrayList<Livro>> response) {
                ArrayList<Livro> livros = response.body();
                delegate.lidaCom(livros);
            }

            @Override
            public void onFailure(Call<ArrayList<Livro>> call, Throwable t) {
                delegate.lidaCom(t);
            }
        });
    }


}
