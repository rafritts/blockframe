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

public class WebServicesTest {

    @Mock
    private BlockchainWebService mockBlockchainWebService;
    @Mock
    private DifficultyTargetWebService mockDifficultyTargetWebService;
    @Mock
    private EchoWebService mockEchoWebService;
    @Mock
    private TransactionWebService mockTransactionWebService;

    @InjectMocks
    private WebServices webServices = new WebServices();

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
        webServices.startWebServices();
        verify(mockBlockchainWebService).run();
        verify(mockDifficultyTargetWebService).run();
        verify(mockEchoWebService).run();
        verify(mockTransactionWebService).run();
    }

}
