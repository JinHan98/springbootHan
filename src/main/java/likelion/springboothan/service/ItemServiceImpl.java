package likelion.springboothan.service;

import likelion.springboothan.domain.Item;
import likelion.springboothan.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository){
        this.itemRepository=itemRepository;
    }
    @Override
    @Transactional
    public void save(Item item){
        itemRepository.save(item);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll(){
        return itemRepository.findAll();
    }
    @Override
    @Transactional(readOnly = true)
    public Item findById(long id){
        Optional<Item> optionalItem=itemRepository.findById(id);
        if(optionalItem.isPresent()){
            return optionalItem.get();
        }
        else throw new IllegalStateException("not item here");
    }
    @Override
    @Transactional
    public void update(Long id, Item item){
        Optional<Item> optionalItem=itemRepository.findById(id);
        if(optionalItem.isPresent()){
            Item thisItem=optionalItem.get();
            thisItem.setBrand(item.getBrand());
            thisItem.setName(item.getName());
            thisItem.setPrice(item.getPrice());
            thisItem.setStockQuantity(item.getStockQuantity());
        }
    }
}
