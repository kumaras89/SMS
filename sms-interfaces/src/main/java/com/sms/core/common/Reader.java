package com.sms.core.common;


import java.util.function.Function;

/**
 *
 * A monad that can be used for dependency injections
 *
 * Created by Ganesan on 31/05/16.
 */
public class Reader<Conf, T> {

    private Function<Conf, T> read;
    private Reader(Function<Conf, T> read) {
        this.read = read;
    }

    public static <A,B> Reader<A,B> of(Function<A,B> f) {
        return new Reader<>(f);
    }

    public static <A,B> Reader<A,B> pure(B b) {
        return new Reader<>((A a) -> b);
    }

    public <U> Reader<Conf, U> map(Function<T, U> mapper) {
        return of(this.read.andThen(mapper));
    }

    public <V> Reader<Conf,V> flatMap(Function<T,Reader<Conf,V>> toReader) {
        return of(conf -> toReader.apply(read.apply(conf)).read.apply(conf));
    }

    public <BiigerConf> Reader<BiigerConf, T> local(Function<BiigerConf, Conf> extradtFrom) {
        return of(extradtFrom.andThen(read));
    }

    public T with(Conf conf) {
        return read.apply(conf);
    }

}
