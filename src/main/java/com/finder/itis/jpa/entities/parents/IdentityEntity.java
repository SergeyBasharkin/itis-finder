package com.finder.itis.jpa.entities.parents;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
@EqualsAndHashCode()
public class IdentityEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
}
