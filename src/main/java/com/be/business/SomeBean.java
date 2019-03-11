package com.be.business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import javax.ejb.Stateless;

@Stateless
public class SomeBean
{

    @JsonProperty("UID")
    private String id;
    @JsonIgnore
    private double someDoubleField;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public double getSomeDoubleField()
    {
        return someDoubleField;
    }

    public void setSomeDoubleField(double someDoubleField)
    {
        this.someDoubleField = someDoubleField;
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
        SomeBean otherSmbTest = (SomeBean)other;
        return this.toString().equals(otherSmbTest.toString());
    }
}
