package com.texoit.texoitapi.service;

import com.texoit.texoitapi.model.AwardInterval;
import com.texoit.texoitapi.model.AwardProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AwardIntervalService {

    @Autowired
    private AwardProducerService awardProducerService;

    public AwardInterval getAwardIntervalMinMax() {
        List<AwardProducer> awardProducerList =
                awardProducerService.getAwardProducersWithMoreThanOneAward();
        List<AwardProducer> awardProducersMin = new ArrayList<>();
        List<AwardProducer> awardProducersMax = new ArrayList<>();
        Comparator<AwardProducer> comparator = Comparator.comparing(AwardProducer::getInterval);

        awardProducerList.stream()
                .filter(a -> a.getInterval() == awardProducerList.stream().min(comparator).get()
                        .getInterval()).forEach(a -> awardProducersMin.add(a));
        awardProducerList.stream()
                .filter(a -> a.getInterval() == awardProducerList.stream().max(comparator).get()
                        .getInterval()).forEach(a -> awardProducersMax.add(a));

        AwardInterval awardInterval = new AwardInterval();
        awardInterval.setMax(awardProducersMax);
        awardInterval.setMin(awardProducersMin);

        return awardInterval;
    }
}
