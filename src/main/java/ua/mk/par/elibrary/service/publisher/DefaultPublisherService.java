package ua.mk.par.elibrary.service.publisher;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.mk.par.elibrary.controller.permition.forms.EditPermitionForm;
import ua.mk.par.elibrary.controller.publisher.forms.EditPublisherForm;
import ua.mk.par.elibrary.entity.Publisher;
import ua.mk.par.elibrary.repository.publisher.PublisherRepository;

@Service
@Transactional
public class DefaultPublisherService implements PublisherService {

    private final PublisherRepository publisherRepository;

    public DefaultPublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public Publisher getById(Long id) {
        return publisherRepository.getById(id);
    }

    @Override
    public EditPublisherForm getFormById(Long id) {
        if (id == 0l){
            EditPublisherForm eu = new EditPublisherForm();
            return eu;
        }
        Publisher a = getById(id);
        if (a != null) {
            EditPublisherForm eu = new EditPublisherForm();
            eu.setId(a.getId());
            eu.setName(a.getName());
            eu.setDescription(a.getDescription());
            return eu;
        }
        return null;
    }

    @Override
    public List<EditPublisherForm> getAll(Boolean forward, Long first, int max) {
        List<Publisher> publishs = publisherRepository.getAll(forward, first, max);
        List<EditPublisherForm> formPubList = new ArrayList<>();
        for (Publisher publisher : publishs) {
            EditPublisherForm formPub = new EditPublisherForm();
            formPub.setId(publisher.getId());
            formPub.setName(publisher.getName());
            formPub.setDescription(publisher.getDescription());
            formPubList.add(formPub);
        }
        if (!forward) {
            Collections.sort(formPubList, new Comparator<EditPublisherForm>() {
                @Override
                public int compare(EditPublisherForm o1, EditPublisherForm o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        return formPubList;
    }

    @Override
    public EditPublisherForm create(EditPublisherForm editPublisherForm) throws IOException {
        Publisher publisher = new Publisher();
        publisher.setName(editPublisherForm.getName());
        publisher.setDescription(editPublisherForm.getDescription());
        publisherRepository.create(publisher);
        editPublisherForm.setId(publisher.getId());
        return editPublisherForm;
    }

    @Override
    public void update(EditPublisherForm editPublisherForm) throws IOException  {
        if (editPublisherForm.getId()==null){
            create(editPublisherForm);
            return;
        }
        Publisher publisher = publisherRepository.getById(editPublisherForm.getId());
        if (publisher == null) {
            return;
        } else {
            publisher.setId(editPublisherForm.getId());
            publisher.setName(editPublisherForm.getName());
            publisher.setDescription(editPublisherForm.getDescription());
            publisherRepository.update(publisher);
        }
    }

    @Override
    public void delete(Long id) {
        publisherRepository.delete(id);

    }
}
