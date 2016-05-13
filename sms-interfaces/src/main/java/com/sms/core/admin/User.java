package com.sms.core.admin;

import com.sms.core.BaseModel;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "LG_MA_USER")
public class User extends BaseModel {

    @Column(name = "US_NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "US_ROLE")
    private UserRole role;

    @Column(name = "US_PASSWORD")
    private String password;

    public User() {
        super();
    }

    private User(final Builder builder) {
        //super(builder);
        this.name = builder.name.get();
        this.role = builder.userRole.get();
        this.password = builder.password.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final User user) {

        return builder()
                .withId(user.getId())
                .withRole(user.getRole())
                .withPassword(user.getPassword());
    }

    public String getName() {
        return name;
    }

    public UserRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public static class Builder extends BaseModel.Builder<User, Builder> {

        private Optional<String> name = Optional.empty();
        private Optional<UserRole> userRole = Optional.empty();
        private Optional<String> password = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withName(final String theName) {
            this.name = Optional.of(theName);
            return this;
        }

        public Builder withRole(final UserRole theUserRole) {
            this.userRole = Optional.of(theUserRole);
            return this;
        }

        public Builder withPassword(final String thePassword) {
            this.password = Optional.of(thePassword);
            return this;
        }

        public User build() {
            return new User(this);
        }

        @Override
        protected Builder getThis() {
            return this;
        }
    }

}
