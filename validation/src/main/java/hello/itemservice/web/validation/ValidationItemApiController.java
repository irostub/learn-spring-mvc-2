package hello.itemservice.web.validation;

import hello.itemservice.domain.item.ItemFinal;
import hello.itemservice.domain.item.ItemFinalRepository;
import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    private final ItemFinalRepository itemFinalRepository;

    @PostMapping("add")
    public ResponseEntity<Object> addItem(@RequestBody @Validated ItemSaveForm saveForm, BindingResult bindingResult) {
        if (saveForm.getPrice() != null && saveForm.getQuantity() != null) {
            int result = saveForm.getPrice() * saveForm.getQuantity();
            if (result < 10_000) {
                bindingResult.reject("totalPriceMin", new Object[]{10_000, result}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
        }

        ItemFinal item = new ItemFinal(saveForm.getItemName(), saveForm.getPrice(), saveForm.getQuantity());
        ItemFinal savedItem = itemFinalRepository.save(item);

        return new ResponseEntity<>(savedItem, HttpStatus.OK);
    }
}
