package com.lyc.sys;

import com.lyc.util.BaseUtil;

public class Assert {
    public static void isNull(Object object, String msg) throws Exception {
        if (BaseUtil.empty(object))
            throw new Exception(msg);
    }
}
