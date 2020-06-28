package lk.lakshan.store.pricecalculator.service;

import lk.lakshan.store.pricecalculator.model.Item;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> getAllItems();
    Item findItemById(int id);
    ResponseEntity calculatePrice(int id, int singleUnits, int cartons, boolean isTable);
    void addItem(Item item);
}

