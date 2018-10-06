package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.events.RefreshEvent;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ErroFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static ErroFragment com(Throwable erro) {
        ErroFragment erroFragment = new ErroFragment();

        Bundle argumentos = new Bundle();
        argumentos.putSerializable("erro", erro);
        erroFragment.setArguments(argumentos);

        return erroFragment;
    }


    @BindView(R.id.fragment_erro_mensagem)
    TextView mensagem;

    @BindView(R.id.fragment_erro_swipe)
    SwipeRefreshLayout swipe;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_erro, container, false);
        ButterKnife.bind(this, view);

        swipe.setOnRefreshListener(this);
        swipe.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_dark, android.R.color.holo_red_light);

        Bundle arguments = getArguments();

        Throwable erro = (Throwable) arguments.getSerializable("erro");

        mensagem.setText(erro.getMessage());

        return view;
    }

    @Override
    public void onRefresh() {
        EventBus.getDefault().post(new RefreshEvent());
    }
}
