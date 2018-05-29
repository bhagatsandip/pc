package in.planetclubs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "oldMembershipType",
    "newMembershipType",
    "transactionType",
    "operationType"
})
@XmlRootElement(name = "MembershipTransanctionType")
public class MembershipTransanctionType {
	
	 @XmlElement(name = "oldMembership")
    protected MembershipType oldMembershipType;

    @XmlElement(name = "newMembership")
    protected MembershipType newMembershipType;
    
    @XmlElement(name = "transactionType")
    protected TransactionType transactionType;
    
    @XmlElement(name = "operationType")
    protected String operationType;
    
    

	public MembershipType getOldMembershipType() {
		return oldMembershipType;
	}

	public void setOldMembershipType(MembershipType oldMembershipType) {
		this.oldMembershipType = oldMembershipType;
	}

	public MembershipType getNewMembershipType() {
		return newMembershipType;
	}

	public void setNewMembershipType(MembershipType newMembershipType) {
		this.newMembershipType = newMembershipType;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	
	
	    

}
