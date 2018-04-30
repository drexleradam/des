package hu.pte.ttk.des.controller;

import hu.pte.ttk.des.MyDes;
import hu.pte.ttk.des.entity.Message;
import hu.pte.ttk.des.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

@Controller
public class MessageController {

    private MyDes des = new MyDes();

    private MessageService service;

    @Autowired
    public void setService(MessageService service) {
        this.service = service;
    }

    @RequestMapping(value = "/")
    public String redirect(Model model) {
        model.addAttribute("title", "Kezdőlap");
        return "home";
    }

    @RequestMapping(value = "/home")
    public String home(Model model) {
        model.addAttribute("title", "Kezdőlap");
        return "home";
    }

    @RequestMapping(value = "/list")
    public String list(Model model, @RequestParam(defaultValue = "1") int page) {
        model.addAttribute("title", "Eltárolt adatok");
        Page<Message> messagePage = service.getAll(page, 8);
        if (messagePage.getTotalPages() != 0) {
            model.addAttribute("messages", messagePage);
        } else {
            model.addAttribute("noEntity", "A tábla jelenleg üres, végezzen el egy kódolást, hogy feltöltse a táblát.");
        }
        return "list";
    }

    @RequestMapping(value = "/encode", method = RequestMethod.GET)
    public String encode(Model model) {
        model.addAttribute("title", "Kódolás");
        model.addAttribute("button", "kódolás");
        model.addAttribute("input_label", "Nyílt szöveg:");
        model.addAttribute("output_label", "Kódolt szöveg:");
        return "code";
    }

    @RequestMapping(value = "/decode", method = RequestMethod.GET)
    public String decode(Model model) {
        model.addAttribute("title", "Dekódolás");
        model.addAttribute("button", "dekódolás");
        model.addAttribute("input_label", "Kódolt szöveg:");
        model.addAttribute("output_label", "Nyílt szöveg:");
        return "code";
    }

    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    public String encrypt(@RequestParam String text,
                          @RequestParam String key,
                          Model model) {
        model.addAttribute("title", "Kódolás");
        model.addAttribute("button", "kódolás");
        model.addAttribute("input_label", "Nyílt szöveg:");
        model.addAttribute("output_label", "Kódolt szöveg:");
        model.addAttribute("text", text);
        model.addAttribute("key", key);
        byte[] preOutput = text.getBytes();
        if ((preOutput.length % 8 == 0)) {
            if (key.length() == 8) {
                preOutput = des.encrypt(preOutput, key);
                preOutput = Base64.getEncoder().encode(preOutput);
                if (preOutput != null) {
                    String output = new String(preOutput);
                    model.addAttribute("output", output);
                    service.save(new Message(text, key, output));
                }
            } else {
                model.addAttribute("keyerror", "Valahogy elrontotta a kulcsot, 8 karakter hosszúnak kell hogy legyen!");
            }
        } else {
            model.addAttribute("texterror", "A nyílt szöveg karakterszáma nem 8-nak a többszöröse. (" + preOutput.length + ")");
        }
        return "code";
    }

    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    public String decrypt(@RequestParam String text,
                          @RequestParam String key,
                          Model model) {
        model.addAttribute("title", "Dekódolás");
        model.addAttribute("button", "dekódolás");
        model.addAttribute("input_label", "Kódolt szöveg:");
        model.addAttribute("output_label", "Nyílt szöveg:");
        model.addAttribute("text", text);
        model.addAttribute("key", key);
        byte[] preOutput = Base64.getDecoder().decode(text.getBytes());
        if (preOutput.length % 8 == 0) {
            if (key.length() == 8) {
                byte[] output = des.decrypt(preOutput, key);
                if (output != null) {
                    model.addAttribute("output", new String(output));
                }
            } else {
                model.addAttribute("keyerror", "Valahogy elrontotta a kulcsot, 8 karakter hosszúnak kell hogy legyen!");
            }
        } else {
            model.addAttribute("texterror", "A kódolsz szöveg karakterszáma nem 8-nak a többszöröse. (" + preOutput.length + ")");
        }
        return "code";
    }

}
