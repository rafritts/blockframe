package com.blockframe.mining;

import com.blockframe.blocks.Block;
import com.blockframe.blocks.BlockHeader;
import com.blockframe.transactions.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

public class MinerTest {

    private Block block = new Block();
    private BlockHeader blockHeader = new BlockHeader();

    @Before
    public void init() {
        blockHeader.setMerkleRoot("MerkleRoot");
        blockHeader.setPreviousBlockHash("PreviousBlockHash");
        blockHeader.setVersion("1.0");
        block.setBlockHeader(blockHeader);
        LinkedList<Transaction> listOfTransactions = new LinkedList<>();
        listOfTransactions.add(new Transaction("Test Transaction"));
        listOfTransactions.getFirst().setVerified(true);
        block.setListOfVerifiedTransactions(listOfTransactions);
    }

    @Test
    public void testMiner() {
        Miner.mineBlock(block, 1);
    }

}
