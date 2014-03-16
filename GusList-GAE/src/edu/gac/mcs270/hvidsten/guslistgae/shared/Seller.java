/*
 * Seller class. Represents a person who sells a posted ad item
 */
package edu.gac.mcs270.hvidsten.guslistgae.shared;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Seller  implements Serializable{
	private static final long serialVersionUID = 1L;
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;
    
    @Persistent
	private String name="no_val";
	
	public Seller() {}
	
	public Seller(String string) {
		name = string;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String n) {
		name = n;
	}
	
	public Long getID(){
		return id;
	}
}
