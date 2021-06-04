package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product, Integer> { // crud operasyonları için

	// iki interface arasında extends olur
	// c# daki entityframeworkrepository = jparepository

	Product getByProductName(String productName);
	//select* from products where product_name=abc
	
	Product getByProductNameAndCategory_CategoryId(String productName,int categoryId); // And operatoru olan where koşulu yazıyor
	
	List<Product> getByProductNameOrCategory_CategoryId(String productName,int categoryId);
	
	List<Product> getByCategoryIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	//select*from products where product_name=birsey and categoryId=birsey
	//veritabanı tablosunu unut alltakiini yazarken
	//: parametre demek
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName,int categoryId);
	
	/////////////////
	
	//join atmamız lazım
	//select p.productId, p.productNamefrom,c.catogoryName Category c inner join Product p 
	// on c.CategoryId = p.categoryId
	
	//jpql burda yazılcak  .net linq
	
	//select burda yıldız olmadığı için başladık
	//içindeki isimlendirmeler entityde yazılan sekilde db de degil
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto(p.id, p.productName, c.categoryName) From Category c Inner Join c.products p ")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();



}
//bunlar hazır isimlndirme kuralına uymak önemli her seyi jpa repositroy hallediyor