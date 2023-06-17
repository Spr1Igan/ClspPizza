package com.example.sprsite.bean;

import com.example.sprsite.models.Orders;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Data
@SessionScope
@Component
public class HttpSessionBean {
    private Orders order;
}
