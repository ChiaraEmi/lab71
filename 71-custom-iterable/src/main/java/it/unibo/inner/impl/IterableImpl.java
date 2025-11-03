package it.unibo.inner.impl;

import it.unibo.inner.api;

public class IterableImpl<T> implements IterableWithPolicy<T>{
    
    private ArrayList<T> list;

    IterableImpl<T>(final <T> elements[]){
       this.list = new ArrayList<T>(elements);
    }

    void setIteartionPolicy(Predicate<T> filter){

    }
}