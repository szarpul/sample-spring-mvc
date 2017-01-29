package com.intive.samples.spring.mvc.samples;


public class Client {

    private String firstName;
    private String surname;

    public Client(){
        System.out.println("Init client...");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return String.format("Client: %s %s", firstName, surname);
    }

    public static String convertToString(Client client) {
        return client.firstName + "," + client.surname;
    }

    public static Client convertToObject(String text){
        String[] tab = text.split(",");
        Client client = new Client();
        client.firstName = tab[0];
        client.surname = tab[1];
        return client;
    }
}
