package com.mysite.sbb;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Component;

@Component		// Bean 에 객체 등록 : commonUtil (객체명)
public class CommonUtil {
	
	// @Commponent , @Controller, @Service, @Repository   : Bean 에 객체 등록 

	public String markdown ( String markdown) {
		Parser parser = Parser.builder().build(); 
		Node document = parser.parse(markdown); 
		HtmlRenderer render = HtmlRenderer.builder().build(); 		
		return render.render(document); 
	}
}