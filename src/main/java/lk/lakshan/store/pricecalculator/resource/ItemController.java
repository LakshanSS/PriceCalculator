package lk.lakshan.store.pricecalculator.resource;

import lk.lakshan.store.pricecalculator.model.Item;
import lk.lakshan.store.pricecalculator.repository.ItemRepository;
import lk.lakshan.store.pricecalculator.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity <List<Item>> getItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PostMapping("items")
    public String addItem(@RequestBody Item item) {
        itemService.addItem(item);
        return "Item " + item.getId() + "added";
    }

    @GetMapping("items/{id}")
    public ResponseEntity<Item> findItemById(@PathVariable int id) {
        return ResponseEntity.ok(itemService.findItemById(id));
    }

    @GetMapping("calculatePrice")
    public ResponseEntity calculatePrice(@RequestParam(value = "id") int id,
                                         @RequestParam(value = "singleUnits") int singleUnits,
                                         @RequestParam(value = "cartons") int cartons,
                                         @RequestParam(value = "isTable") boolean isTable) {
        return itemService.calculatePrice(id, singleUnits, cartons, isTable);
    }}
