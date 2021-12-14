package jpabook.jpashop.api;

import static java.util.stream.Collectors.*;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    /**
     * V1
     * jackson에서 양방향 관계 무한루프
     * 프록시 객체 json 에러
     * db column 노출
     */
    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        return orderRepository.findAllByString(new OrderSearch());
    }

    /**
     * V2
     * Lazy Loading으로 쿼리가 많이 발생함
     * db column 노출 x
     */
    @GetMapping("/api/v2/simple-orders")
    public List<OrderSimpleQueryDto> ordersV2() {
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());

        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(toList());
    }

    /**
     * V3 fetch join 사용
     * 프록시 객체가 아닌 실제 객체로 한번에 가져온다.
     */
    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream()
                .map(OrderSimpleQueryDto::new)
                .collect(toList());
    }

    /**
     * V4.
     * 재사용성이 V4에 비해 떨어진다
     * 조금더 성능 최적화, 필요한 것들만 select
     * api 스펙이 repository에 들어왔다
     * repository는 객체 그래프 조회할때 사용하는게 맞다.
     */
    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }

}
