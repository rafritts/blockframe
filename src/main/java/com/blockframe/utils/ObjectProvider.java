package com.blockframe.utils;

import com.blockframe.blockchain.Blockchain;
import com.blockframe.blocks.BlockMaker;
import com.blockframe.blocks.BlockPool;
import com.blockframe.persistence.StorageManager;
import com.blockframe.webservices.WebServiceManager;
import com.blockframe.transactions.TransactionPool;

public class ObjectProvider {

    public static final String VERSION = "1.0.0";
    public static int DIFFICULTY_TARGET = 5;

    public static TransactionPool transactionPool = new TransactionPool();
    public static Blockchain blockchain = new Blockchain();
    public static BlockMaker blockMaker = new BlockMaker();
    public static BlockPool blockPool = new BlockPool();
    public static WebServiceManager webServiceManager = new WebServiceManager();
    public static StorageManager storageManager = new StorageManager();
}
