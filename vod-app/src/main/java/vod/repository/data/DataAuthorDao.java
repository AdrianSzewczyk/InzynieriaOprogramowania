package vod.repository.data;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import vod.model.Author;
import vod.repository.AuthorDao;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Primary
public class DataAuthorDao implements AuthorDao {
    private final AuthorRepository authorRepository;
    @Override
    public List<Author> findAll(){return authorRepository.findAll();}

    @Override
    public Author findById(int id){return authorRepository.findById(id).orElse(null);}

    @Override
    public Author add(Author a){return authorRepository.save(a);}
}
