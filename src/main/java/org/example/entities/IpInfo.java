package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@ToString
@Data
public class IpInfo {


    @Id
    private String ip;
    private String city;
    private String region;
    private String country;
    private String loc;
    private String org;
    private String postal;
    private String timezone;
    private String readme;

    private Double longitude;
    private Double latitude;


    public void setLong_Lat_time()
    {
        setLatitude(Double.parseDouble(loc.substring(0,loc.indexOf(','))));
        setLongitude(Double.parseDouble(loc.substring(loc.indexOf(',')+1)));
    }

}
