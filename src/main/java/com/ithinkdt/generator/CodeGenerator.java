package com.ithinkdt.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * 参考文档
 * https://mp.baomidou.com/guide/generator.html
 */
public class CodeGenerator {
    //数据库连接地址
    public static String dataSourceUrl = "jdbc:mysql://localhost:3306/mybatis_plus?useUnicode=true&useSSL=false&characterEncoding=utf8";
    //数据库账号
    public static String dataUserName = "root";
    //数据库密码
    public static String dataPassword = "root";
    //需要生成的表，多张表用逗号分隔
    public static String tables = "t_defect_info,t_defect_mail";
    //表前缀
    public static String tablePrefix = "t_";
    //项目目录
    public static String projectPath = "D:\\workspace\\generator";
    //开发人员
    public static String author = "WangTeng";
    //父包名
    public static String parentPage = "com.ithinkdt.web.iris";
    //生成SQL文件的模板
    public static String templatePathMapperXml = "/templates/iris/mapper.xml.ftl";
    //生成的SQL文件保存的路径
    public static String sqlMapperXmlPath = projectPath + "/src/main/resources/conf/dao/sqlmap/";
    //生成Dao文件的模板
    public static String templatePathDao = "/templates/iris/mapper.java";
    //生成Service文件的模板
    public static String templatePathService = "/templates/iris/service.java";
    //生成ServiceImpl文件的模板
    public static String templatePathServiceImpl = "/templates/iris/serviceImpl.java";
    //生成Entity文件的模板
    public static String templatePathEntity = "/templates/iris/entity.java";
    //生成Controller文件的模板
    public static String templatePathController = "/templates/iris/controller.java";


    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        //数据源配置
        mpg.setDataSource(initDataSource());
        //全局配置
        mpg.setGlobalConfig(initGlobalConfig());
        // 包配置
        mpg.setPackageInfo(initPackageConfig());
        //自定义配置
        mpg.setCfg(initInjectionConfig());
        // 配置模板
        mpg.setTemplate(initTemplateConfig());
        // 策略配置
        mpg.setStrategy(initStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 数据源配置
     */
    public static DataSourceConfig initDataSource(){
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataSourceUrl);
        //适用于mysql5.6以下版本
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(dataUserName);
        dsc.setPassword(dataPassword);
        return dsc;
    }

    /**
     * 全局配置
     */
    public static GlobalConfig initGlobalConfig(){
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(author);
        gc.setOpen(false);
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sDao");
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        return gc;
    }

    /**
     * 包配置
     */
    public static PackageConfig initPackageConfig(){
        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包的位置
        pc.setParent(parentPage);
        pc.setMapper("dao");
        return pc;
    }

    /**
     * 自定义配置
     */
    public static InjectionConfig initInjectionConfig(){
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        //模板引擎是 freemarker
        String templatePath = templatePathMapperXml;
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return sqlMapperXmlPath + tableInfo.getEntityName() + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    /**
     * 配置模板
     */
    public static TemplateConfig initTemplateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setMapper(templatePathDao);
        templateConfig.setService(templatePathService);
        templateConfig.setServiceImpl(templatePathServiceImpl);
        templateConfig.setEntity(templatePathEntity);
        templateConfig.setController(templatePathController);
        templateConfig.setXml(null);
        return templateConfig;
    }

    /**
     * 配置策略
     */
    public static StrategyConfig initStrategyConfig(){
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        //表名
        strategy.setInclude(tables.split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //表前缀
        strategy.setTablePrefix(tablePrefix);
        return strategy;
    }

}
