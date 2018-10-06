package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapter;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.services.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {


    @BindView(R.id.fragment_lista_livros)
    RecyclerView lista;
    private boolean carregando;
    private ArrayList<Livro> livros;


    public static ListaLivrosFragment com(ArrayList<Livro> livros) {
        ListaLivrosFragment listaLivrosFragment = new ListaLivrosFragment();

        Bundle argumentos = new Bundle();
        argumentos.putSerializable("livros", livros);

        listaLivrosFragment.setArguments(argumentos);

        return listaLivrosFragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        livros = (ArrayList<Livro>) arguments.getSerializable("livros");


        lista.setAdapter(new LivroAdapter(livros));

        lista.setLayoutManager(new LinearLayoutManager(getContext()));


        Mugen.with(lista, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                carregando = true;
                new WebClient().buscaLivros(livros.size(), 5);
                Snackbar.make(lista, "Carregando", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public boolean isLoading() {
                return carregando;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return false;
            }
        }).start();


        return view;


    }


    @Override
    public void onResume() {
        super.onResume();

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        ActionBar actionBar = activity.getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(false);

        actionBar.setTitle("Catalogo");


    }

    public void adiciona(ArrayList<Livro> novos) {

        carregando = false;
        livros.addAll(novos);
        lista.getAdapter().notifyDataSetChanged();
    }
}
