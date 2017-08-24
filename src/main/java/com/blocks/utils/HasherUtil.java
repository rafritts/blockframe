package com.blocks.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HasherUtil {

    public static String hashString(String string) {
        return DigestUtils.sha256Hex(string);
    }


}
