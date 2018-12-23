package app.service.impl;

import app.entity.Peak;
import app.entity.Root;
import app.repository.PeakRepository;
import app.service.PeakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PeakServiceImpl implements PeakService {
    @Autowired
    private PeakRepository peakRepository;

    @Override
    public Peak save(Peak peak) {
        return peakRepository.save(peak);
    }

    @Override
    public Peak getById(String id) {
        return peakRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        peakRepository.deleteById(id);
    }

    @Override
    public List<Peak> findByRoot(Root root) {
        return peakRepository.findByRoot(root);
    }

    @Override
    public List<Peak> findAll() {
        Iterable<Peak> peaks = peakRepository.findAll();
        List<Peak> collection = new ArrayList<>();
        peaks.forEach(collection::add);
        return collection;
    }
}
