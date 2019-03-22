package com.be.business.leankit;

import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

public class BoardInfo
{
    public static int getVersion(String boardId) {
        Integer result = versionHashMap.get(boardId);
        if (result == null) {
            result = 0;
        }
        return result;
    }

    public static void setVersion(String boardId, int version) {
        versionHashMap.put(boardId, version);
    }
    private static Map<String, Integer> versionHashMap = new HashMap<>();
}
