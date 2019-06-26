/*
* ------------------------------------------------------------------
* Copyright © 2017 Hangzhou DtDream Technology Co.,Lt d. All rights reserved.
* ------------------------------------------------------------------
* Product: ZhongZhi Platform
* Module Name: $conf$
* Date Created: 2017-10-23
* Description:
* ------------------------------------------------------------------
* Modification History
* DATE			Name			Description
* ------------------------------------------------------------------
* 2017-01-09	$admin0000		$
* ------------------------------------------------------------------
*/
package com.demo.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@ConditionalOnMissingBean(Flyway.class)
@EnableConfigurationProperties(FlywayProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
public class FlywayConfig {

    @Autowired
    private FlywayProperties properties = new FlywayProperties();

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private DataSource dataSource;

    @Autowired(required = false)
    @FlywayDataSource
    private DataSource flywayDataSource;

    @Value("${spring.flyway.clean:false}")
    private Boolean needClean;

    @PostConstruct
    public void initDatabase(){
        checkLocationExists();
        Flyway flyway = flyway();
        if (needClean) {
            flyway.clean();
        }
        flyway.migrate();
    }

    public void checkLocationExists() {
        if (this.properties.isCheckLocation()) {
            Assert.state(!this.properties.getLocations().isEmpty(),
                    "Migration script locations not configured");
            boolean exists = hasAtLeastOneLocation();
            Assert.state(exists, "Cannot find migrations location in: "
                    + this.properties.getLocations()
                    + " (please add migrations or check your Flyway configuration)");
        }
    }

    private boolean hasAtLeastOneLocation() {
        for (String location : this.properties.getLocations()) {
            if (this.resourceLoader.getResource(location).exists()) {
                return true;
            }
        }
        return false;
    }

    public Flyway flyway() {
        Flyway flyway = new Flyway();
        if (this.properties.isCreateDataSource()) {
            flyway.setDataSource(this.properties.getUrl(), this.properties.getUser(),
                    this.properties.getPassword(), this.properties.getInitSqls().toArray(new String[0]));
        }
        else if (this.flywayDataSource != null) {
            flyway.setDataSource(this.flywayDataSource);
        }
        else {
            flyway.setDataSource(this.dataSource);
        }
        return flyway;
    }
//
//    public static void main(String[] args) {
//        Flyway flyway = new Flyway();
//
//        String url = "jdbc:mysql://rdse11l20r85g3aoda8e.mysql.dtdream.lab.com:3306/dtdream_op?characterEncoding=UTF-8";
//        String user = "dtdream";
//        String password = "123456";
//        flyway.setDataSource(url, user, password);
//        flyway.setLocations("migrations");
//        flyway.setValidateOnMigrate(true);
////        flyway.clean();
//        flyway.migrate();
//    }
}