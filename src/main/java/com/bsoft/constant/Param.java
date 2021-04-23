package com.bsoft.constant;

import com.google.common.collect.Lists;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.List;

public class Param implements Serializable
{
    private static final long serialVersionUID = 1620587026510490025L;
    private List<Parameter> parameters;

    public Param() {
        this.parameters = Lists.newArrayList();
    }

    @XmlElement(name = "parameter")
    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public void setParameters(final List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public boolean isHaveParameters() {
        return this.parameters.size() != 0;
    }

    public void setParameter(final Parameter pa) {
        for (final Parameter parameter : this.parameters) {
            if (parameter.getDim_key().equals(pa.getDim_key())) {
                parameter.setValue(pa.getValue());
            }
        }
    }
}
