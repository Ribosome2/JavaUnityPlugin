package com.example.texturehelper;

public class HelloWorld {
    public static String sayHello(){
        return "Hello world from JNI by kyle dddddd";
    }

    public static String callKotlin(){
        return Utility.Companion.GetSth();
    }
}
