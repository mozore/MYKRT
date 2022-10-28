package com.example.data.controller;

import com.example.api.Jl900Service;
import com.example.api.Rss131Service;
import com.example.pojo.entity.Jl900;
import com.example.pojo.entity.Rss131;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {
    // @DubboReference
    // Rss131Service rss131Service;
    // @DubboReference
    // Jl900Service jl900Service;
    //
    // @GetMapping("/getJl900")
    // public Jl900 getJl900() {
    //     return jl900Service.getNewRecord();
    // }
    //
    // @GetMapping("/getRss131")
    // public Rss131 getRss131() {
    //     return rss131Service.getNewRecord();
    // }
}
