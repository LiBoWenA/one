package com.example.day1225fresco;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

public class injectView {
    public static void bind(Object object){
        try {
            parser(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parser(Object object) throws Exception {
      final   Class<?> aClass = object.getClass();
        View view=null;
        Field[] fields = aClass.getDeclaredFields();
        for (Field field:fields) {
            if (field.isAnnotationPresent(InjectViewAnnotation.class)){
                InjectViewAnnotation injectViewAnnotation=field.getAnnotation(InjectViewAnnotation.class);
                int value =injectViewAnnotation .value();
                if (value<0){
                    throw  new Exception("error");

                }else {
                    field.setAccessible(true);
                    if (object instanceof  View){
                        view=((View) object).findViewById(value);
                    }else if (object instanceof Activity){
                        view=((Activity) object).findViewById(value);
                    }
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.i("TAG","点击了一下！");
                        }
                    });
                    field.set(object,view);
                }
            }
        }
    }
}
