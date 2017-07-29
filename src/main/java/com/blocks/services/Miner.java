package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.utils.BlockUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Miner {

    private static int leadingZeros = 5;
    private static long nonce = 0;
    private static long lastSecond;
    private static long lastMeasuredNonce = 0;
    private static long hashRate;

    public static boolean mineBlock(Block block) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(block.getPayload().getBytes("UTF-8"));
        byte[] digest = messageDigest.digest();
        String initialHash = String.format("%064x", new java.math.BigInteger(1, digest));
        String noncePlusHash;
        String blockHash;
        lastSecond = System.nanoTime();
        do {
            noncePlusHash = String.valueOf(nonce) + initialHash + block.getPreviousPayloadHash();
            messageDigest.update(noncePlusHash.getBytes("UTF-8"));
            blockHash = String.format("%064x", new java.math.BigInteger(1, messageDigest.digest()));
            printHashingInfo();
        } while (!isValidNonceHash(blockHash));
        block.setMinedPayloadHash(blockHash);
        block.setNonce(nonce);
        block.setMined(true);
        nonce = 0;
        lastMeasuredNonce = 0;
        BlockUtil.printBlock(block);
        return true;
    }

    private static boolean isValidNonceHash(String hash) {
        for (int i = 0; i < leadingZeros; i++) {
            if (hash.charAt(i) != '0') {
                nonce++;
                return false;
            }
        }
        return true;
    }

    private static void printHashingInfo() {
        if (System.nanoTime() - lastSecond > 1000000000) {
            lastSecond = System.nanoTime();
            hashRate = nonce - lastMeasuredNonce;
            lastMeasuredNonce = nonce;
        }
        System.out.print("Current Nonce: " + nonce + " | Current hashRate: " + hashRate + " hps" + "\r");
    }

}
