package com.blockframe.utils;

import org.apache.commons.codec.digest.DigestUtils;

public final class HasherUtil {

    public static String hashString(String string) {
        return DigestUtils.sha256Hex(string);
    }


}
