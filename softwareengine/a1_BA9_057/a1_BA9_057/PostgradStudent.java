package a1_BA9_057;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @author Pham Tuan Thanh
 * @overview PostgradStudent is a person who has information about id, name ,
 * phoneNumber, address
 * @attributes id Integer
 * name String
 * phoneNumber String
 * address String
 * gpa Float
 * @object A typical PostgradStudent is
 *
 * <pre>
 * s=<id,name,phoneNumber,address>
 * </pre>
 * <p>
 * , where
 * <pre>
 * id(id), name(name),
 * 	 phoneNumber(phoneNumber), address(address), gpa(gpa)
 * </pre>
 * @abstract_properties mutable(id)=false, optional(id)=false ,
 * min(id)=100000001, max(id)=1000000000,
 * mutable(name)=true, optional(name)=false,
 * length(name)=50, mutable(phoneNumber)=true, optional(phoneNumber)=false
 * length(phoneNumber)=10, mutable(address)=true, optional(address)=false
 * length(address)=100, mutable(gpa)=true, optional(gpa)=false, min(gpa)=0.0
 * max(gpa)=4.0
 */
public class PostgradStudent extends Student {

  @DomainConstraint(type = "Float", mutable = true, optional = false, min = 0.0f, max = 4.0f)
  private float gpa;

  /**
   * @effects <pre>
   * if id, name, phoneNumber, address, gpa are valid
   *   initialise this as PostgradStudent:<id,name,phoneNumber,address,gpa>
   * else
   *   print error message
   * </pre>
   */
  public PostgradStudent(@AttrRef("id") int id, @AttrRef("name") String name,
                         @AttrRef("phoneNumber") String phoneNumber,
                         @AttrRef("address") String address,
                         @AttrRef("gpa") float gpa) {
    super(id, name, phoneNumber, address);
    if (!checkGPA(gpa))
      throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
    this.gpa = gpa;
  }

  /**
   * @effects return <tt>gpa</tt>
   */
  @DOpt(type = OptType.Observer)
  @AttrRef("gpa")
  public float getGPA() {
    return gpa;
  }

  /**
   * @effects this.gpa=gpa
   */
  @DOpt(type = OptType.Mutator)
  @AttrRef("gpa")
  public void setGPA(float gpa) {
    if (checkGPA(gpa)) {
      this.gpa = gpa;
    } else {
      throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
    }
  }

  /**
   * @effects <pre>
   * if gpa is a valid gpa
   *  return true
   * else
   *   return false
   * </pre>
   */
  private boolean checkGPA(float val) {
    if (val < 0.0f) {
      return false;
    } else return !(val > 4.0f);
  }

  /**
   * @effects if id is a valid id
   * return true
   * else
   * return false
   */
  @Override
  @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 100000001, max = 1000000000)
  protected boolean checkID(int id) {
    if (id < 100000001) {
      return false;
    } else return id <= 1000000000;
  }

  /**
   * @effects return a string present PostgradStudent
   */
  @Override
  public String toString() {
    return "Post graduate student name: {" + getName() + "}";
  }

  /**
   * @effects if this satisfies rep invariant
   * return true
   * else
   * return false
   */
  public boolean repOK() {
    if (!super.repOK()) {
      return false;
    }
    return checkGPA(gpa);
  }
}
