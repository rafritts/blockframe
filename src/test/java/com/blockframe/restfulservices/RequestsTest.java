package com.blockframe.restfulservices;

import com.blockframe.blocks.Block;
import com.blockframe.restfulservices.requests.BlockValidationRequest;
import com.blockframe.restfulservices.requests.DifficultyTargetRequest;
import com.blockframe.restfulservices.requests.TransactionRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestsTest {

    @Test
    public void testBlockValidationRequest() {
        BlockValidationRequest blockValidationRequest = new BlockValidationRequest();
        Block block = new Block();
        blockValidationRequest.setBlock(block);
        assertEquals(block, blockValidationRequest.getBlock());
    }

    @Test
    public void testDifficultyTargetRequest() {
        DifficultyTargetRequest difficultyTargetRequest = new DifficultyTargetRequest();
        difficultyTargetRequest.setDifficultyTarget(5);
        assertEquals(5, difficultyTargetRequest.getDifficultyTarget());
    }

    @Test
    public void testTransactionRequest() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setDetails("details");
        assertEquals("details", transactionRequest.getDetails());
    }
}
