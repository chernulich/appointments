package appointer.calendar;

import java.util.List;

import biweekly.ICalDataType;
import biweekly.io.CannotParseException;
import biweekly.io.ParseContext;
import biweekly.io.SkipMeException;
import biweekly.io.WriteContext;
import biweekly.io.json.JCalValue;
import biweekly.io.scribe.property.ICalPropertyScribe;
import biweekly.io.xml.XCalElement;
import biweekly.parameter.ICalParameters;

public class ImportanceScribe extends ICalPropertyScribe<Importance> {
	  public ImportanceScribe() {
	    super(Importance.class, "X-IMPORTANCE", ICalDataType.INTEGER);
	  }

	  //optional
	  //determines the iCal data type of the property's value
	  protected ICalDataType _dataType(Importance property) {
	    if (property.getText() != null) {
	      return ICalDataType.TEXT;
	    }
	    return ICalDataType.INTEGER;
	  }

	  //optional
	  //tweaks the parameters before the property is written
	  protected void _prepareParameters(Importance property, ICalParameters copy) {
	    Integer value = property.getNumber();
	    if (value != null && value >= 10) {
	      copy.put("X-MESSAGE", "very important!!");
	    }
	  }

	  //required
	  //writes the property to a plain-text iCal
	  protected String _writeText(Importance property) {
	    return write(property);
	  }

	  //required
	  //parses the property from a plain-text iCal
	  protected Importance _parseText(String value, ICalDataType dataType, ICalParameters parameters, List<String> warnings) {
	    value = unescape(value);
	    return parse(value, dataType);
	  }

	  private String unescape(String value) {
		  StringBuilder sb = new StringBuilder(value.length());

		    for (int i = 0; i < value.length(); i++) {
		        char ch = value.charAt(i);
		        if (ch == '\\') {
		            char nextChar = (i == value.length() - 1) ? '\\' : value
		                    .charAt(i + 1);
		            // Octal escape?
		            if (nextChar >= '0' && nextChar <= '7') {
		                String code = "" + nextChar;
		                i++;
		                if ((i < value.length() - 1) && value.charAt(i + 1) >= '0'
		                        && value.charAt(i + 1) <= '7') {
		                    code += value.charAt(i + 1);
		                    i++;
		                    if ((i < value.length() - 1) && value.charAt(i + 1) >= '0'
		                            && value.charAt(i + 1) <= '7') {
		                        code += value.charAt(i + 1);
		                        i++;
		                    }
		                }
		                sb.append((char) Integer.parseInt(code, 8));
		                continue;
		            }
		            switch (nextChar) {
		            case '\\':
		                ch = '\\';
		                break;
		            case 'b':
		                ch = '\b';
		                break;
		            case 'f':
		                ch = '\f';
		                break;
		            case 'n':
		                ch = '\n';
		                break;
		            case 'r':
		                ch = '\r';
		                break;
		            case 't':
		                ch = '\t';
		                break;
		            case '\"':
		                ch = '\"';
		                break;
		            case '\'':
		                ch = '\'';
		                break;
		            // Hex Unicode: u????
		            case 'u':
		                if (i >= value.length() - 5) {
		                    ch = 'u';
		                    break;
		                }
		                int code = Integer.parseInt(
		                        "" + value.charAt(i + 2) + value.charAt(i + 3)
		                                + value.charAt(i + 4) + value.charAt(i + 5), 16);
		                sb.append(Character.toChars(code));
		                i += 5;
		                continue;
		            }
		            i++;
		        }
		        sb.append(ch);
		    }
		    return sb.toString();
	
	}

	//optional
	  //writes the property to an XML document (xCal)
	  protected void _writeXml(Importance property, XCalElement element) {
	    Integer value = property.getNumber();
	    if (value != null) {
	      if (value > 100) {
	        throw new SkipMeException("Way too high.");
	      }
	      element.append(ICalDataType.INTEGER, value.toString()); //writes: <x-importance><integer>1</integer></x-importance>
	      return;
	    }

	    String text = property.getText();
	    if (text != null) {
	      element.append(ICalDataType.TEXT, text); //writes: <x-importance><text>high</text></x-importance>
	    }

	  }

	  //optional
	  //reads the property from an XML document (xCal)
	  protected Importance _parseXml(XCalElement element, ICalParameters parameters, List<String> warnings) {
	    String text = element.first(ICalDataType.TEXT);
	    if (text != null) {
	      return new Importance(text);
	    }

	    String number = element.first(ICalDataType.INTEGER);
	    if (number != null) {
	      try {
	        return new Importance(Integer.valueOf(number));
	      } catch (NumberFormatException e) {
	        throw new CannotParseException("Numeric value expected: " + number);
	      }
	    }

	    return new Importance(0);
	  }

	  //optional
	  //writes the property to a JSON document (jCal)
	  protected JCalValue _writeJson(Importance property) {
	    return JCalValue.single(write(property));
	  }

	  //optional
	  //reads the property from a JSON document (jCal)
	  protected Importance _parseJson(JCalValue value, ICalDataType dataType, ICalParameters parameters, List<String> warnings) {
	    String valueStr = value.asSingle();
	    return parse(valueStr, dataType);
	  }

	  private Importance parse(String value, ICalDataType dataType) {
	    if (dataType == ICalDataType.TEXT) {
	      return new Importance(value);
	    }

	    try {
	      return new Importance(Integer.valueOf(value));
	    } catch (NumberFormatException e) {
	      throw new CannotParseException("Numeric value expected: " + value);
	    }
	  }

	  private String write(Importance property) {
	    String text = property.getText();
	    if (text != null) {
	      return text;
	    }

	    Integer number = property.getNumber();
	    if (number != null) {
	      return number.toString();
	    }

	    return "";
	  }

	@Override
	protected Importance _parseText(String arg0, ICalDataType arg1, ICalParameters arg2, ParseContext arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String _writeText(Importance arg0, WriteContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	}