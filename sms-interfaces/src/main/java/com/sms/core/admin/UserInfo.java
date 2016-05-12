package com.sms.core.admin;

import java.util.Optional;

public class UserInfo{

    private String name;
    private String role;
    private String password;

    public UserInfo() {

    }

    private UserInfo(final Builder builder) {
        this.name = builder.name.get();
        this.role = builder.role.get();
        this.password = builder.password.get();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final User user) {
        return builder()
                .withName(user.getName())
                .withRole(user.getRole().name())
                .withPassword(user.getPassword());
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {

        private Optional<String> name = Optional.empty();
        private Optional<String> role = Optional.empty();
        private Optional<String> password = Optional.empty();

        private Builder() {
            super();
        }

        public Builder withName(final String theName) {
            this.name = Optional.of(theName);
            return this;
        }

        public Builder withRole(final String theRole) {
            this.role = Optional.of(theRole);
            return this;
        }

        public Builder withPassword(final String thePassword) {
            this.password = Optional.of(thePassword);
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }

        protected Builder getThis() {
            return this;
        }
    }
}
