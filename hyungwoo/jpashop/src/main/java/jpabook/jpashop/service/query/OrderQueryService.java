package jpabook.jpashop.service.query;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderQueryService {

	private final OrderRepository orderRepository;

	public List<OrderDto> ordersV3() {
		List<Order> orders = orderRepository.findAllWithItem();

		List<OrderDto> result = orders.stream()
			.map(o -> new OrderDto(o))
			.collect(Collectors.toList());

		return result;
	}
}
