package epclaim.tasks;

import java.util.UUID;

import epclaim.compiler.Agent;
import epclaim.compiler.FunctionSignature;
import epclaim.utils.MessageType;

public class Send extends PrimitiveTask {
	String uniqueID;
	String actionActivityName;
	MessageType type;
	String recipient;
	public Send(FunctionSignature functionSignature, MessageType type,String recipient) {
		super(functionSignature);
		this.uniqueID = UUID.randomUUID().toString();
		
		this.type = type;
		this.recipient = recipient;
	}
	
	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ActionMessage [uniqueID=" + uniqueID + ", content=" + message
				+ ", type=" + type + "]";
	}

	
}
