package org.producer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.m4.domain.model.Product;

public class ProductController extends AbstractController {
	public Product getProduct() {
		Product p = new Product();
		p.setProdName("prod name");
		p.setProdId("p1");
		p.setColor("Green");
		return p;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest paramHttpServletRequest,
			HttpServletResponse paramHttpServletResponse) throws Exception {
		ModelAndView model = new ModelAndView("product");

		String prodId = paramHttpServletRequest.getParameter("id");
		if (StringUtils.isNotEmpty(prodId)) {
			model.addObject("prod", getProduct(prodId));
		} else {
			model.addObject("prods", getProducts());
		}

		return model;
	}

	public Product getProduct(String prodId) {
		Product p = new Product();
		p.setProdName("prod name");
		p.setProdId(prodId);
		p.setColor("Green");
		return p;
	}

	public List<Product> getProducts() {

		List<Product> ps = new ArrayList<Product>();

		Product p = new Product();
		p.setProdName("prod name");
		p.setProdId("p");
		p.setColor("Green");

		ps.add(p);

		Product p1 = new Product();
		p1.setProdName("prod name");
		p1.setProdId("p1");
		p1.setColor("Green");

		ps.add(p1);

		return ps;
	}
}
