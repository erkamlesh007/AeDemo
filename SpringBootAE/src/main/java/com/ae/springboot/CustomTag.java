
package com.ae.springboot;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class CustomTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		final JspWriter out = getJspContext().getOut();
		out.println( "Arithmetic Expression Custom Tag!" );
	}
}
