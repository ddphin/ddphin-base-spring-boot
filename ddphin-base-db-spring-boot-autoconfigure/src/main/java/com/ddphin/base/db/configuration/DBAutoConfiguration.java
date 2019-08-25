package com.ddphin.base.db.configuration;

import com.ddphin.base.db.service.DBUpgradeService;
import com.ddphin.base.db.service.impl.DBUpgradeServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
public class DBAutoConfiguration {
    @Bean
    public DBUpgradeService dbUpgradeService() {
        return new DBUpgradeServiceImpl();
    }

    @PostConstruct
    public void dbUpgrade() {
        Boolean DB_INIT = this.dbUpgradeService().checkAndAutoInit();
        Boolean DB_UPGRADE = this.dbUpgradeService().checkAndAutoUpgrade();

        log.info("DB Init/Upgrade:\n" +
                 "            Database Unit: {}\n" +
                 "         Database Upgrade: {}\n",
                DB_INIT,
                DB_UPGRADE);
    }
}
