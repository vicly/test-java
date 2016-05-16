package vic.test.jerseyswagger.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Vic Liu
 */
@ApiModel(description = "user model")
public class User {
    private Long id;
    private String name;
    private int age;
    @ApiModelProperty(allowableValues = "vip, standard")
    private UserType type;

    /*
     * type: "string"
     * format: "date"
     */
    @ApiModelProperty(dataType = "date")
    private Date birthday;

    /*
     * type: "string"
     * format: "date-time"
     */
    private Date registrationDateTime = new Date();

    public User() {}

    public User(Long id, String name, int age, UserType type, String birthday) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.type = type;

        // using local timezone
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this. birthday = sdf.parse(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid birthday: " + birthday);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDateTime() {
        return registrationDateTime;
    }

    public void setRegistrationDateTime(Date registrationDateTime) {
        this.registrationDateTime = registrationDateTime;
    }
}
