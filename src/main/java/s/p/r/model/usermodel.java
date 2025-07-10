package s.p.r.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat; // Add this import

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class usermodel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;   
    private String prof;
    private String city;

    @DateTimeFormat(pattern = "yyyy-MM-dd") 
    @Temporal(TemporalType.DATE)
    private Date date;
    private String address;
    

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProf() {
        return prof;
    }
    public void setProf(String prof) {
        this.prof = prof;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}