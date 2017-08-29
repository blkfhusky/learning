package com.zxc.flowable.boot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * id: JY02341
 * zhuxc@joyowo.com
 * Created by zhuxc on 2017/8/29.
 */
@Entity
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    public Photo() {
    }

    public Photo(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
