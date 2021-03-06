/*
 * Java bean class for entity table RM_PRODUCT 
 * Created on 3 Oct 2013 ( Time 12:18:07 )
 * Generated by Telosys Tools Generator ( version 2.0.0 )
 */

package com.pruvn.rms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pruvn.rms.domain.Product;
import com.pruvn.rms.model.ProductForm;
import com.pruvn.rms.service.ProductService;
import com.pruvn.rms.utils.CommonUtils;
import com.pruvn.rms.utils.Constant.ParameterApplication;

/**
 * Controller implementation for table "RM_PRODUCT"
 * 
 * @author Telosys Tools Generator
 * 
 */

@Controller
public class ProductController {
	private static final Logger logger = Logger
			.getLogger(ProductController.class);

	private ProductService productService;
	
	//private BranchService branchService;

	/**
	 * @return the RmProductService
	 */
	public ProductService getProductService() {
		return productService;
	}

	/**
	 * @param ProductService
	 *            the RmProductService to set
	 */
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/*public BranchService getBranchService() {
		return branchService;
	}

	public void setBranchService(BranchService branchService) {
		this.branchService = branchService;
	}*/

	@RequestMapping(value = "/admin/productlist", method = RequestMethod.GET)
	public String productlist(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach productController#userlist...");
		List<Product> list = productService.findAll();
		model.addAttribute("productList", list);
		return "productlist";
	}

	@RequestMapping(value = "/admin/productmod", method = RequestMethod.GET)
	public String createproduct(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach productController#createproduct...");
		ProductForm form = new ProductForm();
		String productid = request.getParameter("id");
		if (StringUtils.isNotEmpty(productid)
				&& CommonUtils.isNumeric(productid.trim())) {
			// Get user and popular this user to form
			Product product = productService.getById(new Integer(
					productid));
			if (product != null) {
				form.setId(product.getId());

				form.setCode(product.getCode());
				form.setName(product.getName());
				form.setDescription(product.getDescription());
				form.setBranchId(product.getBranchId());
				form.setIsActived(product.getIsActived() != null && product.getIsActived() == ParameterApplication.ACTIVE.getStatus());
			}
		}
		
		form.setBranchs(productService.getAllActiveBranchs());
		model.addAttribute(form);
		return "productmod";
	}

	@RequestMapping(value = "/admin/productmod", method = RequestMethod.POST)
	public String createproductSubmit(@Valid ProductForm form,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.debug("reach productController#createproductSubmit...");
		Product product = null;

		if (StringUtils.isEmpty(form.getCode())) {
			result.reject("product.emptyrequirefields",
					"Please input required field !");
			model.addAttribute(form);
			return "productmod";
		}

		if (form.getId() != null && form.getId() > 0) {
			product = productService.getById(form.getId());
		} else {
			product = productService.findByCode(form.getCode());

			if (product != null) {
				result.reject("product.notexisted",
						"product " + form.getCode() + " existed !");
				form.setBranchs(productService.getAllActiveBranchs());
				model.addAttribute(form);
				return "productmod";
			}
		}

		if (product == null) {
			product = new Product();
		}
		product.setId(form.getId());

		product.setCode(form.getCode());
		product.setName(form.getName());
		product.setDescription(form.getDescription());
		product.setIsActived(form.getIsActived() ? ParameterApplication.ACTIVE.getStatus() : ParameterApplication.NOACTIVE.getStatus());
		product.setBranchId(form.getBranchId() == 0 ? null : form.getBranchId());
		productService.saveOrUpdate(product);

		return "redirect:/admin/productlist.html";
	}

	@RequestMapping(value = "/product/del", method = RequestMethod.GET)
	public String productdel(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		logger.debug("reach productController#productdel...");
		String productid = request.getParameter("id");
		if (StringUtils.isNotEmpty(productid)
				&& CommonUtils.isNumeric(productid.trim())) {
			// Get product and popular this user to form
			Product product = productService.getById(new Integer(
					productid));
			if (product != null) {
				product.setIsActived(ParameterApplication.NOACTIVE.getStatus());
				productService.saveOrUpdate(product);
			}
		}
		return "redirect:/admin/productlist.html";
	}
}