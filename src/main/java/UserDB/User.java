package UserDB;

public class User {
    private Integer UserId=-1;
    private String FirstName;
    private String LastName;
    private boolean Host;
    private String email;
    private int age;
    private int telephone;


    public User(int userId, String firstName, String lastName, Boolean host, String email, int age, int telephone) {
        super();
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Host = host;
        email = email;
        age=age;
        telephone= telephone;


    }
    public User(String firstName, String lastName, Boolean host,String email, int age, int telephone) {
        super();
        FirstName = firstName;
        LastName = lastName;
        Host = host;
        email = email;
        age=age;
        telephone= telephone;
    }
    public Integer getUserId() {
        return UserId;
    }
    public void setUserId(Integer userId) {
        UserId = userId;
    }
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public boolean isHost() {
        return Host;
    }
    public void setHost(boolean host) {
        Host = host;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }


    public String getEmail() {
        return email;
    }
    public int getAge() {
        return age;
    }
    public int getTelephone() {
        return telephone;
    }



    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Host=" + Host +
                ", email=" + email +
                ", age=" + age +
                ", telephone=" + telephone +
                '}';
    }
}
