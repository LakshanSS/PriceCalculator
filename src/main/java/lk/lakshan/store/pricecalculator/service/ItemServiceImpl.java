package lk.lakshan.store.pricecalculator.service;

import lk.lakshan.store.pricecalculator.model.Item;
import lk.lakshan.store.pricecalculator.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository repository;
    private static DecimalFormat df = new DecimalFormat("0.00");

    public List<Item> getAllItems() {
        return repository.findAll();
    }

    public Item findItemById(int id) {
        return repository.findItemById(id);
    }

    public ResponseEntity calculatePrice(int id, int singleUnits, int cartons, boolean isTable) {
        Item item = findItemById(id);
        double priceOfCarton = item.getCartonPrice();
        int unitsPerCarton = item.getUnitsPerCarton();

        if (isTable) {
            Map<Integer, String> priceMap = new HashMap<>();
            for (int q=1; q<=50; q++) {
                priceMap.put(q, calculateTotalAmount(q, 0, priceOfCarton, unitsPerCarton));
            }
            return ResponseEntity.ok(priceMap);
        } else {
            return ResponseEntity.ok(calculateTotalAmount(singleUnits, cartons, priceOfCarton, unitsPerCarton));
        }
    }

    public void addItem(Item item) {
        repository.save(item);
    }

    private String calculateTotalAmount (int singleUnits, int cartons, double priceOfCarton, int unitsPerCarton) {
        int noOfCartons = (singleUnits/unitsPerCarton) + cartons;
        int noOfSingleUnits = singleUnits % unitsPerCarton;

        double amountForSingleUnits = 0;
        if (noOfSingleUnits > 0) {
            amountForSingleUnits = (priceOfCarton/unitsPerCarton) * 1.3 * noOfSingleUnits;
        }

        double amountForCartons = noOfCartons * priceOfCarton;

        if (noOfCartons > 2) {
            amountForCartons = amountForCartons * 0.9;
        }

        //double totalAmount = Math.round((amountForCartons + amountForSingleUnits) * 100.00) / 100.00;
        String totalAmount = df.format(amountForCartons + amountForSingleUnits);
        return totalAmount;
    }
}
