package com.blockframe.mining;

import com.blockframe.blocks.Block;
import com.blockframe.transactions.Transaction;
import com.blockframe.blocks.BlockPrinter;
import com.blockframe.utils.HasherUtil;

import java.util.Date;

public class Miner {

    private static long nonce = 0;
    private static long previousSecond;
    private static long lastMeasuredNonce = 0;
    private static int elapsedTime = 0;

    public static void mineBlock(Block block, int leadingZeros) {
        String merkleRoot = block.getBlockHeader().getMerkleRoot();
        String previousPayloadHash = block.getBlockHeader().getPreviousBlockHash();
        String blockHash;
        long startTime = System.nanoTime();
        if (previousSecond == 0) {
            previousSecond = System.nanoTime();
        }
        do {
            blockHash = getBlockHash(merkleRoot, previousPayloadHash);
            printHashInfo();
        } while (!isValidNonceHash(blockHash, leadingZeros));
        long miningTime = System.nanoTime() - startTime;
        postMinedInfoToBlock(block, blockHash, miningTime);
        postMinedTransactionsStatuses(block);
        BlockPrinter.printMinedBlock(block);
        resetMiner();
    }

    private static void postMinedTransactionsStatuses(Block block) {
        for (Transaction transaction : block.getListOfVerifiedTransactions()) {
            transaction.setMined(true);
        }
    }

    private static String getBlockHash(String merkleRoot, String previousBlockhash) {
        return HasherUtil.hashString(merkleRoot + previousBlockhash + String.valueOf(nonce));
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
            long hashRate = nonce - lastMeasuredNonce;
            lastMeasuredNonce = nonce;
            elapsedTime++;
            System.out.print("\r" + "Current Nonce: " + nonce
                    + " | Current hashRate: " + hashRate + " hps"
                    + " | Elapsed time: " + elapsedTime);
        }
    }

    private static void postMinedInfoToBlock(Block block, String blockHash, long miningTime) {
        block.getBlockHeader().setMinedHash(blockHash);
        block.getBlockHeader().setNonce(nonce);
        block.getBlockHeader().setMiningTimeInSeconds(miningTime / 1000000000.0);
        block.getBlockHeader().setTimeStamp(new Date().getTime());
    }

    private static void resetMiner() {
        nonce = 0;
        lastMeasuredNonce = 0;
        elapsedTime = 0;
    }

}
