package dto;

import lombok.Value;

public class Dto {

    private Dto(){
    }

    @Value
    public static class User{
        String name;
        String phone;
    }

}
