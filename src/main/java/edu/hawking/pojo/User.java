package edu.hawking.pojo;

/**
 * 杜皓君 created by 2021/3/27
 * User
 **/
public class User {
    Long userId;
    String userName;

    public User(Long userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        final StringBuilder JSON = new StringBuilder("{\"User\":{");
        JSON.append("\"userId\":")
                .append(userId);
        JSON.append(",\"userName\":\"")
                .append(userName).append('\"');
        JSON.append('}');
        JSON.append('}');
        return JSON.toString();
    }
}
