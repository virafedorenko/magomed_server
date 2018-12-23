package app.service;

import app.entity.Root;
import app.entity.User;

import java.util.List;

public interface RootService {
    Root save(Root root);

    Root getById(String id);

    void delete(String id);

}
