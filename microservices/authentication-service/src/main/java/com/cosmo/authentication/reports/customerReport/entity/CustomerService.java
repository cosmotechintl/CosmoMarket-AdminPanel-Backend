package com.cosmo.authentication.reports.customerReport.entity;

import com.cosmo.authentication.reports.customerReport.model.DeleteCustomerRequest;
import com.cosmo.authentication.reports.customerReport.model.FetchCustomerDetail;
import com.cosmo.authentication.reports.customerReport.model.BlockCustomerRequest;
import com.cosmo.common.model.ApiResponse;
import com.cosmo.common.model.SearchParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final WebClient webClient;

    public Mono<ApiResponse> getRegisteredCustomers(SearchParam searchParam){
        return webClient.post()
                .uri("http://localhost:9091/customer/get")
                .bodyValue(searchParam)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
    public Mono<ApiResponse> getCustomerDetails(FetchCustomerDetail fetchCustomerDetail) {
        return webClient.post()
                .uri("http://localhost:9091/customer/get/detail")
                .bodyValue(fetchCustomerDetail)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
    public Mono<ApiResponse> deleteCustomer(DeleteCustomerRequest request) {
        return webClient.post()
                .uri("http://customer-service/api/customer/delete")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
    public Mono<ApiResponse> blockCustomer(BlockCustomerRequest request) {
        return webClient.post()
                .uri("http://localhost:9091/customer/block")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
}
