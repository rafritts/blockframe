package com.blockframe.restfulservices.responses;

import com.blockframe.BlockframeApp;

public class DifficultyTargetResponse {

    private int difficultyTarget = BlockframeApp.getDifficultyTarget();

    public int getDifficultyTarget() {
        return difficultyTarget;
    }

    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
    }
}
