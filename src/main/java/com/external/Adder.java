package com.external;

public class Adder implements IAdder {
  private int state = 1;

  public Adder() {}

  public int add(int a, int b) {
    return state + a + b;
  }

  public int inc() {
    return ++state;
  }
}