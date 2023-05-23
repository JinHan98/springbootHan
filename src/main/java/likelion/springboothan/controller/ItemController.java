package likelion.springboothan.controller;

import likelion.springboothan.domain.Item;
import likelion.springboothan.service.ItemService;
import likelion.springboothan.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemServiceImpl){
        this.itemService=itemServiceImpl;
    }
    @GetMapping("new")
    public String createForm(Model model){
        model.addAttribute("itemForm",new Item());
        return "items/createItemForm";
    }

    @PostMapping("new")
    public String create(Item item){
        itemService.save(item);
        return "redirect:/home";
    }
    @GetMapping("")
    public String findAll(Model model){
        List<Item> itemList=itemService.findAll();
        model.addAttribute("itemList",itemList);
        return "items/itemList";
    }
    @GetMapping("{id}/update")
    public String updateForm(@PathVariable("id")Long id,Model model){
        Item item=itemService.findById(id);
        model.addAttribute("itemFormUpdate",item);
        return "items/updateItemForm";
    }
    @PostMapping("update")
    public String update(@RequestParam Long id, @ModelAttribute("form") Item item){
        itemService.update(id,item);
        return "redirect:/items";
    }
}
