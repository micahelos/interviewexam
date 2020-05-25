package pl.eureka.product.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.eureka.product.data.Product;
import pl.eureka.product.data.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public ProductService(ProductRepository repo) {
		this.repo=repo;
	}
	
	public Product createProduct(Product product) {
		 return Optional.ofNullable(repo.save(product)).orElseThrow(RuntimeException::new);
	}
	
	public Product getProductById(Long id) {
			return repo.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public List<Product> getAll() {
		List<Product> p = new ArrayList<Product>();
		repo.findAll().forEach(e -> p.add(e));
		return p;
}
}
