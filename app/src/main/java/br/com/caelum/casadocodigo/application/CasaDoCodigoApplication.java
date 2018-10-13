package br.com.caelum.casadocodigo.application;

import android.app.Application;

import br.com.caelum.casadocodigo.dagger.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.dagger.DaggerCasaDoCodigoComponent;

public class CasaDoCodigoApplication extends Application {


    private CasaDoCodigoComponent component;

    private static CasaDoCodigoApplication instance;

    public static CasaDoCodigoApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        component = DaggerCasaDoCodigoComponent.create();

    }


    public CasaDoCodigoComponent getComponent() {
        return component;
    }
}
