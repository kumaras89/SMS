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
    private Long id;

    /**
     * Instantiates a new abstract base model. Required for Hibernate.
     */
    protected BaseModel() {
        super();
    }

    protected BaseModel(final Builder<?, ?> builder) {
        this.id = builder.id.orElse(null);
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    public abstract static class Builder<E extends BaseModel, B extends Builder<E, B>> {

        private Optional<Long> id = Optional.empty();

        protected Builder() {
            super();
        }

        protected Builder(final BaseModel aModel) {
            this.id = Optional.of(aModel.id);
        }

        public B withId(final Long aId) {
            this.id = Optional.ofNullable(aId);
            return getThis();
        }

        public abstract E build();

        protected abstract B getThis();

    }
}
