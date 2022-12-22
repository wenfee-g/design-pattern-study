package com.wenfee.design.builder.test;

import com.wenfee.design.builder.demo_01.DecorationPackageController;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Wenfee
 * @date 2022/12/22
 * <p>
 * 单元测试- 建造者模式
 */
public class ApiTest {

    @Test
    public void test_builder_demo_01(){
        DecorationPackageController decoration = new DecorationPackageController();

        // 豪华欧式
        System.out.println(decoration.getMatterList(new BigDecimal("132.52"),1));

        // 轻奢田园
        System.out.println(decoration.getMatterList(new BigDecimal("98.25"),2));

        // 现代简约
        System.out.println(decoration.getMatterList(new BigDecimal("85.43"),3));
    }
}
