package codsoft.backend.services;
import codsoft.backend.models.User;
import codsoft.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{



    @Autowired
    private UserRepository userRepository;
    @Override
    public void updateEmail(String oldMail, String newMail)
    {
       User user=userRepository.findByEmail(oldMail);
       user.setEmail(newMail);
       userRepository.save(user);
    }

    @Override
    public void updatePassword(String Email, String newPassword){

        User user=userRepository.findByEmail(Email);
        user.setEmail(newPassword);
        userRepository.save(user);
    }


}
