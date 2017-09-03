package com.blockframe.persistence;

import com.blockframe.blocks.Block;
import com.blockframe.blocks.BlockHeader;
import com.blockframe.transactions.Transaction;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class StorageManagerTest {

    private final String RETRIEVED_BLOCK_AS_JSON =
            "{\"blockHeader\":" +
                    "{\"blockId\":\"1\"," +
                    "\"difficultyTarget\":0," +
                    "\"timeStamp\":0," +
                    "\"nonce\":100," +
                    "\"miningTimeInSeconds\":0.0}, " +
                    "\"listOfVerifiedTransactions\":" +
                    "[{\"verified\":false," +
                    "\"mined\":false," +
                    "\"details\":\"test transaction\"}]}";

    @Mock
    private RedisClient mockRedisClient;
    @Mock
    private StatefulRedisConnection mockStatefulRedisConnection;
    @Mock
    private RedisCommands mockRedisCommands;

    @InjectMocks
    @Spy
    private StorageManager storageManager;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        doReturn(mockRedisClient).when(storageManager).getRedisClient();
        doReturn(mockStatefulRedisConnection).when(mockRedisClient).connect();
        doReturn(mockRedisCommands).when(mockStatefulRedisConnection).sync();
    }

    @Test
    public void testStoreBlock() throws Exception {
        Block block = new Block();
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setBlockId("1");
        block.setBlockHeader(blockHeader);
        storageManager.storeBlock(block);
        verify(mockRedisCommands).set(anyString(), anyString());
    }

    @Test
    public void testRetrieveBlock() throws Exception {
        Block block = new Block();
        BlockHeader blockHeader = new BlockHeader();
        blockHeader.setBlockId("1");
        blockHeader.setNonce(100);
        block.setBlockHeader(blockHeader);
        LinkedList<Transaction> listOfTransactions = new LinkedList<>();
        listOfTransactions.add(new Transaction("test transaction"));
        block.setListOfVerifiedTransactions(listOfTransactions);
        storageManager.storeBlock(block);
        doReturn(RETRIEVED_BLOCK_AS_JSON).when(mockRedisCommands).get(anyString());
        Block retrievedBlock = storageManager.retrieveBlock("blockId-1");
        assertEquals("1", retrievedBlock.getBlockHeader().getBlockId());
        assertEquals(100, retrievedBlock.getBlockHeader().getNonce());
        assertEquals(1, retrievedBlock.getListOfVerifiedTransactions().size());
        assertEquals("test transaction", retrievedBlock.getListOfVerifiedTransactions().getFirst().getDetails());
    }
}
