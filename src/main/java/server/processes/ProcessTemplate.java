package server.processes;

import com.google.gson.JsonSyntaxException;
import json.Json;
import json.validation.ConstraintViolated;
import json.validation.ValidateJson;
import server.exceptions.BadRequestException;

public abstract class ProcessTemplate implements Process {
    public <T> T buildRequest(String json, Class<T> className) throws BadRequestException {
        try {
            var request = Json.fromJson(json, className);
            ValidateJson.validate(request);
            return request;
        } catch (JsonSyntaxException e) {
            throw new BadRequestException("");
        } catch (ConstraintViolated e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
