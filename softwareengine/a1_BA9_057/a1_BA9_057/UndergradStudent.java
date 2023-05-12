package a1_BA9_057;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @author Pham Tuan Thanh
 * @overview : UndergradStudent is a person who has information about id, name ,
 * phoneNumber, address
 * @attributes id Integer
 * name String
 * phoneNumber String
 * address String
 * @object A typical UndergradStudent is <pre>s=<id,name,phoneNumber,address></pre>, where <pre>id(id), name(name),
 * 	 phoneNumber(phoneNumber), address(address)</pre>
 * @abstract_properties mutable(id)=false, optional(id)=false, min(id)=100000, max(id)=10000000,
 * mutable(name)=true, optional(name)=false, length(name)=,0
 * mutable(phoneNumber)=true, optional(phoneNumber)=false, length(phoneNumber)=1,
 * mutable(address)=true, optional(address)=false, length(address)=100
 */

public class UndergradStudent extends Student {
  // constructor methods

  /**
   * @effects <pre>
   *   if id, name, phoneNumber, address are valid
   *     initialise this as UndergradStudent:<id,name,phoneNumber,address>
   *   else
   *     print error message
   * </pre>
   */
  public UndergradStudent(@AttrRef("id") int id, @AttrRef("name") String name,
                          @AttrRef("phoneNumber") String phoneNumber,
                          @AttrRef("address") String address)
    throws NotPossibleException {
    super(id, name, phoneNumber, address);
  }

  /**
   * @effects: <pre>
   * if id is a valid id
   *  return true
   * else
   *   return false
   */
  @Override
  @DomainConstraint(type = "Integer", mutable = false, optional = false, min = 100000, max = 100000000)
  protected boolean checkID(int id) {
    if (id < 100000) {
      return false;
    } else return id <= 100000000;
  }

  /**
   * @effects return a string present UndergradStudent
   */
  @Override
  public String toString() {
    return "UndergradStudent information: name = " + getName();
  }
}
