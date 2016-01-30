package org.helpful.nag.controllers;

import org.helpful.nag.nags.tomcat.TomcatApp;
import org.helpful.nag.nags.tomcat.dao.Tomcat8TextDAO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nags")
public class NagsController {

    @RequestMapping
    public List<TomcatApp> nags() throws IOException {
        return
            Tomcat8TextDAO.findApps().collect(Collectors.toList());

    }
}
