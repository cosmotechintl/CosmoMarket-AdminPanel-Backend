package com.cosmo.common.service;

import com.cosmo.common.model.WebClientRequestModel;
import com.cosmo.common.model.ApiResponse;
import reactor.core.publisher.Mono;

public interface WebClientService {
    <T> Mono<ApiResponse<T>> connect(WebClientRequestModel webClientRequestModel);
}

