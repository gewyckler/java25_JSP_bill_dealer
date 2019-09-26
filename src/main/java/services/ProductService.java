package services;

import database.EntityDao;
import model.Invoice;
import model.Product;
import model.TaxType;

import java.util.List;

public class ProductService {
    private EntityDao entityDao = new EntityDao();

    public void addProduct(Invoice invoice, String name, double price, TaxType taxType, int stock) {
        Product product = new Product(name, price, taxType, stock);
        product.setInvoice(invoice);
        entityDao.saveOrUpdate(product);
    }

    public void removeProductById(Long productIdToDelete) {
        entityDao.delete(Product.class, productIdToDelete);
    }

    public List<Product> findAll() {
        return entityDao.getAll(Product.class);
    }
}
