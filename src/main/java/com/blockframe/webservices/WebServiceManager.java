package com.blockframe.webservices;

import com.blockframe.webservices.services.*;

public class WebServiceManager {

    private EchoWebService echoWebService = new EchoWebService();
    private DifficultyTargetWebService difficultyTargetWebService = new DifficultyTargetWebService();
    private TransactionWebService transactionWebService = new TransactionWebService();
    private BlockchainWebService blockchainWebService = new BlockchainWebService();
    private BlockValidatorService blockValidatorService = new BlockValidatorService();

    public void startWebServices() {
        System.out.println("Starting echo web service...");
        echoWebService.run();
        System.out.println("Starting transaction web service...");
        transactionWebService.run();
        System.out.println("Starting blockchain web service...");
        blockchainWebService.run();
        System.out.println("Starting difficultyTarget web service...");
        difficultyTargetWebService.run();
        System.out.println("Starting blockValidator web service...");
        blockValidatorService.run();
        System.out.println("Ready to mine.");
        System.out.println();
    }

}
