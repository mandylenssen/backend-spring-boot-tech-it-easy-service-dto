package nl.novi.techiteasy1121.services;

import nl.novi.techiteasy1121.Dtos.television.TelevisionDto;
import nl.novi.techiteasy1121.Dtos.television.TelevisionInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.RemoteController;
import nl.novi.techiteasy1121.models.Television;
import nl.novi.techiteasy1121.repositories.RemoteControllerRepository;
import nl.novi.techiteasy1121.repositories.TelevisionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Zet de annotatie boven de klasse, zodat Spring het herkent en inleest als Service.
@Service
public class TelevisionService {

    // We importeren de repository nu in de service in plaats van in de controller.
    // dit mag met constructor injection of autowire.
    private final TelevisionRepository televisionRepository;
    private final RemoteControllerRepository remoteControllerRepository;

    public TelevisionService(TelevisionRepository televisionRepository, RemoteControllerRepository remoteControllerRepository){
        this.televisionRepository = televisionRepository;
        this.remoteControllerRepository = remoteControllerRepository;
    }

    // Vanuit de repository kunnen we een lijst van Televisions krijgen, maar de communicatie container tussen Service en
    // Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit moet een voor een, omdat
    // de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<TelevisionDto> getAllTelevisions() {
        List<Television> tvList = televisionRepository.findAll();
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            TelevisionDto dto = transferToDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    // Vanuit de repository kunnen we een lijst van Televisions met een bepaalde brand krijgen, maar de communicatie
    // container tussen Service en Controller is de Dto. We moeten de Televisions dus vertalen naar TelevisionDtos. Dit
    // moet een voor een, omdat de translateToDto() methode geen lijst accepteert als argument, dus gebruiken we een for-loop.
    public List<TelevisionDto> getAllTelevisionsByBrand(String brand) {
        List<Television> tvList = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        List<TelevisionDto> tvDtoList = new ArrayList<>();

        for(Television tv : tvList) {
            TelevisionDto dto = transferToDto(tv);
            tvDtoList.add(dto);
        }
        return tvDtoList;
    }

    // Deze methode is inhoudelijk hetzelfde als het was in de vorige opdracht. Wat verandert is, is dat we nu checken
    // op optional.isPresent in plaats van optional.isEmpty en we returnen een TelevisionDto in plaats van een Television.
    public TelevisionDto getTelevisionById(Long id) {
        Optional<Television> televisionOptional = televisionRepository.findById(id);
        if (televisionOptional.isPresent()){
            Television tv = televisionOptional.get();
            return transferToDto(tv);
        } else {
            throw new RecordNotFoundException("geen televisie gevonden");
        }
    }

    // In deze methode moeten we twee keer een vertaal methode toepassen.
    // De eerste keer van dto naar televsion, omdat de parameter een dto is.
    // De tweede keer van television naar dto, omdat de return waarde een dto is.
    public TelevisionDto addTelevision(TelevisionInputDto dto) {

        Television tv = transferToTelevision(dto);
        televisionRepository.save(tv);

        return transferToDto(tv);
    }

    // Deze methode is inhoudelijk neit veranderd. Het is alleen verplaatst naar de Service laag.
    public void deleteTelevision(@RequestBody Long id) {

        televisionRepository.deleteById(id);

    }

    // Deze methode is inhoudelijk niet veranderd, alleen staat het nu in de Service laag en worden er Dto's en
    // vertaal methodes gebruikt.
    public TelevisionDto updateTelevision(Long id, TelevisionInputDto newTelevision) {

        Optional<Television> televisionOptional = televisionRepository.findById(id);
        if (televisionOptional.isPresent()){

            Television television1 = televisionOptional.get();


            television1.setAmbiLight(newTelevision.ambiLight);
            television1.setAvailableSize(newTelevision.availableSize);
            television1.setBluetooth(newTelevision.bluetooth);
            television1.setBrand(newTelevision.brand);
            television1.setHdr(newTelevision.hdr);
            television1.setName(newTelevision.name);
            television1.setOriginalStock(newTelevision.originalStock);
            television1.setPrice(newTelevision.price);
            television1.setRefreshRate(newTelevision.refreshRate);
            television1.setScreenQuality(newTelevision.screenQuality);
            television1.setScreenType(newTelevision.screenType);
            television1.setSmartTv(newTelevision.smartTv);
            television1.setSold(newTelevision.sold);
            television1.setType(newTelevision.type);
            television1.setVoiceControl(newTelevision.voiceControl);
            television1.setWifi(newTelevision.wifi);
            Television returnTelevision = televisionRepository.save(television1);

            return transferToDto(returnTelevision);

        } else {

            throw new  RecordNotFoundException("geen televisie gevonden");

        }

    }

    // Dit is de vertaal methode van TelevisionInputDto naar Television.
    public Television transferToTelevision(TelevisionInputDto dto){
        var television = new Television();

        television.setType(dto.type);
        television.setBrand(dto.brand);
        television.setName(dto.name);
        television.setPrice(dto.price);
        television.setAvailableSize(dto.availableSize);
        television.setRefreshRate(dto.refreshRate);
        television.setScreenType(dto.screenType);
        television.setScreenQuality(dto.screenQuality);
        television.setSmartTv(dto.smartTv);
        television.setWifi(dto.wifi);
        television.setVoiceControl(dto.voiceControl);
        television.setHdr(dto.hdr);
        television.setBluetooth(dto.bluetooth);
        television.setAmbiLight(dto.ambiLight);
        television.setOriginalStock(dto.originalStock);
        television.setSold(dto.sold);

        return television;
    }

    // Dit is de vertaal methode van Television naar TelevisionDto
    public TelevisionDto transferToDto(Television television){
        TelevisionDto dto = new TelevisionDto();

        dto.id = television.getId();
        dto.type = television.getType();
        dto.brand = television.getBrand();
        dto.name = television.getName();
        dto.price = television.getPrice();
        dto.availableSize = television.getAvailableSize();
        dto.refreshRate = television.getRefreshRate();
        dto.screenType = television.getScreenType();
        dto.screenQuality = television.getScreenQuality();
        dto.smartTv = television.getWifi();
        dto.wifi = television.getWifi();
        dto.voiceControl = television.getVoiceControl();
        dto.hdr = television.getHdr();
        dto.bluetooth = television.getBluetooth();
        dto.ambiLight = television.getAmbiLight();
        dto.originalStock = television.getOriginalStock();
        dto.sold = television.getSold();

        return dto;
    }

    public void assignRemoteControllerToTelevision(Long id, Long remoteControllerId) {
        Optional<Television> optionalTelevision = televisionRepository.findById(id);
        Optional<RemoteController> optionalRemoteController = remoteControllerRepository.findById(remoteControllerId);

        if(optionalTelevision.isPresent() && optionalRemoteController.isPresent()) {
            Television television = optionalTelevision.get();

            television.setRemoteController(optionalRemoteController.get());
            televisionRepository.save(television);
        } else {
            throw new RecordNotFoundException();
        }
    }



}

