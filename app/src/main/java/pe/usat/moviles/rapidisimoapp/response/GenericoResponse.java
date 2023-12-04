package pe.usat.moviles.rapidisimoapp.response;

import com.google.gson.JsonObject;

public class GenericoResponse {
    private JsonObject data;
    private String message;
    private boolean status;

    public JsonObject getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

}
