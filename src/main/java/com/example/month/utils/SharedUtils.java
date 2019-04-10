package com.example.month.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedUtils {

    //添加数据
    public static void put(Context context, String name, Object object) {
        SharedPreferences sp = getSharedPreferences(context);
        SharedPreferences.Editor edit = sp.edit();
        if (object instanceof String) {
            edit.putString(name, (String) object);
        } else if (object instanceof Integer) {
            edit.putInt(name, (Integer) object);
        } else if (object instanceof Float) {
            edit.putFloat(name, (Float) object);
        } else if (object instanceof Boolean) {
            edit.putBoolean(name, (Boolean) object);
        } else if (object instanceof Long) {
            edit.putLong(name, (Long) object);
        }
        edit.commit();
    }

    //获取数据

    public static String getString(Context context, String name) {
        return getSharedPreferences(context).getString(name, null);
    }

    public static boolean getBoole(Context context, String name) {
        return getSharedPreferences(context).getBoolean(name, false);
    }

    public static int getInt(Context context, String name) {
        return getSharedPreferences(context).getInt(name, 0);
    }

    public static float getFloat(Context context, String name) {
        return getSharedPreferences(context).getFloat(name, 0);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("shop", 0);
    }
}
