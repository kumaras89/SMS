package com.sms.core.admin;

import java.util.Optional;

public class UserInfo{

	private Long id;
	private String firstName;
	private String lastName;
    private String name;
    private String role;
    private String branch;

    public String getBranch() {
        return branch;
    }

    public UserInfo() {

    }

    private UserInfo(final Builder builder) {
    	this.id = builder.id.orElse(null);
        this.name = builder.name.get();
        this.role = builder.role.get();
        this.firstName = builder.firstName.get();
    	this.lastName = builder.lastName.get();
        this.branch = builder.branch.get();
        
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder toBuilder(final User user) {
        return builder()
                .withName(user.getName())
                .withRole(user.getRole().name())
                .withBranch(user.getBranch())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withId(user.getId());

    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }    
    
    public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}


	public static class Builder {

    	private Optional<Long> id = Optional.empty();
    	private Optional<String> firstName = Optional.empty();
    	private Optional<String> lastName = Optional.empty();
    	private Optional<String> name = Optional.empty();
        private Optional<String> role = Optional.empty();
        private Optional<String> branch = Optional.empty();
        

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

        public Builder withId(Long theId) {
            this.id = Optional.of(theId);
            return this;
        }

        public Builder withFirstName(String theFirstName) {
            this.firstName = Optional.of(theFirstName);
            return this;
        }

        public Builder withLastName(String theLastName) {
            this.lastName = Optional.of(theLastName);
            return this;
        }

        public Builder withBranch(String theBranch) {
            this.branch = Optional.of(theBranch);
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
