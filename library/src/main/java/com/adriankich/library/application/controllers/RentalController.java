package com.adriankich.library.application.controllers;

import com.adriankich.library.application.dto.request.RentalRequestDTO;
import com.adriankich.library.application.dto.response.RentalResponseDTO;
import com.adriankich.library.domain.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/rentals", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController extends Controller {

    @Autowired
    private RentalService rentalService;

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDTO> getRentalById(@PathVariable Long id) {
        return ok(rentalService.getRentalById(id));
    }

    @PostMapping
    public ResponseEntity<RentalResponseDTO> createRental(@RequestBody @Valid RentalRequestDTO rentalRequestDTO) {
        return created(rentalService.createRental(rentalRequestDTO));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<RentalResponseDTO> returnsBooks(@PathVariable Long id) {
        return ok(rentalService.returnsBooks(id));
    }
}
