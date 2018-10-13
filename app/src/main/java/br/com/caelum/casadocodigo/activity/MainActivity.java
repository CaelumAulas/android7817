package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

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
        new WebClient().buscaLivros(0, 5);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_main_carrinho) {
            Intent intent = new Intent(this, CarrinhoActivity.class);

            startActivity(intent);
        } else if (item.getItemId() == R.id.menu_main_sair) {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        return false;
    }

    @Subscribe
    public void lidaCom(Livro livro) {


        exibe(DetalhesLivroFragment.com(livro), true);

    }

    @Subscribe
    public void lidaCom(ArrayList<Livro> livros) {

        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentById(R.id.frame_principal);

        if (fragment instanceof ListaLivrosFragment) {

            ListaLivrosFragment livrosFragment = (ListaLivrosFragment) fragment;

            livrosFragment.adiciona(livros);

        } else {
            exibe(ListaLivrosFragment.com(livros), false);
        }
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
