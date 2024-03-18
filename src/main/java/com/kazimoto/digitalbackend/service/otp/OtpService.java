package com.kazimoto.digitalbackend.service.otp;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;

@Service
public class OtpService {
    private static final int OTP_EXPIRATION_MINUTES = 5;
    private static final int OTP_LENGTH = 6;

    private Map<String, String> otpMap = new ConcurrentHashMap<>();

    public String generateOTP(String username){
        String otp = RandomStringUtils.randomNumeric(OTP_LENGTH);
        otpMap.put(username, otp);
        scheduleOtpExpiry(username);
        return otp;
    }

    public boolean verifyOTP(String username, String otp) {
        String storedOTP = otpMap.get(username);
        return storedOTP != null && storedOTP.equals(otp);
    }

    private void scheduleOtpExpiry(String username) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> otpMap.remove(username), OTP_EXPIRATION_MINUTES, TimeUnit.MINUTES);
        executorService.shutdown();
    }

    public String sendOTP(String username) {
        String otp = generateOTP(username);
        // Return OTP as JSON
        return "{\"otp\": \"" + otp + "\"}";
    }
}
