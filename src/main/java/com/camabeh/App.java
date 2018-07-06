package com.camabeh;

import com.external.IAdder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
  private static void testLoadViaReflection(CustomClassLoader loader, byte[] source) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    Class<?> testClass = loader.define("com.external.Adder", source);
    Object obj = testClass.newInstance();
    Method add = obj.getClass().getMethod("add", int.class, int.class);
    Method inc = obj.getClass().getMethod("inc");
    Object result = add.invoke(obj, 2, 3);
    System.out.println(result);
    System.out.println(inc.invoke(obj));

  }

  private static void testLoadViaCasting(CustomClassLoader loader, byte[] source) throws IllegalAccessException, InstantiationException {
    Class<?> testClass = loader.define("com.external.Adder", source);
    IAdder obj = (IAdder) testClass.newInstance();
    int result = obj.add(2, 3);
    System.out.println(result);
    System.out.println(obj.inc());
  }

  public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
    byte[] source = Files.readAllBytes(Paths.get("/home/camabeh/Projects/classloader-zip-experiment/data/com/external/Adder.class"));
    CustomClassLoader loader = new CustomClassLoader(Thread.currentThread().getContextClassLoader());
//    testLoadViaReflection(loader, source);
    testLoadViaCasting(loader, source);
  }
}
