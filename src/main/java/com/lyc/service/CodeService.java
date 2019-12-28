package com.lyc.service;

import com.lyc.bean.*;
import com.lyc.dao.BeanDao;
import com.lyc.sys.Assert;
import com.lyc.util.BaseUtil;
import com.lyc.util.FreemarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Package: com.lyc.service
 * @Author: lyc
 * @Date: 2019年12月28日14:54:55
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
    public List<Table> queryAlltable() {
        List<Table> tabs = dao.query("core.all_table");
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

    /**
     * 执行生成文件逻辑
     *
     * @param sys
     * @return String
     */
    public void submit(Sys sys) throws Exception {
        Path path = handlePath(sys);
        doJava(sys,path);
//        doHtml(sys);
    }

    /**
     * 执行生成java部分
     *
     * @param sys
     * @return String
     */
    private void doJava(Sys sys,Path path) {
        Map<String, Object> map = new HashMap<String, Object>();
        //查询表字段
        List<Column> columns = handleJavacolums(getTableColums(sys.getTabName()));
        map.put("sys", sys);
        map.put("serial", new Serial());
        map.put("columns", columns);
        FreemarkerUtil freemarkerUtil = FreemarkerUtil.getInstance();
        freemarkerUtil.sPrint(map, "/java/bean.ftl");
//        freemarkerUtil.fPrint(map, "/java/bean.ftl", "F:/lkk/jiopeel-code/src/main/java/com/lyc/sys/bean.java");
    }

    /**
     * 处理路径
     * @param sys
     */
    private Path handlePath(Sys sys) throws Exception {
        String javaPath=sys.getJavaPath();
        String viewPath=sys.getViewPath();
        Assert.isNull(javaPath,"java输出路径不存在");
        Assert.isNull(viewPath,"模板输出路径不存在");
        Path path=new Path(javaPath,viewPath+"/"+sys.getBeanName().toLowerCase());
        Field[] fields = BaseUtil.getAllFields(path);
        for (Field field : fields) {
            field.setAccessible(true);
            Object obj = getFieldVal(field, path);
            File file=new File(String.valueOf(obj));
            if (!file.exists())
                file.mkdirs();
        }
        return path;
    }

    /**
     * @Description :获取字段对应的值
     * @param: field  字段
     * @param: bean
     * @Return: Object 返回值
     * @auhor:lyc
     * @Date:2019/12/21 11:48
     */
    private  Object getFieldVal(Field field, Path path) {
        field.setAccessible(true);
        Object obj = null;
        try {
            obj = field.get(path);
            if (obj instanceof String)
                obj = String.valueOf(obj);
            if (obj instanceof Integer)
                obj = BaseUtil.parseInt(obj);
            if (obj instanceof Date)
                obj = BaseUtil.Dateformat((Date) obj);
            if (obj instanceof BigDecimal)
                obj = ((BigDecimal) obj).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * 根据表名查询表字段
     * @param tabname
     * @return List<Column>
     */
    private List<Column> getTableColums(String tabname) {
        return dao.query("core.table_column", tabname);
    }

    /**
     * 执行生成html部分
     *
     * @param sys
     * @return String
     */
    private void doHtml(Sys sys) {
    }

    /**
     * 处理java类型
     *
     * @param colums
     * @return List<Column>
     */
    private List<Column> handleJavacolums(List<Column> colums) {
        for (Column colum : colums) {
            String columnType = colum.getColumnType();
            if (columnType.indexOf("varchar") > -1 || columnType.indexOf("text") > -1) {
                columnType = "String";
            } else if (columnType.indexOf("int") > -1) {
                columnType = "Integer";
            } else if (columnType.indexOf("datetime") > -1) {
                columnType = "Date";
            } else if (columnType.indexOf("decimal") > -1) {
                columnType = "BigDecimal";
            } else if (columnType.indexOf("float") > -1 || columnType.indexOf("double") > -1) {
                columnType = "Double";
            }
            colum.setColumnType(columnType);
            colum.setColumnName(colum.getColumnName());
        }
        return colums;
    }

}
