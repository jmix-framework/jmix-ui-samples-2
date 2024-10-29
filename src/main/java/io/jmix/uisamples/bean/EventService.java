package io.jmix.uisamples.bean;

import io.jmix.core.DataManager;
import io.jmix.uisamples.entity.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("uisamples_EventService")
public class EventService {

    private final DataManager dataManager;

    public EventService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Event> fetchEvents(LocalDate start, LocalDate end) {
        return dataManager.load(Event.class)
                .query("select e from Event e where e.startDate >= :start and e.endDate < :end")
                .parameter("start", start.atStartOfDay())
                .parameter("end", end.atStartOfDay())
                .list();
    }
}
