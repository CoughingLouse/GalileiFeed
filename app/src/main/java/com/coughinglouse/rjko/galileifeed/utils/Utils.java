package com.coughinglouse.rjko.galileifeed.utils;

/**
 * Created by rjko on 01/08/17.
 */

public class Utils {

    public static String daUrlANome(String s){

        String ret = s.substring(s.lastIndexOf('/'));
        return ret.replace("%20", " ");
    }
}
