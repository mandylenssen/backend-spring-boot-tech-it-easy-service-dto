package nl.novi.techiteasy1121.services;


import nl.novi.techiteasy1121.Dtos.remotecontroller.RemoteControllerDto;
import nl.novi.techiteasy1121.Dtos.remotecontroller.RemoteControllerInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.RemoteController;
import nl.novi.techiteasy1121.repositories.RemoteControllerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RemoteControllerService {

    private final RemoteControllerRepository remoteControllerRepository;


    public RemoteControllerService(RemoteControllerRepository remoteControllerRepository) {
        this.remoteControllerRepository = remoteControllerRepository;
    }

    public List<RemoteControllerDto> getAllRemoteControllers() {
        List<RemoteController> RemoteControllerList = remoteControllerRepository.findAll();
        List<RemoteControllerDto> RemoteControllerDtoList = new ArrayList<>();

        for(RemoteController remoteController : RemoteControllerList) {
            RemoteControllerDto dto = transferToDto(remoteController);
            RemoteControllerDtoList.add(dto);
        }
        return RemoteControllerDtoList;
    }

    public RemoteControllerDto getRemoteControllerById(Long id) {
        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(id);
        if (remoteControllerOptional.isPresent()){
            RemoteController remoteController = remoteControllerOptional.get();
            return transferToDto(remoteController);
        } else {
            throw new RecordNotFoundException("geen remote controller gevonden");
        }
    }

    public RemoteControllerDto addRemoteController(RemoteControllerInputDto dto) {

        RemoteController remoteController = transferToRemoteController(dto);
        remoteControllerRepository.save(remoteController);
        return transferToDto(remoteController);
    }

    // Deze methode is inhoudelijk neit veranderd. Het is alleen verplaatst naar de Service laag.
    public void deleteRemoteController(@RequestBody Long id) {

        remoteControllerRepository.deleteById(id);

    }

    public RemoteControllerDto updateRemoteController(Long id, RemoteControllerInputDto newRemoteController) {

        Optional<RemoteController> remoteControllerOptional = remoteControllerRepository.findById(id);
        if (remoteControllerOptional.isPresent()){

            RemoteController remoteController = remoteControllerOptional.get();
            remoteController.setCompatibleWith(newRemoteController.compatibleWith);
            remoteController.setBatteryType(newRemoteController.batteryType);
            remoteController.setName(newRemoteController.name);
            remoteController.setBrand(newRemoteController.brand);
            remoteController.setPrice(newRemoteController.price);
            remoteController.setOriginalStock(newRemoteController.originalStock);
            RemoteController returnRemoteController = remoteControllerRepository.save(remoteController);

            return transferToDto(returnRemoteController);

        } else {

            throw new  RecordNotFoundException("geen remote controller gevonden");

        }

    }

    // Dit is de vertaal methode van RemoteControllerInputDto naar RemoteController.
    public RemoteController transferToRemoteController(RemoteControllerInputDto dto){
        RemoteController remoteController = new RemoteController();

        remoteController.setCompatibleWith(dto.compatibleWith);
        remoteController.setBatteryType(dto.batteryType);
        remoteController.setName(dto.name);
        remoteController.setBrand(dto.brand);
        remoteController.setPrice(dto.price);
        remoteController.setOriginalStock(dto.originalStock);


        return remoteController;
    }

    // Dit is de vertaal methode van RemoteController naar RemoteControllerDto
    public RemoteControllerDto transferToDto(RemoteController remoteController){
        RemoteControllerDto dto = new RemoteControllerDto();

        dto.id = remoteController.getId();
        dto.compatibleWith = remoteController.getCompatibleWith();
        dto.batteryType = remoteController.getBatteryType();
        dto.name = remoteController.getName();
        dto.brand = remoteController.getBrand();
        dto.price = remoteController.getPrice();
        dto.originalStock = remoteController.getOriginalStock();

        return dto;
    }

}


