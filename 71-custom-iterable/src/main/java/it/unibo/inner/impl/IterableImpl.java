package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterableImpl<T> implements IterableWithPolicy<T>{
    
    private List<T> list;
    private Predicate<T> filter;

    public IterableImpl(final T[] elements){
       this(elements, new Predicate<T> (){
            @Override
            public boolean test(T elem) {
                return true;
            }
        })
    }

    public IterableImpl(final T[] elements, final Predicate<T> filter){
       this.list = new ArrayList<>();
       for(T i : elements){
            list.add(i);
       }
       this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.filter = filter;
    }

    public InnerIterator iterator(){
        return new InnerIterator();
    }

    class InnerIterator implements Iterator<T>{
        private int i;

        public InnerIterator(){
            this.i = -1;
        }

        @Override
        public boolean hasNext(){
            if(i + 1 < IterableImpl.this.list.size()){
                return true;
            }
            return false;
        }

        @Override
        public T next(){
            while(hasNext()){
                i++;
                if(IterableImpl.this.filter.test(IterableImpl.this.list.get(i))){
                    return IterableImpl.this.list.get(i);
                }
            }
            return null;
        }
    }
}