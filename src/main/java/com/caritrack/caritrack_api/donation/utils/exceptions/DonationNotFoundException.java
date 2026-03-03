package com.caritrack.caritrack_api.donation.utils.exceptions;

public class DonationNotFoundException extends RuntimeException {
    public DonationNotFoundException(String message) {
        super(message);
    }
}
