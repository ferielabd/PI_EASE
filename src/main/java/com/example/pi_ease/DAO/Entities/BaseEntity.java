package com.example.pi_ease.DAO.Entities;

import com.example.pi_ease.Services.Interfaces.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements BaseModel, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;


}