package com.vita.bookworm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vita.Models.GenreMaster;
import com.vita.bookworm.Repository.GenreMasterRepository;

@Service
public class GenreMasterService {
    GenreMasterRepository genreMasterRepository;

    @Autowired
    public GenreMasterService(GenreMasterRepository genreMasterRepository) {
        this.genreMasterRepository = genreMasterRepository;
    }

    public GenreMaster addGenre(GenreMaster genreMaster) {
        GenreMaster g = genreMasterRepository.save(genreMaster);
        return g;
    }

    public GenreMaster getGenreById(int id) {
        GenreMaster g = genreMasterRepository.findById(id).get();
        return g;
    }
    public List<GenreMaster> getAll(){
        return genreMasterRepository.findAll();
    }
}
