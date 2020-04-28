package iwona.pl.modol12project.config;

import javax.sql.DataSource;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataBase {
    private DataSource dataSource;

    public DataBase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void initResult() {
//        String sql = "CREATE TABLE images(id int auto_increment, url varchar (255), content varchar(500), primary key(id))";
//        jdbcTemplate().update(sql);
//    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void dropTable() {
//        String sql;
//        sql = "DROP TABLE IF EXISTS images";
//        jdbcTemplate().update(sql);
//    }
}
