package com.sms.core;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseModel implements Serializable {

    /**
     * <p>
     * Field names constants used in DAOs <br>
     * and Column names constants used in ORM annotations
     * </p>
     */

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5418291380983718474L;

    /**
     * The id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Instantiates a new abstract base model. Required for Hibernate.
     */
    protected BaseModel() {
        super();
    }


    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }


}
