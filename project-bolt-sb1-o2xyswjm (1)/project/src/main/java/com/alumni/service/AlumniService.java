package com.alumni.service;

import com.alumni.model.Alumni;
import com.alumni.repository.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumniService {
    
    @Autowired
    private AlumniRepository alumniRepository;
    
    // Get all alumni
    public List<Alumni> getAllAlumni() {
        return alumniRepository.findAllByOrderByBatchDesc();
    }
    
    // Get alumni by id
    public Optional<Alumni> getAlumniById(Long id) {
        return alumniRepository.findById(id);
    }
    
    // Get alumni by year
    public List<Alumni> getAlumniByYear(Integer year) {
        return alumniRepository.findByYear(year);
    }
    
    // Save alumni
    public Alumni saveAlumni(Alumni alumni) {
        return alumniRepository.save(alumni);
    }
    
    // Update alumni
    public Alumni updateAlumni(Long id, Alumni alumniDetails) {
        Optional<Alumni> optionalAlumni = alumniRepository.findById(id);
        if (optionalAlumni.isPresent()) {
            Alumni alumni = optionalAlumni.get();
            alumni.setName(alumniDetails.getName());
            alumni.setBatch(alumniDetails.getBatch());
            alumni.setCompany(alumniDetails.getCompany());
            alumni.setContact(alumniDetails.getContact());
            return alumniRepository.save(alumni);
        }
        return null;
    }
    
    // Delete alumni
    public boolean deleteAlumni(Long id) {
        if (alumniRepository.existsById(id)) {
            alumniRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Search alumni by name
    public List<Alumni> searchAlumniByName(String name) {
        return alumniRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Search alumni by company
    public List<Alumni> searchAlumniByCompany(String company) {
        return alumniRepository.findByCompanyContainingIgnoreCase(company);
    }
}