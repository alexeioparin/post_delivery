package com.test.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void toJSON() {
        Result result = new Result(true, "someData", "someMessage");
        assertEquals("{\"status\":\"success\",\"data\":\"someData\"," +
                "\"message\":\"someMessage\"}", result.toJSON());
    }
}