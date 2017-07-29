package com.blocks.utils;

import com.blocks.models.Block;
import com.google.gson.Gson;

public class BlockUtil {

    public static void setPayloadAsTransactionList(Block block) {
        block.setPayload(new Gson().toJson(block.getListOfVerifiedTransactions()));
    }

    public static void printBlock(Block block) {
        System.out.println("Block Payload: " + block.getPayload());
        System.out.println("Found hash: " + block.getCurrentPayloadHash());
        System.out.println("Found nonce: " + block.getNonce());
    }
}
