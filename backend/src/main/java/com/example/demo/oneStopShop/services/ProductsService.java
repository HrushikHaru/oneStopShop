package com.example.demo.oneStopShop.services;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.codec.binary.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.oneStopShop.dtos.GetAllProductsDto;
import com.example.demo.oneStopShop.dtos.PostProductDetailsDto;
import com.example.demo.oneStopShop.entities.Products;
import com.example.demo.oneStopShop.entities.Sizes;
import com.example.demo.oneStopShop.entities.SubCategories;
import com.example.demo.oneStopShop.exceptions.ProductNotFoundException;
import com.example.demo.oneStopShop.repositories.ProductsRepository;
import com.example.demo.oneStopShop.repositories.SizesRepository;

@Service
public class ProductsService {
	
	private final ProductsRepository productsRepo;
	
	private final SizesRepository sizesRepo;
	
	private final JdbcTemplate jdbcTemp;
	
	public ProductsService(ProductsRepository productsRepo, SizesRepository sizesRepo, JdbcTemplate jdbcTemp) {
		this.productsRepo = productsRepo;
		this.sizesRepo = sizesRepo;
		this.jdbcTemp = jdbcTemp;
	}

	public String saveProductImg(MultipartFile productImg, String imageValue) {
		
		File f = new File(imageValue);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		try {
			
			FileOutputStream fos = new FileOutputStream(f+File.separator+productImg.getOriginalFilename(),true);
			
			DataOutputStream dos = new DataOutputStream(fos);
			dos.write(productImg.getBytes());
			dos.flush();
			dos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productImg.getOriginalFilename();
	}

	public void postProductDetails(PostProductDetailsDto detailsDto) {
		
		Products product = new Products();
		
		product.setBrand(detailsDto.getBrand());
		product.setColor(detailsDto.getColor());
		product.setDescription(detailsDto.getDescription());
		product.setDiscountedPrice(detailsDto.getDiscountedPrice());
		product.setMaterial(detailsDto.getMaterial());
		product.setOriginalPrice(detailsDto.getOriginalPrice());
		product.setProductImgName(detailsDto.getProductImgName());
		product.setTitle(detailsDto.getTitle());
		
		SubCategories subCategory = new SubCategories();
		subCategory.setSubCategoryId(detailsDto.getSubCategoryId());
		product.setSubCategory(subCategory);
		
		Sizes sizes = new Sizes();
		sizes.setSizeS(detailsDto.getSizeS());
		sizes.setSizeM(detailsDto.getSizeM());
		sizes.setSizeL(detailsDto.getSizeL());
		
		sizesRepo.save(sizes);
		
		product.setSizes(sizes);
		
		productsRepo.save(product);
		
	}

	public List<GetAllProductsDto> getAllProducts(String imgValue) {
		
		String query = "select a.product_id, a.brand, a.color, a.description, a.discounted_price, a.material, a.original_price, a.product_img_name, a.title\r\n"
				+ ", e.sizel, e.sizem, e.sizes, c.category_name, b.sub_category_name\r\n"
				+ "from products a left join sub_categories b ON(a.sub_category_id = b.sub_category_id) left join categories c \r\n"
				+ "ON(c.category_id = b.category_id) left join sizes e ON(a.size_id = e.size_id)";
		
		List<GetAllProductsDto> products = jdbcTemp.query(query, (resultSet, rowNum)->{
			
			GetAllProductsDto product = new GetAllProductsDto();
			
			product.setProductId(resultSet.getInt(1));
			product.setBrand(resultSet.getString(2));
			product.setColor(resultSet.getString(3));
			product.setDescription(resultSet.getString(4));
			product.setDiscountedPrice(resultSet.getInt(5));
			product.setMaterial(resultSet.getString(6));
			product.setOriginalPrice(resultSet.getInt(7));
			
			//For Image base64Coding
			product.setBase64EncodedProductImg(convertToBase64EncodedImage(imgValue, resultSet.getString(8)));
			
			product.setProductTitle(resultSet.getString(9));
			product.setSizeL(resultSet.getInt(10));
			product.setSizeM(resultSet.getInt(11));
			product.setSizeS(resultSet.getInt(12));
			product.setCategoryName(resultSet.getString(13));
			product.setSubCategoryName(resultSet.getString(14));
			
			return product;
		});
		
		return products;
		
	}
	
	public String convertToBase64EncodedImage(String imgValue, String string) {
		
		String path = imgValue +"/"+string;
		
		try {
			Path imgPath = Paths.get(path);
			
			byte [] img = Files.readAllBytes(imgPath);
			
			return Base64.encodeBase64String(img);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public GetAllProductsDto getParticularProduct(int id, String imageValue) {
		
		
		Optional<Products> productCheck = productsRepo.findById(id);
		
		if(productCheck.isEmpty()) throw new ProductNotFoundException("The requested Product was not found");
			
		String query = "select a.product_id, a.brand, a.color, a.description, a.discounted_price, a.original_price, a.title, b.sizel, b.sizem, b.sizes,\r\n"
				+ "c.sub_category_name, d.category_name, a.material, a.product_img_name\r\n"
				+ "from products a left join sizes b ON(a.size_id = b.size_id) \r\n"
				+ "left join sub_categories c ON(a.sub_category_id = c.sub_category_id)\r\n"
				+ "left join categories d ON(c.category_id = d.category_id) where a.product_id = ?";
		
		List<GetAllProductsDto> products = jdbcTemp.query(query, (ps)->ps.setInt(1, id), (resultSet,rowNum)->{
			GetAllProductsDto product = new GetAllProductsDto();
			
			product.setProductId(resultSet.getInt(1));
			product.setBrand(resultSet.getString(2));
			product.setColor(resultSet.getString(3));
			product.setDescription(resultSet.getString(4));
			product.setDiscountedPrice(resultSet.getInt(5));
			product.setOriginalPrice(resultSet.getInt(6));
			product.setProductTitle(resultSet.getString(7));
			product.setSizeL(resultSet.getInt(8));
			product.setSizeM(resultSet.getInt(9));
			product.setSizeS(resultSet.getInt(10));
			product.setCategoryName(resultSet.getString(12));
			product.setSubCategoryName(resultSet.getString(11));
			product.setMaterial(resultSet.getString(13));
			product.setBase64EncodedProductImg(convertToBase64EncodedImage(imageValue, resultSet.getString(14)));
			
			return product;
		});
		
		return products.get(0);
		
	}

	
	
}
