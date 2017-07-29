package com.blocks;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Block block1 = new Block();
        Block block2 = new Block();
        Block block3 = new Block();

        block1.setPreviousPayloadHash("");
        block1.setPayload("This is some text");
        Miner.mineBlock(block1);
        printBlock(block1);


        block2.setPreviousPayloadHash(block1.getCurrentPayloadHash());
        block2.setPayload("This is some more text");
        Miner.mineBlock(block2);
        printBlock(block2);

        block3.setPreviousPayloadHash(block2.getCurrentPayloadHash());
        block3.setPayload("This is some yet more text");
        Miner.mineBlock(block3);
        printBlock(block3);
    }

    public static void printBlock(Block block) {
        System.out.println("Initial Payload: " + block.getPayload());
        System.out.println("Found hash: " + block.getCurrentPayloadHash());
        System.out.println("Found nonce: " + block.getNonce());
    }
}
