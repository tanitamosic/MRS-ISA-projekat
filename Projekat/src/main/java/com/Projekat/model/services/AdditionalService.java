package com.Projekat.model.services;

import javax.persistence.*;

@Entity
@Table(name="additional_services")
public class AdditionalService {

    @Id
    @SequenceGenerator(name = "asIdSeqGen", sequenceName = "asIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "asIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @Column(name="name", unique=false, nullable=false)
    private String name;


}
