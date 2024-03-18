package asia.fourtitude.interviewq.jumble.controller;

import java.time.ZonedDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import asia.fourtitude.interviewq.jumble.core.JumbleEngine;
import asia.fourtitude.interviewq.jumble.model.ExistsForm;
import asia.fourtitude.interviewq.jumble.model.PrefixForm;
import asia.fourtitude.interviewq.jumble.model.ScrambleForm;
import asia.fourtitude.interviewq.jumble.model.SearchForm;
import asia.fourtitude.interviewq.jumble.model.SubWordsForm;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/")
public class RootController {

    private static final Logger LOG = LoggerFactory.getLogger(RootController.class);

    private final JumbleEngine jumbleEngine;

    @Autowired(required = true)
    public RootController(JumbleEngine jumbleEngine) {
        this.jumbleEngine = jumbleEngine;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("timeNow", ZonedDateTime.now());
        return "index";
    }

    @GetMapping("scramble")
    public String doGetScramble(Model model) {
        model.addAttribute("form", new ScrambleForm());
        return "scramble";
    }

    @PostMapping("scramble")
    public String doPostScramble(
            @ModelAttribute(name = "form")@Valid ScrambleForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "scramble";
        }

        String inputText = form.getWord();
        form.setScramble(jumbleEngine.scramble(inputText));

        return "scramble";
    }

    @GetMapping("palindrome")
    public String doGetPalindrome(Model model) {
        model.addAttribute("words", this.jumbleEngine.retrievePalindromeWords());
        return "palindrome";
    }

    @GetMapping("exists")
    public String doGetExists(Model model) {
        model.addAttribute("form", new ExistsForm());
        return "exists";
    }

    @PostMapping("exists")
    public String doPostExists(
            @ModelAttribute(name = "form") @Valid ExistsForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "exists";
        }

        String inputText = form.getWord();
        form.setExists(jumbleEngine.exists(inputText));

        return "exists";
    }

    @GetMapping("prefix")
    public String doGetPrefix(Model model) {
        model.addAttribute("form", new PrefixForm());
        return "prefix";
    }

    @PostMapping("prefix")
    public String doPostPrefix(
            @ModelAttribute(name = "form") @Valid PrefixForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "prefix";
        }

        String inputText = form.getPrefix();
        form.setWords(jumbleEngine.wordsMatchingPrefix(inputText));

        return "prefix";

    }

    @GetMapping("search")
    public String doGetSearch(Model model) {
        model.addAttribute("form", new SearchForm());
        return "search";
    }

    @PostMapping("search")
    public String doPostSearch(
            @ModelAttribute(name = "form") @Valid SearchForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "search";
        }

        String startChar = form.getStartChar();
        String endChar = form.getEndChar();
        Integer wordLength = form.getLength();

        form.setWords(jumbleEngine.searchWords(startChar.charAt(0), endChar.charAt(0), wordLength));


        return "search";
    }

    @GetMapping("subWords")
    public String goGetSubWords(Model model) {
        model.addAttribute("form", new SubWordsForm());
        return "subWords";
    }

    @PostMapping("subWords")
    public String doPostSubWords(
            @ModelAttribute(name = "form") @Valid SubWordsForm form,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "subWords";
        }

        String word = form.getWord();
        int wordLength = form.getMinLength();

        form.setWords(jumbleEngine.generateSubWords(word,wordLength));


        return "subWords";
    }

}
