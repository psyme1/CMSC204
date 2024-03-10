package com.example.assignment3;

import java.util.*;

public class BasicDoubleLinkedList<T> {
    protected Node<T> front;
    protected Node<T> end;
    private int size;
    public BasicDoubleLinkedList(){
        this.front = null;
        this.end = null;
        this.size = 0;
    }

    public void addToEnd(T data){
        Node<T> node = new Node<>(data);
        if(end == null){ end = node; front = node; return;}
        if(end == front){end = node; node.previous = front; front.next = node; return;}
        node.previous = end;
        end.next = node;
        end = node;
    }

    public void addToFront(T data){
        Node<T> node = new Node<>(data);
        if(front == null){front = node; end = node; return;}
        if(end == front){front = node; end.previous = node; front.next = end; return;}
        node.next = front;
        front.previous = node;
        front = node;
    }

    public T getFirst(){
        return front.data;
    }

    public T getLast(){
        return end.data;
    }

    public int getSize(){
        if(end == front) return 1;
        if(front == null) return 0;
        int count = 1;
        Node<T> temp = front;
        while(temp.next != null){
            count++;
            temp = temp.next;
        }
        return count;
    }

    public T retrieveFirstElement(){
        Node<T> temp = front;
        if(front == null) return null;
        if(front.next == null){front = null; return temp.data;}
        front.next.previous = null;
        front = front.next;
        return temp.data;
    }

    public T retrieveLastElement(){
        Node<T> temp = end;
        if(end == null) return null;
        if(end.previous == null) {end = null; return temp.data;}
        end.previous.next = null;
        end = end.previous;
        return temp.data;
    }

    public ArrayList<T> toArrayList(){
        ArrayList<T> arrayList = new ArrayList<>();
        Node<T> temp = front;
        while(temp != null){
            arrayList.add(temp.data);
            temp = temp.next;
        }
        return arrayList;
    }

    public ListIterator<T> iterator(){
        return new DoubleLinkedListIterator();
    }


    public void remove(T data, Comparator<T> comparator){
        Node<T> temp = front;
        if(front == null) return;
        while(temp.next != null){
            if(comparator.compare(data, temp.data) == 0){
                if(temp.previous != null){
                    temp.previous.next = temp.next;
                    temp.next.previous = temp.previous;
                }
                if(front == temp) front = temp.next;
                if(end == temp) end = temp.previous;
            }
            temp = temp.next;
        }
    }

    public class Node<T> {
        protected T data;
        protected Node<T> next;
        protected Node<T> previous;
        public Node(T data){
            this.data = data;
        }
    }

    private class DoubleLinkedListIterator implements ListIterator<T>{
        private Node<T> current;
        public DoubleLinkedListIterator(){
            current = front;
        }

        @Override
        public boolean hasNext() {
            return (current != null && current.next != current);
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            return (current.previous != null && current.previous != current);
        }

        @Override
        public T previous() {
            T  data = current.data;
            current = current.previous;
            return data;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T t) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T t) {
            throw new UnsupportedOperationException();
        }
    }

}
