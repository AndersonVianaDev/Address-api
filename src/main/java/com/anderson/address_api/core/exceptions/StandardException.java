package com.anderson.address_api.core.exceptions;

import java.time.Instant;

public record StandardException(Instant timestamp, Integer status, String error, String path) {
}
