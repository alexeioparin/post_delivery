package com.test.pojo;

public class Result {
    private String status;
    private String data;
    private String message;

    public Result (boolean isSuccess, String data, String message) {
        if (isSuccess) {
            this.status = "success";
        } else {
            this.status = "failure";
        }
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String toJSON() {
        return "{\"status\":\"" + status + "\",\"data\":\"" + data + "\",\"message\":\"" + message + "\"}";
    }
}
