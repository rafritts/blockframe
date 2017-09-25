package com.blockframe.webservices;

import com.blockframe.webservices.services.BlockchainWebService;
import com.blockframe.webservices.services.DifficultyTargetWebService;
import com.blockframe.webservices.services.EchoWebService;
import com.blockframe.webservices.services.TransactionWebService;
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

    @InjectMocks
    private WebServiceManager webServiceManager = new WebServiceManager();

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
