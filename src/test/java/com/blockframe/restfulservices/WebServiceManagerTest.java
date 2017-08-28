package com.blockframe.restfulservices;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.restfulservices.services.BlockchainWebService;
import com.blockframe.restfulservices.services.DifficultyTargetWebService;
import com.blockframe.restfulservices.services.EchoWebService;
import com.blockframe.restfulservices.services.TransactionWebService;
import com.blockframe.transactions.TransactionPool;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

public class WebServiceManagerTest {

    @Mock
    private BlockchainWebService mockBlockchainWebService;
    @Mock
    private DifficultyTargetWebService mockDifficultyTargetWebService;
    @Mock
    private EchoWebService mockEchoWebService;
    @Mock
    private TransactionWebService mockTransactionWebService;
    @Mock
    private Blockchain mockBlockchain;
    @Mock
    private TransactionPool mockTransactionPool;

    @InjectMocks
    private WebServiceManager webServiceManager = new WebServiceManager(mockTransactionPool, mockBlockchain);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWebServiceManager() {
        doNothing().when(mockBlockchainWebService).run();
        doNothing().when(mockDifficultyTargetWebService).run();
        doNothing().when(mockEchoWebService).run();
        doNothing().when(mockTransactionWebService).run();
        webServiceManager.startWebServices();
        verify(mockBlockchainWebService).run();
        verify(mockDifficultyTargetWebService).run();
        verify(mockEchoWebService).run();
        verify(mockTransactionWebService).run();
    }

}
