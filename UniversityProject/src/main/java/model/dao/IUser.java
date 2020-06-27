package model.dao;

public interface IUser {
    boolean register(String username, String password);
    void login(String username, String password);

}
