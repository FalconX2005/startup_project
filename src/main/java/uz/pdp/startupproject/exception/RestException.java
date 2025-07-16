package uz.pdp.startupproject.exception;

import lombok.Getter;

/**
 * Created by: Umar
 * DateTime: 2/10/2025 4:48 PM
 */
@Getter
public class RestException extends RuntimeException {
    private final int statusCode;

    public RestException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public static RestException error(String message) {
        return new RestException(message, 400);
    }

    public static RestException notFound(String message, Object id) {
        return new RestException(message + (id != null ? " with id " + id : ""), 404);
    }

    public static RestException badRequest(String message) {
        return new RestException(message, 400);
    }
}
