package com.blockframe.restfulservices;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.restfulservices.services.BlockchainWebService;
import com.blockframe.restfulservices.services.DifficultyTargetWebService;
import com.blockframe.restfulservices.services.EchoWebService;
import com.blockframe.restfulservices.services.TransactionWebService;
import com.blockframe.transactions.TransactionPool;

public class WebServiceManager {

    private EchoWebService echoWebService = new EchoWebService();
    private DifficultyTargetWebService difficultyTargetWebService = new DifficultyTargetWebService();
    private TransactionWebService transactionWebService;
    private BlockchainWebService blockchainWebService;

    public WebServiceManager(TransactionPool transactionPool, Blockchain blockchain) {
        this.transactionWebService = new TransactionWebService(transactionPool);
        this.blockchainWebService = new BlockchainWebService(blockchain);
    }

    public void startWebServices() {
        System.out.println("Starting echo web service...");
        echoWebService.run();
        System.out.println("Starting transaction web service...");
        transactionWebService.run();
        System.out.println("Starting blockchain web service...");
        blockchainWebService.run();
        System.out.println("Starting difficultyTarget web service...");
        difficultyTargetWebService.run();
        System.out.println("Ready to mine.");
        System.out.println();
    }

}
