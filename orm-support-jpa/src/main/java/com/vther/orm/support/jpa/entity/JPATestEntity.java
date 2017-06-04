package com.vther.orm.support.jpa.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Cacheable(false)
@Table(name = "t_jpa_test_entity")
@Entity
public class JPATestEntity implements Serializable {
    private static final long serialVersionUID = 1212061447845144260L;
    @Id
    private Long id;

    private String name;
}
