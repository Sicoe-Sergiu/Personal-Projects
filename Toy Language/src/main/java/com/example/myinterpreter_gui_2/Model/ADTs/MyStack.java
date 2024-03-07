package com.example.myinterpreter_gui_2.Model.ADTs;

import com.example.myinterpreter_gui_2.Exceptions.ADTException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{

    Stack<T> stack;
    public MyStack(){ this.stack = new Stack<>(); }


    @Override
    public T pop() throws ADTException {
        if (stack.isEmpty())
            throw new ADTException("Stack is empty!");
        return stack.pop();
    }

    @Override
    public void push(T element) {
        stack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<T> getReversed() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }

    @Override
    public String toString() {
        return this.stack.toString();
    }

}
