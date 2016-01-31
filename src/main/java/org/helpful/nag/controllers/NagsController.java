package org.helpful.nag.controllers;

import org.helpful.nag.naggers.Nag;
import org.helpful.nag.naggers.tomcat.AppCompareNagger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nags")
public class NagsController {

    final AppCompareNagger appCompareNagger = new AppCompareNagger();

    @RequestMapping
    public List<Nag> nags() throws IOException {
        return
            appCompareNagger.findNags().collect(Collectors.toList());

    }
}
