package com.example.day1225fresco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectActivity extends AppCompatActivity{
    @InjectViewAnnotation(R.id.btn)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect);
        //绑定当前视图
        injectView.bind(this);


        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    private void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {

        Class<?> forName = Class.forName("com.example.day1225fresco.Bean");
        Bean bean = new Bean();
        Object instance = forName.newInstance();


        Method[] declaredMethods = forName.getDeclaredMethods();
        for (int i = 0; i < declaredMethods.length; i++) {
            Log.i("TAG","私有方法"+declaredMethods[i].toString());
        }

        Field name = forName.getDeclaredField("name");
        name.setAccessible(true);
        Object o = name.get(instance);
        Log.i("TAG","修改前:"+o);

        name.set(instance,"唉呀妈呀小可爱啊");

        Method getName = forName.getDeclaredMethod("getName", new Class[]{});
        getName.setAccessible(true);
        Object invoke = getName.invoke(instance, new Object[]{});
        Log.i("TAG","修改后:"+invoke);


    }
}
