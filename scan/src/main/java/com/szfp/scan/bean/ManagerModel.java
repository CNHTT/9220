package com.szfp.scan.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 戴尔 on 2017/12/26.
 */
@Entity
public class ManagerModel implements Serializable {
    static  final  long serialVersionUID=42L;
    @Id(autoincrement = true)
    private Long id;
    private boolean ture;
    private String name;
    private String pass;
    @Generated(hash = 1868490879)
    public ManagerModel(Long id, boolean ture, String name, String pass) {
        this.id = id;
        this.ture = ture;
        this.name = name;
        this.pass = pass;
    }
    @Generated(hash = 669890929)
    public ManagerModel() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getTure() {
        return this.ture;
    }
    public void setTure(boolean ture) {
        this.ture = ture;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPass() {
        return this.pass;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }

}
