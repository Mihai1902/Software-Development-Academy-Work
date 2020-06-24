package business.services;

import model.dto.User;
import model.service.GenericService;
import model.service.IUser;

import java.util.List;

public class UserService implements IUser {

    private GenericService<User> userGenericService = new GenericService<>();


    @Override
    public boolean register(String username, String password) {
        User user = new User();
        List<User> list = userGenericService.getAll(user);
        boolean bool = list.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(username));
        if (bool) {
            return true;
        } else {
            user.setUsername(username);
            user.setPassword(password);
            userGenericService.add(user);
            return false;
        }

    }

    @Override
    public boolean login(User user) {
        List<User> list = userGenericService.getAll(user);
        return list.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername())
                && u.getPassword().equalsIgnoreCase(user.getPassword()));
    }
}
