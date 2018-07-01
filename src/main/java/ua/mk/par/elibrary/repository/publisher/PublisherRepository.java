package ua.mk.par.elibrary.repository.publisher;

import java.util.List;

import ua.mk.par.elibrary.entity.Publisher;

public interface PublisherRepository {

    public Publisher getById(Long id);

    public List<Publisher> getAll(Boolean forward, Long first, Integer max);

    public Publisher create(Publisher publisher);

    public Publisher update(Publisher publisher);

    public void delete(Long id);
}
