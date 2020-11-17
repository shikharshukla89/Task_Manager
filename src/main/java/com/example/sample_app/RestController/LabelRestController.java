package com.example.sample_app.RestController;

import com.example.sample_app.entity.Label;
import com.example.sample_app.service.LabelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/labels")
public class LabelRestController {

    private final LabelService labelService;

    public LabelRestController(LabelService labelService) {
        this.labelService = labelService;
    }

    @GetMapping
    public List<Label> findAll(){

        List<Label> labelList = labelService.findAll();
        labelList.forEach(this::sanitize);
        return labelList;
    }

    @GetMapping("/{id}")
    public Optional<Label> findOne(@PathVariable("id") int id){

        Optional<Label> label = labelService.findOne(id);

        return label;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Label label) {
        label = labelService.save(label);
        return new ResponseEntity<>(label, HttpStatus.CREATED);
    }

    @PutMapping("/{labelId}")
    public Label update(@RequestBody Label label, @PathVariable("labelId") int id){

        label.setId(id);
        labelService.save(label);

        return label;
    }

//    @DeleteMapping
//    public void deleteLabels(){
//
//        labelRepo.deleteAll();
//    }

    @DeleteMapping("/{id}")
    public void deleteOne(@PathVariable("id") int id){

        labelService.deleteOne(id);
    }

    private Label sanitize(Label mLabel){
        if (mLabel.getTasks() != null){
            mLabel.getTasks().forEach(task -> task.setLabel(null));
        }
        return mLabel;
    }

}
