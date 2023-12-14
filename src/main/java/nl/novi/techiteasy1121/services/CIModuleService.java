package nl.novi.techiteasy1121.services;

import nl.novi.techiteasy1121.Dtos.cimodule.CIModuleDto;
import nl.novi.techiteasy1121.Dtos.cimodule.CIModuleInputDto;
import nl.novi.techiteasy1121.exceptions.RecordNotFoundException;
import nl.novi.techiteasy1121.models.CIModule;

import nl.novi.techiteasy1121.repositories.CIModuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CIModuleService {

    private final CIModuleRepository ciModuleRepository;

    public CIModuleService(CIModuleRepository ciModuleRepository) {
        this.ciModuleRepository = ciModuleRepository;
    }

    public List<CIModuleDto> getAllCIModules() {
        List<CIModule> ciModuleList = ciModuleRepository.findAll();
        List<CIModuleDto> ciModuleDtoList = new ArrayList<>();

        for(CIModule cimodule : ciModuleList) {
            CIModuleDto dto = transferToDto(cimodule);
            ciModuleDtoList.add(dto);
        }
        return ciModuleDtoList;
    }

    public CIModuleDto getCIModuleById(Long id) {
        Optional<CIModule> ciModuleOptional = ciModuleRepository.findById(id);
        if (ciModuleOptional.isPresent()){
            CIModule cimodule = ciModuleOptional.get();
            return transferToDto(cimodule);
        } else {
            throw new RecordNotFoundException("geen CI-module gevonden");
        }
    }

    public CIModuleDto addCIModule(CIModuleInputDto dto) {

        CIModule cimodule = transferToCIModule(dto);
        ciModuleRepository.save(cimodule);
        return transferToDto(cimodule);
    }

    // Deze methode is inhoudelijk neit veranderd. Het is alleen verplaatst naar de Service laag.
    public void deleteCIModule(@RequestBody Long id) {

        ciModuleRepository.deleteById(id);

    }

    public CIModuleDto updateCIModule(Long id, CIModuleInputDto newCIModule) {

        Optional<CIModule> ciModuleOptional = ciModuleRepository.findById(id);
        if (ciModuleOptional.isPresent()){

            CIModule cimodule = ciModuleOptional.get();
            cimodule.setName(newCIModule.name);
            cimodule.setType(newCIModule.type);
            cimodule.setPrice(newCIModule.price);
            CIModule returnCIModule = ciModuleRepository.save(cimodule);

            return transferToDto(returnCIModule);

        } else {

            throw new  RecordNotFoundException("geen CI-module gevonden");

        }

    }

    // Dit is de vertaal methode van TelevisionInputDto naar CIModule.
    public CIModule transferToCIModule(CIModuleInputDto dto){
        CIModule ciModule = new CIModule();

        ciModule.setName(dto.name);
        ciModule.setType(dto.type);
        ciModule.setPrice(dto.price);


        return ciModule;
    }

    // Dit is de vertaal methode van CIModule naar CIModuleDto
    public CIModuleDto transferToDto(CIModule ciModule){
        CIModuleDto dto = new CIModuleDto();

        dto.id = ciModule.getId();
        dto.name = ciModule.getName();
        dto.type = ciModule.getType();
        dto.price = ciModule.getPrice();

        return dto;
    }

}
