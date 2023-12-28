package com.example.demo.bulkInsert;

import java.util.List;

import org.json.JSONObject;
import org.springframework.data.repository.CrudRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Entity
public class Data {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="Data", columnDefinition="jsonb",  unique=false)
    private JSONObject data;
    
    public Data(JSONObject data) {
      this.data = data;
  }
}


interface DataRepository extends CrudRepository<Data, Long> {

  <LData extends Data> List<LData> saveAll(Iterable<LData> data);
}