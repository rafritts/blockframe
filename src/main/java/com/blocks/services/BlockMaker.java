package com.blocks.services;

import com.blocks.models.Block;
import com.blocks.models.BlockHeader;
import com.blocks.models.Blockchain;
import com.blocks.models.Transaction;
import com.blocks.resources.TransactionPool;
import com.blocks.utils.HasherUtil;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class BlockMaker {

    private TransactionPool transactionPool;
    private Blockchain blockchain;

    public BlockMaker(TransactionPool pool, Blockchain blockchain) {
        this.transactionPool = pool;
        this.blockchain = blockchain;
    }

    public Block createBlock(String version, int difficultyTarget) {
        Block block = new Block();
        LinkedList<Transaction> listOfTransactions = populateBlockWithValidatedTransactions(block);
        createBlockHeader(version, difficultyTarget, block, listOfTransactions);
        return block;
    }

    private LinkedList<Transaction> populateBlockWithValidatedTransactions(Block block) {
        LinkedList<Transaction> listOfTransactions = transactionPool.getAllUnverifiedTransactions();
        verifyTransactions(listOfTransactions);
        addAllTransactionsToBlock(listOfTransactions, block);
        block.setPayloadAsJson(new Gson().toJson(block.getListOfVerifiedTransactions()));
        return listOfTransactions;
    }

    private void createBlockHeader(String version, int difficultyTarget, Block block, LinkedList<Transaction> listOfTransactions) {
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setMerkleRoot(constructMerkleRoot(listOfTransactions));
        blockHeader.setPreviousBlockHash(getPreviousBlockHash());
        blockHeader.setVersion(version);
        blockHeader.setDifficultyTarget(difficultyTarget);
        block.setBlockHeader(blockHeader);
    }

    private String constructMerkleRoot(LinkedList<Transaction> listOfTransactions) {
        LinkedList<String> merkleTree;
        merkleTree = getInitialHashesOfBottomRow(listOfTransactions);
        while (merkleTree.size() > 1) {
            merkleTree = combineHashes(merkleTree);
        }
        return (merkleTree.size() > 0) ? merkleTree.getFirst() : "";
    }

    private LinkedList<String> combineHashes(LinkedList<String> merkleTree) {
        LinkedList<String> combinedHashes = new LinkedList<>();
        for (int i = 0; i < merkleTree.size() - 1; i += 2) {
            combinedHashes.add(HasherUtil.hashString(
                    merkleTree.get(i) + merkleTree.get(i + 1)));
        }
        return combinedHashes;
    }

    private LinkedList<String> getInitialHashesOfBottomRow(LinkedList<Transaction> listOfTransactions) {
        LinkedList<String> bottomRow = new LinkedList<>();
        for (int i = 0; i < listOfTransactions.size() - 1; i += 2) {
            bottomRow.add(HasherUtil.hashString(
                    listOfTransactions.get(i).getDetails() + listOfTransactions.get(i + 1).getDetails()));
        }
        return bottomRow;
    }

    private String getPreviousBlockHash() {
        try {
            return blockchain.getBlockchain().getLast().getBlockHeader().getMinedHash();
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
