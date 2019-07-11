package beni.simulatorpremi.testing;

import com.google.gson.Gson;

public class Main {

    public static void main(String[] args){
        System.out.println("GSON");

        SerializeUserNested();

    }

    private static void SerializeUserNested(){
        UserAdress address = new UserAdress(
                "Main Street",
                "32",
                "Bandung",
                "Indonesia"
        );
        UserNested userNested = new UserNested(
                "Beni",
                "beni@gmail.com",
                27,
                true,
                address
        );
        String json = new Gson().toJson(userNested);
    }

}
