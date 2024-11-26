package com.adriankich.library.application.controllers;

import com.adriankich.library.application.dto.request.RenterRequestDTO;
import com.adriankich.library.application.dto.response.RenterResponseDTO;
import com.adriankich.library.domain.service.RenterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/renters", produces = MediaType.APPLICATION_JSON_VALUE)
public class RenterController extends Controller{

    @Autowired
    private RenterService renterService;

    @PostMapping
    public ResponseEntity<RenterResponseDTO> createRenter(@RequestBody @Valid RenterRequestDTO renterRequestDTO) {
        return created(renterService.createRenter(renterRequestDTO));
    }
}
