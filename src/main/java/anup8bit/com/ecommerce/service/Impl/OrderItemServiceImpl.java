package anup8bit.com.ecommerce.service.Impl;

import anup8bit.com.ecommerce.dto.OrderItemDetailsDto;
//import anup8bit.com.ecommerce.mapper.OrderMapper;
//import anup8bit.com.ecommerce.mapper.ProductMapper;
import anup8bit.com.ecommerce.model.Order;
import anup8bit.com.ecommerce.model.OrderItem;
import anup8bit.com.ecommerce.model.Product;
import anup8bit.com.ecommerce.model.Seller;
import anup8bit.com.ecommerce.repository.OrderItemRepository;
import anup8bit.com.ecommerce.repository.OrderRepository;
import anup8bit.com.ecommerce.repository.ProductRepository;
import anup8bit.com.ecommerce.repository.SellerRepository;
import anup8bit.com.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final Executor executor;
    private final SellerRepository sellerRepository;
//    private final ProductMapper productMapper;
//    private final OrderMapper orderMapper;

    public OrderItemServiceImpl(
            OrderItemRepository orderItemRepository,
            ProductRepository productRepository,
            SellerRepository sellerRepository,
            OrderRepository orderRepository,
//            ProductMapper productMapper,
//            OrderMapper orderMapper,
            @Qualifier("aggregationExecutor") Executor executor
    ) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
//        this.productMapper = productMapper;
//        this.orderMapper = orderMapper;
        this.sellerRepository = sellerRepository;
        this.executor = executor;
    }

    public CompletableFuture<OrderItemDetailsDto> getOrderItemDetails(String id) {
        OrderItem orderItem = orderItemRepository.getOrderItemByOrderId(id)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        CompletableFuture<Product> productFuture = CompletableFuture.supplyAsync(() -> {
            return productRepository.findById(orderItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
        }, executor);

        CompletableFuture<Order> orderFuture = CompletableFuture.supplyAsync(() -> {
            return orderRepository.findById(orderItem.getOrderId())
                    .orElseThrow(() -> new RuntimeException(("order not found")));
        }, executor);

        CompletableFuture<Seller> sellerFuture = CompletableFuture.supplyAsync(()-> {
            return sellerRepository.findBySellerId(orderItem.getSellerId())
                    .orElseThrow(() -> new RuntimeException("Seller not found."));
        }, executor);

        System.out.println(executor);

        return CompletableFuture.allOf(productFuture, orderFuture, sellerFuture)
                .thenApply(v -> {
                    return new OrderItemDetailsDto(
                            orderFuture.join(),
                            productFuture.join(),
                            sellerFuture.join(),
                            orderItem.getOrderItemId(),
                            orderItem.getSellerId(),
                            orderItem.getPrice(),
                            orderItem.getFreightValue()
                    );
                });

        /**return productFuture
                .thenCombine(orderFuture, (product, order) -> new OrderItemDetailsDto(
//                            orderMapper.toOrderDto(order),
//                            productMapper.toProductDto(product),
                            order,
                            product,
                            orderItem.getOrderItemId(),
                            orderItem.getSellerId(),
                            orderItem.getPrice(),
                            orderItem.getFreightValue()
                    )
                );**/
    }
}
