package com.poststone.catalog.service;

import com.poststone.catalog.client.ConverterClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyService {

    private static final String USD_CURRENCY = "USD";
    private static BigDecimal usdRate = new BigDecimal(2.5);
    private final ConverterClient converterClient;

    @Scheduled(cron = "0 0/2 * * * ?")
    @CacheEvict(value = "product", allEntries = true)
    public void getActualUSDRate() {
        log.info("Sending request to NBRB for actual USD rate");
        BigDecimal actualRate = converterClient.getCurrency(USD_CURRENCY).getExactRate();
        log.info("Actual USD rate is ".concat(actualRate.toString()));

        usdRate = actualRate;
    }

    public BigDecimal getUSDRate() {
        return usdRate;
    }
}
