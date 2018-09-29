package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo.interfaces.LivroDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;

public class MainActivity extends AppCompatActivity implements LivroDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exibe(new ListaLivrosFragment(), false);

    }

    @Override
    public void lidaCom(Livro livro) {

        exibe(DetalhesLivroFragment.com(livro), true);

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
