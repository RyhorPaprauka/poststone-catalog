package com.poststone.catalog.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "nbrb", url = "http://www.nbrb.by")
public interface ConverterClient {

    @GetMapping("API/ExRates/Rates/{abbreviation}?ParamMode=2")
    CurrencyVO getCurrency(@PathVariable(value = "abbreviation") String abbreviation);
}
