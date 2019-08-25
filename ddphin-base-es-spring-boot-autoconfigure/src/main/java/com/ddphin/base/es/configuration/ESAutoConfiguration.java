package com.ddphin.base.es.configuration;

import com.ddphin.base.es.service.ESUpgradeService;
import com.ddphin.base.es.service.impl.ESUpgradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * DBConfigration
 *
 * @Date 2019/7/20 下午12:19
 * @Author ddphin
 */
@Slf4j
@Configuration
public class ESAutoConfiguration {
    private RestHighLevelClient esclient;

    public ESAutoConfiguration(RestHighLevelClient esclient) {
        this.esclient = esclient;
    }

    @Bean
    public ESUpgradeService esUpgradeService() {
        return new ESUpgradeServiceImpl(esclient);
    }

    @PostConstruct
    public void dbUpgrade() {
        Boolean ES_UPGRADE = this.esUpgradeService().checkAndAutoUpgrade();

        log.info("ES Upgrade:\n" +
                 "    Elasticsearch Upgrade: {}",
                ES_UPGRADE);
    }
}
