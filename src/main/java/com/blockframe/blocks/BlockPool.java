package com.blockframe.blocks;

import com.blockframe.utils.ObjectProvider;

import java.util.LinkedList;

public class BlockPool {

    private LinkedList<Block> listOfBlocks = new LinkedList<>();

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
        removeMinedBlocks(getMinedBlocks());
    }

    public void moveMinedBlocksToBlockChain() {
        for (Block block : getMinedBlocks()) {
            ObjectProvider.blockchain.addBlock(block);
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
