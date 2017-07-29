package com.blocks.main;

import com.blocks.BlockChainApp;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        BlockChainApp blockChainApp = new BlockChainApp();
        blockChainApp.run();
    }
}
