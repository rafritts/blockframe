package com.blockframe.blocks;

import com.blockframe.transactions.Transaction;
import com.blockframe.transactions.TransactionVerifier;
import com.blockframe.utils.HasherUtil;
import com.blockframe.utils.ObjectProvider;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BlockMaker {

    public Block createUnminedBlock() {
        Block block = new Block();
        LinkedList<Transaction> listOfTransactions = populateBlockWithValidatedTransactions(block);
        createBlockHeader(block, listOfTransactions);
        return block;
    }

    private LinkedList<Transaction> populateBlockWithValidatedTransactions(Block block) {
        LinkedList<Transaction> listOfTransactions = ObjectProvider.transactionPool.getAllUnverifiedTransactions();
        verifyTransactions(listOfTransactions);
        addAllTransactionsToBlock(listOfTransactions, block);
        block.setPayloadAsJson(new Gson().toJson(block.getListOfVerifiedTransactions()));
        return listOfTransactions;
    }

    private void createBlockHeader(Block block, LinkedList<Transaction> listOfTransactions) {
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setMerkleRoot(constructMerkleRoot(listOfTransactions));
        blockHeader.setPreviousBlockHash(getPreviousBlockHash());
        blockHeader.setVersion(ObjectProvider.VERSION);
        blockHeader.setDifficultyTarget(ObjectProvider.DIFFICULTY_TARGET);
        block.setBlockHeader(blockHeader);
    }

    private String constructMerkleRoot(LinkedList<Transaction> listOfTransactions) {
        LinkedList<String> merkleTree;
        merkleTree = getInitialHashesOfBottomRow(listOfTransactions);
        while (merkleTree.size() > 1) {
            merkleTree = combineHashes(merkleTree);
        }
        return merkleTree.size() > 0 ? merkleTree.getFirst() : "";
    }

    private LinkedList<String> combineHashes(LinkedList<String> merkleTree) {
        LinkedList<String> combinedHashes = new LinkedList<>();
        for (int i = 0; i < merkleTree.size(); i += 2) {
            if (i == merkleTree.size() - 1) {
                combinedHashes.add(HasherUtil.hashString(merkleTree.get(i)));
            } else {
                combinedHashes.add(HasherUtil.hashString(
                        merkleTree.get(i) + merkleTree.get(i + 1)));
            }
        }
        return combinedHashes;
    }

    private LinkedList<String> getInitialHashesOfBottomRow(LinkedList<Transaction> listOfTransactions) {
        LinkedList<String> bottomRow = new LinkedList<>();
        for (int i = 0; i < listOfTransactions.size(); i += 2) {
            if (i == listOfTransactions.size() - 1) {
                bottomRow.add(HasherUtil.hashString(listOfTransactions.get(i).getDetails()));
            } else {
                bottomRow.add(HasherUtil.hashString(
                        listOfTransactions.get(i).getDetails() + listOfTransactions.get(i + 1).getDetails()));
            }
        }
        return bottomRow;
    }

    private String getPreviousBlockHash() {
        try {
            return ObjectProvider.blockchain.getBlockchain().getLast().getBlockHeader().getMinedHash();
        } catch (NoSuchElementException exp) {
            return "0000000000000000000000000000000000000000000000000000000000000000";
        }
    }

    private void verifyTransactions(LinkedList<Transaction> listOfUnverifiedTransactions) {
        TransactionVerifier transactionVerifier = new TransactionVerifier();
        for (Transaction transaction : listOfUnverifiedTransactions) {
            transactionVerifier.verify(transaction);
        }
    }

    private void addAllTransactionsToBlock(LinkedList<Transaction> listOfVerifiedTransactions, Block block) {
        for (Transaction transaction : listOfVerifiedTransactions) {
            if (transaction.isVerified()) {
                block.addVerifiedTransaction(transaction);
            }
        }
    }

}
