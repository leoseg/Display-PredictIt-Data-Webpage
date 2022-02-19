package com.webpage.predictpoliticalpartyprice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="twittercontractmap")
public class TwitterContractMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    String name;

    @Getter
    @Setter
    String topicName;

    @Getter
    @Setter
    String hashtag;

    @Getter
    @Setter
    String translation;


}
