package entity;

import java.util.Objects;
import java.util.UUID;

public class User {
    private String userName;
    private String userId;
    private int maxBorrowLimit = 3;

    //CONS
    public User(String userName){
        setUserName(userName);
        userId = UUID.randomUUID().toString();
    }

    //GETTERS / SETTERS
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public int getMaxBorrowLimit() {
        return maxBorrowLimit;
    }

    //HASHCODE
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }
}
