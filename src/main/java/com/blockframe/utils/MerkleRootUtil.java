package com.blockframe.utils;

import com.blockframe.transactions.Transaction;

import java.util.LinkedList;

public class MerkleRootUtil {

    public static String constructMerkleRoot(LinkedList<Transaction> listOfTransactions) {
        LinkedList<String> merkleTree;
        merkleTree = getInitialHashesOfBottomRow(listOfTransactions);
        while (merkleTree.size() > 1) {
            merkleTree = combineHashes(merkleTree);
        }
        return merkleTree.size() > 0 ? merkleTree.getFirst() : "";
    }

    private static LinkedList<String> combineHashes(LinkedList<String> merkleTree) {
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

    private static LinkedList<String> getInitialHashesOfBottomRow(LinkedList<Transaction> listOfTransactions) {
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


}
