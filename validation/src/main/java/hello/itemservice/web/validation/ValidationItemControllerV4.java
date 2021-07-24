package hello.itemservice.web.validation;

import hello.itemservice.domain.item.*;
import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
public class ValidationItemControllerV4 {

    private final ItemFinalRepository itemFinalRepository;

    @GetMapping
    public String items(Model model) {
        List<ItemFinal> items = itemFinalRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        ItemFinal item = itemFinalRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm saveForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (saveForm.getPrice() != null && saveForm.getQuantity() != null) {
            int result = saveForm.getPrice() * saveForm.getQuantity();
            if (result < 10_000) {
                bindingResult.reject("totalPriceMin", new Object[]{10_000, result}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            return "validation/v4/addForm";
        }

        ItemFinal item = new ItemFinal(saveForm.getItemName(), saveForm.getPrice(), saveForm.getQuantity());

        ItemFinal savedItem = itemFinalRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v4/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        ItemFinal item = itemFinalRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm updateForm, BindingResult bindingResult) {
        if (updateForm.getPrice() != null && updateForm.getQuantity() != null) {
            int result = updateForm.getPrice() * updateForm.getQuantity();
            if (result < 10_000) {
                bindingResult.reject("totalPriceMin", new Object[]{10_000, result}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            return "validation/v4/editForm";
        }

        ItemFinal item = new ItemFinal(updateForm.getId(), updateForm.getItemName(), updateForm.getPrice(), updateForm.getQuantity());

        itemFinalRepository.update(itemId, item);
        return "redirect:/validation/v4/items/{itemId}";
    }

}

