package ac.jejunu.mcl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by Kim on 2016-06-11.
 */
@Entity
public class Stepcount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int count;
    private Date date;

}
