package com.blocks.blockchain;

import com.blocks.blocks.Block;

import java.util.LinkedList;

public class Blockchain {

    LinkedList<Block> blockchain = new LinkedList<>();

    public void addBlock(Block block) {
        blockchain.add(block);
    }

    public int getBlockchainLength() {
        return blockchain.size();
    }

    public LinkedList<Block> getBlockchain() {
        return blockchain;
    }

}
