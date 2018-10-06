package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroAdapter extends RecyclerView.Adapter {

    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,
                                                      int posicao) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.item_livro_par, viewGroup, false);


        return new MeuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int posicao) {

        Livro livro = livros.get(posicao);
        MeuViewHolder holder = (MeuViewHolder) viewHolder;

        holder.nome.setText(livro.getNome());

        Picasso.get().load(livro.getUrlFoto()).fit().into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    class MeuViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_livro_nome)
        TextView nome;

        @BindView(R.id.item_livro_foto)
        ImageView foto;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.item_livro)
        public void clickNoItem(View view) {

            Livro selecionado = livros.get(getAdapterPosition());

            EventBus.getDefault().post(selecionado);
        }
    }
}
