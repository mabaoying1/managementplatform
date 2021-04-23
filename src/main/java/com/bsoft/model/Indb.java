package com.bsoft.model;

import java.io.Serializable;

/**
 * @author mabaoying
 * @ClassName: Indb
 * @Description: 指标实体类
 * @date: 2019/9/23
 * @最后修改人:
 * @最后修改时间:
 */
public class Indb implements Serializable {
    private String id;
    private String indbNameKey;
    private String indbNameValue;
    private String indbNameDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndbNameKey() {
        return indbNameKey;
    }

    public void setIndbNameKey(String indbNameKey) {
        this.indbNameKey = indbNameKey;
    }

    public String getIndbNameValue() {
        return indbNameValue;
    }

    public void setIndbNameValue(String indbNameValue) {
        this.indbNameValue = indbNameValue;
    }

    public String getIndbNameDesc() {
        return indbNameDesc;
    }

    public void setIndbNameDesc(String indbNameDesc) {
        this.indbNameDesc = indbNameDesc;
    }
}
