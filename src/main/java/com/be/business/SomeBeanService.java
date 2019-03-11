package com.be.business;

import com.be.helpers.OperationResult;

import javax.ejb.*;
import java.util.HashMap;

@Singleton
public class SomeBeanService
{
    private HashMap<String, SomeBean> someBeanMap = new HashMap<String, SomeBean>();

    private boolean isSmbTestInCash(String id)
    {
        return (someBeanMap.getOrDefault(id, null) != null);
    }

    public SomeBean getSmbTest(String id)
    {
        return someBeanMap.getOrDefault(id, null);
    }

    public OperationResult deletesmbTest(String id)
    {
        SomeBean result = someBeanMap.remove(id);
        if (result == null) {
            return OperationResult.error("SomeBean is not found");
        }
        return OperationResult.ok();
    }

    public OperationResult addSmbTest(SomeBean someBean)
    {
        if (isSmbTestInCash(someBean.getId())) {
            return OperationResult.error("SomeBean with this id already exists");
        }

        someBeanMap.put(someBean.getId(), someBean);

        return OperationResult.ok();
    }

    public OperationResult updateSmbTest(SomeBean someBean) throws Exception
    {
        if (isSmbTestInCash(someBean.getId())) {
            return OperationResult.error("SomeBean is not found");
        }
        someBeanMap.put(someBean.getId(), someBean);
        return OperationResult.ok();
    }

    @Remove
    public void finished()
    {

    }
}
