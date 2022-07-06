package tmall.bean;

/**
 * @author: bytedance
 * @Email: shenhao.leon@bytedance.com
 * @date: 2022-07-06 8:32 下午
 * @desc:
 */
public class ProductImage {
    private String type;
    private Product product;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
