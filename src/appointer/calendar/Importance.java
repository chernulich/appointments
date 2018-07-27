package appointer.calendar;

import biweekly.property.ICalProperty;

public class Importance extends ICalProperty {
	  private Integer number;
	  private String text;

	  public Importance(Integer value) {
	    this.number = value;
	  }

	  public Importance(String text) {
	    this.text = text;
	  }

	  public Integer getNumber() {
	    return number;
	  }

	  public void setNumber(Integer number) {
	    this.number = number;
	    this.text = null;
	  }

	  public String getText() {
	    return text;
	  }

	  public void setText(String text) {
	    this.number = null;
	    this.text = text;
	  }

	}