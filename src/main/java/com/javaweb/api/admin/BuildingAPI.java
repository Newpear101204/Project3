package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.IBuildingService;
import com.javaweb.service.IUserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController( value = "buildingAPIOfAdmin")
@RequestMapping("/admin/building")
public class BuildingAPI {
    @Autowired
    private IBuildingService buildingService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    public void AddOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){


        buildingService.addBuilding(buildingDTO);

    }

    @DeleteMapping("/{ids}")
    public void DeleteBuiling(@PathVariable List<Long> ids ){
        // xuong DB xoa
        buildingService.DeleteBuilding(ids);


    }

    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs (@PathVariable Long id ){
        ResponseDTO a = buildingService.listStaffs(id);
        return a;
    }

    @PostMapping("/assignment")
    public void updateAssignmentBuilding (@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.UpdateAssignmentBuilding(assignmentBuildingDTO);



    }
}
