package com.blocks.resources;

import com.blocks.models.Block;

import java.util.LinkedList;

public class BlockPool {

    LinkedList<Block> listOfBlocks = new LinkedList<>();

    public void addBlock(Block block) {
        listOfBlocks.add(block);
    }
}
