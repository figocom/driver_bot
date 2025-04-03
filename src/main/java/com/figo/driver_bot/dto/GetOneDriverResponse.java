package com.figo.driver_bot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class GetOneDriverResponse {

    @JsonProperty("account")
    private Account account;

    @JsonProperty("person")
    private Person person;

    @JsonProperty("profile")
    private Profile profile;

    @JsonProperty("car_id")
    private String carId;

    @JsonProperty("order_provider")
    private OrderProvider orderProvider;

    public GetOneDriverResponse() {
    }

    public GetOneDriverResponse(Account account, Person person, Profile profile, String carId, OrderProvider orderProvider) {
        this.account = account;
        this.person = person;
        this.profile = profile;
        this.carId = carId;
        this.orderProvider = orderProvider;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public OrderProvider getOrderProvider() {
        return orderProvider;
    }

    public void setOrderProvider(OrderProvider orderProvider) {
        this.orderProvider = orderProvider;
    }

    @Override
    public String toString() {
        return "GetOneDriverResponse{" +
                "account=" + account +
                ", person=" + person +
                ", profile=" + profile +
                ", carId='" + carId + '\'' +
                ", orderProvider=" + orderProvider +
                '}';
    }

    public static class Person {
        @JsonProperty("full_name")
        private FullName fullName;

        @JsonProperty("contact_info")
        private ContactInfo contactInfo;

        @JsonProperty("driver_license")
        private DriverLicense driverLicense;

        @JsonProperty("driver_license_experience")
        private DriverLicenseExperience driverLicenseExperience;

        @JsonProperty("tax_identification_number")
        private String taxIdentificationNumber;

        @JsonProperty("employment_type")
        private String employmentType;

        public Person() {
        }

        public Person(FullName fullName, ContactInfo contactInfo, DriverLicense driverLicense, DriverLicenseExperience driverLicenseExperience, String taxIdentificationNumber, String employmentType) {
            this.fullName = fullName;
            this.contactInfo = contactInfo;
            this.driverLicense = driverLicense;
            this.driverLicenseExperience = driverLicenseExperience;
            this.taxIdentificationNumber = taxIdentificationNumber;
            this.employmentType = employmentType;
        }

        public FullName getFullName() {
            return fullName;
        }

        public void setFullName(FullName fullName) {
            this.fullName = fullName;
        }

        public ContactInfo getContactInfo() {
            return contactInfo;
        }

        public void setContactInfo(ContactInfo contactInfo) {
            this.contactInfo = contactInfo;
        }

        public DriverLicense getDriverLicense() {
            return driverLicense;
        }

        public void setDriverLicense(DriverLicense driverLicense) {
            this.driverLicense = driverLicense;
        }

        public DriverLicenseExperience getDriverLicenseExperience() {
            return driverLicenseExperience;
        }

        public void setDriverLicenseExperience(DriverLicenseExperience driverLicenseExperience) {
            this.driverLicenseExperience = driverLicenseExperience;
        }

        public String getTaxIdentificationNumber() {
            return taxIdentificationNumber;
        }

        public void setTaxIdentificationNumber(String taxIdentificationNumber) {
            this.taxIdentificationNumber = taxIdentificationNumber;
        }

        public String getEmploymentType() {
            return employmentType;
        }

        public void setEmploymentType(String employmentType) {
            this.employmentType = employmentType;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "fullName=" + fullName +
                    ", contactInfo=" + contactInfo +
                    ", driverLicense=" + driverLicense +
                    ", driverLicenseExperience=" + driverLicenseExperience +
                    ", taxIdentificationNumber='" + taxIdentificationNumber + '\'' +
                    ", employmentType='" + employmentType + '\'' +
                    '}';
        }
    }

    public static class FullName {
        @JsonProperty("first_name")
        private String firstName;

        @JsonProperty("last_name")
        private String lastName;

        public FullName() {
        }

        public FullName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "FullName{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }

    }

    public static class ContactInfo {
        @JsonProperty("phone")
        private String phone;

        public ContactInfo() {
        }

        public ContactInfo(String phone) {
            this.phone = phone;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "ContactInfo{" +
                    "phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class DriverLicense {
        @JsonProperty("country")
        private String country;

        @JsonProperty("expiry_date")
        private String expiryDate;

        @JsonProperty("issue_date")
        private String issueDate;

        @JsonProperty("number")
        private String number;

        public DriverLicense() {
        }

        public DriverLicense(String country, String expiryDate, String issueDate, String number) {
            this.country = country;
            this.expiryDate = expiryDate;
            this.issueDate = issueDate;
            this.number = number;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

        public String getIssueDate() {
            return issueDate;
        }

        public void setIssueDate(String issueDate) {
            this.issueDate = issueDate;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "DriverLicense{" +
                    "country='" + country + '\'' +
                    ", expiryDate=" + expiryDate +
                    ", issueDate=" + issueDate +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

    public static class DriverLicenseExperience {
        @JsonProperty("total_since_date")
        private String totalSinceDate;

        public DriverLicenseExperience() {
        }

        public DriverLicenseExperience(String totalSinceDate) {
            this.totalSinceDate = totalSinceDate;
        }

        public String getTotalSinceDate() {
            return totalSinceDate;
        }

        public void setTotalSinceDate(String totalSinceDate) {
            this.totalSinceDate = totalSinceDate;
        }

        @Override
        public String toString() {
            return "DriverLicenseExperience{" +
                    "totalSinceDate=" + totalSinceDate +
                    '}';
        }
    }


    public static class Profile {
        @JsonProperty("hire_date")
        private String hireDate;

        @JsonProperty("work_status")
        private String workStatus;

        public Profile() {
        }

        public Profile(String hireDate, String workStatus) {
            this.hireDate = hireDate;
            this.workStatus = workStatus;
        }

        public String getHireDate() {
            return hireDate;
        }

        public void setHireDate(String hireDate) {
            this.hireDate = hireDate;
        }

        public String getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(String workStatus) {
            this.workStatus = workStatus;
        }

        @Override
        public String toString() {
            return "Profile{" +
                    "hireDate=" + hireDate +
                    ", workStatus='" + workStatus + '\'' +
                    '}';
        }
    }


    public static class Account {
        @JsonProperty("balance_limit")
        private String balanceLimit;

        @JsonProperty("work_rule_id")
        private String workRuleId;

        @JsonProperty("payment_service_id")
        private String paymentServiceId;

        @JsonProperty("block_orders_on_balance_below_limit")
        private boolean blockOrdersOnBalanceBelowLimit;

        public Account() {
        }

        public Account(String balanceLimit, String workRuleId, String paymentServiceId, boolean blockOrdersOnBalanceBelowLimit) {
            this.balanceLimit = balanceLimit;
            this.workRuleId = workRuleId;
            this.paymentServiceId = paymentServiceId;
            this.blockOrdersOnBalanceBelowLimit = blockOrdersOnBalanceBelowLimit;
        }

        public String getBalanceLimit() {
            return balanceLimit;
        }

        public void setBalanceLimit(String balanceLimit) {
            this.balanceLimit = balanceLimit;
        }

        public String getWorkRuleId() {
            return workRuleId;
        }

        public void setWorkRuleId(String workRuleId) {
            this.workRuleId = workRuleId;
        }

        public String getPaymentServiceId() {
            return paymentServiceId;
        }

        public void setPaymentServiceId(String paymentServiceId) {
            this.paymentServiceId = paymentServiceId;
        }

        public boolean isBlockOrdersOnBalanceBelowLimit() {
            return blockOrdersOnBalanceBelowLimit;
        }

        public void setBlockOrdersOnBalanceBelowLimit(boolean blockOrdersOnBalanceBelowLimit) {
            this.blockOrdersOnBalanceBelowLimit = blockOrdersOnBalanceBelowLimit;
        }

        @Override
        public String toString() {
            return "Account{" +
                    "balanceLimit='" + balanceLimit + '\'' +
                    ", workRuleId='" + workRuleId + '\'' +
                    ", paymentServiceId='" + paymentServiceId + '\'' +
                    ", blockOrdersOnBalanceBelowLimit=" + blockOrdersOnBalanceBelowLimit +
                    '}';
        }
    }

    public static class OrderProvider {
        @JsonProperty("platform")
        private boolean platform;

        @JsonProperty("partner")
        private boolean partner;

        public OrderProvider() {
        }

        public OrderProvider(boolean platform, boolean partner) {
            this.platform = platform;
            this.partner = partner;
        }

        public boolean isPlatform() {
            return platform;
        }

        public void setPlatform(boolean platform) {
            this.platform = platform;
        }

        public boolean isPartner() {
            return partner;
        }

        public void setPartner(boolean partner) {
            this.partner = partner;
        }

        @Override
        public String toString() {
            return "OrderProvider{" +
                    "platform=" + platform +
                    ", partner=" + partner +
                    '}';
        }
    }
}
