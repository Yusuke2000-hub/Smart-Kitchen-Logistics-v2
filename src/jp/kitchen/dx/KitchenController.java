package jp.kitchen.dx;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KitchenController {

    @GetMapping("/hello")
    public String hello() {
        return "本日の営業を開始します";
    }

    @PostMapping("/order")
    public String order() {
        return "注文を受け付けました";
    }

}