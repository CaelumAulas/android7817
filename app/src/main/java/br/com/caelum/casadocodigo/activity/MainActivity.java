package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.events.RefreshEvent;
import br.com.caelum.casadocodigo.fragment.CarregandoFragment;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ErroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.services.WebClient;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (naoTiverDados(savedInstanceState)) {
            buscaLivros();
            exibe(new CarregandoFragment(), false);
        }
    }

    private void buscaLivros() {
        new WebClient().buscaLivros();
    }

    private boolean naoTiverDados(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe
    public void lidaCom(Livro livro) {

        exibe(DetalhesLivroFragment.com(livro), true);

    }

    @Subscribe
    public void lidaCom(ArrayList<Livro> livros) {
        exibe(ListaLivrosFragment.com(livros), false);
    }

    @Subscribe
    public void lidaCom(Throwable erro) {

        exibe(ErroFragment.com(erro), false);
    }


    @Subscribe
    public void recebe(RefreshEvent event){
        buscaLivros();
    }

    public void exibe(Fragment fragment, boolean podeEmpilhar) {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.frame_principal, fragment);

        if (podeEmpilhar) {
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }
}
