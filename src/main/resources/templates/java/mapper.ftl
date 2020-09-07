<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${sys.lowBeanName}">
    <select id="getInfo" parameterType="String" resultType="${JavaNameMap.result}">
        select * from t_app where id=<#noparse>#{_paramer}</#noparse>
    </select>

    <select id="getListPage" parameterType="${JavaNameMap.query}" resultType="${JavaNameMap.result}">
        select * from t_app  t where 1=1
    </select>

    <select id="list" parameterType="${JavaNameMap.query}" resultType="${JavaNameMap.result}">
        select * from t_app where 1=1
    </select>
</mapper>