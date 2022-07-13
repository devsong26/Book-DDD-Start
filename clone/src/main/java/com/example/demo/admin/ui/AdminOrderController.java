package com.example.demo.admin.ui;

import com.example.demo.common.ui.Pagination;
import com.example.demo.order.command.application.StartShippingService;
import com.example.demo.order.query.application.ListRequest;
import com.example.demo.order.query.application.OrderDetailService;
import com.example.demo.order.query.application.OrderViewListService;
import com.example.demo.order.query.dto.OrderSummary;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

    private final OrderViewListService orderViewListService;
    private final OrderDetailService orderDetailService;
    private final StartShippingService startShippingService;

    public AdminOrderController(
            OrderViewListService orderViewListService,
            OrderDetailService orderDetailService,
            StartShippingService startShippingService) {
        this.orderViewListService = orderViewListService;
        this.orderDetailService = orderDetailService;
        this.startShippingService = startShippingService;
    }

    @RequestMapping("/orders")
    public String orders(
            @RequestParam(name = "p", defaultValue = "1") int page,
            ModelMap modelMap){
        int size = 20;
        ListRequest listRequest = new ListRequest(page - 1, size);
        Page<OrderSummary> orderPage = orderViewListService.getList(listRequest);
        modelMap.addAttribute("orderPage", orderPage);
        modelMap.addAttribute("pagination",
                new Pagination(
                        orderPage.getNumber() + 1,
                        orderPage.getTotalPages(),
                        5
                ));
        return "admin/adminOrders";
    }

}
