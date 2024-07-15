package auto.code.refactoring.classes;

import java.util.Date;

public abstract class Customer {
    private Date dateOfBirth;
    private String firstName;
    private String lastName;

    public abstract void setCustomerInfo(String lastName);
}