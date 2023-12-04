package com.hexagonal.tasks.infrastructure.adapters;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.hexagonal.tasks.domain.models.AdditionalTaskInfo;
import com.hexagonal.tasks.domain.ports.out.ExternalServicePort;

public class ExternalServiceAdapter implements ExternalServicePort {

    private final RestTemplate restTemplate;

    public ExternalServiceAdapter(){
        restTemplate = new RestTemplate();
    }

    @Override
    public AdditionalTaskInfo getAdditionalTaskInfo(Long taskId) {
        String apiUrl = "https://jsonplaceholder.typicode.com/todos/" + taskId;
        ResponseEntity<JsonPlaceHolderTodo> response = restTemplate.getForEntity(apiUrl, JsonPlaceHolderTodo.class);
        JsonPlaceHolderTodo todo = response.getBody();
        if(todo == null){
            return null;
        }
        apiUrl = "https://jsonplaceholder.typicode.com/users/" + todo.getUserId();
        ResponseEntity<JsonPlaceHolderUser> userResponse = restTemplate.getForEntity(apiUrl, JsonPlaceHolderUser.class);
        JsonPlaceHolderUser user = userResponse.getBody();
        if(user == null){
            return null;
        }
        return new AdditionalTaskInfo(user.getId(), user.getName(), user.getMail());
    }

    private static class JsonPlaceHolderTodo {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

    }

    private static class JsonPlaceHolderUser {
        private Long id;
        private String name;
        private String mail;

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getMail() {
            return mail;
        }
    }

}
