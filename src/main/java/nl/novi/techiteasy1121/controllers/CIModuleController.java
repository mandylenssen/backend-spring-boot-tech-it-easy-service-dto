package nl.novi.techiteasy1121.controllers;

import nl.novi.techiteasy1121.Dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy1121.Dtos.television.TelevisionDto;
import nl.novi.techiteasy1121.models.CIModule;
import nl.novi.techiteasy1121.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;


    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }



}
