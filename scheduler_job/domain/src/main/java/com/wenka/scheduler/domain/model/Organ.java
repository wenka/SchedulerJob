package com.wenka.scheduler.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 文卡<wkwenka@gmail.com>  on 17-3-14.
 */
@Entity
@Table(name = "organ")
public class Organ extends AbstractEntity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
