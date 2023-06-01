package com.eduardo.app.controller.client;

import com.eduardo.app.constant.RestRequestMapping;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestRequestMapping.CLIENT)
public class Client {

    @GetMapping
    public ResponseEntity<?> all() {
        Map<String, Object> response = new HashMap();
        response.put("ok", true);
        response.put("data", "arrays data");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

}
