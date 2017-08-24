package com.blocks.resources;

import com.blocks.models.Block;
import com.blocks.models.Blockchain;

import java.util.LinkedList;

public class BlockPool {

    private LinkedList<Block> listOfBlocks = new LinkedList<>();
    private Blockchain blockchain;

    public BlockPool(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void addBlock(Block block) {
        listOfBlocks.add(block);
    }

    public Block getFirstUnminedBlock() {
        for (Block block : listOfBlocks) {
            if (block.getBlockHeader().getMinedHash() == null)
                return block;
        }
        return null;
    }

    public void cleanBlockPool() {
        LinkedList<Block> minedBlocks = getMinedBlocks();
        moveMinedBlocksToBlockChain(minedBlocks);
        removeMinedBlocks(minedBlocks);
    }

    private void moveMinedBlocksToBlockChain(LinkedList<Block> minedBlocks) {
        for (Block block : minedBlocks) {
            blockchain.addBlock(block);
        }
    }

    private void removeMinedBlocks(LinkedList<Block> minedBlocks) {
        listOfBlocks.removeAll(minedBlocks);
    }

    private LinkedList<Block> getMinedBlocks() {
        LinkedList<Block> minedBlocks = new LinkedList<>();
        for (Block block : listOfBlocks) {
            if (!(block.getBlockHeader().getMinedHash() == null)) {
                minedBlocks.add(block);
            }
        }
        return minedBlocks;
    }


}
