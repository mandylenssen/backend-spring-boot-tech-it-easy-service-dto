package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy1121.Dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy1121.Dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy1121.Dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy1121.services.RemoteControllerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/remotecontrollers")
public class RemoteControllerController {

    private final RemoteControllerService remoteControllerService;

    public RemoteControllerController(RemoteControllerService remoteControllerService) {
        this.remoteControllerService = remoteControllerService;
    }



    @GetMapping
    public ResponseEntity<List<RemoteControllerDto>> getAllRemoteControllers() {

        List<RemoteControllerDto> dtos = remoteControllerService.getAllRemoteControllers();
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> getRemoteController(@PathVariable("id")Long id) {

        // We spreken hier ook weer een service methode aan in plaats van direct de repository aan te spreken
        RemoteControllerDto remoteController = remoteControllerService.getRemoteControllerById(id);

        return ResponseEntity.ok().body(remoteController);

    }

    @PostMapping
    public ResponseEntity<RemoteControllerDto> addRemoteController(@Valid @RequestBody RemoteControllerInputDto remoteControllerInputDto) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        RemoteControllerDto dto = remoteControllerService.addRemoteController(remoteControllerInputDto);

        return ResponseEntity.created(null).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRemoteController(@PathVariable Long id) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        remoteControllerService.deleteRemoteController(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<RemoteControllerDto> updateRemoteController(@PathVariable Long id, @Valid @RequestBody RemoteControllerInputDto newRemoteController) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        // Alle logica die hier eerst stond, is nu ook verplaatst naar de service laag.
        RemoteControllerDto dto = remoteControllerService.updateRemoteController(id, newRemoteController);

        return ResponseEntity.ok().body(dto);
    }



}
