    package com.example.demo.dto;

    public class UserDto {
        private Long id;
        private String name;
        private String lastName;
        private String email;
        private String phone;
        private String companyName;
        private String roleInSociety;

        //get&set

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRoleInSociety() {
            return roleInSociety;
        }

        public void setRoleInSociety(String roleInSociety) {
            this.roleInSociety = roleInSociety;
        }
    }
