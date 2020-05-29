package au.usyd.artrader.domain;

public class Response {
    private Object results;
    private String error;

    public Response(Object results) {
        this.results = results;
    }

    public Response(String error) {
        this.error = error;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
        this.results = results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
