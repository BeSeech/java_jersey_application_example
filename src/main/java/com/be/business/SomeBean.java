package com.be.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.ejb.Stateless;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Stateless
public class SomeBean
{

    @JsonProperty("IDD")
    private String id;
    @JsonIgnore
    private double somedoubleField;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public double getSomedoubleField()
    {
        return somedoubleField;
    }

    public void setSomedoubleField(double somedoubleField)
    {
        this.somedoubleField = somedoubleField;
    }

    public SomeBean()
    {
    }

    @Override
    public String toString()
    {
        return new ReflectionToStringBuilder(this, ToStringStyle.NO_CLASS_NAME_STYLE).toString();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        if (other == this)
            return true;
        if (!(other instanceof SomeBean))
            return false;
        SomeBean othersmbTest = (SomeBean)other;
        return this.toString().equals(othersmbTest.toString());
    }
}
