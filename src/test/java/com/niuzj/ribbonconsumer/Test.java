package com.niuzj.ribbonconsumer;

public class Test {
    public static void main(String[] args) {
        new Test().testThis();

    }

    public void testThis(){
        System.out.println(Test.this == this);
    }
}
