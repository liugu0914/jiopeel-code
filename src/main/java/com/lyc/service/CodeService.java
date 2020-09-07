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
    public List<Table> queryAlltable(String search) {
        List<Table> tabs = dao.query("core.all_table",search);
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
            dbType = "mysql";
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
    public Base submit(Sys sys) {
        Base base = new Base();
        boolean flag =true;
        try {
            Path path = handlePath(sys);
            doPrint(sys, path);
            base.setMessage("文件生成成功");
        } catch (Exception e) {
            e.printStackTrace();
            flag =false;
            base.setMessage(e.getMessage());
        }
        base.setResult(flag);
        return base;
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
            if (!chkName(sys, name))
                continue;
            log.info(obj);
            //java
            if (obj.endsWith(Constant.FILE_JAVA) || obj.endsWith(Constant.FILE_XML)) {
//                freemarkerUtil.sPrint(map, Constant.JAVA + name + Constant.FILE_FTL);
                freemarkerUtil.javaPrint(map,name,obj);
            }
            //html
            if (obj.endsWith(sys.getSuffix())) {
//                freemarkerUtil.sPrint(map, Constant.HTML + name + Constant.FILE_FTL);
                freemarkerUtil.htmlPrint(map,name,obj);
            }
        }
    }

    /**
     * 处理路径
     * @param sys
     */
    private Path handlePath(Sys sys) throws Exception {
        sys.setBeanPath(cp(sys.getBeanPath()));
        sys.setEventPath(cp(sys.getEventPath()));
        sys.setLogicPath(cp(sys.getLogicPath()));
        sys.setViewPath(cp(sys.getViewPath()));
        sys.setLowBeanName(sys.getBeanName().toLowerCase());//mapper ， html

        String viewPath = sys.getViewPath();
        if (!Constant.YES.equals(sys.getBean()) && !Constant.YES.equals(sys.getEvent())
                && !Constant.YES.equals(sys.getLogic()) && !Constant.YES.equals(sys.getView()))
            throw new Exception("不可都为空");
        String htmlUri = viewPath.substring(viewPath.lastIndexOf(Constant.FILE_DIVISION) + 1);
        sys.setHtmlUri(htmlUri);

        Path path = new Path(sys);
        Field[] fields = BaseUtil.getAllFields(path);
        String javaName;
        Map<String, Object> JavaNameMap = new HashMap<String, Object>();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            String obj = String.valueOf(getFieldVal(field, path));
            if (name.endsWith("File")) {
                String filename = name.replace("File", "");
                javaName = obj.substring(obj.lastIndexOf(Constant.FILE_DIVISION) + 1)
                        .replace(Constant.FILE_JAVA, "")
                        .replace(sys.getSuffix(), "");
                JavaNameMap.put(filename, javaName);
            }
            if (name.endsWith("File") || name.endsWith("Path"))
                continue;
            if (!chkName(sys, name))
                continue;
            File file = new File(obj);
            if (!file.exists()) {
                log.info(file.getPath());
                file.mkdirs();
            }
        }
        map.put("JavaNameMap", JavaNameMap);
        return path;
    }

    /**
     * 检测是否可导出
     * @param sys
     * @param name
     * @return boolean
     */
    public boolean chkName(Sys sys, String name) {
        boolean flag = false;
        if (Constant.YES.equals(sys.getBean()) && isBean(name))
            flag = true;
        if (Constant.YES.equals(sys.getEvent()) && isEvent(name))
            flag = true;
        if (Constant.YES.equals(sys.getLogic()) && isLogic(name))
            flag = true;
        if (Constant.YES.equals(sys.getView()) && isView(name))
            flag = true;
        return flag;
    }

    /**
     * 是否包含字符串
     * @param name
     * @param array
     * @return boolean
     */
    public boolean containsList(String name, String... array) {
        boolean flag = false;
        if (array == null || array.length == 0)
            return false;
        for (String str : array) {
            if (name.contains(str)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 是否为bean
     * @param name
     * @return boolean
     */
    public boolean isBean(String name) {
        return containsList(name, "bean", "result", "form", "query");
    }

    /**
     * 是否为Event
     * @param name
     * @return boolean
     */
    public boolean isEvent(String name) {
        return containsList(name, "event");
    }

    /**
     * 是否为Logic
     * @param name
     * @return boolean
     */
    public boolean isLogic(String name) {
        return containsList(name, "logic", "dao", "mapper");
    }

    /**
     * 是否为View
     * @param name
     * @return boolean
     */
    public boolean isView(String name) {
        return containsList(name, "html");
    }

    /**
     * 处理路径
     * @param path
     * @return boolean
     */
    public String cp(String path) {
        if (BaseUtil.empty(path))
            return path;
        path = path.replace("\\", Constant.FILE_DIVISION);
        if (path.endsWith(Constant.FILE_DIVISION))
            path = path.substring(0, path.length() - 1);
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
     * 处理java类型 默认包装类型
     *
     * @param colums
     * @return List<Column>
     */
    private List<Column> handleJavacolums(List<Column> colums) {
        for (Column colum : colums) {
            String columnType = colum.getColumnType().toLowerCase();
            if (containsList(columnType,"char","text")) {
                columnType = "String";
            } else if (containsList(columnType,"bigint","numeric")) {
                columnType = "Long";
            } else if (containsList(columnType,"float","double")) {
                columnType = "Double";
            } else if (containsList(columnType,"int")) {
                columnType = "Integer";
            } else if (containsList(columnType,"date")) {
                columnType = "Date";
            } else if (containsList(columnType,"decimal")) {
                columnType = "BigDecimal";
            }
            colum.setColumnType(columnType);
        }
        return colums;
    }

}
