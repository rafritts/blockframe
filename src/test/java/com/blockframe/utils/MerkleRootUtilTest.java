package com.blockframe.utils;

import com.blockframe.transactions.Transaction;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class MerkleRootUtilTest {

    @Test
    public void testMerkleRootNoElements() {
        LinkedList<Transaction> emptyLinkedList = new LinkedList<>();
        assertEquals("", MerkleRootUtil.constructMerkleRoot(emptyLinkedList));
    }

    @Test
    public void testMerkleRootOneElement() {
        LinkedList<Transaction> oneTransactionList = new LinkedList<>();
        oneTransactionList.add(new Transaction("test"));
        assertEquals("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
                MerkleRootUtil.constructMerkleRoot(oneTransactionList));
    }

    @Test
    public void testMerkleRootTwoElements() {
        LinkedList<Transaction> twoTransactionList = new LinkedList<>();
        twoTransactionList.add(new Transaction("test1"));
        twoTransactionList.add(new Transaction("test2"));
        assertEquals("4f980b6f9baa6965f760d0bf2b2ccbee483032e5df01d77bbd9e25f7517a06b9",
                MerkleRootUtil.constructMerkleRoot(twoTransactionList));
    }

    @Test
    public void testMerkleRootFiveElements() {
        LinkedList<Transaction> fiveTransactionList = new LinkedList<>();
        fiveTransactionList.add(new Transaction("test1"));
        fiveTransactionList.add(new Transaction("test2"));
        fiveTransactionList.add(new Transaction("test3"));
        fiveTransactionList.add(new Transaction("test4"));
        fiveTransactionList.add(new Transaction("test5"));
        assertEquals("b7f1344ceda7a164f48dccb668ba4a12c3f14b24093c36bb73a029c1d3580f28",
                MerkleRootUtil.constructMerkleRoot(fiveTransactionList));
    }


}
