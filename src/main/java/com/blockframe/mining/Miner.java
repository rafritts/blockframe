package com.blockframe.mining;

import com.blockframe.blocks.Block;
import com.blockframe.transactions.Transaction;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;
import java.util.stream.IntStream;

public class Miner {

    public static void mineBlock(Block block) {
        String blockHeaderString = constructBlockHeaderString(block);
        int currentNonce = 0;
        int previouslyReportedNonce = 0;
        int elapsedTime = 0;
        int difficultyTarget = block.getBlockHeader().getDifficultyTarget();
        String hashedBlockHeader = "";
        long lastSecond = System.nanoTime();
        while (true) {
            hashedBlockHeader = DigestUtils.sha256Hex(blockHeaderString + currentNonce);
            if (isValidHash(hashedBlockHeader, difficultyTarget)) {
                break;
            }
            if (System.nanoTime() - lastSecond > 1000000000) {
                lastSecond = System.nanoTime();
                System.out.print("\rCurrent Nonce: " + currentNonce
                        + " | Hashrate: " + (currentNonce - previouslyReportedNonce)
                        + " | Elapsed Time: " + elapsedTime);
                elapsedTime++;
                previouslyReportedNonce = currentNonce;
            }
            currentNonce++;
        }
        cleanSystemOut();
        finalizeBlock(block, currentNonce, elapsedTime, hashedBlockHeader);
    }

    private static void finalizeBlock(Block block, int currentNonce, int elapsedTime, String hashedBlockHeader) {
        postMinedInfoToBlock(block, hashedBlockHeader, currentNonce, elapsedTime);
        postMinedTransactionsStatuses(block);
    }

    private static void postMinedTransactionsStatuses(Block block) {
        for (Transaction transaction : block.getListOfVerifiedTransactions()) {
            transaction.setMined(true);
        }
    }

    private static boolean isValidHash(String hashedBlockHeader, int difficultyTarget) {
        return IntStream.range(0, difficultyTarget).allMatch(value -> hashedBlockHeader.charAt(value) == '0');
    }

    private static String constructBlockHeaderString(Block block) {
        return block.getBlockHeader().getVersion()
                + block.getBlockHeader().getPreviousBlockHash()
                + block.getBlockHeader().getMerkleRoot();
    }

    private static void postMinedInfoToBlock(Block block, String blockHash, int finalNonce, int elapsedTime) {
        block.getBlockHeader().setMinedHash(blockHash);
        block.getBlockHeader().setNonce(finalNonce);
        block.getBlockHeader().setMiningTimeInSeconds(elapsedTime / 1000000000.0);
        block.getBlockHeader().setTimeStamp(new Date().getTime());
    }

    private static void cleanSystemOut() {
        System.out.print("\r                                                                                           ");
        System.out.println();
    }
}
