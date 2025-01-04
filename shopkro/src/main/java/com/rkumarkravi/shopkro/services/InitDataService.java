package com.rkumarkravi.shopkro.services;

import com.rkumarkravi.shopkro.entities.InitData;
import com.rkumarkravi.shopkro.repositories.InitDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InitDataService {
    @Autowired
    InitDataRepository initDataRepository;

    public Map<String,String> getInitData(){
        Map<String,String> initData=new HashMap<>();
       initDataRepository.findByStatusOrderByCreatedAtAsc()
               .forEach(x->{
                   initData.put(x.getKey(),x.getValue());
               });
       return initData;
    }
}
