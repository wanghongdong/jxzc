package com.jxzc.web.web.base;

import com.izu.framework.diamond.DiamondPropertiesBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiamondConfiguration {

    @Bean
    public DiamondPropertiesBeanPostProcessor diamondPropertiesBeanPostProcessor(){
        DiamondPropertiesBeanPostProcessor diamondPropertiesBeanPostProcessor = new DiamondPropertiesBeanPostProcessor();
        return diamondPropertiesBeanPostProcessor;
    }

}
