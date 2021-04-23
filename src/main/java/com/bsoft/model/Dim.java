package com.bsoft.model;

import java.io.Serializable;

/**
 * @author mabaoying
 * @ClassName: Dim
 * @Description: 维度实体类
 * @date: 2019/9/23
 * @最后修改人:
 * @最后修改时间:
 */
public class Dim implements Serializable {

    private String id;
    private String dimKey;
    private String dimValue;
    private String dimDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDimKey() {
        return dimKey;
    }

    public void setDimKey(String dimKey) {
        this.dimKey = dimKey;
    }

    public String getDimValue() {
        return dimValue;
    }

    public void setDimValue(String dimValue) {
        this.dimValue = dimValue;
    }

    public String getDimDesc() {
        return dimDesc;
    }

    public void setDimDesc(String dimDesc) {
        this.dimDesc = dimDesc;
    }
}
