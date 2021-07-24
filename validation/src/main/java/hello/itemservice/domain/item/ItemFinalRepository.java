package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemFinalRepository {

    private static final Map<Long, ItemFinal> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    public ItemFinal save(ItemFinal item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public ItemFinal findById(Long id) {
        return store.get(id);
    }

    public List<ItemFinal> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, ItemFinal updateParam) {
        ItemFinal findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }

}
