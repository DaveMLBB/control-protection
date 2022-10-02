package co.develhope.loginDemo.salary.entities;

import co.develhope.loginDemo.user.entities.User;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table
@Data
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private long amount;

    @OneToOne
    private User user;
}
