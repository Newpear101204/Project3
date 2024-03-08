package com.javaweb.converter;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentareaEntity;
import com.javaweb.enums.Districts;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class BuildingDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse EntityConverter(BuildingEntity item){
        List<RentareaEntity> rentarea = item.getListRentarea();
        String rentareaSt = rentarea.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        Map<String,String> districtsList = Districts.type();
        String dis = districtsList.get(item.getDistrict());
        String adress = item.getStreet() + "," + item.getWard() +"," + dis;
        BuildingSearchResponse a = modelMapper.map(item,BuildingSearchResponse.class);
        a.setAddress(adress);
        a.setRentArea(rentareaSt);
        return a;
    }

    public BuildingEntity buildingDTOConverter(BuildingDTO buildingDTO){
        BuildingEntity a = modelMapper.map(buildingDTO,BuildingEntity.class);
        a.setType(String.join(",", buildingDTO.getTypeCode()));
        a.setListRentarea(null);
        return a;
    }

    public BuildingDTO buildingEntityConverter( BuildingEntity buildingEntity){
        BuildingDTO a = modelMapper.map(buildingEntity,BuildingDTO.class);
        a.setRentArea(RentareaToString(buildingEntity.getListRentarea()));
        a.setTypeCode(StringtypeCodeToList(buildingEntity.getType()));
        return a;
    }

    public List<Integer> RentareaStringToInteger(String s){
        List<Integer> listRentarea = new ArrayList<>();
        String[] numberStrings = s.split(",");
        for (String numberString : numberStrings) {
            int number = Integer.parseInt(numberString);
            listRentarea.add(number);
        }
        return listRentarea;
    }

    public List<String> StringtypeCodeToList (String s){
        List<String> list = new ArrayList<>();
        String[] numberStrings = s.split(",");
        for (String numberString : numberStrings) {
            list.add(numberString);

        }
        return list;
    }

    public String RentareaToString (List<RentareaEntity> listRent){
        List<String> k = new ArrayList<>();
        for (RentareaEntity it : listRent){
            k.add(it.getValue().toString());
        }
        return String.join(",",k);

    }
}
