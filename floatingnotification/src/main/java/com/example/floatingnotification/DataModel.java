package com.example.floatingnotification;

public class DataModel<T> {

    private String id;

    private String message;

    private String type;

    private T data;

    public DataModel(String type, T data) {
        this.type = type;
        this.data = data;
    }

    public DataModel(String id, String message, String type) {
        this.id = id;
        this.message = message;
        this.type = type;
    }


    public static <T> DataModel  add(String type, T data){
        return new DataModel(type,data);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



}