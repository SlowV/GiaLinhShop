package com.slowv.fruit.domain.rest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PagingRequest {
    private int page = 0;
    private int size = Integer.MAX_VALUE;
    private Map<String, String> orders;

    public Pageable pageable() {
        if (CollectionUtils.isEmpty(orders)) {
            return PageRequest.of(page, size);
        }

        List<Sort.Order> sortableList = new ArrayList<>();
        orders.forEach((key, value) -> {
            Sort.Direction direction = Sort.Direction.DESC.name().equals(value) ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, key);
            sortableList.add(order);
        });
        Sort sortable = Sort.by(sortableList);
        return PageRequest.of(page, size, sortable);
    }
}
