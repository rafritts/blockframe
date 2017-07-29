package com.blocks.utils;

import com.blocks.models.Block;

public class BlockUtil {
    public static void printBlock(Block block) {
        System.out.println("Initial Payload: " + block.getPayload());
        System.out.println("Found hash: " + block.getCurrentPayloadHash());
        System.out.println("Found nonce: " + block.getNonce());
    }
}
