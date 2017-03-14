package com.wenka.scheduler.domain.model;

/**
 * @author 文卡<wkwenka@gmail.com>  on 17-3-14.
 */


import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    private String id = UUID.randomUUID().toString();

    public AbstractEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            boolean var10000;
            label35:
            {
                AbstractEntity that = (AbstractEntity) o;
                if (this.getId() != null) {
                    if (this.getId().equals(that.getId())) {
                        break label35;
                    }
                } else if (that.getId() == null) {
                    break label35;
                }

                var10000 = false;
                return var10000;
            }

            var10000 = true;
            return var10000;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.getId() != null ? this.getId().hashCode() : 0;
    }


}
