package vic.test.jpa.basic.inheritance;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import vic.test.jpa.NamedEntity;

@Entity
@Table(name = "shape")
@Inheritance(strategy = InheritanceType.JOINED)
public class Shape extends NamedEntity {

}
