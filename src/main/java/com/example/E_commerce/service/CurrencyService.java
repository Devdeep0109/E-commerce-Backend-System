package com.example.E_commerce.service;

import com.example.E_commerce.dto.orderDto.ExchangeRateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class CurrencyService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${exchange.api.base-url}")
    private String baseUrl;

    @Value("${exchange.api.key}")
    private String apiKey;

    @Value("${exchange.api.base-currency}")
    private String baseCurrency;


    public BigDecimal getExchangeRate(String targetCurrency) {

        String url = String.format(
                "%s/%s/latest/%s",
                baseUrl,
                apiKey,
                baseCurrency
        );

        ResponseEntity<ExchangeRateResponse> response =
                restTemplate.getForEntity(url, ExchangeRateResponse.class);

        ExchangeRateResponse body = response.getBody();

        if (body == null || !"success".equalsIgnoreCase(body.getResult())) {
            throw new RuntimeException("Failed to fetch exchange rates");
        }

        BigDecimal rate = body.getConversionRates().get(targetCurrency);

        if (rate == null) {
            throw new RuntimeException("Unsupported currency: " + targetCurrency);
        }

        return rate;
    }
}
