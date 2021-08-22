package com.example.receptenapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;

public class UtitlityClass {
    private static String LOGINID = "loginid";
    private static String TOKENID = "tokenid";

    public static void setLoginId(Context Context, String string) {
        Context.getSharedPreferences(Context.getPackageName(), 0).edit()
                .putString(LOGINID, string).commit();
    }

    public static String getLoginId(Context Context) {
        return Context.getSharedPreferences(Context.getPackageName(), 0)
                .getString(LOGINID, "");
    }

    public static void setToken(Context Context, String string) {
        Context.getSharedPreferences(Context.getPackageName(), 0).edit()
                .putString(TOKENID, string).commit();
    }

    public static String getToken(Context Context) {
        return Context.getSharedPreferences(Context.getPackageName(), 0)
                .getString(TOKENID, "");
    }

    public static void logout(Context Context) {
        Context.getSharedPreferences(Context.getPackageName(), 0).edit()
                .putString(LOGINID, "").commit();
        Context.getSharedPreferences(Context.getPackageName(), 0).edit()
                .putString(TOKENID, "").commit();
    }
    public  static boolean hasNetworkAccess(Context context){

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnectedOrConnecting();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean isNetworkAvailable(Context context) {
        boolean isOnline = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
                isOnline = capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
            } else {
                NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
                isOnline = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isOnline;
    }
}
