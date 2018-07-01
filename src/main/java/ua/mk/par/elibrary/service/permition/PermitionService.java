package ua.mk.par.elibrary.service.permition;

import java.io.IOException;
import java.util.List;

import ua.mk.par.elibrary.controller.permition.forms.EditPermitionForm;
import ua.mk.par.elibrary.entity.Permition;

public interface PermitionService {

    public Permition getById(Long id);

    public EditPermitionForm getFormById(Long id);

    public EditPermitionForm create(EditPermitionForm editPermitionForm) throws IOException;

    public void update(EditPermitionForm editPermitionForm) throws IOException;

    public void delete(Long id);

    public List<EditPermitionForm> getAll(Boolean forward, Long first, int max);
}
