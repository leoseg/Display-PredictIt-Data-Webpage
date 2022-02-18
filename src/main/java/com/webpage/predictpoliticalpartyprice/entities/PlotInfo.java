package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class PlotInfo {

    @Getter@Setter
    String date;

    @Getter@Setter
    List<ContractData> contractDataList;

    @Getter@Setter
    List<String> presidentNames;

    public List<String> getCandidateNames(){
        List<String> candidateNames =  new ArrayList<String>() {{
            for(ContractData contractData : contractDataList){
                add(contractData.getName());
            }
        }};
        return candidateNames;
    }
}
