package com.example.assignment3;

import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>{
    Comparator<T> comparator;

    public SortedDoubleLinkedList(Comparator<T> comparableObject){
        comparator = comparableObject;
        front = null;
        end = null;
    }

    public void add(T data){
        Node<T> node = new Node(data);
        if(front == null){ front = node; end = node; return;}
        if(front == end){
            if(comparator.compare(node.data, front.data) == 1){
                front.next = node;
                node.previous = front;
                end = node;
            }else {
                front = node;
                front.next = end;
                end.previous = front;
            }
            return;
        }
        Node<T> temp = front;
        while(temp != null){
            if(comparator.compare(node.data, temp.data) == -1){
                node.next = temp;
                node.previous = temp.previous;
                temp.previous = node;
                if(node.previous != null)
                node.previous.next = node;
                else front = node;
                return;
            }
            temp = temp.next;
        }
        end.next = node;
        node.previous = end;
        end = node;
    }
    public void addToFront(T data){
        throw new UnsupportedOperationException();
    }
    public void addToEnd(T data){
        throw new UnsupportedOperationException();
    }
    public ListIterator<T> iterator(){
        return super.iterator();
    }

    public void remove(T data, Comparator<T> comparator){
        super.remove(data, comparator);
    }
}
