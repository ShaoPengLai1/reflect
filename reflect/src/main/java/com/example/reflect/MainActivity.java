package com.example.reflect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "~~~~MainActivity~~~~";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //1.通过字符串获取Class对象，这个字符串必须带上完整路径名
            Class c = Class.forName("com.example.reflect.Student");
            //2.获取声明的构造方法，传入所需参数的类名，如果有多个参数，用','连接即可
            Constructor constructor = c.getDeclaredConstructor(String.class);
            //如果是私有的构造方法，需要调用下面这一行代码使其可使用，公有的构造方法则不需要下面这一行代码
            constructor.setAccessible(true);
            //使用构造方法的newInstance方法创建对象，传入构造方法所需参数，如果有多个参数，用','连接即可
            Object newInstance = constructor.newInstance("NameShao");
            //3.获取声明的字段，传入字段名
            Field studentAge = c.getDeclaredField("studentAge");
            //如果是私有的字段，需要调用下面这一行代码使其可使用，公有的字段则不需要下面这一行代码
            studentAge.setAccessible(true);
            //使用字段的set方法设置字段值，传入此对象以及参数值
            studentAge.set(newInstance,24);
            //4.获取声明的函数，传入所需参数的类名，如果有多个参数，用','连接即可
            Method declaredMethod = c.getDeclaredMethod("show", String.class);
            //如果是私有的函数，需要调用下面这一行代码使其可使用，公有的函数则不需要下面这一行代码
            declaredMethod.setAccessible(true);
            Object message = declaredMethod.invoke(newInstance, "message");
            Log.d(TAG,"message:"+message);
            //TODO 获取所有声明的构造方法
            Constructor[] constructors = c.getDeclaredConstructors();
            for (Constructor constructor1:constructors){
                Log.i(TAG,"constructor1:"+constructor1);
            }
            //TODO 获取所有公有的构造方法
            Constructor[] constructors1 = c.getConstructors();
            for (Constructor constructor1:constructors1){
                Log.i(TAG,"constructor1:"+constructor1);
            }

            //TODO 获取所有声明的字段
            Field[] declaredFields = c.getDeclaredFields();
            for (Field declaredField:declaredFields){
                Log.i(TAG,"declaredField:"+declaredField);
            }
            //TODO 获取所有公有的字段
            Field[] fieldList  = c.getFields();
            for (Field field:fieldList){
                Log.i(TAG,"field:"+field);
            }
            //TODO 获取所有声明的函数
            Method[] declaredMethods = c.getDeclaredMethods();
            for (Method ignored :declaredMethods){
                Log.i(TAG,"ignored:"+ignored);
            }
            //TODO 获取所有公有的函数
            Method[] methods = c.getMethods();
            for (Method method:methods){
                Log.i(TAG,"method:"+method);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
