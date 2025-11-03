package it.unibo.inner.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IterableImpl<T> implements IterableWithPolicy<T>{
    
    private List<T> list;
    private int i;

    public IterableImpl(final T[] elements){
       this.list = new ArrayList<>();
       for(T i : elements){
            list.add(i);
       }
    }

    public void setIterationPolicy(Predicate<T> filter){
    }

    public InnerIterator iterator(){
        return new InnerIterator();
    }

    class InnerIterator implements Iterator<T>{
        private int i;

        public InnerIterator(){
            this.i = -1;
        }

        public boolean hasNext(){
            if(i + 1 < IterableImpl.this.list.size()){
                return true;
            }
            return false;
        }

        public T next(){
            if(hasNext()){
                i++;
                return IterableImpl.this.list.get(i);
            }
            return null;
        }
    }
}