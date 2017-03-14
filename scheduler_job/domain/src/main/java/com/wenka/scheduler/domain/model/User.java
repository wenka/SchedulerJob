package com.wenka.scheduler.domain.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author 文卡<wkwenka@gmail.com>  on 17-3-14.
 */
@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    private String name;

    @ManyToOne
    private Organ organ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organ getOrgan() {
        return organ;
    }

    public void setOrgan(Organ organ) {
        this.organ = organ;
    }
}
