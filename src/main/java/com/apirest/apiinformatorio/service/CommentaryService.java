package com.apirest.apiinformatorio.service;

import com.apirest.apiinformatorio.exception.ResourceNotFoundException;
import com.apirest.apiinformatorio.model.Commentary;
import com.apirest.apiinformatorio.repository.CommentaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentaryService {

    @Autowired
    private CommentaryRepository commentaryRepository;

    public List<Commentary> getCommentaries() {
        return commentaryRepository.findAll();
    }

    public Commentary getCommentaryById(Long id) {
        Optional<Commentary> optionalCommentary = commentaryRepository.findById(id);
        if(optionalCommentary.isPresent()) {
            return optionalCommentary.get();
        } else {
            throw new ResourceNotFoundException("Comentario con id: "+id+" no encontrado.");
        }
    }

    public Commentary addCommentary(Commentary commentary) {
        return commentaryRepository.save(commentary);
    }

    public void deleteCommentaryById(Long id) {
        Optional<Commentary> optionalCommentary = commentaryRepository.findById(id);
        if(optionalCommentary.isPresent()) {
            commentaryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Comentario con id: "+id+" no encontrado.");
        }
    }

    public Commentary updateCommentary(Commentary commentary) {
        Optional<Commentary> optionalCommentary = commentaryRepository.findById(commentary.getId());
        if(optionalCommentary.isPresent()) {
            Commentary commentaryToUpdate = optionalCommentary.get();
            commentaryToUpdate.setComment(commentary.getComment());
            commentaryRepository.save(commentaryToUpdate);
            return commentaryToUpdate;
        } else {
            throw new ResourceNotFoundException("Comentario con id: "+commentary.getId()+" no encontrado.");
        }
    }

    public List<Commentary> getCommentariesPostByLimit(Long id_post, Integer lim) {
        return commentaryRepository.getCommentariesPostByLimit(id_post, lim);
    }



}
