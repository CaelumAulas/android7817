package br.com.caelum.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroInvertidoAdapter extends RecyclerView.Adapter {


    private List<Livro> livros;

    public LivroInvertidoAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_livro_impar, parent, false);

        return new NossoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder,
                                 int position) {

        Livro livro = livros.get(position);

        final NossoViewHolder holder = (NossoViewHolder) viewHolder;

        holder.nome.setText(livro.getNome());

        Picasso.get()
                .load(livro.getUrlFoto())
                .fit()
                .into(holder.foto, new Callback() {
                    @Override
                    public void onSuccess() {
                        // já deu certo
                    }

                    @Override
                    public void onError(Exception e) {

                        Picasso.get()
                                .load(R.drawable.livro)
                                .into(holder.foto);
                    }
                });


    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    class NossoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_livro_nome)
        public TextView nome;

        @BindView(R.id.item_livro_foto)
        public ImageView foto;

        public NossoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.item_livro)
        public void clickNaLista() {

            int posicao = getAdapterPosition();

            Livro livro = livros.get(posicao);

            EventBus.getDefault().post(livro);

        }


    }
}