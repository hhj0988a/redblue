package com.example.demo.bulkInsert;

import java.sql.PreparedStatement;
import java.util.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BulkInsert {
    @Autowired
    DataRepository dataRepository;  
    List<Data> data = new ArrayList<Data>();
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/insertData")
    public ResponseEntity<String> insertData(JSONObject json) {
        Data newData= new Data(json);
        if (data.size()<500){
            data.add(newData);
            return ResponseEntity.ok("added in array");
        }
        else{
            data.add(newData);
            saveAll(data);
            return ResponseEntity.ok("bulk saved");
        }        
    }

    @Transactional
    public void saveAll(List<Data> dataList){
        String sql = "INSERT INTO data_table (data)"+
                "VALUES (?)";

        jdbcTemplate.batchUpdate(sql,
                dataList,
                dataList.size(),
                (PreparedStatement ps, Data data) -> {
                    ps.setString(1, data.getData().toString());
                });
    }

}