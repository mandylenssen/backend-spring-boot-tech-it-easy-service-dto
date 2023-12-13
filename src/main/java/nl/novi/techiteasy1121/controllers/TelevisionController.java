package nl.novi.techiteasy1121.controllers;

import nl.novi.techiteasy1121.Dtos.general.IdInputDto;
import nl.novi.techiteasy1121.Dtos.television.TelevisionDto;
import nl.novi.techiteasy1121.Dtos.television.TelevisionInputDto;
import nl.novi.techiteasy1121.services.TelevisionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/televisions")


public class TelevisionController {

    // We importeren hier (via de constructor, maar het mag ook @Autowired zijn) nu de Service in plaats van direct de Repository.
    private final TelevisionService televisionService;


    public TelevisionController(TelevisionService televisionService){
        this.televisionService = televisionService;
    }

    // Je ziet dat de return waarde van deze methode nu ResponseEntity<List<TelevisionDto>> is in plaats van <ResponseEntity<List<Television>>
    @GetMapping
    public ResponseEntity<List<TelevisionDto>> getAllTelevisions(@RequestParam(value = "brand", required = false) Optional<String> brand) {

        List<TelevisionDto> dtos;

        if (brand.isEmpty()){

            // We halen niet direct uit de repository een lijst met Televisions, maar we halen uit de service een lijst met TelevisionDto's
            dtos = televisionService.getAllTelevisions();

        } else {
            // Dit is ook een service methode geworden in plaats van direct de repository aan te spreken.
            dtos = televisionService.getAllTelevisionsByBrand(brand.get());

        }

        return ResponseEntity.ok().body(dtos);

    }

    // De return waarde is ook hier een TelevisionDto in plaats van een Television
    @GetMapping("/{id}")
    public ResponseEntity<TelevisionDto> getTelevision(@PathVariable("id")Long id) {

        // We spreken hier ook weer een service methode aan in plaats van direct de repository aan te spreken
        TelevisionDto television = televisionService.getTelevisionById(id);

            return ResponseEntity.ok().body(television);

    }
// fddff
    // Ook hier returnen we weer een TelevisionDto, maar ook de parameter is een dto geworden.
    // Het is niet verplicht om een "outputdto" en een "inputdto" te hebben, zeker omdat ze in dit geval hetzelfde zijn,
    // maar we willen jullie laten zien dat het mogelijk is. In sommige gevallen kan het zelfs nodig zijn.
    @PostMapping
    public ResponseEntity<TelevisionDto> addTelevision(@Valid @RequestBody TelevisionInputDto televisionInputDto) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        TelevisionDto dto = televisionService.addTelevision(televisionInputDto);

        return ResponseEntity.created(null).body(dto);

    }

    // Hier veranderd niks aan de methode. We hebben niet meer de naam van de pathvariabele expliciet genoemd, omdat de
    // parameter-naam overeen komt met de naam van de pathvariabele.
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable Long id) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        televisionService.deleteTelevision(id);

        return ResponseEntity.noContent().build();

    }

    // Deze methode returned nu een ResponseEntity<TelevisionDto> in plaats van een ResponseEntity<Television> en deze
    // methode vraagt nu om een Long en een TelevisionInputDto in de parameters in plaats van een Long en een Television.
    @PutMapping("/{id}")
    public ResponseEntity<TelevisionDto> updateTelevision(@PathVariable Long id, @Valid @RequestBody TelevisionInputDto newTelevision) {

        // Hier gebruiken we weer een service methode in plaats van direct de repository aan te spreken.
        // Alle logica die hier eerst stond, is nu ook verplaatst naar de service laag.
        TelevisionDto dto = televisionService.updateTelevision(id, newTelevision);

        return ResponseEntity.ok().body(dto);
    }


    @PutMapping("/{id}/remotecontroller")
    public ResponseEntity<Object> assignTelevisionToRemoteController(@PathVariable Long id, @RequestBody IdInputDto input) {

        televisionService.assignRemoteControllerToTelevision(id, input.id);
        return ResponseEntity.noContent().build();
    }

}
