package com.lyc.service;

import com.lyc.dao.BeanDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Package: com.lyc.service
 * @Author: lyc
 * @Date: 2019/10/4 21:34
 */
@Service
@Slf4j
public class CodeService {

    @Resource
    private BeanDao dao;

    /**
     * @description：查询所有数据表名称
     * @author ：lyc
     * @date ：2019/12/27 17:04
     */
    public List<String> queryAlltable() {
        List<String> tabs = dao.query("core.all_table");
        return tabs;
    }


    /**
     * 通过Connection获取数据库类型
     *
     * @param connection
     * @return String
     */
    private String getDBType(Connection connection) {
        String dbType = "mysql";
        //通过driverName是否包含关键字判断
        try {
            if (connection.getMetaData().getDriverName().toUpperCase().indexOf("MYSQL") != -1) {
                dbType = "mysql";
            } else if (connection.getMetaData().getDriverName().toUpperCase().indexOf("SQL SERVER") != -1) {
                dbType = "sqlserver";
            } else if (connection.getMetaData().getDriverName().toUpperCase().indexOf("ORACLE") != -1) {
                dbType = "oracle";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbType;
    }
}
