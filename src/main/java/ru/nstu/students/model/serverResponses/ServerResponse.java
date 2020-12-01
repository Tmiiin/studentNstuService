package ru.nstu.students.model.serverResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServerResponse {
    HttpStatus status;
    Object responseBody;
    public ServerResponse(HttpStatus status, Object responseBody){
        this.status = status;
        this.responseBody = responseBody;
    }
}
