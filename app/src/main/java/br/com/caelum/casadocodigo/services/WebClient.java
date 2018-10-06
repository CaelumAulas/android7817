package br.com.caelum.casadocodigo.services;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient {

    public void buscaLivros(int indicePrimeiro, int qtd) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://cdcmob.herokuapp.com/")
                //.addConverterFactory(GsonConverterFactory.create()) -> usando gson na vida :)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();


        LivroService service = retrofit.create(LivroService.class);


        Call<ArrayList<Livro>> chamada = service.buscaLivros(qtd, indicePrimeiro);


        chamada.enqueue(new Callback<ArrayList<Livro>>() {
            @Override
            public void onResponse(Call<ArrayList<Livro>> call, Response<ArrayList<Livro>> response) {
                ArrayList<Livro> livros = response.body();
                EventBus.getDefault().post(livros);
            }

            @Override
            public void onFailure(Call<ArrayList<Livro>> call, Throwable erro) {
                EventBus.getDefault().post(erro);

            }
        });
    }


}
