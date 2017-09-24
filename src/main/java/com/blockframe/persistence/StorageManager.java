package com.blockframe.persistence;

import com.blockframe.blocks.Block;
import com.google.gson.Gson;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.sync.RedisCommands;

public class StorageManager {

    private static final String KEY_PREFIX = "blockId-";
    Gson gson = new Gson();

    public void storeBlock(Block block) {
        RedisClient redisClient = getRedisClient();
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.set(KEY_PREFIX + block.getBlockHeader().getBlockId(), gson.toJson(block));
        System.out.println("Block " + block.getBlockHeader().getBlockId() + " was stored under key: "
                + KEY_PREFIX + block.getBlockHeader().getBlockId());
        connection.close();
        redisClient.shutdown();
    }

    public Block retrieveBlock(String blockId) {
        RedisClient redisClient = getRedisClient();
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        Block block = gson.fromJson(syncCommands.get(blockId), Block.class);
        connection.close();
        redisClient.shutdown();
        return block;
    }

    protected RedisClient getRedisClient() {
        // redishost is defined in the docker-compose.yml
        // file at the root level of this project.
        return RedisClient.create("redis://redishost:6379/0");
    }


}
