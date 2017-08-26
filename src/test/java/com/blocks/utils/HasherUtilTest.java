package com.blocks.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HasherUtilTest {

    @Test
    public void testHasher() {
        HasherUtil hasherUtil = new HasherUtil();
        String testText = "testText";
        String hashOfTestText = HasherUtil.hashString(testText);
        assertEquals("85ac464c2f22837c991c50fdaa5facc25a09fd7664b5d50427f69b4df7744bdc", hashOfTestText);
    }
}
