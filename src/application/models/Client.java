package application.models;

public class Client {

    private int id;
    private String name;
    private int revenue;
    private String date;
    private int companyId;

    public Client(int companyId, String name, int revenue, String date) {
        this.companyId = companyId;
        this.name = name;
        this.revenue = revenue;
        this.date = date;
    }

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

    public String getDate() {
        return date;
    }

    public int getCompanyId() {
        return companyId;
    }
}
