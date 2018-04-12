package com.zonsim.examhelper.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * desc
 * <p>
 * Created by tangjunwei on 2018/4/12.
 * <a href="mailto:tjwabc@gmail.com">Contact me</a>
 * <a href="https://github.com/tangjw">Follow me</a>
 */
@Entity
public class ExamQuestion implements Serializable{
    
    private static final long serialVersionUID = -6984937910665912696L;
    @Id(autoincrement = true)
    private Long id;
    private String qId;
    private String qKey;
    private String qValue;
    private int remember;
    private int int1;
    private int int2;
    private int int3;
    private String string1;
    private String string2;
    private String string3;
    
    
    
    @Generated(hash = 1959471373)
    public ExamQuestion(Long id, String qId, String qKey, String qValue,
            int remember, int int1, int int2, int int3, String string1,
            String string2, String string3) {
        this.id = id;
        this.qId = qId;
        this.qKey = qKey;
        this.qValue = qValue;
        this.remember = remember;
        this.int1 = int1;
        this.int2 = int2;
        this.int3 = int3;
        this.string1 = string1;
        this.string2 = string2;
        this.string3 = string3;
    }
    @Generated(hash = 78936532)
    public ExamQuestion() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getQId() {
        return this.qId;
    }
    public void setQId(String qId) {
        this.qId = qId;
    }
    public String getQKey() {
        return this.qKey;
    }
    public void setQKey(String qKey) {
        this.qKey = qKey;
    }
    public String getQValue() {
        return this.qValue;
    }
    public void setQValue(String qValue) {
        this.qValue = qValue;
    }
    public int getRemember() {
        return this.remember;
    }
    public void setRemember(int remember) {
        this.remember = remember;
    }
    public int getInt1() {
        return this.int1;
    }
    public void setInt1(int int1) {
        this.int1 = int1;
    }
    public int getInt2() {
        return this.int2;
    }
    public void setInt2(int int2) {
        this.int2 = int2;
    }
    public int getInt3() {
        return this.int3;
    }
    public void setInt3(int int3) {
        this.int3 = int3;
    }
    public String getString1() {
        return this.string1;
    }
    public void setString1(String string1) {
        this.string1 = string1;
    }
    public String getString2() {
        return this.string2;
    }
    public void setString2(String string2) {
        this.string2 = string2;
    }
    public String getString3() {
        return this.string3;
    }
    public void setString3(String string3) {
        this.string3 = string3;
    }
    
    
}
