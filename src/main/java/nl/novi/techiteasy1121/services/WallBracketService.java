package nl.novi.techiteasy1121.services;

import nl.novi.techiteasy1121.Dtos.wallbracket.WallBracketDto;
import nl.novi.techiteasy1121.Dtos.wallbracket.WallBracketInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.WallBracket;
import nl.novi.techiteasy1121.repositories.WallBracketRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {

    private final WallBracketRepository wallBracketRepository;


    public WallBracketService(WallBracketRepository wallBracketRepository) {
        this.wallBracketRepository = wallBracketRepository;
    }

    public List<WallBracketDto> getAllWallBrackets() {
        List<WallBracket> wallBracketList = wallBracketRepository.findAll();
        List<WallBracketDto> wallBracketDtoList  = new ArrayList<>();

        for(WallBracket wallBracket : wallBracketList) {
            WallBracketDto dto = transferToDto(wallBracket);
            wallBracketDtoList.add(dto);
        }
        return wallBracketDtoList   ;
    }

    public WallBracketDto getWallBracketById(Long id) {
        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(id);
        if (wallBracketOptional.isPresent()){
            WallBracket wallBracket = wallBracketOptional.get();
            return transferToDto(wallBracket);
        } else {
            throw new RecordNotFoundException("geen Wallbracket gevonden");
        }
    }

    public WallBracketDto addWallBracket(WallBracketInputDto dto) {

        WallBracket wallBracket = transferToWallBracket(dto);
        wallBracketRepository.save(wallBracket);
        return transferToDto(wallBracket);
    }

    // Deze methode is inhoudelijk neit veranderd. Het is alleen verplaatst naar de Service laag.
    public void deleteWallBracket(@RequestBody Long id) {

        wallBracketRepository.deleteById(id);

    }

    public WallBracketDto updateWallBracket(Long id, WallBracketInputDto newWallBracket) {

        Optional<WallBracket> wallBracketOptional = wallBracketRepository.findById(id);
        if (wallBracketOptional.isPresent()){

            WallBracket wallBracket = wallBracketOptional.get();
            wallBracket.setSize(newWallBracket.size);
            wallBracket.setAdjustable(newWallBracket.adjustable);
            wallBracket.setName(newWallBracket.name);
            WallBracket returnWallBracket = wallBracketRepository.save(wallBracket);

            return transferToDto(returnWallBracket);

        } else {

            throw new  RecordNotFoundException("geen wall bracket gevonden");

        }

    }

    // Dit is de vertaal methode van TelevisionInputDto naar CIModule.
    public WallBracket transferToWallBracket(WallBracketInputDto dto){
        WallBracket wallBracket = new WallBracket();

        wallBracket.setSize(dto.size);
        wallBracket.setAdjustable(dto.adjustable);
        wallBracket.setName(dto.name);
        wallBracket.setPrice(dto.price);


        return wallBracket;
    }

    public WallBracketDto transferToDto(WallBracket wallBracket){
        WallBracketDto dto = new WallBracketDto();

        dto.id = wallBracket.getId();
        dto.size = wallBracket.getSize();
        dto.adjustable = wallBracket.getAdjustable();
        dto.name = wallBracket.getName();
        dto.price = wallBracket.getPrice();

        return dto;
    }
}
