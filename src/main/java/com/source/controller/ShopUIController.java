package com.source.controller;


import com.source.model.CustomerData;
import com.source.model.TrackingNumber;
import com.source.model.User;
import com.source.service.AuthenticationService;
import com.source.service.LendService;
import com.source.service.ReturnService;
import com.source.service.TrackingIdService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;
import java.util.Map;

@Controller
public class ShopUIController {

    private static final Logger logger = LoggerFactory.getLogger(ShopUIController.class);


    @Autowired
    AuthenticationService authService;

    @Autowired
    LendService lendService;

    @Autowired
    TrackingIdService trackingIdService;

    @Autowired
    ReturnService returnService;

    @GetMapping("/pawnshop")
    public ModelAndView authorizeUser(){
        List<User> login = authService.getAdminCredentials();
        String user = login.get(0).getUserName();
        String password = login.get(0).getPassword();
        ModelAndView model = new ModelAndView("authentication");
        model.addObject("adminName",user);
        model.addObject("adminPassword",password);
        return model;
    }

    @GetMapping("/pawnshop/home")
    public ModelAndView homePage(@RequestParam(name="admin") String admin){

        ModelAndView model = new ModelAndView("home");
        model.addObject("adminName",admin);
        return model;
    }

    @GetMapping("pawnshop/lend")
    public ModelAndView lendPage(@RequestParam(name="admin") String admin) {

        List<TrackingNumber> trackingId = trackingIdService.generateTrackingId();
        int trackId = trackingId.get(0).getTrackingId();
        logger.info("Tracking Id :: "+trackId);
        ModelAndView model = new ModelAndView("lend");
        model.addObject("adminName",admin);
        model.addObject("trackingId",trackId);
        return model;
    }
    @GetMapping("pawnshop/return")
    public ModelAndView returnPage(@RequestParam(name="admin") String admin) {

        ModelAndView model = new ModelAndView("return");
        model.addObject("adminName",admin);
        return model;
    }

    @GetMapping("pawnshop/viewRecord")
    public ModelAndView viewRecord(@RequestParam(name="admin") String admin) {

        ModelAndView model = new ModelAndView("viewrecord");
        model.addObject("adminName",admin);
        return model;
    }

    @GetMapping("pawnshop/dailyReport")
    public ModelAndView dailyReport(@RequestParam(name="admin") String admin) {

        ModelAndView model = new ModelAndView("dailyReport");
        model.addObject("adminName",admin);
        return model;
    }

    @GetMapping("pawnshop/search")
    public ModelAndView search(@RequestParam(name="admin") String admin) {

        ModelAndView model = new ModelAndView("search");
        model.addObject("adminName",admin);
        return model;
    }

    @GetMapping("pawnshop/calculateInterest")
    public ModelAndView calculateInterest(@RequestParam(name="admin") String admin) {

        ModelAndView model = new ModelAndView("calculateInterest");
        model.addObject("adminName",admin);
        return model;
    }

}
