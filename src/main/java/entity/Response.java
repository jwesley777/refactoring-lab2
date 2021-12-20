package entity;

public class Response {
    String message;

    public Response(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Server response:" + message + '\n';
    }
}
