package codegenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

public class CodeGenerator {
    @Test
    public void generate() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mianyang?serverTimeZone=GMT%2B8",
                        "root", "abc123")
                .globalConfig(builder -> {
                    builder.author("xuhaoyu") // 设置作者
                            .dateType(DateType.ONLY_DATE)
                            // .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()   // 防止资源管理器打开
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("pojo"); // 设置父包模块名
                    // 默认在mapper下的xml文件夹
                    // .pathInfo(Collections.singletonMap(
                    //         OutputFile.xml,
                    //         System.getProperty("user.dir") + "\\src\\main\\java")); // 设置mapperXml生成路径,
                })
                .strategyConfig(builder -> {
                    builder.addInclude("device_info", "RSS131", "JL900"
                                    , "H3R7000") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_") // 设置过滤表前缀
                            .addFieldPrefix("is_");
                    // Entity
                    builder.entityBuilder()
                            .enableLombok() // lombok
                            .enableChainModel()
                            // .idType(IdType.ASSIGN_ID);  // id 采用哪种类型 这是雪花
                            .idType(IdType.AUTO);  // id 采用哪种类型 这是雪花
                    // Service
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService") // Service前缀去掉默认的I
                            .formatServiceImplFileName("%sServiceImpl");    // ServiceImpl前缀
                    // Controller
                    builder.controllerBuilder()
                            .enableRestStyle()  // restful api风格
                            .enableHyphenStyle();   // url驼峰转连字符
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
