package br.com.caelum.casadocodigo.converter;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class ItemServiceRequestConverter implements Converter<String, RequestBody> {

    @Override
    public RequestBody convert(String json) throws IOException {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }
}
