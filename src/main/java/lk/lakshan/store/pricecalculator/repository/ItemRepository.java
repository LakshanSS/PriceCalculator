package lk.lakshan.store.pricecalculator.repository;

import lk.lakshan.store.pricecalculator.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ItemRepository extends MongoRepository <Item, Integer> {
    Item findItemById (@Param("id") int id);

}
