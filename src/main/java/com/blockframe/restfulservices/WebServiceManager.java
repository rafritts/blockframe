package com.blockframe.restfulservices;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.transactions.TransactionPool;

public class WebServiceManager {

    private EchoWebService echoWebService = new EchoWebService();
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
        System.out.println("Ready to mine.");
        System.out.println();
    }

}
