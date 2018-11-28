package com.lib.util.views;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FindViewUtil {
    public static void inject(Activity activity) {
        inject(activity, null);
    }

    public static void inject(Activity activity, View.OnClickListener listener) {
        if (listener != null) {
            getActivityFields(activity, listener);
        } else {
            findActivityMethods(activity, getActivityFields(activity, null));
        }
    }

    public static void inject(Fragment fragment) {
        inject(fragment, null);
    }

    public static void inject(Fragment fragment, View.OnClickListener listener) {
        if (listener != null) {
            //遍历属性,对设置注解的view进行初始化
            getFragmentFields(fragment, listener);
        } else {
            //遍历方法 将设置注解的方法绑定到相应的view的点击事件中
            findFragmentMethods(fragment, getFragmentFields(fragment, null));
        }
    }

    @NonNull
    private static Class<Fragment> getFragmentFields(Fragment fragment, View.OnClickListener listener) {
        Class<Fragment> activityClass = (Class<Fragment>) fragment.getClass();
        Field fields[] = activityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                int viewId = field.getAnnotation(BindView.class).value();
                View view = fragment.getView().findViewById(viewId);
                if (listener != null) {
                    view.setOnClickListener(listener);
                }
                try {
                    //这一行代码是必须的，否则直接调用set方法不生效
                    field.setAccessible(true);
                    field.set(fragment, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return activityClass;
    }

    private static void findFragmentMethods(final Fragment activity, Class<Fragment> activityClass) {
        Method methods[] = activityClass.getDeclaredMethods();
        for (final Method method : methods) {
            BindClick bindClick = method.getAnnotation(BindClick.class);
            if (bindClick != null) {
                int[] viewId = bindClick.value();
                for (int i = 0; i < viewId.length; i++) {
                    activity.getView().findViewById(viewId[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                //调用该方法,否则直接调用set方法不生效
                                method.setAccessible(true);
                                method.invoke(activity,view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }


    private static void findActivityMethods(final Activity activity, Class<Activity> activityClass) {
        Method methods[] = activityClass.getDeclaredMethods();
        for (final Method method : methods) {
            BindClick bindClick = method.getAnnotation(BindClick.class);
            if (bindClick != null) {
                int[] viewId = bindClick.value();
                for (int i = 0; i < viewId.length; i++) {
                    activity.findViewById(viewId[i]).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            try {
                                //调用该方法,否则直接调用set方法不生效
                                method.setAccessible(true);
                                method.invoke(activity, view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

    @NonNull
    private static Class<Activity> getActivityFields(Activity activity, View.OnClickListener listener) {
        Class<Activity> activityClass = (Class<Activity>) activity.getClass();
        Field fields[] = activityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                int viewId = field.getAnnotation(BindView.class).value();
                View view = activity.findViewById(viewId);
                if (listener != null) {
                    view.setOnClickListener(listener);
                }
                try {
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return activityClass;
    }
}
