package com.alumni.service;

import com.alumni.model.Alumni;
import com.alumni.repository.AlumniRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AlumniServiceTest {

    @Mock
    private AlumniRepository alumniRepository;

    @InjectMocks
    private AlumniService alumniService;

    private Alumni testAlumni;

    @BeforeEach
    void setUp() {
        testAlumni = new Alumni("John Doe", 2020, "Tech Corp", "john@example.com");
        testAlumni.setId(1L);
    }

    @Test
    void testGetAllAlumni() {
        List<Alumni> alumniList = Arrays.asList(testAlumni);
        when(alumniRepository.findAllByOrderByBatchDesc()).thenReturn(alumniList);

        List<Alumni> result = alumniService.getAllAlumni();

        assertEquals(1, result.size());
        assertEquals(testAlumni.getName(), result.get(0).getName());
        verify(alumniRepository, times(1)).findAllByOrderByBatchDesc();
    }

    @Test
    void testGetAlumniById() {
        when(alumniRepository.findById(1L)).thenReturn(Optional.of(testAlumni));

        Optional<Alumni> result = alumniService.getAlumniById(1L);

        assertTrue(result.isPresent());
        assertEquals(testAlumni.getName(), result.get().getName());
        verify(alumniRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveAlumni() {
        when(alumniRepository.save(any(Alumni.class))).thenReturn(testAlumni);

        Alumni result = alumniService.saveAlumni(testAlumni);

        assertEquals(testAlumni.getName(), result.getName());
        verify(alumniRepository, times(1)).save(testAlumni);
    }

    @Test
    void testDeleteAlumni() {
        when(alumniRepository.existsById(1L)).thenReturn(true);

        boolean result = alumniService.deleteAlumni(1L);

        assertTrue(result);
        verify(alumniRepository, times(1)).existsById(1L);
        verify(alumniRepository, times(1)).deleteById(1L);
    }
}