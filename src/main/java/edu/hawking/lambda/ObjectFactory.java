package edu.hawking.lambda;

@FunctionalInterface
public interface ObjectFactory<T> {
    T getObject();
}
