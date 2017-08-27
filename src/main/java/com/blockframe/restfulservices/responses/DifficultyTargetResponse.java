package com.blockframe.restfulservices.responses;

import com.blockframe.BlockChainApp;

public class DifficultyTargetResponse {

    private int difficultyTarget = BlockChainApp.getDifficultyTarget();

    public int getDifficultyTarget() {
        return difficultyTarget;
    }

    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
    }
}
