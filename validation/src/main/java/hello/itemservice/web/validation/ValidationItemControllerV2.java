package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;
    private final ItemValidator itemValidator;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    //BindingResult 기초적 사용 버전
    //@PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.debug("in bindingResult={}", bindingResult);
        log.debug("in item={}", item);
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", "상품 이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1_000 || item.getPrice() > 1_000_000) {
            bindingResult.addError(new FieldError("item", "price", "가격은 1,000원 이상 1,000,000이하만 입력가능합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", "수량은 9999이하만 입력가능합니다."));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10_000) {
                bindingResult.addError(new ObjectError("item", "가격 * 수량의 합은 10,000원 이상이어야합니다. 현재 값 : " + result));
            }
        }

        if (bindingResult.hasErrors()) {
            log.debug("out bindingResult={}", bindingResult);
            log.debug("out item={}", item);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //바인딩 실패 값을 뷰로 넘겨주는 버전
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.debug("in bindingResult={}", bindingResult);
        log.debug("in item={}", item);
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, null, null, "상품 이름은 필수입니다."));
        }
        if (item.getPrice() == null || item.getPrice() < 1_000 || item.getPrice() > 1_000_000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, null, null, "가격은 1,000원 이상 1,000,000이하만 입력가능합니다."));
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, null, null, "수량은 9999이하만 입력가능합니다."));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10_000) {
                bindingResult.addError(new ObjectError("item", null, null, "가격 * 수량의 합은 10,000원 이상이어야합니다. 현재 값 : " + result));
            }
        }

        if (bindingResult.hasErrors()) {
            log.debug("out bindingResult={}", bindingResult);
            log.debug("out item={}", item);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //바인딩 실패 시 전송할 message 를 체계적으로 관리 하는 버전
    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        log.debug("in bindingResult={}", bindingResult);
        log.debug("in item={}", item);
        if (!StringUtils.hasText(item.getItemName())) {
            bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[]{"required.item.itemName"}, null, null));
        }
        if (item.getPrice() == null || item.getPrice() < 1_000 || item.getPrice() > 1_000_000) {
            bindingResult.addError(new FieldError("item", "price", item.getPrice(), false, new String[]{"range.item.price"}, new Object[]{1_000, 1_000_000}, null));
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[]{"max.item.quantity"}, new Object[]{9999}, null));
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10_000) {
                bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10_000, result}, null));
            }
        }

        if (bindingResult.hasErrors()) {
            log.debug("out bindingResult={}", bindingResult);
            log.debug("out item={}", item);
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //addError() 를 사용해서 하려다보니 뭔가 복잡하고 귀찮다. 그래서 더 줄이도록 하면서 에러 수준에 따라 더 상세한 메세지가 자동으로 선택되도록 수정한다.
    //핵심은 rejectValue() 를 사용함으로써 MessageCodesResolver 를 사용하여 오류 코드를 수준에 따라 선택되도록 한다는 것에 있다.
    //@PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //간단하게 값이 비었는지 공백인지 등을 판단할 땐 ValidationUtils 를 사용해주면 더 직관적이고 편하게 검증할 수 있다.
        ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult, "itemName", "required");

        if (item.getPrice() == null || item.getPrice() < 1_000 || item.getPrice() > 1_000_000) {
            bindingResult.rejectValue("itemName", "range", new Object[]{1_000, 1_000_000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            bindingResult.rejectValue("itemName", "max", new Object[]{9999}, null);
        }

        if (item.getPrice() != null && item.getQuantity() != null) {
            int result = item.getPrice() * item.getQuantity();
            if (result < 10_000) {
                bindingResult.reject("totalPriceMin", new Object[]{10000, result}, null);
            }
        }

        if (bindingResult.hasErrors()) {
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //컨트롤러의 로직 중 검증 로직이 절반을 넘어간다. 컨트롤러가 검증을 위해 처리하는 일이 많다는 것이다.
    //검증 로직은 검증기에 맞겨주자.
    @PostMapping("/add")
    public String addItemV5(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        itemValidator.validate(item, bindingResult);

        if (bindingResult.hasErrors()) {
            return "validation/v2/addForm";
        }

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

