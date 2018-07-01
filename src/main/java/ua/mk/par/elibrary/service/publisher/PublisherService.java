package ua.mk.par.elibrary.service.publisher;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.publisher.forms.EditPublisherForm;
import ua.mk.par.elibrary.entity.Publisher;

public interface PublisherService {

    public Publisher getById(Long id);

    public EditPublisherForm getFormById(Long id);

    public EditPublisherForm create(EditPublisherForm editPublisherForm) throws IOException;

    public void update(EditPublisherForm editPublisherForm) throws IOException;

    public void delete(Long id);

    public List<EditPublisherForm> getAll(Boolean forward, Long first, int max);
}
