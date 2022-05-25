public class StudentModel {
    private String pass;
    private String firstName;
    private String lastName;
    private String email;
    private String field;
    private Long idNumber;
    private Integer yearOfBirth;
    private Integer monthOfBirth;
    private Integer dayOfBirth;

    public StudentModel() {

    }

    public StudentModel(String firstName,
                        String lastName,
                        String email,
                        String field,
                        Integer yearOfBirth,
                        Integer monthOfBirth,
                        Integer dayOfBirth,
                        Long idNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.field = field;
        this.yearOfBirth = yearOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.dayOfBirth = dayOfBirth;
        this.idNumber = idNumber;
    }

    public String getPass() {
        return pass;
    }

    public String setPass() {
        idGenerator id = new idGenerator();
        this.pass = id.generate();
        return id.generate();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Long getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(Long idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Integer getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(Integer monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public Integer getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Integer dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
