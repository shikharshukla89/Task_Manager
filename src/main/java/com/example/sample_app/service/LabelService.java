package com.example.sample_app.service;

import com.example.sample_app.entity.Label;
import com.example.sample_app.repository.LabelRepository;
import com.example.sample_app.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LabelService {
    private final LabelRepository labelRepository;

    TaskRepository taskRepository;

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public List<Label> findAll(){

        return labelRepository.findAll();
    }

    public Optional<Label> findOne (int id) {
      return labelRepository.findById(id);
    }

    @Transactional
    public Label save (Label label) {
        return labelRepository.save(label);
    }

    public Label update(Label label){
        Label mLabel = labelRepository.getOne(label.getId());
        mLabel.setName(label.getName());

        return mLabel;
    }

    @Transactional
    public void deleteOne(int id){

//        Label label = labelRepository.getOne(id);
//        label.getTasks().removeAll(label.getTasks());
//        labelRepository.delete(label);

        labelRepository.deleteById(id);
    }
}
