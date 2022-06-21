package com.Projekat.controller;

import com.Projekat.dto.ActionDTO;
import com.Projekat.mail.MyMailSender;
import com.Projekat.model.services.QuickAction;
import com.Projekat.model.services.Service;
import com.Projekat.model.users.Client;
import com.Projekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ActionController {

    @Autowired
    private AdventureService adventureService;
    @Autowired
    private ActionService actionService;
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private MyMailSender mailSender;
    @PostMapping("/adventure/create-action")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public ResponseEntity<String> createAction(@RequestBody ActionDTO actionDTO) {
        Service s = adventureService.findOne(actionDTO.getServiceId());
        if (null == s) {
            return new ResponseEntity<>("Doslo je do greske", HttpStatus.BAD_REQUEST);
        }
        QuickAction qa = new QuickAction(actionDTO.getActionDateFrom(), actionDTO.getActionDateTo(), actionDTO.getDiscount(), s);
        actionService.saveNewAction(qa);
        List<Client> clients = subscriptionService.getSubscribedClients(s.getId());

        String mail_body = "Postovani\r\n\r\nZa uslugu na koju ste pretplaceni" + s.getName()+", je otvorena akcija!\r\n\r\nVas TurboJavaSpringbootTurizamExpo";
        for (Client c: clients) {
            String username = userService.findUsernameById(c.getId());
            mailSender.notifyClientOfQuickAction(username, mail_body);
        }

        return new ResponseEntity<>("Uspesno ste kreirali akciju", HttpStatus.OK);
    }
}
