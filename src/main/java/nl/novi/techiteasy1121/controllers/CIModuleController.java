package nl.novi.techiteasy1121.controllers;

import nl.novi.techiteasy1121.services.CIModuleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;


    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }
}
