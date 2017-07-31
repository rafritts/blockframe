package com.blocks.utils;

import com.blocks.models.Block;
import com.google.gson.Gson;

public class BlockUtil {

    public static void setPayloadAsTransactionList(Block block) {
        block.setPayload(new Gson().toJson(block.getListOfVerifiedTransactions()));
    }

    public static void printMinedBlock(Block block) {
        System.out.println("-----------------------------                                               ");
        System.out.println("Block Payload: " + block.getPayload());
        System.out.println("Mined hash: " + block.getMinedPayloadHash());
        System.out.println("Final nonce: " + block.getNonce());
    }
}
