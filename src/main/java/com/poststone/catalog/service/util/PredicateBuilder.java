package com.poststone.catalog.service.util;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

@Getter
@NoArgsConstructor
public class PredicateBuilder {

    private BooleanExpression expression = Expressions.asBoolean(true).isTrue();

    public <V> void add(V value, Function<V, BooleanExpression> function) {
        if (Objects.nonNull(value) && !isEmptyIfString(value) && !isEmptyIfCollection(value)) {
            expression = Objects.isNull(expression) ? function.apply(value) : expression.and(function.apply(value));
        }
    }

    private <V> boolean isEmptyIfString(V value) {
        return value instanceof String && ((String) value).isEmpty();
    }

    private <V> boolean isEmptyIfCollection(V value) {
        return value instanceof Collection && ((Collection) value).isEmpty();
    }
}
