package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface IBuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);

    ResponseDTO listStaffs (Long buildingId);

    void UpdateOrAdd( BuildingDTO buildingDTO);

    BuildingDTO getBuildingDTO ( Long id);

    void DeleteBuilding( List<Long> ids);

    void UpdateAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

}
