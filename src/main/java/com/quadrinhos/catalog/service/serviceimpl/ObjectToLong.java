package com.quadrinhos.catalog.service.serviceimpl;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ObjectToLong implements Converter<Object, Integer> {
    @Override
    public Integer convert(Object source) {
        var teste = Integer.valueOf(source.toString());
        return teste;
    }

    @Override
    public <U> Converter<Object, U> andThen(Converter<? super Integer, ? extends U> after) {
        return Converter.super.andThen(after);
    }
}
