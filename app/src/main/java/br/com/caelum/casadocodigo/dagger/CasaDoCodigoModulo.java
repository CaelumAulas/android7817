package br.com.caelum.casadocodigo.dagger;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import dagger.Module;
import dagger.Provides;

@Module
public class CasaDoCodigoModulo {

    @Provides
    @Singleton
    public Carrinho getCarrinho(){
        return new Carrinho();
    }
}
