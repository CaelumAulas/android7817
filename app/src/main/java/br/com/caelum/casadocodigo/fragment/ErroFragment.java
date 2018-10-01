package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ErroFragment extends Fragment {
    public static ErroFragment com(Throwable erro) {
        ErroFragment erroFragment = new ErroFragment();

        Bundle argumentos = new Bundle();
        argumentos.putSerializable("erro", erro);
        erroFragment.setArguments(argumentos);

        return erroFragment;
    }


    @BindView(R.id.fragment_erro_mensagem)
    TextView mensagem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_erro, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments = getArguments();

        Throwable erro = (Throwable) arguments.getSerializable("erro");

        mensagem.setText(erro.getMessage());

        return view;
    }
}
