package com.fanfengping.zeus.util;

import java.io.UnsupportedEncodingException;

public class CharsetUtil {
    public static String convert(String str, String ncharset) throws UnsupportedEncodingException {
        return new String(str.getBytes(), ncharset);
    }
}
