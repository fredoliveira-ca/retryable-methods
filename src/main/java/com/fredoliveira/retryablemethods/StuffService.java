package com.fredoliveira.retryablemethods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
class StuffService {

    private static Logger logger = LoggerFactory.getLogger(StuffService.class);

    @Retryable(
            value = TimeoutException.class,
            maxAttempts = 4,
            backoff = @Backoff(delay = 5000))
    HttpStatus doSomeStuff() throws TimeoutException {
        logger.info("Trying call external service.");

        if(callExternalService() == HttpStatus.GATEWAY_TIMEOUT) {
            throw new TimeoutException();
        }

        return callExternalService();
    }
    private HttpStatus callExternalService() {
        return HttpStatus.GATEWAY_TIMEOUT;
    }

}
