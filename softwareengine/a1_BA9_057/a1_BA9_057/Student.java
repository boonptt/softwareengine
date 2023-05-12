package a1_BA9_057;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @author Pham Tuan Thanh
 * @overview : Student is a person who has information about id, name ,
 * phoneNumber, address
 * @attributes id Integer
 * name String
 * phoneNumber String
 * address String
 * @object A typical Student is <pre>s=<id,name,phoneNumber,address></pre>, where <pre>id(id), name(name),
 * 	 phoneNumber(phoneNumber), address(address)</pre>
 * @abstract_properties mutable(id)=false, optional(id)=false, min(id)=1,max(id)=1000000000,
 * mutable(name)=true,optional(name)=false, length(name)=5,
 * mutable(phoneNumber)=true, optional(phoneNumber)=false, length(phoneNumber)=10,
 * mutable(address)=true,optional(address)=false, length(address)=100
 */

public class Student implements Comparable<Student> {
  @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 1, max = 1000000000)
  private int id;
  @DomainConstraint(type = "String", mutable = true, optional = false, length = 50)
  private String name;
  @DomainConstraint(type = "String", mutable = true, optional = false, length = 10)
  private String phoneNumber;
  @DomainConstraint(type = "String", mutable = true, optional = false, length = 100)
  private String address;

  /**
   * @effects <pre>
   *   if id, name, phoneNumber, address are valid
   *     initialise this as Student:<id,name,phoneNumber,address>
   *   else
   *     print error message
   * </pre>
   */
  public Student(@AttrRef("id") int id, @AttrRef("name") String name,
                 @AttrRef("phoneNumber") String phoneNumber,
                 @AttrRef("address") String address) throws NotPossibleException {
    if (!checkID(id))
      throw new NotPossibleException("Student.init: invalid id: " + id);
    else if (!checkName(name))
      throw new NotPossibleException("Student.init: invalid name: " + name);
    else if (!checkPhoneNumber(phoneNumber))
      throw new NotPossibleException("Student.init: invalid phoneNumber: " + phoneNumber);
    else if (!checkAddress(address))
      throw new NotPossibleException("Student.init: invalid address: " + address);
    this.id = id;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

  /**
   * @effects return <tt>id</tt>
   */
  @DOpt(type = OptType.Observer)
  @AttrRef("id")
  public int getID() {
    return id;
  }

  /**
   * @effects this.id = id
   */
  @DOpt(type = OptType.Mutator)
  @AttrRef("id")
  public void setID(int id) {
    if (!checkID(id)) {
      throw new NotPossibleException("Student.init: invalid id: " + id);
    }
    this.id = id;
  }

  /**
   * @effects return <tt>name</tt>
   */
  @DOpt(type = OptType.Observer)
  @AttrRef("name")
  public String getName() {
    return name;
  }

  /**
   * @effects this.name=name
   */
  @DOpt(type = OptType.Mutator)
  @AttrRef("name")
  public void setName(String name) {
    if (!checkName(name))
      throw new NotPossibleException("Student.init: invalid name: " + name);
    this.name = name;
  }

  /**
   * @effects return <tt>phoneNumber</tt>
   */
  @DOpt(type = OptType.Observer)
  @AttrRef("phoneNumber")
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * @effects this.phoneNumber = phoneNumber
   */
  @DOpt(type = OptType.Mutator)
  @AttrRef("phoneNumber")
  public void setPhoneNumber(String phoneNumber) {
    if (checkPhoneNumber(phoneNumber))
      throw new NotPossibleException("Student.init: invalid phoneNumber: " + phoneNumber);
    this.phoneNumber = phoneNumber;
  }

  /**
   * @effects return <tt>address</tt>
   */
  @DOpt(type = OptType.Observer)
  @AttrRef("address")
  public String getAddress() {
    return address;
  }

  /**
   * @effects this.address = address
   */
  @DOpt(type = OptType.Mutator)
  @AttrRef("address")
  public void setAddress(String address) {
    if (checkAddress(address))
      throw new NotPossibleException("Student.init: invalid address: " + address);
    this.address = address;
  }

  /**
   * @effects <pre>
   * if this satisfies rep invariant
   *   return true
   * else
   *   return false
   *          </pre>
   */
  public boolean repOK() {
    return validate(id, name, phoneNumber, address);
  }

  /**
   * @effects return a string present Student
   */
  @Override
  public String toString() {
    return "Student information: name = " + this.name + ", id = " + this.id + "}";
  }

  /**
   * @effects <pre>
   * if < i,n,p,a > is a valid tuple
   *   return true
   * else
   *   return false
   * </pre>
   */
  private boolean validate(int id, String n, String p, String a) {
    return checkID(id) && checkName(n) && checkPhoneNumber(p) && checkAddress(a);
  }

  /**
   * @effects <pre>
   * if id is a valid id
   *  return true
   * else
   *   return false
   * <pre>
   */

  protected boolean checkID(int id) {
    if (id < 1) {
      return false;
    } else return id <= 1000000000;
  }

  /**
   * @effects <pre>
   * if name is valid
   *   return true
   * else
   *   return false
   *          </pre>
   */
  private boolean checkName(String name) {
    if (name == null) {
      return false;
    } else if (name.length() < 0) {
      return false;
    } else return name.length() <= 50;
  }

  /**
   * @effects <pre>
   * if phoneNumber is valid
   *   return true
   * else
   *   return false
   *</pre>
   */
  private boolean checkPhoneNumber(String phoneNumber) {
    if (phoneNumber == null) {
      return false;
    } else if (phoneNumber.length() < 0) {
      return false;
    } else return phoneNumber.length() <= 10;
  }

  /**
   * @effects <pre>
   * if address is valid
   *   return true
   * else
   *   return false
   *          </pre>
   */

  private boolean checkAddress(String address) {
    if (address == null) {
      return false;
    } else if (address.length() < 0) {
      return false;
    } else return address.length() <= 100;
  }

  /**
   * @effects compare student name
   */
  public int compareTo(Student student) throws NullPointerException, ClassCastException {
    if (student == null)
      throw new NullPointerException("Student.compareTo");
    else if (!(student instanceof Student))
      throw new ClassCastException("Student.compareTo: not a Student " + student);
    return this.name.compareTo(student.name);
  }
}

