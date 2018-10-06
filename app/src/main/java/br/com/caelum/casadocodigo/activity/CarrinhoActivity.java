package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {


    @BindView(R.id.valor_carrinho)
    TextView valor;

    @BindView(R.id.lista_itens_carrinho)
    RecyclerView listaItens;

    private Carrinho carrinho = new Carrinho();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Carrinho");

        actionBar.setDisplayHomeAsUpEnabled(true);


        List<Item> itens = carrinho.getItens();

        ItensAdapter adapter = new ItensAdapter(itens, this);
        listaItens.setLayoutManager(new LinearLayoutManager(this));
        listaItens.setAdapter(adapter);
        valor.setText("R$ " + carrinho.getValor());


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}
