package tmall.bean;

/**
 * @author: bytedance
 * @Email: shenhao.leon@bytedance.com
 * @date: 2022-07-06 6:06 下午
 * @desc:
 */
public class Property {
    private String name;
    private Category category;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
