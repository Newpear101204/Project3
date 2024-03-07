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
import com.javaweb.model.response.StaffResponseDTO;
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

        // lay tat ca nhan vien len
        List<UserEntity> Allstaffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");

        // tim assginment co id cua toa nha
        List<AssignmentBuildingEntity> listass = assignmentBuildingRepository.findByBuildingId(buildingId);

        // lay nhan  vien thong qua buildingId cua assignment
        List<UserEntity> staffs = new ArrayList<>();
        for (AssignmentBuildingEntity it : listass){
            staffs.add(userRepository.findById(it.getStaffs().getId()).get());
        }
        //

        List<StaffResponseDTO> liststaffDTO = new ArrayList<>();
        for (UserEntity user : Allstaffs){
            StaffResponseDTO x = new StaffResponseDTO();
            x.setFullName(user.getFullName());
            x.setStaffId(user.getId());
            if (staffs.contains(user)){
                x.setChecked("checked");
            }
            else {
                x.setChecked("");
            }
            liststaffDTO.add(x);
        }

        //
        ResponseDTO a = new ResponseDTO();
        a.setData(liststaffDTO);
        a.setMessage("ok");

        return a;
    }

    @Override
    public void addBuilding(BuildingDTO buildingDTO) {
        BuildingEntity newBuilding = buildingDTOConverter.buildingDTOConverter(buildingDTO);

        buildingRepository.save(newBuilding);
        if(newBuilding.getId() != null){
          rentareaRepository.deleteAllByBuildings(newBuilding);
        }
        List<Integer> listRentarea = new ArrayList<>();
        String[] numberStrings = buildingDTO.getRentArea().split(",");
        for (String numberString : numberStrings) {
            int number = Integer.parseInt(numberString);
            listRentarea.add(number);
        }
        for (Integer it : listRentarea){
            RentareaEntity x = new RentareaEntity();
            x.setBuilding(newBuilding);
            x.setValue(it);
            rentareaRepository.save(x);
        }
        System.out.println("ok");





    }

    @Override
    public BuildingDTO getBuildingDTO(Long id) {
        BuildingEntity a = buildingRepository.findById(id).get();
        return buildingDTOConverter.buildingEntityConverter(a);
    }

    @Override
    public void DeleteBuilding(List<Long> ids) {
        // xoa rentarea
        for (Long it : ids){
            BuildingEntity a = buildingRepository.findById(it).get();
            rentareaRepository.deleteAllByBuildings(a);
            assignmentBuildingRepository.deleteAllByBuilding(a);
        }
        // xoa toa nha
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
