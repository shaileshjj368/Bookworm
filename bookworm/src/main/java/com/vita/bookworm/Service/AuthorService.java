package com.vita.bookworm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.AuthorMaster;
import com.vita.bookworm.Repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public AuthorMaster getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }
    public List<AuthorMaster> getAllAuthors() {
        return authorRepository.findAll();
    }
    public AuthorMaster addAuthor(AuthorMaster author) {
        return authorRepository.save(author);
    }
    public AuthorMaster updateAuthor(AuthorMaster author) {
        return authorRepository.save(author);
    }
    public void deleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
    public List<AuthorMaster> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

}
