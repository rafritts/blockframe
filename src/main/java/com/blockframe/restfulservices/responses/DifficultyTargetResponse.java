package com.blockframe.restfulservices.responses;

import com.blockframe.utils.ObjectProvider;

public class DifficultyTargetResponse {

    private int difficultyTarget = ObjectProvider.DIFFICULTY_TARGET;

    public int getDifficultyTarget() {
        return difficultyTarget;
    }

    public void setDifficultyTarget(int difficultyTarget) {
        this.difficultyTarget = difficultyTarget;
    }
}
