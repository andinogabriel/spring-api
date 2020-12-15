package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
import com.apirest.apiinformatorio.model.Post;
import com.apirest.apiinformatorio.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()) {
            return optionalPost.get();
        } else {
            throw new ResourceNotFoundException("Post con id: "+id+" no encontrado.");
        }
    }

    public void deletePostById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()) {
            postRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Post con id: "+id+" no encontrado.");
        }
    }

    public Post updatePost(Post post) {
        Optional<Post> optionalPost = postRepository.findById(post.getId());
        if(optionalPost.isPresent()) {
            Post postToUpdate = optionalPost.get();
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setDescription(post.getDescription());
            postToUpdate.setBody(post.getBody());
            postToUpdate.setPublished(post.isPublished());
            postRepository.save(postToUpdate);
            return postToUpdate;
        } else {
            throw new ResourceNotFoundException("Post con id: "+post.getId()+" no encontrado.");
        }
    }

}
