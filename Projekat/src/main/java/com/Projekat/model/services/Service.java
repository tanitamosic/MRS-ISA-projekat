package com.Projekat.model.services;

import com.Projekat.model.Address;
import com.Projekat.model.Photo;
import com.Projekat.model.users.ServiceProvider;
import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Entity
@Inheritance(strategy=TABLE_PER_CLASS)
@Where(clause="is_deleted = false")
public abstract class Service {

    private static Integer cut = 10; // between 0 and 100
    public static Integer getCut() {
        return cut;
    }

    public static boolean setCut(Integer newCut) {
        if (newCut > 100 || newCut < 0) {
            return false;
        }
        cut = newCut;
        return true;
    }

    @Id
    @SequenceGenerator(name = "serviceIdSeqGen", sequenceName = "serviceIdSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "serviceIdSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="owner_id")
    private ServiceProvider owner;

    @Column(name="name", unique=false, nullable=false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="address_id")
    private Address address;

    @Column(name="description", unique=false, nullable=false)
    private String description;

    // TODO: pictures
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@PrimaryKeyJoinColumn(name = "service_id",referencedColumnName = "primaryPhoto_id")
    @JoinColumn(name = "photo_id")
    private Photo primaryPhoto;

     @OneToMany(fetch = FetchType.EAGER)
     @JoinColumn(name = "service_id")
     private Set<Photo> photos;

    @Column(name="rules", unique=false)
    private String rules;

    @Column(name="price", unique=false, nullable=false)
    private Double price;

    @Column(name="rating", unique = false, nullable = true)
    private Double rating;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "additionals",
                joinColumns = {@JoinColumn(name = "service_id")},
                inverseJoinColumns = {@JoinColumn(name = "additional_id")}
    )
    Set<AdditionalService> additionalServices;


    @Column(name="cancellation_terms", unique=false, nullable=false)
    private String cancellationTerms;

    @Column(name="availability_start", unique=false, nullable=false)
    private LocalDateTime availabilityStart;

    @Column(name="availability_end", unique=false, nullable=false)
    private LocalDateTime availabilityEnd;

    @Column(name="is_deleted", unique=false, nullable=false)
    private Boolean isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Photo getPrimaryPhoto() {
        return primaryPhoto;
    }

    public void setPrimaryPhoto(Photo primaryPhoto) {
        this.primaryPhoto = primaryPhoto;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<AdditionalService> getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(Set<AdditionalService> additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getCancellationTerms() {
        return cancellationTerms;
    }

    public void setCancellationTerms(String cancellationTerms) {
        this.cancellationTerms = cancellationTerms;
    }

    public LocalDateTime getAvailabilityStart() {
        return availabilityStart;
    }

    public void setAvailabilityStart(LocalDateTime availabilityStart) {
        this.availabilityStart = availabilityStart;
    }

    public LocalDateTime getAvailabilityEnd() {
        return availabilityEnd;
    }

    public void setAvailabilityEnd(LocalDateTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public ServiceProvider getOwner() {
        return owner;
    }

    public void setOwner(ServiceProvider owner) {
        this.owner = owner;
    }
}
