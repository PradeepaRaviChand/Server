package com.kaleidofin.server.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.kaleidofin.server.model.Fhc;
import com.kaleidofin.server.repository.FhcRepository;
import com.vladmihalcea.hibernate.type.json.internal.JacksonUtil;

@RestController
@RequestMapping("/api")

public class FhcController {
	@Autowired
    FhcRepository repo;
	Logger logger = LoggerFactory.getLogger(FhcController.class);
//	@PostMapping("/add_typeform_data")
//	public Fhc createNote(@Valid @RequestBody Fhc note) {
//	    return repo.save(note);
//	}

	@ResponseBody @RequestMapping("/add_typeform_data")
	public void addFormData(@RequestBody Map<String, Object> payload) { 
		
		Map<String, Object> requestPayload = (Map<String, Object>) payload.get("payload");
		int typeFormId = Integer.valueOf((String) requestPayload.get("id"));
		
		Map<String, Object> formResponse = (Map<String, Object>) requestPayload.get("form_response");
		ArrayList arrlist = (ArrayList) formResponse.get("answers");
		
		Map<String, Object> nameObj = (Map<String, Object>) arrlist.get(0);
        String name = (String) nameObj.get("text");
        
        Map<String, Object> emailObj = (Map<String, Object>) arrlist.get(1);
        String email = (String) emailObj.get("text");
        
        Map<String, Object> phoneNumberObj = (Map<String, Object>) arrlist.get(2);
        String phone_number = (String) (phoneNumberObj.get("phone_number"));
        
        Fhc row = new Fhc();
        row.setName(name);
        row.setEmail(email);
        row.setTypeFormId(typeFormId);
        row.setSessionId("testId");
        row.setStatus("status");
        row.setRequestPayload(requestPayload);
		repo.save(row);
		
	}
	
	@GetMapping("/survey_result/{session_id}")
	public List<Object[]> fetchSurveyResult(@PathVariable(value = "session_id") String sessionId) {
		System.out.println(sessionId);
		List<Object[]> row = repo.findBySessionId(sessionId);
		return row; 
		
	}
}
