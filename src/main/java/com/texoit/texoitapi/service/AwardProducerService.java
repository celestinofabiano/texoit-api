package com.texoit.texoitapi.service;

import com.texoit.texoitapi.model.AwardProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AwardProducerService {

    @Autowired
    private MovieService movieService;

    public List<AwardProducer> getAwardProducersWithMoreThanOneAward() {
        Map<String, AwardProducer> awardProducerMap = new HashMap<>();
        List<AwardProducer> awardProducers = new ArrayList<>();

        movieService.getWinnerMoviesOrderedByProducerAndYear().forEach(movie ->
                Optional.ofNullable(awardProducerMap.get(movie.getProducer())).ifPresentOrElse(
                        awardProducer -> {
                            AwardProducer recentAwardProducer = new AwardProducer();
                            recentAwardProducer.setProducer(movie.getProducer());
                            recentAwardProducer.setPreviousWin(awardProducer.getFollowingWin());
                            recentAwardProducer.setFollowingWin(movie.getYear());
                            fillInterval(recentAwardProducer);
                            awardProducers.add(recentAwardProducer);
                            awardProducerMap.put(recentAwardProducer.getProducer(),
                                    recentAwardProducer);
                        },
                        () -> {
                            AwardProducer firstAwardProducer = new AwardProducer();
                            firstAwardProducer.setProducer(movie.getProducer());
                            firstAwardProducer.setPreviousWin(movie.getYear());
                            firstAwardProducer.setFollowingWin(movie.getYear());
                            fillInterval(firstAwardProducer);
                            awardProducerMap.put(firstAwardProducer.getProducer(),
                                    firstAwardProducer);
                        }
                )
        );

        return awardProducers;
    }

    public void fillInterval(AwardProducer awardProducer) {
        try {
            awardProducer.setInterval(
                    awardProducer.getFollowingWin() - awardProducer.getPreviousWin());
        } catch (Exception e) {
            awardProducer.setInterval(0);
        }
    }

}
