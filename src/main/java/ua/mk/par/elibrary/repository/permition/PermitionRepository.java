package ua.mk.par.elibrary.repository.permition;

import java.util.List;

import ua.mk.par.elibrary.entity.Permition;

public interface PermitionRepository {

    public Permition getById(Long id);

    public List<Permition> getAll(Boolean forward, Long first, Integer max);

    public Permition create(Permition permition);

    public Permition update(Permition permition);

    public void delete(Long id);
}
