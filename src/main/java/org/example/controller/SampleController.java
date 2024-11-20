package org.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SampleController {

    @GetMapping("/testException")
    public ResponseEntity<String> testException(@RequestParam(required = false) String name) {
        if (name == null) {
            return ResponseEntity.badRequest().body("Name parameter is required.");
        }
        return ResponseEntity.ok("Hello, " + name);
    }
}
