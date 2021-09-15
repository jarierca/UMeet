package com.umeet.umeet.services;

import com.umeet.umeet.entities.Category;
import com.umeet.umeet.entities.Friend;
import com.umeet.umeet.interfaces.IServerService;
import com.umeet.umeet.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService implements IServerService {

    @Autowired
    ServerRepository serverRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageFileRepository messageFieldRepository;

    @Autowired
    UserServerRoleRepository userServerRoleRepository;
    
   // @Autowired
    //FriendRepository friendRepository;
    
    @Autowired
    ProfileRepository profileRepository;
    
    @Override
    public void deleteServerCascade(Long idServer) {
        List<Category> categories = categoryRepository.findCategoryByServer(serverRepository.findById(idServer).get());
        categories.stream()
                .forEach(x->{
                    x.getChannels().stream()
                            .forEach(y->y.getMessages()
                                    .stream().forEach(z->{
                                        messageFieldRepository.deleteById(z.getMessageFile().getId());
                                        messageRepository.deleteById(z.getId());
                                    }));
                    x.getChannels().stream()
                            .forEach(w->channelRepository.deleteById(w.getId()));
                });
        categories.stream().forEach(c->categoryRepository.deleteById(c.getId()));
        serverRepository.findById(idServer).get().getUserServerRole()
                .stream()
                .forEach(u->userServerRoleRepository.deleteById(u.getId()));
        serverRepository.deleteById(idServer);
    }
    
    
    public void deleteFriendCascade(Long id) {
      /*  List<Friend> friends = friendRepository.findFriendByUser(profileRepository.findById(id).get());
        friends.stream()
                .forEach(x->{
                    x.get
                });
        
        friendRepository.deleteAllById();*/
    }
}
