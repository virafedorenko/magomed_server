package app.service.impl;

import app.entity.Root;
import app.repository.RootRepository;
import app.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RootServiceImpl implements RootService {
    @Autowired
    private RootRepository rootRepository;

    @Override
    public Root save(Root root) {
        return rootRepository.save(root);
    }

    @Override
    public Root getById(String id) {
        return rootRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        rootRepository.deleteById(id);
    }
}
