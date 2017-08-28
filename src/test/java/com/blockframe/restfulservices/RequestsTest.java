package com.blockframe.restfulservices;

import com.blockframe.restfulservices.requests.DifficultyTargetRequest;
import com.blockframe.restfulservices.requests.TransactionRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestsTest {

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
