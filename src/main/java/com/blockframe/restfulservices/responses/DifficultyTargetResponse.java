package com.blockframe.restfulservices.responses;

import com.blockframe.Blockframe;

public class DifficultyTargetResponse {

    private int difficultyTarget = Blockframe.getDifficultyTarget();

    public int getDifficultyTarget() {
        return difficultyTarget;
    }

    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
    }
}
