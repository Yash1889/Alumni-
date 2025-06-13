package com.alumni.controller;

import com.alumni.model.Alumni;
import com.alumni.service.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/alumni")
public class AlumniWebController {
    
    @Autowired
    private AlumniService alumniService;
    
    // Display all alumni
    @GetMapping
    public String listAlumni(Model model) {
        List<Alumni> alumni = alumniService.getAllAlumni();
        model.addAttribute("alumni", alumni);
        return "alumni/list";
    }
    
    // Show form to add new alumni
    @GetMapping("/new")
    public String showNewAlumniForm(Model model) {
        model.addAttribute("alumni", new Alumni());
        return "alumni/form";
    }
    
    // Show form to edit alumni
    @GetMapping("/edit/{id}")
    public String showEditAlumniForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Alumni> alumni = alumniService.getAlumniById(id);
        if (alumni.isPresent()) {
            model.addAttribute("alumni", alumni.get());
            return "alumni/form";
        }
        redirectAttributes.addFlashAttribute("error", "Alumni not found");
        return "redirect:/alumni";
    }
    
    // Save alumni (both new and edit)
    @PostMapping("/save")
    public String saveAlumni(@Valid @ModelAttribute Alumni alumni, BindingResult result, 
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "alumni/form";
        }
        
        try {
            if (alumni.getId() != null) {
                alumniService.updateAlumni(alumni.getId(), alumni);
                redirectAttributes.addFlashAttribute("success", "Alumni updated successfully");
            } else {
                alumniService.saveAlumni(alumni);
                redirectAttributes.addFlashAttribute("success", "Alumni added successfully");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error saving alumni: " + e.getMessage());
        }
        
        return "redirect:/alumni";
    }
    
    // Delete alumni
    @GetMapping("/delete/{id}")
    public String deleteAlumni(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = alumniService.deleteAlumni(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("success", "Alumni deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "Alumni not found");
        }
        return "redirect:/alumni";
    }
    
    // View alumni details
    @GetMapping("/view/{id}")
    public String viewAlumni(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Alumni> alumni = alumniService.getAlumniById(id);
        if (alumni.isPresent()) {
            model.addAttribute("alumni", alumni.get());
            return "alumni/view";
        }
        redirectAttributes.addFlashAttribute("error", "Alumni not found");
        return "redirect:/alumni";
    }
    
    // Search alumni by year
    @GetMapping("/year/{year}")
    public String getAlumniByYear(@PathVariable Integer year, Model model) {
        List<Alumni> alumni = alumniService.getAlumniByYear(year);
        model.addAttribute("alumni", alumni);
        model.addAttribute("year", year);
        return "alumni/list";
    }
}