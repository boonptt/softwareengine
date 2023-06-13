package a2_BA9_057.studentman;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview : UndergradStudent is a person who study in high school, has information about id, name , phoneNumber, address.
 * @attributes 
 * id 			Integer 
 * name 		String 
 * phoneNumber 	String 
 * address 		String
 * @object UndergradStudent is <pre> s=<id,name,phoneNumber,address> </pre>, where <pre> id(id), name(name), phoneNumber(phoneNumber), address(address) </pre>
 * @abstract_properties 
 * mutable(id)=false /\ optional(id)=false /\ min(id)=100000 /\ max(id)=100000000 /\ mutable(name)=true /\ optional(name)=false /\ length(name)=50
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\ mutable(address)=true /\ optional(address)=false /\ length(address)=100
 */

public class UndergradStudent extends Student {

	private static final int MIN_ID = 100000;
	private static final int MAX_ID = 100000000;
	
	// constructor methods
	
	/**
	 * @effects
	 *          <pre>
	 *            if id, name, phoneNumber, address are accurate
	 *              take this as UndergradStudent: <id,name,phoneNumber,address>
	 *            else
	 *              print message is error
	 *          </pre>
	 */

	public UndergradStudent(@AttrRef("id") int id, @AttrRef("name") String name, @AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address)
	
	throws NotPossibleException {
		super(id, name, phoneNumber, address);
	}

	/**
	 * @effects
	 *<pre>
	 *  if id is excatly
	 *   true
	 *  else
	 *   false
	 *</pre>
	 *    
	*/
	
	@Override
	@DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
	protected boolean validateId(int id) {
		return (id >= MIN_ID && id <= MAX_ID);
	}

	/**
	 * @effects return a chain represent UndergradStudent
	 */

	@Override
	public String toString() {
		return "UndergraduateStudent(" + getName() + ")";
	}  

	/**
	  * @effects 
	  * 	return the HTML form of a UndergraduateStudent
	  */
	@Override
	public String toHtmlDoc() {
			StringBuilder UndergradStudent = new StringBuilder();
			UndergradStudent.append("<html>");
			UndergradStudent.append("\n");
			UndergradStudent.append("<head>");
			UndergradStudent.append("<title>");
			UndergradStudent.append("UnderGradStudent:" + getId() + "-" + getName());
			UndergradStudent.append("</title>");
			UndergradStudent.append("</head>");
			UndergradStudent.append("\n");
			UndergradStudent.append("<body>");
			UndergradStudent.append("\n");
			UndergradStudent.append(getId() + " " + getName() + " " + getPhoneNumber() + " " + getAddress());
			UndergradStudent.append("\n");
			UndergradStudent.append("</body>");
			UndergradStudent.append("</html>");
	
			return UndergradStudent.toString();
	}
}
