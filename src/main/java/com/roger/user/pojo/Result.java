package com.roger.user.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {

    @Schema(description =
            "Status code, " +
            "0 indicates success, " +
            "1 indicates failure")
    private Integer code;

    @Schema(description =
            "Response message, " +
            "provides a description of the operation result")
    private String message;

    @Schema(description =
            "Specific data, " +
            "varies based on the operation")
    private T data;

    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <E> Result<E> success(String message, E data) {
        return new Result<>(0, message, data);
    }

    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    public static Result success(String message) {
        return new Result(0, message, null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
