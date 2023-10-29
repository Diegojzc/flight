package com.tokioschool.flightapp.base;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service

public class BaseService2 {
    private final BaseService1 baseService1;

    public BaseService2(@Lazy BaseService1 baseService1) {
        this.baseService1 = baseService1;
    }

    public String say(){
        return "Hello from service2";
    }
}
