
package com.educacionit.hibernate.beginners.entity;


import javax.persistence.*;


@Entity (name = "Owner")
@Table (name="owner")
@AttributeOverrides ({
        @AttributeOverride (name = "firstName", column = @Column (name="per_first_name")),
        @AttributeOverride (name = "lastName",  column = @Column (name="per_last_name"))
})
public class OwnerAnnotation extends Person2Annotation {


    @Column (name="stocks")
    private Integer stocks;

    @Column (name="partnership_stake")
    private Integer partnershipStake;

    public OwnerAnnotation () {

        super ();
    }

    public OwnerAnnotation (String firstName, String lastName, Integer stocks,
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