package com.blocks.blocks;

public class BlockPrinter {

    public static void printMinedBlock(Block block) {
        System.out.println("\r-----------------------------------BLOCK FOUND!---------------------------------");
        System.out.println("Block Payload: " + block.getPayloadAsJson());
        System.out.println("Total Transactions:" + block.getListOfVerifiedTransactions().size());
        System.out.println("Average Hashrate: " + (int) (block.getBlockHeader().getNonce() / block.getBlockHeader().getMiningTimeInSeconds()));
        System.out.println("Final nonce: " + block.getBlockHeader().getNonce());
        System.out.println("Previous Block's hash: " + block.getBlockHeader().getPreviousBlockHash());
        System.out.println("This Block's Mined hash: " + block.getBlockHeader().getMinedHash());
        System.out.println("Merkle Root: " + block.getBlockHeader().getMerkleRoot());
        System.out.println("Timestamp: " + block.getBlockHeader().getTimeStamp());
        System.out.println("Difficulty: " + block.getBlockHeader().getDifficultyTarget());
        System.out.println("Version: " + block.getBlockHeader().getVersion());
        System.out.println("Total time spent mining: " + (int) block.getBlockHeader().getMiningTimeInSeconds() + " seconds.");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }
}
