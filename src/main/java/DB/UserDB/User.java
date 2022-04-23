package DB.UserDB;

public class User {
    private Integer UserId=-1;
    private String FirstName;
    private String LastName;
    private boolean Host;
    private String Email;
    private int Age;
    private int Telephone;
    private String Password;
    private String Description;


    public User(int userId, String firstName, String lastName, Boolean host, String email, int age, int telephone, String password, String description) {
        super();
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Host = host;
        Email = email;
        Age=age;
        Telephone= telephone;
        Password = password;
        Description = description;
    }

    public User(String firstName, String lastName, Boolean host,String email, int age, int telephone, String password, String description) {
        super();
        FirstName = firstName;
        LastName = lastName;
        Host = host;
        Email = email;
        Age=age;
        Telephone= telephone;
        Password = password;
        Description = description;
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
        this.Email = email;
    }
    public void setAge(Integer age) {
        this.Age = age;
    }
    public void setTelephone(Integer telephone) {
        this.Telephone = telephone;
    }
    public void setPassword(String password) {
        this.Password = password;
    }
    public void setDescription(String description) {
        this.Description = description;
    }


    public String getEmail() {
        return Email;
    }
    public int getAge() {
        return Age;
    }
    public int getTelephone() {
        return Telephone;
    }
    public String getPassword() { return Password; }
    public String getDescription() { return Description; }



    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Host=" + Host  + '\'' +
                ", email=" + Email + '\'' +
                ", age=" + Age + '\'' +
                ", telephone=" + Telephone + '\'' +
                ", password=" + Password + '\'' +
                ", description=" + Description +
                '}';
    }
}
