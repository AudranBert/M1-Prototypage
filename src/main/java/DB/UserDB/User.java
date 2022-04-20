package DB.UserDB;

public class User {
    private Integer UserId=-1;
    private String FirstName;
    private String LastName;
    private boolean Host;

    public User(Integer userId,String firstName, String lastName, Boolean host) {
        super();
        UserId = userId;
        FirstName = firstName;
        LastName = lastName;
        Host = host;
    }
    public User(String firstName, String lastName, Boolean host) {
        super();
        FirstName = firstName;
        LastName = lastName;
        Host = host;
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

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Host=" + Host +
                '}';
    }
}
