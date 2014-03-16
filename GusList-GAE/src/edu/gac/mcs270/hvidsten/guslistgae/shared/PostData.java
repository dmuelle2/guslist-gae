/*
 * PostData class.  Represents a posted ad. 
 */
package edu.gac.mcs270.hvidsten.guslistgae.shared;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class PostData implements Serializable {
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	// Need to define the key this way so that a Seller can be passed
	// as data through RPC services.   
	// See https://developers.google.com/appengine/docs/java/datastore/jdo/creatinggettinganddeletingdata#Keys
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key = "gae.encoded-pk", value = "true")
	private String id;
	
	@Persistent
	private String title="no title";
	@Persistent
	private String description="empty";
	@Persistent
	private double price=0.0;
	@Persistent
	private String address="empty";
	@Persistent
	private String URL;
	
	// Need to define the Seller and Buyer as "Unowned" child objects, 
	//  as they do not disappear when PostData object is deleted.  
	@Persistent
    @Unowned
	private Seller seller;
	@Persistent
    @Unowned
	private Buyer buyer;
	
	// GWT serializable Objects need a no=argument constructor
	public PostData() {}
	
	public PostData(String t, String d, double p, String a,
			         String url, Seller s, Buyer b){
		title = t;
		description = d;
		price = p;
		address = a;
		URL = url;
		seller = s;
		buyer = b;
	}

	// Getters and Setters
	public String getTitle() {
		return title;
	}
	public void setTitle(String t) {
		title = t;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescr(String descr) {
		description  = descr;
	}
	
	public double getPrice(){
		return price;
	}
	public void setPrice(double p) {
		price = p;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String a) {
		address = a;
	}
	
	public String getURL() {
		return URL;
	}
	public void setURL(String url) {
		URL = url;
	}
	
	public Seller getSeller() {
		return seller;
	}
	
	public String getSellerName() {
		if(seller==null) return "";
		else return seller.getName();
	}
	
	public void setSellerName(String name) {
		if(seller==null) seller = new Seller(name);
		else seller.setName(name);
	}
	
	public Buyer getBuyer() {
		return buyer;
	}
	
	public String getBuyerName() {
		if(buyer==null) return "";
		else return buyer.getName();
	}
	public void setBuyerName(String name) {
		if(buyer==null) buyer = new Buyer(name);
		else buyer.setName(name);
	}
	
	public String getID() {
		return id;
	}
	
	public String stringRep() {	
		return "Post title = "+ title +
				"\n description = "+description +
				"\n price = "+ price+
				"\n address = "+ address+
				"\n seller: = "+getSellerName() +
				"\n buyer: = "+getBuyerName() +
				"\n url = "+URL;
	}

}

