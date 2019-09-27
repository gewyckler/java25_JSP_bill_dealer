package services;

import database.EntityDao;
import model.Invoice;
import model.Product;
import model.TaxType;

import java.util.List;
import java.util.Optional;

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

    public Long getInvoiceIdFromProduct(Long productIdToRemove) {
        Optional<Product> optionalProduct = entityDao.getById(Product.class, productIdToRemove);
        return optionalProduct.get().getInvoice().getId();
    }

    public Optional<Product> getProductById(Long productId) {
        return entityDao.getById(Product.class, productId);
    }

    public void updateProduct(Product product) {
        entityDao.saveOrUpdate(product);
    }
}
