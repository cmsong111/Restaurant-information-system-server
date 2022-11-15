package com.galaxy.Restaurantinformationsystem.Entity;

import jdk.jfr.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import javax.persistence.*;
import javax.print.DocFlavor;
import javax.xml.catalog.Catalog;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@SequenceGenerator(
        name="STORE_SEQ_GENERATOR",
        sequenceName = "STORE_SEQ")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class StoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ_GENERATOR")
    @Column(name = "spk")
    Long SPK;

    String name;

    String call;

    @Column(name = "location_region")
    String locationRegion;

    @Column(name = "location_city")
    String locationCity;

    @Column(name = "location_address1")
    String locationAddress1;

    @Column(name = "web")
    List<String> Web;

    List<String> tag;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;

    @OneToOne
    @JoinColumn(name = "is_admin")
    User isAdmin;

    @OneToMany
    String menu;

}
