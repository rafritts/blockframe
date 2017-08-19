package com.blocks.utils;

import com.blocks.models.Block;
import com.google.gson.Gson;

public class BlockUtil {

    public static void printMinedBlock(Block block) {
        System.out.println("\r-----------------------------------BLOCK FOUND!---------------------------------");
        System.out.println("Block Payload: " + block.getPayload());
        System.out.println("Total Transactions:" + block.getListOfVerifiedTransactions().size());
        System.out.println("Average Hashrate: " + (int)(block.getNonce() / block.getMiningTimeInSeconds()));
        System.out.println("Final nonce: " + block.getNonce());
        System.out.println("Previous Block's hash: " + block.getPreviousPayloadHash());
        System.out.println("This Block's Mined hash: " + block.getMinedPayloadHash());
        System.out.println("Total time spent mining: " + (int)block.getMiningTimeInSeconds() + " seconds.");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }
}
