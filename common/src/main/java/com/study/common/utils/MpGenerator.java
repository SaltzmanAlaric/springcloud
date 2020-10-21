package com.study.common.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * mybatisplus 代码生成器
 */
public class MpGenerator {

    /**
     *
     * @Title: main
     * @Description: 生成
     */
    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        // 指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        // 注入自定义配置，可以在模板中使用cfg.queryPackage 设置的值
        InjectionConfig ic = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
               /* map.put("queryPackage", mpg.getPackageInfo().getParent() + "." + MyBatisPlusGenerator.Package.QUERY_PACKAGE);
                map.put("querySuffix", String.format(MyBatisPlusGenerator.Name.QUERY, ""));
                //是否查询列表
                map.put("selectAll", SELECTALL);
                //是否分页查询
                map.put("findPage", FINDPAGE);
                //api前缀
                map.put("urlPrefix", URL_PREFIX);*/
                this.setMap(map);
            }
        };
        mpg.setCfg(ic);

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //输出文件路径
        gc.setOutputDir("E:\\PersonalCodes\\springcloud");
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columnList
        gc.setBaseColumnList(false);
        gc.setAuthor("dkx");
        gc.setOpen(false);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1234");
        dsc.setUrl("jdbc:mysql://localhost:3306/demo?useUnicodle=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude("user");
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);

        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.bcht.its");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setMapper("dao");
        pc.setEntity("model");
        pc.setXml("mapper");
        mpg.setPackageInfo(pc);

        // 执行生成
        mpg.execute();
    }
}
