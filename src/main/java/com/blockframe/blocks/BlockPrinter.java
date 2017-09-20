package com.blockframe.blocks;

public class BlockPrinter {

    public static void printMinedBlock(Block block) {
        System.out.println("\r" + "-----------------------------------BLOCK FOUND!---------------------------------");
        System.out.println("--- Block Header ---");
        System.out.println("Version: " + block.getBlockHeader().getVersion());
        System.out.println("BlockId: " + block.getBlockHeader().getBlockId());
        System.out.println("Difficulty Target: " + block.getBlockHeader().getDifficultyTarget());
        System.out.println("Previous Block's hash: " + block.getBlockHeader().getPreviousBlockHash());
        System.out.println("This Block's Mined hash: " + block.getBlockHeader().getMinedHash());
        System.out.println("Merkle Root: " + block.getBlockHeader().getMerkleRoot());
        System.out.println("Timestamp: " + block.getBlockHeader().getTimeStamp());
        System.out.println("Total mining time: " + (int) block.getBlockHeader().getMiningTimeInSeconds() + " seconds.");
        System.out.println("--- Block Payload ---");
        System.out.println("Total Transactions:" + block.getListOfVerifiedTransactions().size());
        System.out.println("Average Hashrate: " + block.getBlockHeader().getNonce() / (block.getBlockHeader().getMiningTimeInSeconds()+1));
        System.out.println("Final nonce: " + block.getBlockHeader().getNonce());
        System.out.println("Payload as Json: " + block.getPayloadAsJson());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println();
    }
}
