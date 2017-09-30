package org.seckill.dto;

/**
 * 返回ajax返回的请求信息，封装json结果
 * @param <T>
 */
public class SeckillResult<T> {

    private boolean success;

    private T data;

    private String error;

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, T data) {

        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public SeckillResult setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public T getData() {
        return data;
    }

    public SeckillResult setData(T data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return error;
    }

    public SeckillResult setError(String error) {
        this.error = error;
        return this;
    }

    @Override
    public String toString() {
        return "SeckillResult{" +
                "success=" + success +
                ", data=" + data +
                ", error='" + error + '\'' +
                '}';
    }
}
