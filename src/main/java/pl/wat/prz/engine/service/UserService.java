package pl.wat.prz.engine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wat.prz.engine.model.Picture;
import pl.wat.prz.engine.model.User;
import pl.wat.prz.engine.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository ur;

    public List<Picture> getUserPictures(Long id) {
        return null;
    }

    public User findOne(Long id) {
        return ur.findOne(id);
    }

    public List<User> findAll() {
        return ur.findAll();
    }

    public List<User> findAll(String firstName) {
        return ur.findAll(firstName);
    }

    public void add(User us) {
        ur.add(us);
    }

    public void remove(Long id) {
        ur.remove(id);
    }

    public void update(User us, Long id) {
        ur.update(us, id);
    }
}
