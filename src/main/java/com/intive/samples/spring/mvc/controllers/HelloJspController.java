package com.intive.samples.spring.mvc.controllers;

import com.intive.samples.spring.mvc.exceptions.HelloException;
import com.intive.samples.spring.mvc.samples.Account;
import com.intive.samples.spring.mvc.samples.AccountValidator;
import com.intive.samples.spring.mvc.samples.Client;
import com.intive.samples.spring.mvc.samples.ClientPropertyEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

import javax.validation.Valid;

@Controller
@RequestMapping("/hello")
public class HelloJspController {

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder("account")
    public void initBinderForAccount(WebDataBinder binder) {
        binder.addValidators(accountValidator);
    }

    @InitBinder
    public void initBinderForClient(WebDataBinder binder) {
        binder.registerCustomEditor(Client.class, new ClientPropertyEditor());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView hello() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "friend");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ModelAndView helloName(@PathVariable String name) throws HelloException {

        if (!Character.isUpperCase(name.codePointAt(0))) {
            throw new HelloException("Name has to start with upper case! Given name: " + name);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", name);
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = "/${spring.mvc.sample.value}", method = RequestMethod.GET)
    public ModelAndView helloProperties() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name", "Properties");
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    // #################### MODEL ATTRIBUTE #######################

    //Account is added to model via AccountConverter.
    // The AccountConverter is added to context via conversionService in xml:
    // <mvc:annotation-driven conversion-service="conversionService"/>
    @ResponseBody
    @RequestMapping(value = "/account/{account}")
    public String helloAccount(@PathVariable("account") @Valid Account account) {
        return String.format("<h1>Hello! %s</h1>", account.getDesc());
    }

    @ResponseBody
    @RequestMapping(value = "/account/valid/{account}")
    public String helloValidAccount(@ModelAttribute("account") @Valid Account account, BindingResult result) throws HelloException {

        if (result.hasErrors()) {
            throw new HelloException(result.getAllErrors().toString());
        }

        return String.format("<h1>Hello! %s</h1>", account.getDesc());
    }

    //Account is retrieved from param via @ModelAttribute method.
    @ResponseBody
    @RequestMapping(value = "/account/param/")
    public String helloAccountParam(@ModelAttribute("accountFromParam") Account account) {

        return String.format("<h1>Hello! %s</h1>", account.getDesc());
    }

    //Account is retrieved from path variable via @ModelAttribute method.
    @ResponseBody
    @RequestMapping(value = "/account/path/{accountNr}")
    public String helloAccountPath(@ModelAttribute("accountFromPath") Account account) {

        return String.format("<h1>Hello! %s</h1>", account.getDesc());
    }

    //Account is retrieved from path variable via @ModelAttribute method.
    @ResponseBody
    @RequestMapping(value = "/account/client/")
    public String helloClientAccountParam(@ModelAttribute("accountFromParam") Account account, @ModelAttribute("clientFromParam") Client client) {
        return String.format("<h1>Hello %s! %s</h1>", client.toString(), account.getDesc());
    }

    @ModelAttribute
    public void createClientFromParam(@RequestParam(required = false) String firstName, @RequestParam(required = false) String surname, Model model) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setSurname(surname);
        model.addAttribute("clientFromParam", client);
    }


    @ModelAttribute
    public void createAccount(@RequestParam(required = false) String accountNr, Model model) {
        Account account = new Account();
        account.setNumber(accountNr);
        account.setDesc(String.format("This is sample account create by method-level @ModelAttribute from request param. Nr: %s", accountNr));
        model.addAttribute("accountFromParam", account);
    }

    @ModelAttribute
    public void createAccountFromPath(@PathVariable(required = false) String accountNr, Model model) {
        Account account = new Account();
        account.setNumber(accountNr);
        account.setDesc(String.format("This is sample account create by method-level @ModelAttribute from path variable. Nr: %s", accountNr));
        model.addAttribute("accountFromPath", account);
    }

    // ############################################################

    // Registered ClientPropertyEditor in InitBinder method.
    @RequestMapping("clients/{client}")
    @ResponseBody
    public String createClient(@PathVariable Client client) {
        return String.format("<h1>Hello %s!</h1>", client.toString());
    }

    @RequestMapping("/excel")
    public String createExcel() {
        return "excelView";
    }

    //    Exception handled by the DefaultExceptionHandler
    @RequestMapping(value = "/exception/isexception", method = RequestMethod.GET)
    public ModelAndView helloISException(Model model) {
        throw new IllegalStateException("Sample " + IllegalStateException.class.getSimpleName() + " exception.");
    }

    //    Exception handled by the DefaultExceptionHandler
    @RequestMapping(value = "/exception/iaexception", method = RequestMethod.GET)
    public ModelAndView helloIAException(Model model) {
        throw new IllegalArgumentException("Sample " + IllegalArgumentException.class.getSimpleName() + " exception.");
    }

    //    Exception handled by the DefaultExceptionHandler through ResponseEntityExceptionHandler
    @RequestMapping(value = "/exception/mtexception", method = RequestMethod.GET)
    public ModelAndView helloBindException(Model model) throws HttpMediaTypeNotSupportedException {
        throw new HttpMediaTypeNotSupportedException(MediaType.APPLICATION_ATOM_XML, Arrays.asList(MediaType.APPLICATION_JSON));
    }

    //    Exception handled by the controller
    @ExceptionHandler(HelloException.class)
    @ResponseBody
    public ResponseEntity<String> handleControllerExceptions(HelloException ex) {

        //You can comment whole method and annotated exception with @ResponseStatus with appropriate status,
        // then the exception will be processed by DefaultHandlerExceptionResolver
        // and selected status code will be assigned.
        return new ResponseEntity<String>(
                "Some problems occurred with saying hello to you: " + ex.getMessage(),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
