package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.Transaction;
import com.blocks.utils.BlockUtil;

import java.security.MessageDigest;

public class Miner {

    private static long nonce = 0;
    private static long previousSecond;
    private static long lastMeasuredNonce = 0;
    private static long hashRate;
    private static int elapsedTime = 0;

    public static void mineBlock(Block block, int leadingZeros) {
        // MessageDigest is the java object that performs the actual hashing
        MessageDigest messageDigest = getNewMessageDigest();
        String initialHash = getInitialHash(block, messageDigest);
        String blockHash;
        long startTime = System.nanoTime();
        if (previousSecond == 0) {
            previousSecond = System.nanoTime();
        }
        do {
            blockHash = getBlockHash(block, messageDigest, initialHash);
            printHashInfo();
        } while (!isValidNonceHash(blockHash, leadingZeros));
        long miningTime = System.nanoTime() - startTime;
        postMinedInfoToBlock(block, blockHash, miningTime);
        postMinedTransactionsStatuses(block);
        BlockUtil.printMinedBlock(block);
        resetMiner();
    }

    private static void postMinedTransactionsStatuses(Block block) {
        for (Transaction transaction : block.getListOfVerifiedTransactions()) {
            transaction.setMined(true);
        }
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

    private static boolean isValidNonceHash(String hash, int leadingZeros) {
        for (int i = 0; i < leadingZeros; i++) {
            if (hash.charAt(i) != '0') {
                nonce++;
                return false;
            }
        }
        return true;
    }

    private static void printHashInfo() {
        if (System.nanoTime() - previousSecond > 1000000000) {
            previousSecond = System.nanoTime();
            hashRate = nonce - lastMeasuredNonce;
            lastMeasuredNonce = nonce;
            elapsedTime++;
            System.out.print("\r" + "Current Nonce: " + nonce
                    + " | Current hashRate: " + hashRate + " hps"
                    + " | Elapsed time: " + elapsedTime);
        }

    }

    private static void postMinedInfoToBlock(Block block, String blockHash, long miningTime) {
        block.setMinedPayloadHash(blockHash);
        block.setNonce(nonce);
        block.setMined(true);
        block.setMiningTimeInSeconds(miningTime / 1000000000.0);
    }

    private static void resetMiner() {
        nonce = 0;
        lastMeasuredNonce = 0;
        elapsedTime = 0;
    }

}
