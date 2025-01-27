package ca.jrvs.apps.trading.model.domain;

import java.sql.Date;

public class Trader implements Entity<Integer> {

  //P-key
  private Integer id;
  private String first_name;
  private String last_name;
  private Date dob;
  private String country;
  private String email;

  public Trader() {
  }

  public Trader(String first_name, String last_name, Date dob, String country, String email) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.dob = dob;
    this.country = country;
    this.email = email;
  }

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public Date getDob() {
    return dob;
  }

  public void setDob(Date dob) {
    this.dob = dob;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
