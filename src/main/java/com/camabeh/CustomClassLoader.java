package com.camabeh;

public class CustomClassLoader extends ClassLoader {
  public CustomClassLoader(ClassLoader parent) {
    super(parent);
  }

  public Class<?> define(String className, byte[] bytecode) {
    return defineClass(className, bytecode, 0, bytecode.length);
  }
}
