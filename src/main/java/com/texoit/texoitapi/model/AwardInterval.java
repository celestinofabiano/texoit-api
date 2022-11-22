package com.texoit.texoitapi.model;

import java.util.List;

public class AwardInterval {

    private List<AwardProducer> min;

    private List<AwardProducer> max;

    public List<AwardProducer> getMin() {
        return min;
    }

    public void setMin(List<AwardProducer> min) {
        this.min = min;
    }

    public List<AwardProducer> getMax() {
        return max;
    }

    public void setMax(List<AwardProducer> max) {
        this.max = max;
    }

}
