package ua.mk.par.elibrary.service.permition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ua.mk.par.elibrary.controller.permition.forms.EditPermitionForm;
import ua.mk.par.elibrary.entity.Permition;
import ua.mk.par.elibrary.repository.permition.PermitionRepository;

@Service
@Transactional
public class DefaultPermitionService implements PermitionService {

    private final PermitionRepository permitionRepository;

    public DefaultPermitionService(PermitionRepository permitionRepository) {
        this.permitionRepository = permitionRepository;
    }

    @Override
    public Permition getById(Long id) {
        return permitionRepository.getById(id);
    }

    @Override
    public EditPermitionForm getFormById(Long id) {
        if (id == 0l){
            EditPermitionForm eu = new EditPermitionForm();
            return eu;
        }
        Permition a = getById(id);
        if (a != null) {
            EditPermitionForm eu = new EditPermitionForm();
            eu.setId(a.getId());
            eu.setName(a.getName());
            return eu;
        }
        return null;
    }

    @Override
    public List<EditPermitionForm> getAll(Boolean forward, Long first, int max) {
        List<Permition> permitions = permitionRepository.getAll(forward, first, max);
        List<EditPermitionForm> formPermList = new ArrayList<>();
        for (Permition permition : permitions) {
            EditPermitionForm formPerm = new EditPermitionForm();
            formPerm.setId(permition.getId());
            formPerm.setName(permition.getName());
            formPermList.add(formPerm);
        }
        if (!forward) {
            Collections.sort(formPermList, new Comparator<EditPermitionForm>() {
                @Override
                public int compare(EditPermitionForm o1, EditPermitionForm o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
        }
        return formPermList;
    }

    @Override
    public EditPermitionForm create(EditPermitionForm editPermitionForm) throws IOException {
        Permition permition = new Permition();
        permition.setName(editPermitionForm.getName());
        permitionRepository.create(permition);
        editPermitionForm.setId(permition.getId());
        return editPermitionForm;
    }

    @Override
    public void update(EditPermitionForm editPermitionForm) throws IOException  {
        if (editPermitionForm.getId()==null){
            create(editPermitionForm);
            return;
        }
        Permition permition = permitionRepository.getById(editPermitionForm.getId());
        if (permition == null) {
            return;
        } else {
            permition.setId(editPermitionForm.getId());
            permition.setName(editPermitionForm.getName());
            permitionRepository.update(permition);
        }
    }

    @Override
    public void delete(Long id) {
        permitionRepository.delete(id);

    }
}
