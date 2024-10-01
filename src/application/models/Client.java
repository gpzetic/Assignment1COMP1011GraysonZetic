package application.models;

public class Client {

    private int id;
    private String name;
    private int revenue;

    public Client(int id, String name, int revenue) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRevenue() {
        return revenue;
    }
}
