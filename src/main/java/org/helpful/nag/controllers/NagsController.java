package org.helpful.nag.controllers;

import org.helpful.nag.nags.Nag;
import org.helpful.nag.nags.tomcat.AppCompareNagger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nags")
public class NagsController {

    @RequestMapping
    public List<Nag> nags() throws IOException {
        return
            AppCompareNagger.findNags().collect(Collectors.toList());

    }
}
