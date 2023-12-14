package nl.novi.techiteasy1121.controllers;

import jakarta.validation.Valid;
import nl.novi.techiteasy1121.Dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy1121.Dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy1121.Dtos.wallbracket.WallBracketInputDto;
import nl.novi.techiteasy1121.services.WallBracketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallbrackets")
public class WallBracketController {

    private final WallBracketService wallBracketService;

    public WallBracketController(WallBracketService wallBracketService) {
        this.wallBracketService = wallBracketService;
    }

    @GetMapping
    public ResponseEntity<List<WallBracketDto>> getAllWallBrackets() {

        List<WallBracketDto> dtos = wallBracketService.getAllWallBrackets();
        return ResponseEntity.ok(dtos);

    }

    @GetMapping("/{id}")
    public ResponseEntity<WallBracketDto> getWallBracket(@PathVariable("id")Long id) {

        // We spreken hier ook weer een service methode aan in plaats van direct de repository aan te spreken
        WallBracketDto wallBracket = wallBracketService.getWallBracketById(id);

        return ResponseEntity.ok().body(wallBracket);

    }

    @PostMapping
    public ResponseEntity<WallBracketDto> addWallBracket(@Valid @RequestBody WallBracketInputDto wallBracketInputDto) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        WallBracketDto dto = wallBracketService.addWallBracket(wallBracketInputDto);

        return ResponseEntity.created(null).body(dto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteWallBracket(@PathVariable Long id) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
       wallBracketService.deleteWallBracket(id);

        return ResponseEntity.noContent().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<WallBracketDto> updateWallBracket(@PathVariable Long id, @Valid @RequestBody WallBracketInputDto newWallBracket) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        // Alle logica die hier eerst stond, is nu ook verplaatst naar de service laag.
        WallBracketDto dto = wallBracketService.updateWallBracket(id, newWallBracket);

        return ResponseEntity.ok().body(dto);
    }



}
