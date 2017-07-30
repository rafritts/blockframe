package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.utils.BlockUtil;

import java.security.MessageDigest;

public class Miner {

    private static int leadingZeros = 5;
    private static long nonce = 0;
    private static long previousSecond;
    private static long lastMeasuredNonce = 0;
    private static long hashRate;

    public static void mineBlock(Block block) {
        // MessageDigest is the java object that performs the actual hashing
        MessageDigest messageDigest = getNewMessageDigest();
        String initialHash = getInitialHash(block, messageDigest);
        String blockHash;
        do {
            blockHash = getBlockHash(block, messageDigest, initialHash);
            printHashInfo();
        } while (!isValidNonceHash(blockHash));
        postMinedInfoToBlock(block, blockHash);
        resetMiner();
        BlockUtil.printMinedBlock(block);
    }

    private static String getInitialHash(Block block, MessageDigest messageDigest) {
        updateMessageDigest(messageDigest, block.getPayload());
        byte[] digest = messageDigest.digest();
        return String.format("%064x", new java.math.BigInteger(1, digest));
    }

    private static String getBlockHash(Block block, MessageDigest messageDigest, String initialHash) {
        updateMessageDigest(messageDigest, String.valueOf(nonce) + initialHash + block.getPreviousPayloadHash());
        return String.format("%064x", new java.math.BigInteger(1, messageDigest.digest()));

    }

    private static void updateMessageDigest(MessageDigest messageDigest, String string) {
        try {
            messageDigest.update(string.getBytes("UTF-8"));
        } catch (Exception exp) {
            System.out.println("Something went terribly wrong when trying to update messageDigest. "
                    + exp.getMessage());
            System.exit(1);
        }
    }

    private static MessageDigest getNewMessageDigest() {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (Exception exp) {
            System.out.println("Something went terribly wrong when trying to create a new messageDigest. "
                    + exp.getMessage());
            System.exit(1);
        }
        return messageDigest;
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

    private static void printHashInfo() {
        if (previousSecond == 0) {
            previousSecond = System.nanoTime();
        }
        if (System.nanoTime() - previousSecond > 1000000000) {
            previousSecond = System.nanoTime();
            hashRate = nonce - lastMeasuredNonce;
            lastMeasuredNonce = nonce;
        }
        System.out.print("Current Nonce: " + nonce + " | Current hashRate: " + hashRate + " hps" + "\r");
    }

    private static void postMinedInfoToBlock(Block block, String blockHash) {
        block.setMinedPayloadHash(blockHash);
        block.setNonce(nonce);
        block.setMined(true);
    }

    private static void resetMiner() {
        nonce = 0;
        lastMeasuredNonce = 0;
    }

}
