package com.jmsilla.crmapitest.api.controllers;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class CallbackController {
	@GetMapping("/callback")
	public ResponseEntity<Object> callback() {
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
