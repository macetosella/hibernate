
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@Entity (name = "Owner2")
@Table (name="owner2")
@PrimaryKeyJoinColumn (name="per_person_id")
public class Owner2Annotation extends Person3Annotation {


    @Column (name="stocks")
    private Integer stocks;

    @Column (name="partnership_stake")
    private Integer partnershipStake;

    public Owner2Annotation () {

        super ();
    }

    public Owner2Annotation (String firstName, String lastName, Integer stocks,
                             Integer partnershipStake) {

        super (firstName, lastName);

        this.stocks = stocks;
        this.partnershipStake = partnershipStake;
    }


    public Integer getStocks () {

        return this.stocks;
    }

    public void setStocks (Integer stocks) {

        this.stocks = stocks;
    }

    public Integer getPartnershipStake () {

        return this.partnershipStake;
    }

    public void setPartnershipStake (Integer partnershipStake) {

        this.partnershipStake = partnershipStake;
    }
}