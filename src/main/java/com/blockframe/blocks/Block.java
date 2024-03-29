package com.blockframe.blocks;

import com.blockframe.transactions.Transaction;
import com.blockframe.utils.ObjectProvider;

import java.util.LinkedList;

public class Block {

    private BlockHeader blockHeader;
    private String payloadAsJson;
    private LinkedList<Transaction> listOfVerifiedTransactions = new LinkedList<>();

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    public String getPayloadAsJson() {
        return payloadAsJson;
    }

    public void setPayloadAsJson(String payloadAsJson) {
        this.payloadAsJson = payloadAsJson;
    }

    public void addVerifiedTransaction(Transaction transaction) {
        listOfVerifiedTransactions.add(transaction);
    }

    public LinkedList<Transaction> getListOfVerifiedTransactions() {
        return listOfVerifiedTransactions;
    }

    public void setListOfVerifiedTransactions(LinkedList<Transaction> listOfVerifiedTransactions) {
        this.listOfVerifiedTransactions = listOfVerifiedTransactions;
    }

    public void assignBlockId() {
        this.blockHeader.setBlockId(String.valueOf(ObjectProvider.blockchain.getBlockchainLength()));
    }

    public boolean hasTransactionsToMine() {
        return this.getListOfVerifiedTransactions().size() != 0;
    }
}
