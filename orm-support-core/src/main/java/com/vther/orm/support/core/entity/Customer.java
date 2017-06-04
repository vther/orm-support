package com.vther.orm.support.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Cacheable(false)
@Table(name = "t_customer")
@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID = 1212061447845144260L;
    @Id
    private Long customerId;

    private String name;

    private Integer age;

    private Date registerTime;

    @Version
    private Integer version;

}
