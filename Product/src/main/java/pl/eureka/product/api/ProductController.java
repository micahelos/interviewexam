package pl.eureka.product.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import pl.eureka.product.data.Product;

@RestController
@RequestMapping(path="/", produces="application/json")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService=productService;
	}

	@GetMapping("GetProducts/{id}")
	public Product get(@PathVariable(name="id") Long id) {
		try {
			return productService.getProductById(id);
		} catch(RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found", e);
		}
	}
	
	@PostMapping("CreateProduct")
	@ResponseStatus(HttpStatus.CREATED)
	public Product post(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@GetMapping("GetProducts")
	public List<Product> get() {
		try {
			List<Product> list = productService.getAll();
			return list;
		} catch(RuntimeException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found", e);
		}
	}
	
}
