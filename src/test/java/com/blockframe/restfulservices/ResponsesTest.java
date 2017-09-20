package com.blockframe.restfulservices;

import com.blockframe.restfulservices.responses.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResponsesTest {

    @Test
    public void testBlockchainResponse() {
        BlockchainResponse blockchainResponse = new BlockchainResponse();
        blockchainResponse.setLength(5);
        assertEquals(5, blockchainResponse.getLength());
        blockchainResponse.setAverageNonce(100);
        assertEquals(100, blockchainResponse.getAverageNonce());
    }

    @Test
    public void testBlockValidationResponse() {
        BlockValidationResponse blockValidationResponse = new BlockValidationResponse();
        blockValidationResponse.isBlockValid = true;
        assertTrue(blockValidationResponse.isBlockValid);
    }

    @Test
    public void testDifficultyTargetResponse() {
        DifficultyTargetResponse difficultyTargetResponse = new DifficultyTargetResponse();
        difficultyTargetResponse.setDifficultyTarget(5);
        assertEquals(5, difficultyTargetResponse.getDifficultyTarget());
    }

    @Test
    public void testEchoResponse() {
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.setGreeting("Hello from Blockframe!");
        assertEquals("Hello from Blockframe!", echoResponse.getGreeting());
    }

    @Test
    public void testTransactionResponse() {
        TransactionResponse transactionResponse = new TransactionResponse("message");
        transactionResponse.setMessage("some other message");
        assertEquals("some other message", transactionResponse.getMessage());
    }
}
