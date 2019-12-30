package com.lyc.service;

import com.lyc.bean.*;
import com.lyc.dao.BeanDao;
import com.lyc.sys.Assert;
import com.lyc.sys.Constant;
import com.lyc.util.BaseUtil;
import com.lyc.util.FreemarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
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

    private Map<String, Object> map = new HashMap<String, Object>();

    /**
     * @description：查询所有数据表名称
     * @author ：lyc
     * @date ：2019/12/27 17:04
     */
    public List<Table> queryAlltable() {
//        Connection conn = null;
//        try {
//            conn =  dao.getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String dbType = getDBType(conn);
//        log.info("dbType : {}",dbType);
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
        String dbType = "";
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
            dbType="mysql";
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
    public boolean submit(Sys sys) {
        try {
            Path path = handlePath(sys);
            doPrint(sys, path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 执行生成文件部分
     *
     * @param sys
     * @return String
     */
    private void doPrint(Sys sys, Path path) {
        //查询表字段
        List<Column> columns = handleJavacolums(getTableColums(sys.getTabName()));
        map.put("sys", sys);
        map.put("path", path);
        map.put("serial", new Serial());
        map.put("columns", columns);
        Map<String, Object> JavaNameMap = (Map<String, Object>) map.get("JavaNameMap");
        FreemarkerUtil freemarkerUtil = FreemarkerUtil.getInstance();
        Field[] fields = BaseUtil.getAllFields(path);
        for (Field field : fields) {
            String name = field.getName();
            if (!name.endsWith("File"))
                continue;
            name = name.replace("File", "");
            if (JavaNameMap.containsKey(name))
                map.put("javaName", JavaNameMap.get(name));
            field.setAccessible(true);
            String obj = String.valueOf(getFieldVal(field, path));
            log.info(obj);
            //后端
            if (Constant.ON.equals(sys.getBackEnd()) && obj.endsWith(Constant.FILE_JAVA))
                freemarkerUtil.sPrint(map, Constant.JAVA + name + Constant.FILE_FTL);
            //前端
            if (Constant.ON.equals(sys.getFrontEnd()) && obj.endsWith(sys.getSuffix()))
                freemarkerUtil.sPrint(map, Constant.HTML + name + Constant.FILE_FTL);
//            freemarkerUtil.javaPrint(map,name+Constant.FILE_FTL,obj);
        }
    }

    /**
     * 处理路径
     *
     * @param sys
     */
    private Path handlePath(Sys sys) throws Exception {
        String javaPath = sys.getJavaPath();
        String viewPath = sys.getViewPath();
        Assert.isNull(javaPath, "java输出路径不存在");
        Assert.isNull(viewPath, "模板输出路径不存在");
        String javauri = javaPath.substring(javaPath.lastIndexOf(Constant.FILE_DIVISION) + 1);
        String htmluri = viewPath.substring(viewPath.lastIndexOf(Constant.FILE_DIVISION) + 1);
        sys.setJavauri(javauri);
        sys.setHtmluri(htmluri);
        sys.setLowbeanName(sys.getBeanName().toLowerCase());//mapper ， html
        Path path = new Path(sys);
        Field[] fields = BaseUtil.getAllFields(path);
        String javaName = "";
        Map<String, Object> JavaNameMap = new HashMap<String, Object>();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            String obj = String.valueOf(getFieldVal(field, path));
            if (name.endsWith("File")) {
                name = name.replace("File", "");
                javaName = obj.substring(obj.lastIndexOf(Constant.FILE_DIVISION) + 1)
                        .replace(Constant.FILE_JAVA, "")
                        .replace(sys.getSuffix(), "");
                JavaNameMap.put(name, javaName);
            }
            if (name.endsWith("File") || name.endsWith("Path"))
                continue;
            File file = new File(obj);
            if (!file.exists()) {
                log.info(file.getPath());
//                file.mkdirs();
            }
        }
        map.put("JavaNameMap", JavaNameMap);
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
    private Object getFieldVal(Field field, Path path) {
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
     *
     * @param tabname
     * @return List<Column>
     */
    private List<Column> getTableColums(String tabname) {
        return dao.query("core.table_column", tabname);
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
