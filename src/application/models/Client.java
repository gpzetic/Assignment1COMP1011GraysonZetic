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

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setName(String name) {
        if (name.length() <= 1) {
            throw new IllegalArgumentException("Name is too short");
        }
        this.name = name;
    }

    public void setRevenue(int revenue) {
        if (revenue < 0) {
            throw new IllegalArgumentException("Revenue cannot be negative");
        }
        this.revenue = revenue;
    }

    public void setDate(Date date) {
        Date currentDate = new Date();
        if (date.after(currentDate)) {
            throw new IllegalArgumentException(
                "Hire date cannot be in the future."
            );
        }
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
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
