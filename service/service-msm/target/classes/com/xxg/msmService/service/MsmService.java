package com.xxg.msmService.service;

import java.util.Map;

/**
 * @author xxg
 * @version 1.0
 * @date 2020/9/10 10:00
 * @Description:
 * @Params:
 */
public interface MsmService {
    boolean sendMsm(String phone, Map<String, Object> parms);
}
