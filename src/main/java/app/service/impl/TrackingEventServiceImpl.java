package app.service.impl;

import app.entity.TrackingEvent;
import app.repository.TrackingEventRepository;
import app.service.TrackingEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackingEventServiceImpl implements TrackingEventService {
    @Autowired
    private TrackingEventRepository trackingEventRepository;

    @Override
    public TrackingEvent save(TrackingEvent trackingObject) {
        return trackingEventRepository.save(trackingObject);
    }

    @Override
    public TrackingEvent getById(String id) {
        return trackingEventRepository.findById(id).get();
    }

    @Override
    public void delete(String id) {
// TODO: 8/27/2018 implement logic for removing of event
    }
}
