package com.example.demo.core;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


public interface ModelMapperService {

    ModelMapper request();
    ModelMapper response();

}
