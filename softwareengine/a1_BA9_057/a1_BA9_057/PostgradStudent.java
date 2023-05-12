package a1_BA9_057;
import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview PostgradStudent is a person who has information about id, name ,
 *           phoneNumber, address   
 *               
 * @attributes 
 * 	id 	Integer 
 * 	name String 
 * 	phoneNumber String 
 * 	address String 
 * 	gpa Float
 * 
 * @object A typical PostgradStudent is <pre>s=<id,name,phoneNumber,address></pre>, where <pre>id(id), name(name), 
 * 	 phoneNumber(phoneNumber), address(address), gpa(gpa)</pre>
 * 
 * @abstract_properties
 * mutable(id)=false /\ optional(id)=false /\ min(id)=100000001 /\ max(id)=1000000000/\
 * mutable(name)=true /\ optional(name)=false /\ length(name)=50 /\
 * mutable(phoneNumber)=true /\optional(phoneNumber)=false /\ length(phoneNumber)=10/\
 * mutable(address)=true /\ optional(address)=false /\length(address)=100 /\
 * mutable(gpa)=true /\optional(gpa)=false /\ min(gpa)=0.0 /\ max(gpa)=4.0
 * 
 * @author Pham Tuan Thanh
 */

public class PostgradStudent extends Student {
	@DomainConstraint(type = "Float", mutable=true,optional = false, min = 0.0f, max = 4.0f)
	private float gpa;

	// constructor methods
	/**
	 * @effects
	 * 
	 *          <pre>
	 *            if id, name, phoneNumber, address, gpa are valid
	 *              initialise this as PostgradStudent:<id,name,phoneNumber,address,gpa>
	 *            else
	 *              print error message
	 *          </pre>
	 */
	public PostgradStudent(@AttrRef("id") int id, @AttrRef("name") String name,
			@AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address, @AttrRef("gpa") float gpa)
			throws NotPossibleException {
		super(id, name, phoneNumber, address);
		if (!validateGpa(gpa)) {
			throw new NotPossibleException("PostgradStudent.init: invalid gpa: " + gpa);
		}
		this.gpa = gpa;

	}


	/**
	 * @effects return <tt>gpa</tt>
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
	 * 
	 *          <pre>
	 *            if gpa is a valid gpa
	 *             return true 
	 *            else 
	 *              return false
	 *          </pre>
	 */
	private boolean validateGpa(float gpa) {
		if (gpa < 0.0f) {
			return false;
		} else if (gpa > 4.0f) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * @effects
	 * 
	 *          <pre>
	 *            if id is a valid id
	 *             return true 
	 *            else 
	 *              return false
	 *    
	*/
	
	@Override
	@DomainConstraint(type="Integer",mutable=false,optional=false,min=100000001,max=1000000000)
	protected boolean validateId(int id) {
		if(id<100000001) {
			return false;
		}else if(id>1000000000) {
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * @effects return a string present PostgradStudent
	 */
	@Override
	public String toString() {
		return "PostgradStudent(" + getName() + ")";
	}
	
	/**
	 * @effects
	 * 
	 *          <pre>
	 *            if this satisfies rep invariant
	 *              return true 
	 *            else
	 *              return false
	 *          </pre>
	 */
	public boolean repOK() {
		if(!super.repOK()) {
			return false;
		}
		if(!validateGpa(gpa)) {
			return false;
		}
		return true;
	}

}
