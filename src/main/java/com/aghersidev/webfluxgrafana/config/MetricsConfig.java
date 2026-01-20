package com.aghersidev.webfluxgrafana.config;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MetricsConfig {

    @Bean
    MeterFilter commonTags() {
        return MeterFilter.commonTags(List.of(Tag.of("application", "sales-ingestion"),Tag.of("env", "demo")));
    }

    @Bean
    MeterFilter denyJvmGcPauseLessUseful() {
        return MeterFilter.denyNameStartsWith("jvm.gc.pause");
    }
}
