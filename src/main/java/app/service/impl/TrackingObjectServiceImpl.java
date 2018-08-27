package app.service.impl;

import app.entity.TrackingObject;
import app.repository.TrackingObjectRepository;
import app.service.TrackingObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackingObjectServiceImpl implements TrackingObjectService {
    @Autowired
    private TrackingObjectRepository trackingObjectRepository;

    @Override
    public TrackingObject save(TrackingObject trackingObject) {
        return trackingObjectRepository.save(trackingObject);
    }

    @Override
    public TrackingObject getById(String id) {
        return trackingObjectRepository.findById(id).get();
    }

    @Override
    public void delete(String id) {
        // TODO: 8/22/2018  logic of removing
    }
}
