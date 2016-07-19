package com.sms.core.common;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * <replace with description of each class>.
 *
 * @author snagasubramaniyam on 27-06-2016 18:08.
 */
public class PredicateBuilder<C> {

    private final List<Predicate> predicates;
    private final Optional<C> criteria;

    private PredicateBuilder(final Optional<C> criteria, final List<Predicate> predicates) {
        this.criteria = criteria;
        this.predicates = predicates;
    }


    public static <T, C> PredicateBuilder<C> of(final Optional<C> criteria) {
        return new PredicateBuilder<>(criteria, new ArrayList<>());
    }

    public static <T, C> PredicateBuilder<C> of(final Optional<C> criteria, final List<Predicate> predicates) {
        return new PredicateBuilder<>(criteria, predicates);
    }

    public <P> PredicateBuilder<C> map( final Function<C, P> getter, final Function<P, Predicate> predicateFunction) {
        final Predicate predicate = criteria.map(getter)
            .filter(p -> p instanceof String ? !((String) p).isEmpty() : true)
            .map(p -> predicateFunction.apply(p))
            .orElse(null);
        if (predicate != null) {
            predicates.add(predicate);
        }
        return of(criteria, new ArrayList<>(predicates));

    }

    public List<Predicate> get() {
        return predicates;
    }

    public Predicate[] getArray() {
        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
