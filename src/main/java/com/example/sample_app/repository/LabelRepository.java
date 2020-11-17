package com.example.sample_app.repository;

import com.example.sample_app.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Integer> {

//    @Query("UPDATE label l SET l.name= :labelName Where l.id= :labelId")
//    Label update(@Param("labelId") int id, @Param("labelName") String name);
}
