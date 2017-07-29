package com.blocks.services;

import com.blocks.models.Block;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Miner {

    static int leadingZeros = 5;
    static long nonce = 0;

    public static boolean mineBlock(Block block) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(block.getPayload().getBytes("UTF-8"));
        byte[] digest = messageDigest.digest();
        String initialHash = String.format("%064x", new java.math.BigInteger(1, digest));
        String noncePlusHash;
        String blockHash;
        do {
            noncePlusHash = String.valueOf(nonce) + initialHash + block.getPreviousPayloadHash();
            messageDigest.update(noncePlusHash.getBytes("UTF-8"));
            blockHash = String.format("%064x", new java.math.BigInteger(1, messageDigest.digest()));
        } while (!isValidNonceHash(blockHash));
        block.setCurrentPayloadHash(blockHash);

        block.setNonce(nonce);
        nonce = 0;
        return true;
    }

    private static boolean isValidNonceHash(String hash) {
        System.out.print("Current Nonce: " + nonce + "\r");
        for (int i = 0; i < leadingZeros; i++) {
            if (hash.charAt(i) != '0') {
                nonce++;
                return false;
            }
        }
        return true;
    }


}
