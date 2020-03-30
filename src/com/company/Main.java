package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    List list = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) throws Exception {
        NameList nameList = new NameList();
        nameList.add("first");
        class MyThread extends Thread {
            @Override
            public void run() {
                System.out.println(nameList.removeFirst());
            }
        }
       MyThread myThread = new MyThread();
        myThread.setName("one");
        myThread.start();
        new MyThread().start();
    }

    static class NameList {
        List list = Collections.synchronizedList(new ArrayList<>());

        public void add(String name) {
            list.add(name);
        }

        public String removeFirst() {
            if (list.size() > 0) {
                if(Thread.currentThread().getName().equals("one")) {
                    Thread.yield();
                }
                return (String)list.remove(0);
            }
            return null;
        }
    }
}

// synchronized lists are needed when we share a list between different threads