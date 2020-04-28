package iwona.pl.modol12project.dao;

import iwona.pl.modol12project.model.Image;
import java.util.List;

public interface DaoRepo {

    void addImage(Image image);

    List<Image> getAll();

    void delete(long id);
}
