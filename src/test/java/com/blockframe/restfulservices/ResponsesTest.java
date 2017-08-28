package com.blockframe.restfulservices;

import com.blockframe.restfulservices.responses.BlockchainResponse;
import com.blockframe.restfulservices.responses.DifficultyTargetResponse;
import com.blockframe.restfulservices.responses.EchoResponse;
import com.blockframe.restfulservices.responses.TransactionResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponsesTest {

    @Test
    public void testBlockchainResponse() {
        BlockchainResponse blockchainResponse = new BlockchainResponse(5);
        blockchainResponse.setLength(5);
        assertEquals(5, blockchainResponse.getLength());
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
