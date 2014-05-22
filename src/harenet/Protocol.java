/*
 * see license.txt 
 */
package harenet;

import harenet.messages.NetMessageFactory;


/**
 * Simple base Harenet protocol header.  Each UDP packet will contain
 * this protocol header information.  This hold information such as
 * the protocol ID, version, peerId and sequencing mechanisms.
 * 
 * @author Tony
 *
 */
public class Protocol implements Transmittable {

	/**
	 * Protocol Id -- helps filter out garbage packets
	 */
	public static final short PROTOCOL_ID = 0x1e01;
	
	/* serves as a quick filter and version# */
	private short protocolId;
	
	/* what peer this belongs to */
	private byte peerId;
	
	/* number of messages in this packet */
	private byte numberOfMessages;
	
	/* time the packet was sent */
//	private int sentTime;
	
	/* the packet number */
	private int sendSequence;
	
	/* the last acknowledged packet number */
	private int acknowledge;
	
	/* the last 32 acknowledges */
	private int ackHistory;

	/**
	 * 
	 */
	public Protocol() {
		reset();
	}
	
	/**
	 * @return true if the protocol ID matches
	 */
	public boolean isValid() {
		return this.protocolId == PROTOCOL_ID;
	}
	
	/**
	 * Makes the protocol Id valid
	 */
	public void makeValid() {
		this.protocolId = PROTOCOL_ID;
	}
	
	/**
	 * Resets to initial state
	 */
	public void reset() {
		this.protocolId = 0;
		this.peerId = Host.INVALID_PEER_ID;
		this.numberOfMessages = 0;
//		this.sentTime = 0;
		this.sendSequence = 0;
		this.acknowledge = 0;
		this.ackHistory = 0;
	}
	
	/**
	 * @return the number of bytes the protocol header takes
	 */
	public int size() {
		return 2 + // protocolId
			   1 + // peerdId
			   1 + // numberOf messages
//			   4 + // send time
			   4 + // send Sequence
			   4 + // acknowledge
			   4   // ackHistory
			   ;
	}
	
	/**
	 * @param sendSequence the sendSequence to set
	 */
	public void setSendSequence(int sendSequence) {
		this.sendSequence = sendSequence;
	}
	
	/**
	 * @param acknowledge the acknowledge to set
	 */
	public void setAcknowledge(int acknowledge) {
		this.acknowledge = acknowledge;
	}
	
	/**
	 * @param ackHistory the ackHistory to set
	 */
	public void setAckHistory(int ackHistory) {
		this.ackHistory = ackHistory;
	}
	
	/**
	 * @param numberOfMessages the numberOfMessages to set
	 */
	public void setNumberOfMessages(byte numberOfMessages) {
		this.numberOfMessages = numberOfMessages;
	}
	
	/**
	 * @param sentTime the sentTime to set
	 */
//	public void setSentTime(int sentTime) {
//		this.sentTime = sentTime;
//	}
	
	/**
	 * @param peerId the peerId to set
	 */
	public void setPeerId(byte peerId) {
		this.peerId = peerId;
	}
	
	/**
	 * @return the peerId
	 */
	public byte getPeerId() {
		return peerId;
	}
	
	/**
	 * @return the sentTime
	 */
//	public int getSentTime() {
//		return sentTime;
//	}
	
	/**
	 * @return the numberOfMessages
	 */
	public byte getNumberOfMessages() {
		return numberOfMessages;
	}
	
	/**
	 * @return the ackHistory
	 */
	public int getAckHistory() {
		return ackHistory;
	}
	
	/**
	 * @return the acknowledge
	 */
	public int getAcknowledge() {
		return acknowledge;
	}
	
	/**
	 * @return the sendSequence
	 */
	public int getSendSequence() {
		return sendSequence;
	}
	
	/*
	 * (non-Javadoc)
	 * @see harenet.Transmittable#readFrom(harenet.IOBuffer, harenet.messages.NetMessageFactory)
	 */
	@Override
	public void readFrom(IOBuffer buffer, NetMessageFactory messageFactory) {
		this.protocolId = buffer.getShort();
		this.peerId = buffer.get();
		this.numberOfMessages = buffer.get();
//		this.sentTime = buffer.getInt();
		this.sendSequence = buffer.getInt();
		this.acknowledge = buffer.getInt();
		this.ackHistory = buffer.getInt();
	}
	
	/* (non-Javadoc)
	 * @see netspark.Transmittable#writeTo(java.nio.ByteBuffer)
	 */
	@Override
	public void writeTo(IOBuffer buffer) {
		buffer.putShort(PROTOCOL_ID);
		buffer.put(this.peerId);
		buffer.put(this.numberOfMessages);
//		buffer.putInt(this.sentTime);
		
		buffer.putInt(this.sendSequence);
		buffer.putInt(this.acknowledge);
		buffer.putInt(this.ackHistory);
	}	
}
