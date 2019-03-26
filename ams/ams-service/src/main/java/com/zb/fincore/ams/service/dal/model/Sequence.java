package com.zb.fincore.ams.service.dal.model;

import com.zb.fincore.ams.common.model.BaseDo;

public class Sequence extends BaseDo {

    private String sequenceName;

    private Long currentVal;

    private Integer sequenceStep;

    private Long version;

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    public Long getCurrentVal() {
        return currentVal;
    }

    public void setCurrentVal(Long currentVal) {
        this.currentVal = currentVal;
    }

    public Integer getSequenceStep() {
        return sequenceStep;
    }

    public void setSequenceStep(Integer sequenceStep) {
        this.sequenceStep = sequenceStep;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}