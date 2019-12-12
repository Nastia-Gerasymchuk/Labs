package main.model;

import main.enums.CategoryEmployee;

import javax.validation.constraints.Size;
import java.util.Objects;

public class Category {
    protected int idCategory;
    protected CategoryEmployee name;
    protected double persentMoney;
    protected static int count;

    {
        idCategory =count++;}

    public int getIdCategory() {
        return idCategory;
    }

    public CategoryEmployee getName() {
        return name;
    }

    @Size(max=1,message = "persent money needs to be from 0 to 1")
    public double getPersentMoney() {
        return persentMoney;
    }
    private Category(CategoryBuilder categoryBuilder){
        this.name =categoryBuilder.nameCategory;
        this.persentMoney =categoryBuilder.persentMoney;
    }

    @Override
    public String toString() {
        return "Category: " +
                "name=" + name +
                ", persentMoney=" + persentMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return  name == category.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, name, persentMoney);
    }

    public static class CategoryBuilder{
        private CategoryEmployee nameCategory;
        private double persentMoney;
        private int idCategory;


        public CategoryBuilder setnameCategory(CategoryEmployee nameCategory) {
            this.nameCategory = nameCategory;
            return this;
        }

        public CategoryBuilder setpersentMoney(double persentMoney) {
            this.persentMoney = persentMoney;
            return this;
        }

        public CategoryBuilder setIdCategory(int idCategory) {
            this.idCategory = idCategory;
            return this;
        }

        public Category build(){
            return new Category(this);
        }
    }
}
