package com.blockframe.blocks;

import com.blockframe.utils.HasherUtil;
import com.blockframe.utils.MerkleRootUtil;

public class BlockValidator {

    public static boolean validateBlock(Block block) {
        String constructedMerkleRoot = MerkleRootUtil.constructMerkleRoot(block.getListOfVerifiedTransactions());
        String previousBlockHash = block.getBlockHeader().getPreviousBlockHash();
        String nonce = String.valueOf(block.getBlockHeader().getNonce());
        String constructedHash = HasherUtil.hashString(block.getBlockHeader().getVersion() + previousBlockHash + constructedMerkleRoot + nonce);
        return constructedHash.equals(block.getBlockHeader().getMinedHash());
    }
}
