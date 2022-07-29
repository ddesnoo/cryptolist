package ddesnoo.nl.CryptoList;

public class ResponseCodeModel {

    /**
     * In this file we create a custom model for response codes. This will make it easy to return a JSON with the desired fields
     *
     * fields:
     * Statuscode
     * Status
     *
     * JSON:
     * {
     *  "Status": "Currency updated",
     *  "StatusCode": 200
     * }
     */

    private int StatusCode;
    private String Status;

    public ResponseCodeModel(int statusCode, String status) {
        StatusCode = statusCode;
        Status = status;
    }

    public ResponseCodeModel() {
    }

    @Override
    public String toString() {
        return "ResponseCodeModel{" +
                "StatusCode=" + StatusCode +
                ", Status='" + Status + '\'' +
                '}';
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

}
