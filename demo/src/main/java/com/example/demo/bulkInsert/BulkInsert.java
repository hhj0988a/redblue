package com.example.demo.bulkInsert;

import java.util.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulkInsert {
    @Autowired
    DataRepository dataRepository;  
    List<Data> data = new ArrayList<Data>();

    @PostMapping("/insertData")
    public ResponseEntity<String> insertData(JSONObject json) {
        Data newData= new Data(json);
        if (data.size()<500){
            data.add(newData);
            return ResponseEntity.ok("added in array");
        }
        else{
            data.add(newData);
            dataRepository.saveAll(data);
            return ResponseEntity.ok("bulk saved");
        }        
    }

}