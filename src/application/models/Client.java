package application.models;

import java.util.Date;

/**
 * Represents a client in the system.
 * This class holds information about a client including their ID, name, revenue,
 * associated date, and company ID.
 */

public class Client {

    private int id;
    private String name;
    private int revenue;
    private String date;
    private int companyId;

    /**
     * Constructs a new Client with specified company ID, name, revenue, and date.
     *
     * @param companyId The ID of the company associated with this client
     * @param name The name of the client
     * @param revenue The revenue generated by the client
     * @param date The date associated with this client record
     */

    public Client(int companyId, String name, int revenue, String date) {
        this.companyId = companyId;
        this.name = name;
        this.revenue = revenue;
        this.date = date;
    }

    /**
     * Constructs a new Client with specified ID, name, and revenue.
     *
     * @param id The unique identifier for the client
     * @param name The name of the client
     * @param revenue The revenue generated by the client
     */

    public Client(int id, String name, int revenue) {
        this.id = id;
        this.name = name;
        this.revenue = revenue;
    }

    /**
     * Sets the company ID for this client.
     *
     * @param companyId The new company ID to set
     */

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    /**
     * Sets the name of the client.
     *
     * @param name The new name to set for the client
     * @throws IllegalArgumentException if the name is 1 character or less in length
     */

    public void setName(String name) {
        if (name.length() <= 1) {
            throw new IllegalArgumentException("Name is too short");
        }
        this.name = name;
    }

    /**
     * Sets the revenue for this client.
     *
     * @param revenue The new revenue to set for the client
     * @throws IllegalArgumentException if the revenue is negative
     */

    public void setRevenue(int revenue) {
        if (revenue < 0) {
            throw new IllegalArgumentException("Revenue cannot be negative");
        }
        this.revenue = revenue;
    }

    /**
     * Sets the date for this client.
     *
     * @param date The new date to set for the client
     * @throws IllegalArgumentException if the provided date is in the future
     */

    public void setDate(Date date) {
        Date currentDate = new Date();
        if (date.after(currentDate)) {
            throw new IllegalArgumentException(
                "Hire date cannot be in the future."
            );
        }
        this.date = String.valueOf(date);
    }

    /**
     * Sets the ID for this client.
     *
     * @param id The new ID to set for the client
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of this client.
     *
     * @return The unique identifier of the client
     */

    public int getId() {
        return id;
    }

    /**
     * Gets the name of this client.
     *
     * @return The name of the client
     */

    public String getName() {
        return name;
    }

    /**
     * Gets the revenue of this client.
     *
     * @return The revenue generated by the client
     */

    public int getRevenue() {
        return revenue;
    }

    /**
     * Gets the date associated with this client record.
     *
     * @return The date of the client record as a String
     */

    public String getDate() {
        return date;
    }

    /**
     * Gets the company ID associated with this client.
     *
     * @return The ID of the company associated with the client
     */

    public int getCompanyId() {
        return companyId;
    }
}
