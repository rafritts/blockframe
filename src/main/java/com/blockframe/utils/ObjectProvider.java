package com.blockframe.utils;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.blocks.BlockMaker;
import com.blockframe.blocks.BlockPool;
import com.blockframe.persistence.StorageManager;
import com.blockframe.restfulservices.WebServiceManager;
import com.blockframe.transactions.TransactionPool;

public class ObjectProvider {

    public static final String VERSION = "1.0.0";
    private static int DIFFICULTY_TARGET = 1;

    private static TransactionPool transactionPool;
    private static Blockchain blockchain;
    private static BlockMaker blockMaker;
    private static BlockPool blockPool;
    private static WebServiceManager webServiceManager;
    private static StorageManager storageManager;

    public void initializeObjectProvider() {
        transactionPool = new TransactionPool();
        blockchain = new Blockchain();
        blockMaker = new BlockMaker();
        blockPool = new BlockPool();
        webServiceManager = new WebServiceManager();
        storageManager = new StorageManager();
    }

    public static TransactionPool getTransactionPool() {
        return transactionPool;
    }

    public static Blockchain getBlockchain() {
        return blockchain;
    }

    public static BlockMaker getBlockMaker() {
        return blockMaker;
    }

    public static BlockPool getBlockPool() {
        return blockPool;
    }

    public static WebServiceManager getWebServiceManager() {
        return webServiceManager;
    }

    public static StorageManager getStorageManager() {
        return storageManager;
    }

    public static int getDifficultyTarget() {
        return DIFFICULTY_TARGET;
    }

    public static void setDifficultyTarget(int newDifficultyTarget) {
        DIFFICULTY_TARGET = newDifficultyTarget;
    }
}
