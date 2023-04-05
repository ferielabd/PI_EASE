package com.example.pi_ease.Services.Classes;

import com.example.pi_ease.DAO.Entities.Post;
import com.example.pi_ease.DAO.Entities.React;
import com.example.pi_ease.DAO.Entities.Reaction;
import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.PostRepository;
import com.example.pi_ease.DAO.Repositories.ReactRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Interface.IReactServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReactServices implements IReactServices {
    @Autowired
    ReactRepository reactRepository ;
    @Autowired
    PostRepository postRepository ;
    @Autowired
    UserRepository userRepository ;



    public boolean Decision(Long PostId, Long id) {
        // true =dislike
        // false like
        User userConnected = userRepository.findById(id).orElse(null);
        Post post = postRepository.findById(PostId).orElse(null);
        List<React> reactByUser = reactRepository.findReactById(userConnected);
        List<React> reactByPost = reactRepository.findReactByPostId(post);
        boolean liked = false;
        if (!(reactByUser.isEmpty())) {
            for (React ru : reactByUser) {
                if (reactByPost.contains(ru)) {
                    liked = true;
                    System.out.print("rue   ******************************************");
                } else
                    liked = false;
            }
        }
        return liked ;
    }

    @Override
    public void LikeDislikePost(int reaction , Long PostId, Long id) {
        User userConnected = userRepository.findById(id).orElse(null);
        Post post = postRepository.findById(PostId).orElse(null);
        boolean liked = false;
        List<React> userlikes = userConnected.getLikes();
        System.out.println("userlikes    " + userlikes.size() + "******************************************");
        List<React> postlikes = post.getLikes();
        List<React> reactByUser = reactRepository.findReactById(userConnected);
        List<React> reactByPost = reactRepository.findReactByPostId(post);

        if (Decision(PostId, id)) {
            // dislike
            for (React ru : reactByUser) {
                if (reactByPost.contains(ru)) {
                    int nombre = post.getNlikes();
                    nombre = nombre - 1;
                    System.out.println(nombre);
                    post.setNlikes(nombre);
                    postlikes.remove(ru);
                    userlikes.remove(ru);
                    reactRepository.delete(ru);
                    break;
                }
            }

        } else {// like

            React react = new React();
            Reaction myVar = Reaction.LIKE;
            switch (reaction) {
                case 0:
                    myVar = Reaction.LIKE;

                    break;
                case 1:
                    myVar = Reaction.ADORE;

                    break;
                case 2:
                    myVar = Reaction.HAHA;

                    break;
                case 3:
                    myVar = Reaction.WOW;
                    break;
                case 4:
                    myVar = Reaction.CRY;
                    break;
                case 5:
                    myVar = Reaction.ANGRY;
                    break;
            }
            react.setReaction(myVar);
            react.setPostLike(post);
            react.setUserLike(userConnected);
            System.out.print("tasti lajout   ******************************************");
            reactRepository.save(react);
            System.out.print("tasti lajout 22222222222222222   ******************************************");
            userlikes.add(react);
            post.setNlikes(post.getNlikes() + 1);
            postlikes.add(react);

            postRepository.save(post);
            System.out.print("userlikes  final  " + userlikes.size() + "******************************************");
            System.out.print("postlikes   final  " + postlikes.size() + "******************************************");

        }
    }
    @Override
    public React selectById(int idReaction) {

        return reactRepository.findById(idReaction).get() ;
    }

    @Override
    @Transactional
    public void addReactToPost( int reaction , Long PostId, Long id) {
        React react = new React();
        Post post = postRepository.findById(PostId).orElse(null);
        User user = userRepository.findById(id).orElse(null);
        System.out.println("yaaa"+ user.getId());
        System.out.println("yaaa"+ post.getPostId());
        Reaction myVar = Reaction.LIKE;
        switch (reaction) {
            case 0:
                myVar = Reaction.LIKE;

                break;

            case 1:
                myVar = Reaction.ADORE;

                break;
            case 2:
                myVar = Reaction.HAHA;

                break;
            case 3:
                myVar = Reaction.WOW;
                break;
            case 4:
                myVar = Reaction.CRY;
                break;
            case 5:
                myVar = Reaction.ANGRY;
                break;
        }
        react.setReaction(myVar);
        react.setPostLike(post);
        react.setUserLike(user);
        reactRepository.save(react);
        post.setNlikes(post.getNlikes() + 1);

        if (post.getLikes().isEmpty()) {
            List<React> list = new ArrayList<React>();
            list.add(react);
        } else {
            List<React> listr = (List<React>) post.getLikes();
            listr.add(react);
        }
        postRepository.save(post);

    }

    @Override
    @Transactional
    public void DislikePost( int idReaction ,  Long id) {
        React react =reactRepository.findById(idReaction).orElse(null);
        long userreact = react.getUserLike().getId();
        User userConnected = userRepository.findById(id).orElse(null);
        boolean reacttopost = postRepository.findById(react.getPostLike().getPostId()).isPresent();
        if ((userConnected.getId() == userreact) && (reacttopost)) {
            Post post = postRepository.findById(react.getPostLike().getPostId()).orElse(null);
            post.setNlikes(post.getNlikes() - 1);
            List<React> list = post.getLikes();
            list.remove(react);
            postRepository.save(post);
           reactRepository.delete(react);
        }
    }

    @Override
    public void addReactToComment(int reaction, Long idComm, Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void DislikeComment(int idReaction, Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public React add(React Re) {
        return reactRepository.save(Re) ;
    }

    @Override
    public React edit(React Re) {

        return reactRepository.save(Re) ;
    }
 




    @Override
    public void deleteByID(int idReaction) {
        reactRepository.deleteById(idReaction);

    }

    @Override
    public void delete(React Re) {
        reactRepository.delete(Re);


    }
    @Override
    public List<React> addAll(List<React> list) {
        return list ;
    }

    @Override
    public void deleteAll() {
        reactRepository.deleteAll();

    }



}
