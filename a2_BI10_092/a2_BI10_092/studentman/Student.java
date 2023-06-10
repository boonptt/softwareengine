package a2_BI10_092.studentman;

import utils.AttrRef;
import utils.DomainConstraint;
import utils.DOpt;
import utils.NotPossibleException;
import utils.OptType;

/**
 * @overview : Student is a person who has information about id, name , phoneNumber, address.
 * @attributes 
 * id 			Integer 
 * name 		String 
 * phoneNumber 	String 
 * address 		String
 * @object Student is <pre> s=<id,name,phoneNumber,address> </pre>, where <pre> id(id), name(name), phoneNumber(phoneNumber), address(address) </pre>.
 * @abstract_properties 
 * mutable(id)=false /\ optional(id)=false /\ min(id)=1 /\max(id)=1000000000 /\ mutable(name)=true /\optional(name)=false /\ length(name)=50
 * mutable(phoneNumber)=true /\ optional(phoneNumber)=false /\ length(phoneNumber)=10 /\ mutable(address)=true /\optional(address)=false /\ length(address)=100
 * @author Nguyen Tuong Quynh
 */

public class Student implements Comparable<Student>{
	
	private static final int MIN_ID = 1;
	private static final int MAX_ID = 1000000000;
	
	@DomainConstraint(type = "Integer", mutable= false, optional = false, min = MIN_ID, max = MAX_ID)
	private int id;
	@DomainConstraint(type = "String", optional = false, length = 50)
	private String name;
	@DomainConstraint(type = "String", optional = false, length = 10)
	private String phoneNumber;
	@DomainConstraint(type = "String", optional = false, length = 100)
	private String address;

	/**
	 * @effects
	 * 		<pre>
	 * 			if id, name, phoneNumber, address are accurate
	 *          	initialise this as Student: <id,name,phoneNumber,address>
	 *          else
	 *              print error message
	 * 		</pre>
	 */
	public Student(@AttrRef("id") int id, @AttrRef("name") String name, @AttrRef("phoneNumber") String phoneNumber, @AttrRef("address") String address) 
	
	throws NotPossibleException {
		if (!validateId(id)) {
			throw new NotPossibleException("Student.init: invalid id: " + id);
		}
		if (!validateName(name)) {
			throw new NotPossibleException("Student.init: invalid name: " + name);
		}
		if (!validatePhoneNumber(phoneNumber)) {
			throw new NotPossibleException("Student.init: invalid phoneNumber: " + phoneNumber);
		}
		if (!validateAddress(address)) {
			throw new NotPossibleException("Student.init: invalid address: " + address);
		}

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
	
	public int getId() {
		return id;
	}

	/**
	 * @effects this.id = id
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("id")
	
	public void setId(int id) {
		if (validateId(id)) {
			this.id = id;
		} else {
			throw new NotPossibleException("Student.init: invalid id: " + id);
		}
	}

	/**
	 * @effects return <tt> name </tt>
	 */
	@DOpt(type = OptType.Observer)
	@AttrRef("name")
	
	public String getName() {
		return name;
	}

	/**
	 * @effects this.name = name
	 */
	@DOpt(type = OptType.Mutator)
	@AttrRef("name")
	
	public void setName(String name) {
		if (validateName(name)) {
			this.name = name;
		} else {
			throw new NotPossibleException("Student.init: invalid name: " + name);
		}
	}

	/**
	 * @effects return <tt> address </tt>
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
		if (validateAddress(address)) {
			this.address = address;
		} else {
			throw new NotPossibleException("Student.init: invalid address: " + address);
		}
	}

	/**
	 * @effects return <tt> phoneNumber </tt>
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
		if (validatePhoneNumber(phoneNumber)) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new NotPossibleException("Student.init: invalid phoneNumber: " + phoneNumber);
		}
	}

	/**
	 * @effects
	 * 		<pre>
	 * 			if this satisfies rep invariant
	 *          	true 
	 *          else
	 *              false
	 * 		</pre>
	 */
	public boolean repOK() {
		return validate(id, name, phoneNumber, address);
	}

	/**
	 * @effects return a chain represent Student
	 */
	@Override
	public String toString() {
		return "Student(" + name + ")";
	}

	/**
	 * @effects
	 * 		<pre>
	 * 			if <i,n,p,a> is accurate
	 *              true
	 *          else
	 *              false
	 * 		</pre>
	 */
	private boolean validate(int i, String n, String p, String a) {
		return validateId(i) && validateName(n) && validatePhoneNumber(p) && validateAddress(a);
	}

	/**
	 * @effects
	 * 		<pre>
	 *      	if id is accurate 
	 *             true 
	 *          else 
	 *             false
	 *          </pre>
	 */

	protected boolean validateId(int id) {
		if (id < MIN_ID || id > MAX_ID) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @effects
	 * 		<pre>
	 *      	if name is accurate 
	 *              true 
	 *          else 
	 *              false
	 * 		</pre>
	 */
	private boolean validateName(String name) {
		return (name != null && name.length() > 0 && name.length() <= 50);

	}

	/**
	 * @effects
	 * 		<pre>
	 *      	if phoneNumber is accurate
	 *              true 
	 *          else 
	 *              false
	 * 		</pre>
	 */

	private boolean validatePhoneNumber(String phoneNumber) {
		return (phoneNumber != null && phoneNumber.length() > 0 && phoneNumber.length() <=10);
	}

	/**
	 * @effects
	 * 		<pre>
	 *      	if address is accurate
	 *              true 
	 *          else 
	 *              false
	 * 		</pre>
	 */

	private boolean validateAddress(String address) {
		return (address != null && address.length() > 0 && address.length() <= 100);
	}

	/**
	 * @effects contrast student name
	 */
	public int compareTo(Student student) throws NullPointerException, ClassCastException {
		if (student == null)
			throw new NullPointerException("Student.compareTo");
		else if (!(student instanceof Student))
			throw new ClassCastException("Student.compareTo: not a Student " + student);
		return this.name.compareTo(student.name);
	}
	/**
	  * @effects 
	  * 	return the HTML form of a Student
	  */

	 
	  public String toHtmlDoc() {
		  StringBuilder Student = new StringBuilder();
		  Student.append("<html>");
		  Student.append("\n");
		  Student.append("<head>");
		  Student.append("<title>");
		  Student.append("UnderGradStudent:" + getId() + "-" + getName());
		  Student.append("</title>");
		  Student.append("</head>");
		  Student.append("\n");
		  Student.append("<body>");
		  Student.append("\n");
		  Student.append(getId() + " " + getName() + " " + getPhoneNumber() + " " + getAddress());
		  Student.append("\n");
		  Student.append("</body>");
		  Student.append("</html>");
  
		  return Student.toString();
	}
}
