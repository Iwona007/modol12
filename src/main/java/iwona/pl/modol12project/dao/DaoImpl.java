package iwona.pl.modol12project.dao;

import iwona.pl.modol12project.model.Image;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoImpl implements DaoRepo {
    private JdbcTemplate jdbcTemplate;
    private static final String NAME = "images";

    public DaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addImage(Image image) {
        Image image1 = new Image(image.getUrl(), image.getContent());
        String sql = "INSERT INTO images VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, image1.getId(), image1.getUrl(), image1.getContent());
    }

    @Override
    public List<Image> getAll() {
        String sql = "SELECT * FROM " + NAME;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Image.class));
    }

    @Override
    public void delete(long id) {
        String sql = "DELETE FROM " + NAME + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
