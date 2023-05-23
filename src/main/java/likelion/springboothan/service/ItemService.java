package likelion.springboothan.service;

import likelion.springboothan.domain.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemService {
    @Transactional
    public void save(Item item);

    @Transactional(readOnly = true)
    public List<Item> findAll();

    @Transactional(readOnly = true)
    public Item findById(long id);
    @Transactional
    public void update(Long id, Item item);
}