package com.alumni.controller;

import com.alumni.model.Alumni;
import com.alumni.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumni")
@CrossOrigin(origins = "*")
public class AlumniRestController {
    
    @Autowired
    private AlumniService alumniService;
    
    // GET /alumni - Get all alumni
    @GetMapping
    public ResponseEntity<List<Alumni>> getAllAlumni() {
        List<Alumni> alumni = alumniService.getAllAlumni();
        return new ResponseEntity<>(alumni, HttpStatus.OK);
    }
    
    // GET /alumni/{id} - Get alumni by ID
    @GetMapping("/{id}")
    public ResponseEntity<Alumni> getAlumniById(@PathVariable Long id) {
        Optional<Alumni> alumni = alumniService.getAlumniById(id);
        if (alumni.isPresent()) {
            return new ResponseEntity<>(alumni.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // GET /alumni/year/{year} - Get alumni by year
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Alumni>> getAlumniByYear(@PathVariable Integer year) {
        List<Alumni> alumni = alumniService.getAlumniByYear(year);
        return new ResponseEntity<>(alumni, HttpStatus.OK);
    }
    
    // POST /alumni - Create new alumni
    @PostMapping
    public ResponseEntity<Alumni> createAlumni(@Valid @RequestBody Alumni alumni) {
        Alumni savedAlumni = alumniService.saveAlumni(alumni);
        return new ResponseEntity<>(savedAlumni, HttpStatus.CREATED);
    }
    
    // PUT /alumni/{id} - Update alumni
    @PutMapping("/{id}")
    public ResponseEntity<Alumni> updateAlumni(@PathVariable Long id, @Valid @RequestBody Alumni alumni) {
        Alumni updatedAlumni = alumniService.updateAlumni(id, alumni);
        if (updatedAlumni != null) {
            return new ResponseEntity<>(updatedAlumni, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // DELETE /alumni/{id} - Delete alumni
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumni(@PathVariable Long id) {
        boolean deleted = alumniService.deleteAlumni(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // GET /alumni/search/name/{name} - Search by name
    @GetMapping("/search/name/{name}")
    public ResponseEntity<List<Alumni>> searchByName(@PathVariable String name) {
        List<Alumni> alumni = alumniService.searchAlumniByName(name);
        return new ResponseEntity<>(alumni, HttpStatus.OK);
    }
    
    // GET /alumni/search/company/{company} - Search by company
    @GetMapping("/search/company/{company}")
    public ResponseEntity<List<Alumni>> searchByCompany(@PathVariable String company) {
        List<Alumni> alumni = alumniService.searchAlumniByCompany(company);
        return new ResponseEntity<>(alumni, HttpStatus.OK);
    }
}