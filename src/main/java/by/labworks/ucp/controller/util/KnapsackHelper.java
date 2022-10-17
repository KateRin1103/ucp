package by.labworks.ucp.controller.util;

import by.labworks.ucp.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class KnapsackHelper {

    private List<OrderDTO> bestItems = new ArrayList<>();

    private double maxW;

    private double bestPrice;

    public KnapsackHelper(double maxW) {
        this.maxW = maxW;
    }

    private double calcWeigth(List<OrderDTO> items) {
        double sumW = 0;
        for (OrderDTO order : items) {
            sumW += order.getCargo().getWeight();
        }
        return sumW;
    }

    //вычисляет общую стоимость набора предметов
    private double calcPrice(List<OrderDTO> items) {
        double sumPrice = 0;
        for (OrderDTO i : items) {
            sumPrice += i.getCost();
        }
        return sumPrice;
    }

    private void checkSet(List<OrderDTO> items) {
        if (bestItems == null) {
            if (calcWeigth(items) <= maxW) {
                bestItems = items;
                bestPrice = calcPrice(items);
            }
        } else {
            if (calcWeigth(items) <= maxW && calcPrice(items) > bestPrice) {
                bestItems = items;
                bestPrice = calcPrice(items);
            }
        }
    }

    public void makeAllSets(List<OrderDTO> items) {
        if (items.size() > 0)
            checkSet(items);

        for (int i = 0; i < items.size(); i++) {
            List<OrderDTO> newSet = new ArrayList<>(items);
            newSet.remove(i);
            makeAllSets(newSet);
        }
    }

    public List<OrderDTO> getBestSet() {
        return bestItems;
    }
}
