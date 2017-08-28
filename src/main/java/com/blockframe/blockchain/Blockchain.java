package com.blockframe.blockchain;

import com.blockframe.blocks.Block;

import java.util.LinkedList;

public class Blockchain {

    private LinkedList<Block> blockchain = new LinkedList<>();

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
