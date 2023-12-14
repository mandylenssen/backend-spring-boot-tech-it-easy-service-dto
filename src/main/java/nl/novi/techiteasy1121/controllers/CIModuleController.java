package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy1121.Dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy1121.Dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy1121.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cimodules")
public class CIModuleController {

    private final CIModuleService ciModuleService;


    public CIModuleController(CIModuleService ciModuleService) {
        this.ciModuleService = ciModuleService;
    }


    @GetMapping
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {

        List<CIModuleDto> dtos = ciModuleService.getAllCIModules();
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable("id")Long id) {

        // We spreken hier ook weer een service methode aan in plaats van direct de repository aan te spreken
        CIModuleDto ciModule = ciModuleService.getCIModuleById(id);

        return ResponseEntity.ok().body(ciModule);

    }

    @PostMapping
    public ResponseEntity<CIModuleDto> addCIModule(@Valid @RequestBody CIModuleInputDto ciModuleInputDto) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
       CIModuleDto dto = ciModuleService.addCIModule(ciModuleInputDto);

        return ResponseEntity.created(null).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable Long id) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        ciModuleService.deleteCIModule(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable Long id, @Valid @RequestBody CIModuleInputDto newCIModule) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        // Alle logica die hier eerst stond, is nu ook verplaatst naar de service laag.
        CIModuleDto dto = ciModuleService.updateCIModule(id, newCIModule);

        return ResponseEntity.ok().body(dto);
    }



}
