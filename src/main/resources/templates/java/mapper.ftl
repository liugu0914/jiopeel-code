<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${sys.lowBeanName}">
    <select id="getInfo" parameterType="String" resultType="${path.resultPath}">
        select t.* from ${sys.tabName} t where t.id=<#noparse>#{_paramer}</#noparse>
    </select>

    <select id="getListPage" parameterType="${path.queryPath}" resultType="${path.resultPath}">
        select t.* from ${sys.tabName} t where 1=1
    </select>

    <select id="list" parameterType="${path.queryPath}" resultType="${path.resultPath}">
        select t.* from ${sys.tabName} t where 1=1
    </select>
</mapper>