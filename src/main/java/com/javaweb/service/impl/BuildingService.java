package com.javaweb.service.impl;

import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentareaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
 class  BuildingService implements IBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingDTOConverter buildingDTOConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private RentareaRepository rentareaRepository;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> list = buildingRepository.findBuilding(buildingSearchRequest);
        List<BuildingSearchResponse> listResponse = new ArrayList<>();
        for(BuildingEntity item : list){
            BuildingSearchResponse a = buildingDTOConverter.EntityConverter(item);
            listResponse.add(a);
        }
        return listResponse;
    }

    @Override
    public ResponseDTO listStaffs(Long buildingId) {
        List<UserEntity> Allstaffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<AssignmentBuildingEntity> listasffs = assignmentBuildingRepository.findByBuildingId(buildingId);
        List<UserEntity> staffs = new ArrayList<>();
        for (AssignmentBuildingEntity it : listasffs){
            staffs.add(userRepository.findById(it.getStaffs().getId()).get());
        }
        ResponseDTO a = buildingDTOConverter.ConverterToResponseDTO(Allstaffs, listasffs,staffs);
        return a;
    }

    @Override
    public void addBuilding(BuildingDTO buildingDTO) {
        BuildingEntity newBuilding = buildingDTOConverter.buildingDTOConverter(buildingDTO);
        buildingRepository.save(newBuilding);
        if(newBuilding.getId() != null){
          rentareaRepository.deleteAllByBuildings(newBuilding);
        }
        List<Integer> listRentarea = buildingDTOConverter.RentareaStringToInteger(buildingDTO.getRentArea());
        if (listRentarea != null){
            for (Integer it : listRentarea){
                RentareaEntity x = new RentareaEntity();
                x.setBuilding(newBuilding);
                x.setValue(it);
                rentareaRepository.save(x);
            }
        }
    }

    @Override
    public BuildingDTO getBuildingDTO(Long id) {
        BuildingEntity a = buildingRepository.findById(id).get();
        return buildingDTOConverter.buildingEntityConverter(a);
    }

    @Override
    public void DeleteBuilding(List<Long> ids) {
        for (Long it : ids){
            BuildingEntity a = buildingRepository.findById(it).get();
            rentareaRepository.deleteAllByBuildings(a);
            assignmentBuildingRepository.deleteAllByBuilding(a);
        }
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public void UpdateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity x = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteAllByBuilding(x);
        List<Long> listStaffs = assignmentBuildingDTO.getStaffs();
        for (Long it : listStaffs){
           AssignmentBuildingEntity a = new AssignmentBuildingEntity();
           a.setStaffs(userRepository.findById(it).get());
           a.setBuilding(x);
           assignmentBuildingRepository.save(a);
        }
    }


}
