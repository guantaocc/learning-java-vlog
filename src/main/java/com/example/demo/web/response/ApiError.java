package com.example.demo.web.response;

public class ApiError {

    private final long timestamp;
    private final int status;
    private final String code;
    private final String message;
    private final String path;
    private final String requestId;

    public ApiError(
            long timestamp,
            int status,
            String code,
            String message,
            String path,
            String requestId) {

        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.message = message;
        this.path = path;
        this.requestId = requestId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public String getRequestId() {
        return requestId;
    }
}
