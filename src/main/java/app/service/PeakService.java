package app.service;

import app.entity.Peak;
import app.entity.Root;
import app.entity.User;

import java.util.List;

public interface PeakService {
    Peak save(Peak peak);

    Peak getById(String id);

    void delete(String id);

    List<Peak> findByRoot(Root root);

    List<Peak> findAll();
}
