package iwona.pl.modol12project.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Configuration;
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
}
