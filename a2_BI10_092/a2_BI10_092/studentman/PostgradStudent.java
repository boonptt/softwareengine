package a2_BI10_092.studentman;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview PostgradStudent is a person who study in higher education(Master, Ph.D, etc.), has information about id, name , phoneNumber, address       
 * @attributes 
 * 	id 			Integer 
 * 	name		String 
 * 	phoneNumber String 
 * 	address 	String 
 * 	gpa 		Float
 * @object PostgradStudent is <pre> s=<id,name,phoneNumber,address> </pre>, where <pre> id(id), name(name), phoneNumber(phoneNumber), address(address), gpa(gpa) </pre>
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=100000001 /\ max(id)=1000000000 /\mutable(name)=true /\ optional(name)=false /\ length(name)=50 
 * mutable(phoneNumber)=true /\optional(phoneNumber)=false /\ length(phoneNumber)=10 /\ mutable(address)=true /\ optional(address)=false /\length(address)=100 /\ mutable(gpa)=true /\optional(gpa)=false /\ min(gpa)=0.0 /\ max(gpa)=4.0
 * @author Nguyen Tuong Quynh 
 */

public class PostgradStudent extends Student {

	private static final int MIN_ID = 100000001;
	private static final int MAX_ID = 1000000000;
	
	@DomainConstraint(type = "Float", optional = false, min = 0.0F, max = 4.0F)
	private float gpa;

	// constructor methods
	
	/**
	 * @effects
	 * 		<pre>
	 *       	if id, name, phoneNumber, address, gpa are accurate
	 *              initialise this as PostgradStudent: <id,name,phoneNumber,address,gpa>
	 *          else
	 *              print error message
	 * 		</pre>
	 */

	public PostgradStudent(@AttrRef("id") int id, @AttrRef("name") String name,@AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address, @AttrRef("gpa") float gpa)
	
	throws NotPossibleException {
		super(id, name, phoneNumber, address);
		if (!validateGpa(gpa)) {
			throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
		}
		this.gpa = gpa;
	}

	/**
	 * @effects return <tt> gpa </tt>
	 */

	@DOpt(type = OptType.Observer)
	@AttrRef("gpa")
	public float getGpa() {
		return gpa;
	}

	/**
	 * @effects this.gpa=gpa
	 */

	@DOpt(type = OptType.Mutator)
	@AttrRef("gpa")
	public void setGpa(float gpa) {
		if (validateGpa(gpa)) {
			this.gpa = gpa;
		} else {
			throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
		}
	}

	/**
	 * @effects
	 * 		<pre>
	 *      	if gpa is accurate 
	 *             true 
	 *          else 
	 *              false
	 * 		</pre>
	 */

	private boolean validateGpa(float gpa) {
		if (gpa < 0.0f || gpa > 4.0f) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * @effects
	 * 		<pre>
	 *       	if id is accurate
	 *             true 
	 *          else 
	 *             false
	 * 		</pre?
	*/
	
	@Override
	@DomainConstraint(type = "Integer", mutable = false, optional = false, min = MIN_ID, max = MAX_ID)
	protected boolean validateId(int id) {
		return (id >= MIN_ID && id <= MAX_ID);
	}
	
	/**
	 * @effects return a chain represent PostgradStudent
	 */
	@Override
	public String toString() {
		return "PostgradStudent(" + getName() + ")";
	}
	
	/**
	 * @effects
	 * 		<pre>
	 *      	if this satisfies rep invariant
	 *              true 
	 *          else
	 *              false
	 * 		</pre>
	 */

	public boolean repOK() {
		if(!super.repOK() || !validateGpa(gpa)) {
			return false;
		} else {
			return true;
		}
	}


	 /**
	  * @effects 
	  * 	return the HTML form of a PostgradStudent
	  */
	 @Override 
	 public String toHtmlDoc() {
		StringBuilder PostgradStudent = new StringBuilder();
		PostgradStudent.append("<html>");
		PostgradStudent.append("\n");
		PostgradStudent.append("<head>");
		PostgradStudent.append("<title>");
		PostgradStudent.append("UnderGradStudent:" + getId() + "-" + getName());
		PostgradStudent.append("</title>");
		PostgradStudent.append("</head>");
		PostgradStudent.append("\n");
		PostgradStudent.append("<body>");
		PostgradStudent.append("\n");
		PostgradStudent.append(getId() + " " + getName() + " " + getPhoneNumber() + " " + getAddress());
		PostgradStudent.append("\n");
		PostgradStudent.append("</body>");
		PostgradStudent.append("</html>");

		return PostgradStudent.toString();
	 }
	 
}

