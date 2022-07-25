package com.example.demo.order.query.application;

import com.example.demo.order.query.dao.OrderSummaryDao;
import com.example.demo.order.query.dto.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class OrderViewListService {
    private final OrderSummaryDao orderSummaryDao;

    public OrderViewListService(OrderSummaryDao orderSummaryDao) {
        this.orderSummaryDao = orderSummaryDao;
    }

    public Page<OrderSummary> getList(ListRequest listReq) {
        PageRequest pageable = PageRequest.of(
                listReq.getPage(),
                listReq.getSize(),
                Sort.by(Sort.Direction.DESC, "number"));
        return orderSummaryDao.findAll(pageable);
    }
}
