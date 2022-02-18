package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="contractdata")
public class ContractData {

    @Id
    @Getter
    @Setter
    @Column(name="candidateid")
    private int candidateId;

    @Getter
    @Setter
    @Column(nullable = false, length = 45)
    String label;

    @Getter
    @Setter
    @Column(nullable = false, length = 45)
    String name;

    @Getter
    @Setter
    @Column(nullable = false, length = 45,name="marketid")
    int marketId;

    @Getter
    @Setter
    @Column(nullable = false, length = 45,name="marketname")
    String marketName;


}
