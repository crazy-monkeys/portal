package com.crazy.portal.bean.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {

    public static final Integer ACTIVE = 1;

    public static final Integer DELETE = 0;

    public static final String[] ANNOTATION_COMPONENT_SCAN_PACKAGES = {"com.crazy.portal"};

    public static List<Map<String,Object>> getJobGroupList(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("groupCode", "portal-task");
        map.put("groupName", "portal-task-group");
        list.add(map);
        return list;
    }
}
