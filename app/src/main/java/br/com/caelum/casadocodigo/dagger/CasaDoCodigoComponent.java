package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import dagger.Component;

@Component(modules = CasaDoCodigoModulo.class)
@Singleton
public interface CasaDoCodigoComponent {
    void injeta(CarrinhoActivity carrinhoActivity);

    void injeta(DetalhesLivroFragment detalhesLivroFragment);
}
