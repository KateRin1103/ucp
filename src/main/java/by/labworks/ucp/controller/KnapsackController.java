package by.labworks.ucp.controller;

import by.labworks.ucp.controller.util.KnapsackHelper;
import by.labworks.ucp.dto.OrderDTO;
import by.labworks.ucp.dto.DeliveryTransportDTO;
import by.labworks.ucp.service.CargoService;
import by.labworks.ucp.service.CityService;
import by.labworks.ucp.service.DeliveryTransportService;
import by.labworks.ucp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/knapsack")
public class KnapsackController {

    private final OrderService orderService;
    private final CityService cityService;
    private final CargoService cargoService;
    private final DeliveryTransportService deliveryTransportService;

    @Autowired
    public KnapsackController(OrderService orderService,
                              CargoService cargoService,
                              CityService cityService,
                              DeliveryTransportService deliveryTransportService) {

        this.orderService = orderService;
        this.cargoService = cargoService;
        this.cityService = cityService;
        this.deliveryTransportService = deliveryTransportService;
    }

    @GetMapping
    public ModelAndView chooseRoute(Model model) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("cities", cityService.findAll());
        modelAndView.setViewName("order/chooseDeliveryCities");

        return modelAndView;
    }

    @PostMapping("/optimal")
    public ModelAndView getOptimalCar(@ModelAttribute("weight") String weight,
                                      @ModelAttribute("cityA") String cityA,
                                      @ModelAttribute("cityB") String cityB) {
        ModelAndView modelAndView = new ModelAndView();

        DeliveryTransportDTO d = new DeliveryTransportDTO();
        d.setCityA(cityService.findByCityName(cityA));
        d.setCityB(cityService.findByCityName(cityB));
        d.setWeight(Double.parseDouble(weight));
        d.setFreeWeight(Double.parseDouble(weight));
        deliveryTransportService.save(d);

        List<OrderDTO> orders = orderService.findByCityAAndCityB(cityA, cityB);
        List<OrderDTO> resList = new ArrayList<>();

        KnapsackHelper knapsack = new KnapsackHelper(deliveryTransportService.findAll().get(deliveryTransportService.findAll().size() - 1).getFreeWeight());

        knapsack.makeAllSets(orders);
        resList = knapsack.getBestSet();

        //доход с доставки и масса
        double totalWeight = 0;
        double totalCost = 0;
        for (OrderDTO order : resList) {
            totalCost += order.getCost();
            totalWeight += order.getCargo().getWeight();
        }

        double resWeight = 0;
        DeliveryTransportDTO dl = deliveryTransportService.findAll().get(deliveryTransportService.findAll().size() - 1);
        for (OrderDTO orderDTO : resList) {
            orderDTO.setTransport(dl);
            orderService.update(orderDTO);
            resWeight += orderDTO.getCargo().getWeight();
        }
        dl.setFreeWeight(dl.getWeight() - resWeight);
        deliveryTransportService.save(dl);

        modelAndView.setViewName("order/ordersDelivery");
        modelAndView.addObject("weight", weight);
        modelAndView.addObject("totalCost", totalCost);
        modelAndView.addObject("totalWeight", totalWeight);
        modelAndView.addObject("cityA", cityA);
        modelAndView.addObject("cityB", cityB);
        modelAndView.addObject("transportId", deliveryTransportService.findAll().get(deliveryTransportService.findAll().size() - 1).getId());
        modelAndView.addObject("orders", resList);
        return modelAndView;
    }
}
